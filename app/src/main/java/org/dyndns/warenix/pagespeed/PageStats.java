package org.dyndns.warenix.pagespeed;

import org.dyndns.warenix.util.array.ArrayUtil;

/**
 * Basic statistics of a web page
 * 
 * @author warenix
 * 
 */
public class PageStats {
	public int numberResources;
	public int numberHosts;
	public int numberStaticResources;
	public int numberJsResources;

	public int totalRequestBytes;
	public int htmlResponseBytes;
	public int cssResponseBytes;
	public int imageResponseBytes;
	public int javascriptResponseBytes;
	public int otherResponseBytes;
	public int flashResponseBytes;
	public int textResponseBytes;

	String[] colors = { "e2192c", "f3ed4a", "ff7008", "43c121", "f8ce44",
			"ad6bc5", "1051e8" };
	String[] labels = { "JavaScript", "Images", "CSS", "HTML", "Flash", "Text",
			"Other" };

	/**
	 * a pie chart that shows the resource size breakdown of the page being
	 * analyzed
	 * 
	 * @return
	 */
	public String toResourceSizeChartUrl() {
		String chartUrl = "http://chart.apis.google.com/chart?";
		String query = "&chs=300x150";
		query += "&cht=p3";
		query += "&chco=" + ArrayUtil.join(colors, ",");
		query += "&chd=t:"
				+ String.format("%d,%d,%d,%d,%d,%d,%d",
						javascriptResponseBytes, imageResponseBytes,
						cssResponseBytes, htmlResponseBytes,
						flashResponseBytes, textResponseBytes,
						otherResponseBytes);
		query += "&chdl=" + ArrayUtil.join(labels, "|");
		query += "&chp=1.6";

		int largestSingleCategory = javascriptResponseBytes;
		if (largestSingleCategory < imageResponseBytes) {
			largestSingleCategory = imageResponseBytes;
		}
		if (largestSingleCategory < cssResponseBytes) {
			largestSingleCategory = cssResponseBytes;
		}
		if (largestSingleCategory < htmlResponseBytes) {
			largestSingleCategory = htmlResponseBytes;
		}
		if (largestSingleCategory < flashResponseBytes) {
			largestSingleCategory = flashResponseBytes;
		}
		if (largestSingleCategory < textResponseBytes) {
			largestSingleCategory = textResponseBytes;
		}
		if (largestSingleCategory < otherResponseBytes) {
			largestSingleCategory = otherResponseBytes;
		}
		query += "&chds=0," + largestSingleCategory;
		return chartUrl + query;
	}
}
