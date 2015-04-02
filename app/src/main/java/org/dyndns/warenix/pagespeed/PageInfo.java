package org.dyndns.warenix.pagespeed;

public class PageInfo {
	public String title;
	public int score;

	public String toScoreChartUrl() {
		return String
				.format("http://chart.apis.google.com/chart?chf=a,s,000000&chxt=y&chs=300x150&cht=gm&chd=t:%d&chl=%d",
						score, score);
	}
}
