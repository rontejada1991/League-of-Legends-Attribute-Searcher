package com.example.lolactivities;

import java.util.ArrayList;

import com.example.lolattributesearcher.R;
import com.example.loldatabase.DBAdapter;
import com.example.lolobjects.Attribute;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SingleChampion extends Activity {
	RelativeLayout singleChamp;
	Drawable background;
	TextView singleName;
	ImageView singlePath;
	ListView attributeList;
	ArrayList<Attribute> arrayList;
	ArrayAdapter<Attribute> arrayAdapter;
	DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_champion);
		
		// Declaring variables
		singleChamp = (RelativeLayout)findViewById(R.id.singleChamp);
		singleName = (TextView)findViewById(R.id.tvSingleName);
		singlePath = (ImageView)findViewById(R.id.ivSingleImage);
		attributeList = (ListView)findViewById(R.id.lvSingleAttributes);
		
		// Getting info from the bundle
		Bundle extras = getIntent().getExtras();
		
		singleName.setText(extras.getString("champName"));
		singlePath.setImageResource(extras.getInt("champPath"));		
		
		db = new DBAdapter(this, null);
		arrayList = db.select_all_attributes_with_champion(extras.getString("champName"));
		arrayAdapter = new ArrayAdapter<Attribute>(this, android.R.layout.simple_list_item_1, arrayList);
		attributeList.setAdapter(arrayAdapter);
		
		// Setting Alpha
		background = singleChamp.getBackground();
		background.setAlpha(80);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.single_champion, menu);
		return true;
	}
}