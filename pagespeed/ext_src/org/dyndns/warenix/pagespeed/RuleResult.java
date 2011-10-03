package org.dyndns.warenix.pagespeed;

import java.io.Serializable;
import java.util.ArrayList;

public class RuleResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2320199152648372642L;
	public String localizedRuleName;
	public int ruleScore;
	public int ruleImpact;

	public ArrayList<UrlBlock> urlBlockList = new ArrayList<RuleResult.UrlBlock>();

	public static final String RULE_AVOID_BAD_REQUESTS = "AvoidBadRequests";
	public static final String RULE_AVOID_CSS_IMPORT = "AvoidCssImport";
	public static final String RULE_DEFER_PARSING_JAVA_SCRIPT = "DeferParsingJavaScript";
	public static final String RULE_INLINE_SMALL_CSS = "InlineSmallCss";
	public static final String RULE_INLINE_SMALL_JAVASCRIPT = "InlineSmallJavaScript";
	public static final String RULE_LEVERAGE_BROWSER_CACHING = "LeverageBrowserCaching";
	public static final String RULE_MAKE_LANDING_PAGE_REDIRECTS_CACHEABLE = "MakeLandingPageRedirectsCacheable";
	public static final String RULE_MINIFY_CSS = "MinifyCss";
	public static final String RULE_MINIFY_HTML = "MinifyHTML";
	public static final String RULE_MINIFY_JAVA_SCRIPT = "MinifyJavaScript";
	public static final String RULE_MINIMIZE_REDIRECTS = "MinimizeRedirects";
	public static final String RULE_MINIMIZE_REQUEST_SIZE = "MinimizeRequestSize";
	public static final String RULE_OPTIMIZE_IMAGES = "OptimizeImages";
	public static final String RULE_OPTIMIZE_THE_ORDER_OF_STYLES_AND_SCRIPTS = "OptimizeTheOrderOfStylesAndScripts";
	public static final String RULE_PREFER_ASYNC_RESOURCES = "PreferAsyncResources";
	public static final String RULE_PUT_CSS_IN_THE_DOCUMENT_HEAD = "PutCssInTheDocumentHead";
	public static final String RULE_REMOVE_QUERY_STRINGS_FROM_STATIC_RESOURCES = "RemoveQueryStringsFromStaticResources";
	public static final String RULE_SERVE_RESOURCES_FROM_A_CONSISTENT_URL = "ServeResourcesFromAConsistentUrl";
	public static final String RULE_SERVE_SCALED_IMAGES = "ServeScaledImages";
	public static final String RULE_SPECIFY_A_CACHE_VALIDATOR = "SpecifyACacheValidator";
	public static final String RULE_SPECIFY_A_VARY_ACCEPT_ENCODING_HEADER = "SpecifyAVaryAcceptEncodingHeader";
	public static final String RULE_SPECIFY_CHARSET_EARLY = "SpecifyCharsetEarly";
	public static final String RULE_SPECIFY_IMAGE_DIMENSIONS = "SpecifyImageDimensions";
	public static final String RULE_SPRITE_IMAGES = "SpriteImages";

	public static String RULES[] = { RULE_AVOID_BAD_REQUESTS,
			RULE_AVOID_CSS_IMPORT, RULE_DEFER_PARSING_JAVA_SCRIPT,
			RULE_INLINE_SMALL_CSS, RULE_INLINE_SMALL_JAVASCRIPT,
			RULE_LEVERAGE_BROWSER_CACHING,
			RULE_MAKE_LANDING_PAGE_REDIRECTS_CACHEABLE, RULE_MINIFY_CSS,
			RULE_MINIFY_HTML, RULE_MINIFY_JAVA_SCRIPT, RULE_MINIMIZE_REDIRECTS,
			RULE_MINIMIZE_REQUEST_SIZE, RULE_OPTIMIZE_IMAGES,
			RULE_OPTIMIZE_THE_ORDER_OF_STYLES_AND_SCRIPTS,
			RULE_PREFER_ASYNC_RESOURCES, RULE_PUT_CSS_IN_THE_DOCUMENT_HEAD,
			RULE_REMOVE_QUERY_STRINGS_FROM_STATIC_RESOURCES,
			RULE_SERVE_RESOURCES_FROM_A_CONSISTENT_URL,
			RULE_SERVE_SCALED_IMAGES, RULE_SPECIFY_A_CACHE_VALIDATOR,
			RULE_SPECIFY_A_VARY_ACCEPT_ENCODING_HEADER,
			RULE_SPECIFY_CHARSET_EARLY, RULE_SPECIFY_IMAGE_DIMENSIONS,
			RULE_SPRITE_IMAGES };

	/**
	 * combine $1 $2... with arguments
	 * 
	 * @param format
	 * @param args
	 * @return
	 */
	public static String combineFormatArgument(String format,
			ArrayList<String> args) {
		if (args != null) {
			for (int i = 0; i < args.size(); ++i) {
				format = format.replaceAll("\\$" + (i + 1), args.get(i));
			}
		}
		return format;
	}

	@Override
	public String toString() {
		String string = "";
		string += String.format("\nRuleResult: %s: score:%d impact:%d",
				localizedRuleName, ruleScore, ruleImpact);
		string += String.format("\nRuleResult: No. of urlBlocks:%d",
				urlBlockList.size());
		for (UrlBlock urlBlock : urlBlockList) {
			string += String.format("\nRuleResult: %s, No. of urls:%d",
					urlBlock.header, urlBlock.urlList.size());
			if (urlBlock.urlList.size() > 0) {
				for (String url : urlBlock.urlList) {
					string += String.format("\nRuleResult: url:%s", url);
				}
			}
		}
		return string;
	}

	public void addUrlBlock(UrlBlock urlBlock) {
		urlBlockList.add(urlBlock);
	}

	public static class UrlBlock implements Serializable {
		public String header;
		public ArrayList<String> urlList = new ArrayList<String>();;

		public void addUrl(String url) {
			urlList.add(url);
		}
	}

	public String toScoreChartUrl() {
		return String
				.format("http://chart.apis.google.com/chart?chf=a,s,000000&chbh=a,0,0&chs=400x50&cht=bhs&chco=4D89F9,C6D9FD&chd=t:%d|100&a.png",
						ruleScore);
	}
}
