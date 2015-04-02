package org.dyndns.warenix.pagespeed;

import org.dyndns.warenix.pagespeed.ruleResultList.urlBlock.UrlBlockListAdapter;
import org.dyndns.warenix.widget.WebImage;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class RuleResultActivity extends Activity {

	public static final String BUNDLE_RULE_RESULT = "rule_result";

	ExpandableListView urlBlockListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rule_result_activity);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			RuleResult ruleResult = (RuleResult) extras.get(BUNDLE_RULE_RESULT);
			Log.d("pagespeed", ruleResult.toString());

			// create header
			TextView localizedRuleName = (TextView) findViewById(R.id.localizedRuleName);
			localizedRuleName.setText(ruleResult.localizedRuleName);
			TextView ruleScore = (TextView) findViewById(R.id.ruleScore);
			ruleScore.setText("" + ruleResult.ruleScore + "/"
					+ ruleResult.ruleImpact);
			WebImage barImage = (WebImage) findViewById(R.id.barImage);
			barImage.startLoading(ruleResult.toScoreChartUrl());

			TextView sectionHeader = (TextView) findViewById(R.id.sectionHeader);
			sectionHeader.setText(String.format("%s (%d)", getBaseContext()
					.getResources().getString(R.string.suggestion_section),
					ruleResult.urlBlockList.size()));

			// cretae urlblock
			urlBlockListView = (ExpandableListView) findViewById(R.id.urlBlockList);
			urlBlockListView.setAdapter(new UrlBlockListAdapter(
					RuleResultActivity.this, ruleResult));

		}

	}
}
