package org.dyndns.warenix.pagespeed.ruleResultList;

import org.dyndns.warenix.pattern.baseListView.ListViewAdapter;

import android.content.Context;

public class RuleResultListAdapter extends ListViewAdapter {

	public RuleResultListAdapter(Context context) {
		super(context);
	}

	public void addRuleResult(RuleResultListItem ruleResult) {
		itemList.add(ruleResult);
	}

	public void notifyDatSetInvalidated() {
		runNotifyDataSetInvalidated();
	}

}
