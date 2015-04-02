package org.dyndns.warenix.pagespeed.ruleResultList.urlBlock;

import org.dyndns.warenix.pagespeed.R;
import org.dyndns.warenix.pagespeed.RuleResult;

import android.content.Context;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class UrlBlockListAdapter extends BaseExpandableListAdapter {

	RuleResult ruleResult;
	Context context;
	LayoutInflater inflater;

	static class ViewHolder {
		TextView header;
	}

	public UrlBlockListAdapter(Context context, RuleResult ruleResult) {
		this.ruleResult = ruleResult;
		this.context = context;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return ruleResult.urlBlockList.get(groupPosition).urlList
				.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return groupPosition * 100 + childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null) {
			if (inflater == null) {
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}

			view = inflater.inflate(R.layout.url_block, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.header = (TextView) view.findViewById(R.id.header);
			view.setTag(viewHolder);
		}

		ViewHolder viewHolder = (ViewHolder) view.getTag();
		viewHolder.header
				.setText(ruleResult.urlBlockList.get(groupPosition).urlList
						.get(childPosition));
		Linkify.addLinks(viewHolder.header, Linkify.WEB_URLS);
		return view;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return ruleResult.urlBlockList.get(groupPosition).urlList.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return ruleResult.urlBlockList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return ruleResult.urlBlockList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition * 100;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null) {
			if (inflater == null) {
				inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			}

			view = inflater.inflate(R.layout.url_block, null);

			ViewHolder viewHolder = new ViewHolder();
			viewHolder.header = (TextView) view.findViewById(R.id.header);
			view.setTag(viewHolder);
		}

		ViewHolder viewHolder = (ViewHolder) view.getTag();
		viewHolder.header
				.setText(ruleResult.urlBlockList.get(groupPosition).header);
		return view;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
