package com.example.lolactivities;

import java.util.ArrayList;

import com.example.lolattributesearcher.R;
import com.example.loldatabase.DBAdapter;
import com.example.lolobjects.Attribute;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Glossary extends Activity {
	ListView attributeList;
	ArrayList<Attribute> arrayList;
	ArrayAdapter<Attribute> arrayAdapter;
	DBAdapter db;
	Drawable background;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glossary);
		
		attributeList = (ListView)findViewById(R.id.listView1);
		db = new DBAdapter(this, null);
		arrayList = db.get_all_attributes();
		arrayAdapter = new ArrayAdapter<Attribute>(this, android.R.layout.simple_list_item_1, arrayList);
		attributeList.setAdapter(arrayAdapter);
		
		// Setting Alpha
		background = attributeList.getBackground();
		background.setAlpha(80);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.glossary, menu);
		return true;
	}

}
