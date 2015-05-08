package com.example.lolactivities;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.lolattributesearcher.R;
import com.example.loldatabase.DBAdapter;
import com.example.lolobjects.Champion;
import com.example.lolobjects.ImageAdapter;

public class ChampionSelect extends Activity implements OnItemClickListener {
	GridView gridView;
	Drawable background;
	// Necessary to know which champ is selected so you can give info to the next activity
	DBAdapter db;
	ArrayList<Champion> name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.champion_gridview);
		ArrayList<String>values = getIntent().getStringArrayListExtra("values");
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setAdapter(new ImageAdapter(this, values));
		
		gridView.setOnItemClickListener(this);
		
		// Setting up the db for when an item is selected
		db = new DBAdapter(this, null);
		
		// Setting Alpha
		background = gridView.getBackground();
		background.setAlpha(80);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		name = db.get_all_champion();
		Champion passedChamp = name.get(position);
				
		Intent i = new Intent(this, SingleChampion.class);
		i.putExtra("champName", passedChamp.getName());
		i.putExtra("champPath", passedChamp.getPath());
		i.putExtra("champId", passedChamp.get_id());
		startActivity(i);		
	}
	
}
