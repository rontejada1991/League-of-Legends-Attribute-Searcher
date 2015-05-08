package com.example.lolactivities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lolattributesearcher.R;

public class AttributeSelect extends Activity {
	AttributeListAdapter mAdapter;
	ArrayList<AttributeItemInfo> mOrders;
	ListView mList;
	Button searchAttribute;
	RelativeLayout rl;
	Drawable background;
	Bundle bundle;
	ArrayList<String> values;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attribute_select);
		values = new ArrayList<String>();
		rl = (RelativeLayout)findViewById(R.id.relative1);
		
		mList = (ListView)findViewById(R.id.list_attribute);
		mOrders = new ArrayList<AttributeItemInfo>();
		
		mOrders.clear();
//		AttributeItemInfo item1 = new AttributeItemInfo(false, "Energy");
//		AttributeItemInfo item2 = new AttributeItemInfo(false, "Health");
//		AttributeItemInfo item3 = new AttributeItemInfo(false, "No cost (Cooldown)");
//		AttributeItemInfo item4 = new AttributeItemInfo(false, "Fury");

		AttributeItemInfo item5 = new AttributeItemInfo(false, "Blind");
		AttributeItemInfo item6 = new AttributeItemInfo(false, "Blink");
		AttributeItemInfo item7 = new AttributeItemInfo(false, "Blocker");
		AttributeItemInfo item8 = new AttributeItemInfo(false, "Dash");
		AttributeItemInfo item9 = new AttributeItemInfo(false, "Execution");
		AttributeItemInfo item10 = new AttributeItemInfo(false, "Global");
		AttributeItemInfo item11 = new AttributeItemInfo(false, "Haste");
		AttributeItemInfo item12 = new AttributeItemInfo(false, "Healer");
		AttributeItemInfo item13 = new AttributeItemInfo(false, "Mana Heal");		
		AttributeItemInfo item14 = new AttributeItemInfo(false, "Nuke");
		AttributeItemInfo item15 = new AttributeItemInfo(false, "Pet");
		AttributeItemInfo item16 = new AttributeItemInfo(false, "Scout");
		AttributeItemInfo item17 = new AttributeItemInfo(false, "Self Heal");
		AttributeItemInfo item18 = new AttributeItemInfo(false, "Shapeshifter");
		AttributeItemInfo item19 = new AttributeItemInfo(false, "Shield");
		AttributeItemInfo item20 = new AttributeItemInfo(false, "Slow");
		AttributeItemInfo item21 = new AttributeItemInfo(false, "Snare");
		AttributeItemInfo item22 = new AttributeItemInfo(false, "Stance");
		AttributeItemInfo item23 = new AttributeItemInfo(false, "Teleport");
		AttributeItemInfo item24 = new AttributeItemInfo(false, "Interrupt"); 
		
//		mOrders.add(item1);
//		mOrders.add(item2);
//		mOrders.add(item3);
//		mOrders.add(item4);
		mOrders.add(item5);
		mOrders.add(item6);
		mOrders.add(item7);
		mOrders.add(item8);
		mOrders.add(item9);
		mOrders.add(item10);
		mOrders.add(item11);
		mOrders.add(item12);
		mOrders.add(item13);
		mOrders.add(item14);
		mOrders.add(item15);
		mOrders.add(item16);
		mOrders.add(item17);
		mOrders.add(item18);
		mOrders.add(item19);
		mOrders.add(item20);
		mOrders.add(item21);
		mOrders.add(item22);
		mOrders.add(item23);
		mOrders.add(item24);
		
		//Connect Listview and CustomList
		mAdapter = new AttributeListAdapter(this, R.layout.item_attribute, mOrders);
		mList.setAdapter(mAdapter);
		
		//Search Button
		searchAttribute = (Button)findViewById(R.id.btn_search_attribute);
		searchAttribute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AttributeSelect.this, ChampionSelect.class);
				intent.putStringArrayListExtra("values", values);
				startActivity(intent);				
			}
		});
		
		// Setting Alpha
		background = rl.getBackground();
		background.setAlpha(80);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.attribute_select, menu);
		return true;
	}
	
	//Information about Checkbox and TextView
	class AttributeItemInfo	{
		private boolean mChecked;
		private String mListName;		
		public AttributeItemInfo(boolean _check, String _listName)	{
			mChecked = _check;
			mListName = _listName;
		}
		
		public boolean getCheck()	{
			return mChecked;
		}
		public String getListName()	{
			return mListName;
		}

		
	}
	
	class AttributeListAdapter extends ArrayAdapter<AttributeItemInfo>	{
		private List<AttributeItemInfo> mItems;
		private Context mContext;
		public AttributeListAdapter(Context context, int textViewResourceId, List<AttributeItemInfo> objects)	{
			super(context, textViewResourceId, objects);
			
			this.mContext = context;
			this.mItems = objects;
		}
		
		public View getView(int position, View convertView, ViewGroup parent)	{
			AttributeListItemView mItemView;
			if(convertView == null)	{
				mItemView = new AttributeListItemView(mContext);
			}else	{
				mItemView = (AttributeListItemView)convertView;
			}
			
			AttributeItemInfo msg = mItems.get(position);
			mItemView.setMessage(msg);
			convertView = mItemView;
			
			return convertView;
		}
	}
	
	class AttributeListItemView extends LinearLayout	{
		private View childView = null;
		private AttributeItemInfo mItem;
		
		private CheckBox mCheckbox;
		private TextView mListName;
		
		boolean mCheckState;
		String mstrListName;
		
		public AttributeListItemView(Context context, AttributeSet attrs)	{
			super(context, attrs);
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			childView = inflater.inflate(R.layout.item_attribute, null);
			@SuppressWarnings("deprecation")
			LayoutParams ll = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			addView(childView, ll);
			
			mCheckbox = (CheckBox)findViewById(R.id.checkbox_attribute);
			
			
			mCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)	{
//						Toast.makeText(getApplicationContext(),mListName.getText() + " is " + mCheckbox.isChecked(), Toast.LENGTH_SHORT).show();
						values.add(mListName.getText().toString());
					}
					else	{
						values.remove(mListName.getText().toString());
						
					}
				}
			});
			
			
			mListName = (TextView)findViewById(R.id.text_attribute_name);

		}
		
		public AttributeListItemView(Context context)	{
			super(context);
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			childView = inflater.inflate(R.layout.item_attribute, null);
			@SuppressWarnings("deprecation")
			LayoutParams ll = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			addView(childView, ll);
			
			mCheckbox = (CheckBox)findViewById(R.id.checkbox_attribute);
			mListName = (TextView)findViewById(R.id.text_attribute_name);
			mCheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					if(isChecked)	{
//						Toast.makeText(getApplicationContext(),mListName.getText() + " is " + mCheckbox.isChecked(), Toast.LENGTH_SHORT).show();
						values.add(mListName.getText().toString());
					}
					else	{
						values.remove(mListName.getText().toString());
						
					}
				}
			});
		}
		
		public void setMessage(AttributeItemInfo msg)	{
			mItem = msg;
			
			mCheckState = mItem.getCheck();
			mstrListName = mItem.getListName();
			
			if(mCheckState == true)	{
				mCheckbox.setChecked(true);
				Toast.makeText(getApplicationContext(), "checked",
						Toast.LENGTH_SHORT).show();
			}else{
				mCheckbox.setChecked(false);
			}
			
			mListName.setText(mstrListName);
		}
	}

}
