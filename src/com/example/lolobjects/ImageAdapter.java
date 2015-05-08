package com.example.lolobjects;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.loldatabase.DBAdapter;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	ArrayList<String> values;
	private DBAdapter db;
	private ArrayList<Champion> champion;
	public ImageAdapter(Context c, ArrayList<String> v) {
		values = v;
		mContext = c;
		db = new DBAdapter(mContext, values);
		Log.d("11111111", values.size() + " is selected attribute");
		if(values.size() == 0)
			champion = db.get_all_champion();
		else
			champion = db.query_champion();
//		champion = db.get_all_champion();
	}
	
	public int getCount() {
		Log.d("ImageAdapter Size", String.valueOf(champion.size()));
		return champion.size();
//		return mThumbIds.length;
	}
		
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(champion.get(position).getPath());
//        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}