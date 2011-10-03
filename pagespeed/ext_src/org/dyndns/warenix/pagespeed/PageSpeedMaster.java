package org.dyndns.warenix.pagespeed;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

import org.dyndns.warenix.util.WebContent;
import org.dyndns.warenix.util.json.SafeJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PageSpeedMaster {
	static String LOG_TAG = "pagespeedmaster";

	static String GOOGLE_API_KEY = "";

	static String GOOGLE_PAGE_SPEED_ONLINE_URL_FORMAT = "https://www.googleapis.com/pagespeedonline/v1/runPagespeed?url=%s&locale=%s&strategy=%s&pp=1&key=%s&prettyprint=false&userIp=%s";
	static String GOOGLE_CHART_BASE_URL = "http://chart.apis.google.com/chart?";

	public static String STRATEGY_DESKTOP = "desktop";
	public static String STRATEGY_MOBILE = "mobile";

	/**
	 * 
	 * @param url
	 *            full identified with protocol name, e.g http://google.com
	 * @param locale
	 * @param rule
	 *            emtpy string to run all rules
	 * @param strategy
	 * @throws Exception
	 */
	public static PageSpeedResult runPageSpeed(String url, String locale,
			String rule, String strategy) throws Exception {

		// return this result object
		PageSpeedResult pageSpeedResult = new PageSpeedResult();

		String pageSpeedUrl = String.format(
				GOOGLE_PAGE_SPEED_ONLINE_URL_FORMAT, url, locale, strategy,
				GOOGLE_API_KEY, getLocalIpAddress());
		Log.d(LOG_TAG, "pageSpeedUrl:" + pageSpeedUrl);
		String responseString = WebContent.getContent(pageSpeedUrl);
		// String responseString =
		// "{\"kind\":\"pagespeedonline#result\",\"id\":\"http://www.google.com.hk/\",\"responseCode\":200,\"title\":\"Google\",\"score\":97,\"pageStats\":{\"numberResources\":8,\"numberHosts\":3,\"totalRequestBytes\":\"660\",\"numberStaticResources\":4,\"htmlResponseBytes\":\"17286\",\"imageResponseBytes\":\"43585\",\"javascriptResponseBytes\":\"208999\",\"otherResponseBytes\":\"1171\",\"numberJsResources\":2},\"formattedResults\":{\"locale\":\"en_us\",\"ruleResults\":{\"AvoidBadRequests\":{\"localizedRuleName\":\"Avoid bad requests\",\"ruleScore\":100,\"ruleImpact\":0.0},\"AvoidCssImport\":{\"localizedRuleName\":\"Avoid CSS @import\",\"ruleScore\":100,\"ruleImpact\":0.0},\"DeferParsingJavaScript\":{\"localizedRuleName\":\"Defer parsing of JavaScript\",\"ruleScore\":100,\"ruleImpact\":0.0},\"InlineSmallCss\":{\"localizedRuleName\":\"Inline Small CSS\",\"ruleScore\":100,\"ruleImpact\":0.0},\"InlineSmallJavaScript\":{\"localizedRuleName\":\"Inline Small JavaScript\",\"ruleScore\":100,\"ruleImpact\":0.0},\"LeverageBrowserCaching\":{\"localizedRuleName\":\"Leverage browser caching\",\"ruleScore\":100,\"ruleImpact\":0.0},\"MakeLandingPageRedirectsCacheable\":{\"localizedRuleName\":\"Make landing page redirects cacheable\",\"ruleScore\":37,\"ruleImpact\":1.0,\"urlBlocks\":[{\"header\":{\"format\":\"The following landing page redirects are not cacheable. Make them cacheable to speed up page load times for repeat visitors to your site.\"},\"urls\":[{\"result\":{\"format\":\"$1 is an uncacheable redirect to $2\",\"args\":[{\"type\":\"URL\",\"value\":\"http://www.google.com/\"},{\"type\":\"URL\",\"value\":\"http://www.google.com.hk/\"}]}}]}]},\"MinifyCss\":{\"localizedRuleName\":\"Minify CSS\",\"ruleScore\":100,\"ruleImpact\":0.0},\"MinifyHTML\":{\"localizedRuleName\":\"Minify HTML\",\"ruleScore\":100,\"ruleImpact\":0.0},\"MinifyJavaScript\":{\"localizedRuleName\":\"Minify JavaScript\",\"ruleScore\":99,\"ruleImpact\":0.0316,\"urlBlocks\":[{\"header\":{\"format\":\"Minifying the following JavaScript resources could reduce their size by $1 ($2% reduction).\",\"args\":[{\"type\":\"BYTES\",\"value\":\"306B\"},{\"type\":\"INT_LITERAL\",\"value\":\"0\"}]},\"urls\":[{\"result\":{\"format\":\"Minifying $1 could save $2 ($3% reduction).\",\"args\":[{\"type\":\"URL\",\"value\":\"http://www.google.com.hk/extern_js/f/CgV6aC1UVxICaGsrMEU4ACwrMFo4ACwrMA44ACwrMBc4ACwrMDw4ACwrMFE4ACwrMFk4ACwrMAo4AEACmgICY2MsKzAWOAAsKzAZOAAsKzAlOAAsKzAqOAAsKzArOAAsKzA1OAAsKzBAOAAsKzBBOAAsKzBNOAAsKzBOOAAsKzBTOACaAgZzZWFyY2gsKzBUOAAsKzBfOAAsKzBpOAAsKzAdOAAsKzAYOAAsKzAmOAAsgAJJkAJA/4EfOD4SkK8Y.js\"},{\"type\":\"BYTES\",\"value\":\"306B\"},{\"type\":\"INT_LITERAL\",\"value\":\"0\"}]}}]}]},\"MinimizeRedirects\":{\"localizedRuleName\":\"Minimize redirects\",\"ruleScore\":100,\"ruleImpact\":0.0},\"MinimizeRequestSize\":{\"localizedRuleName\":\"Minimize request size\",\"ruleScore\":100,\"ruleImpact\":0.0},\"OptimizeImages\":{\"localizedRuleName\":\"Optimize images\",\"ruleScore\":100,\"ruleImpact\":0.0},\"OptimizeTheOrderOfStylesAndScripts\":{\"localizedRuleName\":\"Optimize the order of styles and scripts\",\"ruleScore\":100,\"ruleImpact\":0.0},\"PreferAsyncResources\":{\"localizedRuleName\":\"Prefer asynchronous resources\",\"ruleScore\":100,\"ruleImpact\":0.0},\"PutCssInTheDocumentHead\":{\"localizedRuleName\":\"Put CSS in the document head\",\"ruleScore\":100,\"ruleImpact\":0.0},\"RemoveQueryStringsFromStaticResources\":{\"localizedRuleName\":\"Remove query strings from static resources\",\"ruleScore\":100,\"ruleImpact\":0.0},\"ServeResourcesFromAConsistentUrl\":{\"localizedRuleName\":\"Serve resources from a consistent URL\",\"ruleScore\":100,\"ruleImpact\":0.0},\"ServeScaledImages\":{\"localizedRuleName\":\"Serve scaled images\",\"ruleScore\":100,\"ruleImpact\":0.0},\"SpecifyACacheValidator\":{\"localizedRuleName\":\"Specify a cache validator\",\"ruleScore\":100,\"ruleImpact\":0.0},\"SpecifyAVaryAcceptEncodingHeader\":{\"localizedRuleName\":\"Specify a Vary: Accept-Encoding header\",\"ruleScore\":100,\"ruleImpact\":0.0},\"SpecifyCharsetEarly\":{\"localizedRuleName\":\"Specify a character set\",\"ruleScore\":100,\"ruleImpact\":0.0},\"SpecifyImageDimensions\":{\"localizedRuleName\":\"Specify image dimensions\",\"ruleScore\":100,\"ruleImpact\":0.0},\"SpriteImages\":{\"localizedRuleName\":\"Combine images into CSS sprites\",\"ruleScore\":100,\"ruleImpact\":0.0}}},\"version\":{\"major\":1,\"minor\":11}}";

		// start parsing
		JSONObject responseJson = new JSONObject(responseString);

		// basic page info
		PageInfo pageInfo = new PageInfo();

		pageInfo.title = responseJson.getString("title");
		pageInfo.score = responseJson.getInt("score");
		// Log.d(LOG_TAG, pageInfo.toScoreChartUrl());

		pageSpeedResult.pageInfo = pageInfo;

		// parse pageStat
		JSONObject pageStatsJson = responseJson.getJSONObject("pageStats");
		PageStats pageStats = new PageStats();
		pageStats.numberResources = SafeJson.getInt(pageStatsJson,
				"numberResources", 0);
		pageStats.numberHosts = SafeJson
				.getInt(pageStatsJson, "numberHosts", 0);
		pageStats.totalRequestBytes = SafeJson.getInt(pageStatsJson,
				"totalRequestBytes", 0);
		pageStats.numberStaticResources = SafeJson.getInt(pageStatsJson,
				"numberStaticResources", 0);
		pageStats.htmlResponseBytes = SafeJson.getInt(pageStatsJson,
				"htmlResponseBytes", 0);
		pageStats.cssResponseBytes = SafeJson.getInt(pageStatsJson,
				"cssResponseBytes", 0);
		pageStats.imageResponseBytes = SafeJson.getInt(pageStatsJson,
				"imageResponseBytes", 0);
		pageStats.javascriptResponseBytes = SafeJson.getInt(pageStatsJson,
				"javascriptResponseBytes", 0);
		pageStats.otherResponseBytes = SafeJson.getInt(pageStatsJson,
				"otherResponseBytes", 0);
		pageStats.numberJsResources = SafeJson.getInt(pageStatsJson,
				"numberJsResources", 0);
		// Log.d(LOG_TAG, pageStats.toResourceSizeChartUrl());
		pageSpeedResult.pageStats = pageStats;

		// parse rule result
		JSONObject formattedResultsJson = responseJson
				.getJSONObject("formattedResults");
		// Log.d(LOG_TAG, formattedResultsJson.getString("locale"));
		JSONObject ruleResults = formattedResultsJson
				.getJSONObject("ruleResults");

		for (int i = 0; i < RuleResult.RULES.length; ++i) {

			// FIXME

			JSONObject ruleResultJson = ruleResults
					.getJSONObject(RuleResult.RULES[i]);
			RuleResult ruleResult = new RuleResult();
			ruleResult.localizedRuleName = ruleResultJson
					.getString("localizedRuleName");
			ruleResult.ruleScore = ruleResultJson.getInt("ruleScore");
			ruleResult.ruleImpact = ruleResultJson.getInt("ruleImpact");
			// Log.d(LOG_TAG, String.format("%s: score:%d impact:%d",
			// ruleResult.localizedRuleName, ruleResult.ruleScore,
			// ruleResult.ruleImpact));

			try {
				JSONArray urlBlocksJsonArray = ruleResultJson
						.getJSONArray("urlBlocks");
				for (int k = 0; k < urlBlocksJsonArray.length(); ++k) {
					JSONObject urlBlocksJson = urlBlocksJsonArray
							.getJSONObject(k);
					JSONObject headerJson = urlBlocksJson
							.getJSONObject("header");
					// Log.d(LOG_TAG, "Suggestion:" +
					// parseFormatArgs(headerJson));
					RuleResult.UrlBlock ruleResultHeader = new RuleResult.UrlBlock();

					// FIXME
					ruleResultHeader.header = parseFormatArgs(headerJson);

					// // urls:
					try {

						JSONArray urlsJsonArray = urlBlocksJson
								.getJSONArray("urls");
						for (int u = 0; u < urlsJsonArray.length(); ++u) {
							JSONObject urlJson = urlsJsonArray.getJSONObject(u);
							JSONObject resultJson = urlJson
									.getJSONObject("result");
							// Log.d(LOG_TAG, "Suggestion url:"
							// + parseFormatArgs(resultJson));
							// FIXME
							ruleResultHeader
									.addUrl(parseFormatArgs(resultJson));
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}

					// FIXME
					ruleResult.addUrlBlock(ruleResultHeader);
				}

			} catch (JSONException e) {
				// not all rule has urlblock
				if (ruleResult.ruleScore != 100) {
					e.printStackTrace();
				}
			}

			pageSpeedResult.addRuleResult(ruleResult);
		}

		return pageSpeedResult;

	}

	static String parseFormatArgs(JSONObject json) throws JSONException {
		String format = json.getString("format");
		String formattedUrl = format;
		try {
			JSONArray argsJsonArray = json.getJSONArray("args");
			ArrayList<String> args = new ArrayList<String>();
			for (int j = 0; j < argsJsonArray.length(); ++j) {
				JSONObject argJson = argsJsonArray.getJSONObject(j);
				args.add(argJson.getString("value"));
			}

			formattedUrl = RuleResult.combineFormatArgument(format, args);

		} catch (JSONException e) {

		}

		return formattedUrl;
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e(LOG_TAG, ex.toString());
		}
		return null;
	}

}
