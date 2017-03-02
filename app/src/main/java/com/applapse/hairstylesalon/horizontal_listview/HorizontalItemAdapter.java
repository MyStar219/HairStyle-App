package com.applapse.hairstylesalon.horizontal_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.applapse.hairstylesalon.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * An array adapter that knows how to render views when given CustomData classes
 */
public class HorizontalItemAdapter extends ArrayAdapter<String> {
	private LayoutInflater mInflater;
	String[] imageList,yesa;
	int s;

	public HorizontalItemAdapter(Context context, String[] values) {
		super(context, R.layout.horizontal_data_view, values);
		imageList = values;
		mInflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}
	public HorizontalItemAdapter(Context context, String[] values,String[] yes,int p) {
		super(context, R.layout.horizontal_data_view, values);
		imageList = values;
		yesa=yes;
		s=p;
		mInflater = (LayoutInflater) getContext().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return imageList.length;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;

		if (convertView == null) {
			// Inflate the view since it does not exist
			convertView = mInflater.inflate(R.layout.horizontal_data_view,
					parent, false);

			// Create and save off the holder in the tag so we get quick access
			// to inner fields
			// This must be done for performance reasons
			holder = new Holder();
			holder.imageView = (ImageView) convertView
					.findViewById(R.id.ivHorizontalItem);
			if(s==1){

						holder.textView=(TextView)convertView.findViewById(R.id.textview);
				holder.textView.setVisibility(View.VISIBLE);


			}
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		ImageLoader.getInstance().displayImage("assets://" + imageList[position],
				holder.imageView);
		if(s==1){

			holder.textView.setText(yesa[position]);
		}

		return convertView;
	}

	/** View holder for the views we need access to */
	private static class Holder {
		public ImageView imageView;
	public TextView textView;
	}
}
