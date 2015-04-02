package org.dyndns.warenix.util.json;

import org.json.JSONException;
import org.json.JSONObject;

public class SafeJson {
	public static int getInt(JSONObject json, String name, int defaultValue) {
		try {
			return json.getInt(name);
		} catch (JSONException e) {
			return defaultValue;
		}
	}

	public static String getString(JSONObject json, String name,
			String defaultValue) {
		try {
			return json.getString(name);
		} catch (JSONException e) {
			return defaultValue;
		}
	}
}
