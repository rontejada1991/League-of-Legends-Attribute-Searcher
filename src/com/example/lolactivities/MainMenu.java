package com.example.lolactivities;

import java.util.ArrayList;

import com.example.lolattributesearcher.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {
	private Button toChampSelect;
	private Button toAttributeSelect;
	private Button toGlossary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toChampSelect = (Button)findViewById(R.id.champion);
		toChampSelect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("intent", "go to Champion Select");
				Intent intent = new Intent(MainMenu.this, ChampionSelect.class);
				intent.putStringArrayListExtra("values", new ArrayList<String>());
				startActivity(intent);
			}
		});
		
		toAttributeSelect = (Button)findViewById(R.id.attribute);
		toAttributeSelect.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainMenu.this, AttributeSelect.class);
				startActivity(intent);
			}
		});
		
		toGlossary = (Button)findViewById(R.id.glossary);
		toGlossary.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainMenu.this, Glossary.class);
				startActivity(intent);
			}
		});		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}