package org.dyndns.warenix.pagespeed;

import java.util.Locale;

import org.dyndns.warenix.pagespeed.ruleResultList.RuleResultListController;
import org.dyndns.warenix.pagespeed.ruleResultList.RuleResultListItem;
import org.dyndns.warenix.widget.WebImage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class PagespeedActivity extends Activity {

	ProgressDialog progressDiallog;
	RuleResultListController controller;
	TextView urlText;
	ViewFlipper logoViewFlipper;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		controller = new RuleResultListController(PagespeedActivity.this,
				R.id.ruleResultList);

		logoViewFlipper = (ViewFlipper) findViewById(R.id.logoViewFlipper);
		logoViewFlipper.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				logoViewFlipper.showNext();
			}
		});

		urlText = (TextView) findViewById(R.id.url);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String url = extras.getString(Intent.EXTRA_TEXT);
			urlText.setText(url);

			runPageSpeed(url);
		} else {
			// when no url is passed, show url input box
			logoViewFlipper.showNext();
		}

		Button runButton = (Button) findViewById(R.id.run);
		runButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String inputtedUrl = urlText.getText().toString();
				if (!inputtedUrl.startsWith("http://")) {
					inputtedUrl = "http://" + inputtedUrl;
				}
				runPageSpeed(inputtedUrl);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case R.id.menu_preference:
			Intent preferenceIntent = new Intent(this,
					PagespeedPreferenceActivity.class);
			startActivity(preferenceIntent);
			break;
		}

		return true;
	}

	// void getCurrentlocale() {
	// Configuration config = getApplication().getResources()
	// .getConfiguration();
	//
	// String currentLocale = config.locale.getLanguage().substring(0, 2)
	// .toLowerCase();
	// Log.d("warenix", "current locale: " + currentLocale);
	// }

	// void switchLocale() {
	// SharedPreferences settings = getSharedPreferences("pagespeed", 0);
	//
	// String locale = settings.getString("locale", Locale.CANADA.toString());
	// PagespeedPreferenceActivity.updateUILocale(getBaseContext(), locale);
	// }

	void runPageSpeed(String url) {
		url = url.replace(" ", "");
		progressDiallog = ProgressDialog.show(this, "", getResources()
				.getString(R.string.running_page_speed), true);
		progressDiallog.setCancelable(true);

		SharedPreferences settings = getSharedPreferences("pagespeed", 0);

		String strategy = settings.getString("strategy", "mobile");
		String locale = settings.getString("locale", Locale.CANADA.toString());

		new PageSpeedAsyncTask(url, locale, "", strategy).execute();
	}

	class PageSpeedAsyncTask extends AsyncTask<Void, Void, Exception> {
		String url;
		String locale;
		String rule;
		String strategy;

		PageSpeedResult pageSpeedResult;

		public PageSpeedAsyncTask(String url, String locale, String rule,
				String strategy) {
			this.url = url;
			this.locale = locale;
			this.rule = rule;
			this.strategy = strategy;
		}

		@Override
		protected Exception doInBackground(Void... args) {
			try {
				controller.clearRuleResult();

				pageSpeedResult = PageSpeedMaster.runPageSpeed(url, locale,
						rule, strategy);
				pageSpeedResult.sortRuleResort();
			} catch (Exception e) {
				e.printStackTrace();
				return e;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Exception v) {
			progressDiallog.dismiss();
			if (v == null) {

				TextView title = (TextView) findViewById(R.id.title);
				title.setText("" + pageSpeedResult.pageInfo.title);

				TextView score = (TextView) findViewById(R.id.score);
				score.setText("" + pageSpeedResult.pageInfo.score);

				WebImage scoreImage = (WebImage) findViewById(R.id.scoreImage);
				scoreImage.startLoading(pageSpeedResult.pageInfo
						.toScoreChartUrl());

				WebImage statImage = (WebImage) findViewById(R.id.statImage);
				statImage.startLoading(pageSpeedResult.pageStats
						.toResourceSizeChartUrl());

				for (int i = 0; i < pageSpeedResult.ruleResultList.size(); ++i) {
					RuleResult ruleResult = pageSpeedResult.ruleResultList
							.get(i);
					controller
							.addRuleResult(new RuleResultListItem(ruleResult));
				}
				controller.notifyDatSetInvalidated();

			}
		}
	}
}
