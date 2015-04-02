package org.dyndns.warenix.pagespeed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PageSpeedResult {

	public PageInfo pageInfo;
	public PageStats pageStats;

	public ArrayList<RuleResult> ruleResultList = new ArrayList<RuleResult>();

	public void addRuleResult(RuleResult ruleResult) {
		ruleResultList.add(ruleResult);
	}

	public void sortRuleResort() {
		Collections.sort(ruleResultList, new Comparator<RuleResult>() {

			@Override
			public int compare(RuleResult object1, RuleResult object2) {
				return object1.ruleScore - object2.ruleScore;
			}
		});
	}
}
