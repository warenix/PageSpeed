package org.dyndns.warenix.pagespeed.ruleResultList;

import org.dyndns.warenix.pagespeed.R;
import org.dyndns.warenix.pagespeed.RuleResult;
import org.dyndns.warenix.pattern.baseListView.ListViewItem;
import org.dyndns.warenix.widget.WebImage;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

public class RuleResultListItem extends ListViewItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3301558104191894466L;
	RuleResult ruleResult;

	static class ViewHolder {
		TextView localizedRuleName;
		WebImage barImage;
		TextView ruleScore;
	}

	public RuleResultListItem(RuleResult ruleResult) {
		this.ruleResult = ruleResult;
	}

	@Override
	protected View createEmptyView(Context context) {
		View view = inflater.inflate(R.layout.rule_result, null);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.localizedRuleName = (TextView) view
				.findViewById(R.id.localizedRuleName);
		viewHolder.ruleScore = (TextView) view.findViewById(R.id.ruleScore);
		viewHolder.barImage = (WebImage) view.findViewById(R.id.barImage);
		view.setTag(viewHolder);
		return view;
	}

	@Override
	protected View fillViewWithContent(Context context, View view) {
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		viewHolder.localizedRuleName.setText(ruleResult.localizedRuleName);
		viewHolder.ruleScore.setText("" + ruleResult.ruleScore + "/"
				+ ruleResult.ruleImpact);
		viewHolder.barImage.startLoading(ruleResult.toScoreChartUrl());
		return view;
	}

	@Override
	public void showContextMenu(ContextMenu menu) {

	}

}
