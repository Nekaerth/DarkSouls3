package com.example.darksouls3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListManager {
	private ListView listView;
	private ListViewAdapter baseAdapter;

	public ListManager(ListView listView, Context context) {
		this.listView = listView;
		this.baseAdapter = new ListViewAdapter(context);
		this.listView.setAdapter(baseAdapter);
	}

	public void updateList(ArrayList<String> list) {
		this.baseAdapter.setList(list);
		this.baseAdapter.notifyDataSetChanged();
	}

	public void clearList() {
		this.baseAdapter.setList(new ArrayList<String>());
		this.baseAdapter.notifyDataSetChanged();
	}

	private class ListViewAdapter extends BaseAdapter {
		private Context context;
		private ArrayList<String> list;
		private LayoutInflater layoutInflater;
		private View rowView;

		private ListViewAdapter(Context context) {
			this.context = context;
			layoutInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return list == null ? 0 : list.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup viewGroup) {
			rowView = layoutInflater.inflate(R.layout.list_row, viewGroup, false);

			TextView nameTextView = rowView.findViewById(R.id.list_row_text_view);
			nameTextView.setText(list.get(position));
			return rowView;
		}

		private void setContext(Context context) {
			this.context = context;
		}

		private void setList(ArrayList<String> list) {
			this.list = list;
		}
	}
}
