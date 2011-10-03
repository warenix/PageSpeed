package org.dyndns.warenix.pagespeed.ruleResultList.urlBlock;

import org.dyndns.warenix.pagespeed.R;
import org.dyndns.warenix.pagespeed.RuleResult.UrlBlock;
import org.dyndns.warenix.pattern.baseListView.ListViewItem;

import android.content.Context;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

public class UrlBLockListItem extends ListViewItem {

	UrlBlock urlBlock;

	static class ViewHolder {
		TextView header;
	}

	@Override
	protected View createEmptyView(Context context) {
		View view = inflater.inflate(R.layout.url_block, null);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.header = (TextView) view.findViewById(R.id.header);
		return view;
	}

	@Override
	protected View fillViewWithContent(Context context, View view) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		viewHolder.header.setText(urlBlock.header);
		return view;
	}

	@Override
	public void showContextMenu(ContextMenu menu) {

	}

}
