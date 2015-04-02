package org.dyndns.warenix.pagespeed;

import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

public class PagespeedPreferenceActivity extends PreferenceActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);

		// fetch stored preference
		SharedPreferences settings = getSharedPreferences("pagespeed", 0);

		// setup locale preference
		String locale = settings.getString("locale", Locale.ENGLISH.toString());

		final ListPreference localePreference = (ListPreference) findPreference("locale");
		CharSequence[] localeNameArray = localePreference.getEntries();
		int index = localePreference.findIndexOfValue(locale);
		localePreference.setSummary(localeNameArray[index].toString());
		localePreference.setValue(locale);

		localePreference
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						String locale = (String) newValue;
						CharSequence[] localeNameArray = ((ListPreference) preference)
								.getEntries();
						int index = ((ListPreference) preference)
								.findIndexOfValue(locale);
						localePreference.setSummary(localeNameArray[index]
								.toString());
						localePreference.setValue(locale);

						SharedPreferences settings = getSharedPreferences(
								"pagespeed", 0);
						SharedPreferences.Editor editor = settings.edit();
						editor.putString("locale", locale);
						editor.commit();

						return true;
					}

				});

		// setup locale preference
		String strategy = settings.getString("strategy", "desktop");

		final ListPreference strategyPreference = (ListPreference) findPreference("strategy");
		CharSequence[] strategyNameArray = strategyPreference.getEntries();
		index = strategyPreference.findIndexOfValue(strategy);
		strategyPreference.setSummary(strategyNameArray[index].toString());
		strategyPreference.setValue(strategy);
		strategyPreference
				.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

					@Override
					public boolean onPreferenceChange(Preference preference,
							Object newValue) {
						String strategy = (String) newValue;

						CharSequence[] localeNameArray = ((ListPreference) preference)
								.getEntries();
						int index = ((ListPreference) preference)
								.findIndexOfValue(strategy);
						strategyPreference.setSummary(localeNameArray[index]
								.toString());
						strategyPreference.setValue(strategy);

						SharedPreferences settings = getSharedPreferences(
								"pagespeed", 0);
						SharedPreferences.Editor editor = settings.edit();
						editor.putString("strategy", strategy);
						editor.commit();

						return true;
					}

				});

	}

	public static void updateUILocale(Context context, String localeString) {
		Locale locale = new Locale(localeString);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		// context.getResources().updateConfiguration(config,
		// context.getResources().getDisplayMetrics());
		context.getResources().updateConfiguration(config, null);
	}
}
