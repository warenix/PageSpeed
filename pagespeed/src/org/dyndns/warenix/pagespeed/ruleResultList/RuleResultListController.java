package org.dyndns.warenix.pagespeed.ruleResultList;

import org.dyndns.warenix.pagespeed.RuleResultActivity;
import org.dyndns.warenix.pattern.baseListView.ListViewAdapter;
import org.dyndns.warenix.pattern.baseListView.ListViewController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

public class RuleResultListController extends ListViewController {
	Activity activityContext;

	public RuleResultListController(Activity context, int resourceId) {
		super(context, resourceId);
		activityContext = context;
	}

	@Override
	public ListViewAdapter setupListViewAdapter(Context context) {
		RuleResultListAdapter adapter = new RuleResultListAdapter(context);
		return adapter;
	}

	public void addRuleResult(RuleResultListItem ruleResult) {
		((RuleResultListAdapter) listAdapter).addRuleResult(ruleResult);
	}

	public void clearRuleResult() {
		((RuleResultListAdapter) listAdapter).clear();
	}

	public void notifyDatSetInvalidated() {
		((RuleResultListAdapter) listAdapter).notifyDatSetInvalidated();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		super.onItemClick(parent, view, position, id);
		RuleResultListItem clickedRuleResult = (RuleResultListItem) listAdapter
				.getItem(position);

		Log.d("pagespeed",
				"urlBlock:" + clickedRuleResult.ruleResult.toString());

		Intent intent = new Intent(activityContext, RuleResultActivity.class);
		intent.putExtra(RuleResultActivity.BUNDLE_RULE_RESULT,
				clickedRuleResult.ruleResult);
		activityContext.startActivity(intent);
	}

}
