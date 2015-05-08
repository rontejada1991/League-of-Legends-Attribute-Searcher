package com.example.loldatabase;

import java.util.ArrayList;

import com.example.lolobjects.Attribute;
import com.example.lolobjects.Champion;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

public class DBAdapter {
	private Context context;
	private SQLiteDatabase db;
	ArrayList<String> values;	
	public DBAdapter(Context context, ArrayList<String> v)	{
		this.context = context;
		values = v;
		this.open();
	}
	
	public void open() throws SQLException	{
		try	{
			db = (new DBHelper(context).getWritableDatabase());
				
		}catch (SQLiteException e)	{
			db = (new DBHelper(context).getReadableDatabase());
		}
	}
	
	/*
	 * These are insert Method. 
	 * But, We don't need to use this method.
	 * Because, We already input all data at DBHelper.class in onCreate() function.
	 * In this class, We'd be better use Select function.
	 */
//	public void insert_champion(String name, int path)	{
//		try	{
//			ContentValues values = new ContentValues();
//			values.put("name", name);
//			values.put("path", path);
//			long result = db.insert(champion_db, null, values);
//		}catch(Exception e)	{
//			e.printStackTrace();
//		}
//	}
//	
//	public void insert_attribute(String name)	{
//		try	{
//			ContentValues values = new ContentValues();
//			values.put("name", name);
//			long result = db.insert(attribute_db, null, values);
//		}catch(Exception e)	{
//			e.printStackTrace();
//		}
//			
//		
//	}
	
	public Cursor select_all_champion()	{
		Cursor c = db.query("champion_db", //table name
				new String[]{"C_id", "name", "path"}, //column statement
				null, //where
				null, //data at where
				null, //group by
				null, //having
				"C_id" + " ASC" //order by
				);
		return c;
	}
	
	public ArrayList<Champion> get_all_champion()	{
		ArrayList<Champion> champions = new ArrayList<Champion>();
		Cursor c = select_all_champion();
		if(c.moveToFirst())	{
			int indexName = c.getColumnIndex("name");
			int indexPath = c.getColumnIndex("path");
			do	{
				String name = c.getString(indexName);
				int path = c.getInt(indexPath);
				
				champions.add(new Champion(name, path));
			}while(c.moveToNext());
		}
		return champions;
	}
	
	public Cursor select_all_attributes()	{
		Cursor c = db.query("attribute_db", //table name
				new String[]{"A_id", "name", "description"}, //column statement
				null, //where
				null, //data at where
				null, //group by
				null, //having
				"A_id" + " ASC" //order by
				);
		return c;
	}	
	
	public ArrayList<Attribute> get_all_attributes()	{
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		Cursor c = select_all_attributes();
		if(c.moveToFirst())	{
			int indexName = c.getColumnIndex("name");
			int indexDescription = c.getColumnIndex("description");
			do	{
				String name = c.getString(indexName);
				String description = c.getString(indexDescription);
				
				attributes.add(new Attribute(name, description));
			}while(c.moveToNext());
		}
		return attributes;
	}
	
	public Cursor select_some_champions(String[] championNames){
		String query = "select * from champion_db where ";
		query = query + "name=" + "\'" + championNames[0] + "\'";
		
		for(int i = 1; i < championNames.length; i++){
			query = query + " or name='\'" + championNames[i] + "\'"; 
		}
		query = query + "order by name asc;";
		Cursor c = db.rawQuery(query, null);
		return c;
	}
	
	public ArrayList<Champion> get_some_champions(String[] championNames){
		ArrayList<Champion> champions = new ArrayList<Champion>();
		Cursor c = select_some_champions(championNames);
		if(c.moveToFirst())	{
			int indexName = c.getColumnIndex("name");
			int indexPath = c.getColumnIndex("path");
			do	{
				String name = c.getString(indexName);
				int path = c.getInt(indexPath);
				
				champions.add(new Champion(name, path));
			}while(c.moveToNext());
		}
		return champions;
	}
	
	public Cursor select_some_attributes(String[] attributeNames){
		String query = "select * from attribute_db where ";
		query = query + "name=" + "\'" + attributeNames[0] + "\'";
		
		for(int i = 1; i < attributeNames.length; i++){
			query = query + " or name='\'" + attributeNames[i] + "\'"; 
		}
		query = query + "order by name asc;";
		Cursor c = db.rawQuery(query, null);
		return c;
	}
	
	public ArrayList<Attribute> get_some_attributes(String[] attributeNames){
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		Cursor c = select_some_champions(attributeNames);
		if(c.moveToFirst())	{
			int indexName = c.getColumnIndex("name");
			int indexDescription = c.getColumnIndex("description");
			do	{
				String name = c.getString(indexName);
				String description = c.getString(indexDescription);
				
				attributes.add(new Attribute(name, description));
			}while(c.moveToNext());
		}
		return attributes;
	}
 	
	public ArrayList<Champion> select_all_champions_with_attribute(String attributeName){
		ArrayList<Champion> champions = new ArrayList<Champion>();
		String query = "select A_id from attribute_db where name='" + attributeName + "';";
		Cursor c = db.rawQuery(query, null);
		int id = 0;
		
		try{
			id = c.getInt(0);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		query = "select * from champion_db inner join champion2attribute_db on champion_db.C_id=champion2attribute_db and champion2attribute_db.A_id=" + id + " order by champion_db.name asc;";
		c = db.rawQuery(query, null);
		
		if(c.moveToFirst())	{
			int indexName = c.getColumnIndex("name");
			int indexPath = c.getColumnIndex("path");
			do	{
				String name = c.getString(indexName);
				int path = c.getInt(indexPath);
				
				champions.add(new Champion(name, path));
			}while(c.moveToNext());
		}
		return champions;
	}
	
	public ArrayList<Attribute> select_all_attributes_with_champion(String championName){
		ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		String query = "select C_id from champion_db where name='" + championName + "';";
		Cursor c = db.rawQuery(query, null);
		int id = 0;
		
		try{
			id = c.getInt(0);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		query = "select * from attribute_db inner join champion2attribute_db on attribute_db.A_id=champion2attribute_db.A_id and champion2attribute_db.C_id=" + id + " order by attribute_db.name asc;";
		c = db.rawQuery(query, null);
		
		if(c.moveToFirst())	{
			int indexName = c.getColumnIndex("name");
			int indexDescription = c.getColumnIndex("description");
			do	{
				String name = c.getString(indexName);
				String description = c.getString(indexDescription);
				
				attributes.add(new Attribute(name, description));
			}while(c.moveToNext());
		}
		return attributes;
	}

	
	public ArrayList<Champion> query_champion()	{
		ArrayList<Champion> champions = new ArrayList<Champion>();
		ArrayList<Integer> aID = new ArrayList<Integer>();
		ArrayList<Integer> cID = new ArrayList<Integer>();
		Cursor c = null;
		StringBuilder sb  = new StringBuilder();
		StringBuilder sql  = new StringBuilder();


		/*
		 * Make query to find attribute_id using checkbox 
		 */
		
		
		sql.delete(0, sql.length());
		sql.append("SELECT * FROM attribute_db where ");
		
		for(int i=0; i<values.size(); i++)	{
			sb.append("name = " + "'" + values.get(i) + "'");
			sql.append("name = " + "'" + values.get(i) + "'");
			if(i < values.size()-1)	{
				sb.append(" OR ");
				sql.append(" OR ");
			}
		}
		
		try	{
		c = db.rawQuery("SELECT * FROM attribute_db where " + sb , null);
		}catch(SQLException e)	{
			
			Log.e("SQL ERROR", e.getMessage() + "222222   A error");
		}
//		Log.e("TEST", "000001");
		if(c.getCount() == 0)	{
			Toast.makeText(context, "None Champion!!", Toast.LENGTH_SHORT).show();
			return champions;
		}

		aID.clear();
		if(c.moveToFirst())	{

			do	{
				int id = c.getInt(c.getColumnIndex("A_id"));
				aID.add(id);
			}while(c.moveToNext());
		}
		
		cID.clear();
		sql.setLength(0);

		
		/*
		 * Make query to find champion_id using attribute_id
		 */
		sql.append("SELECT * FROM champion2attribute_db where A_id in(");
		for(int i=0; i<aID.size(); i++)	{
			sql.append( String.valueOf(aID.get(i)));
			if(i < aID.size()-1)	{
				sql.append(", ");
			}
		}
		sql.append(") group by C_id having COUNT(distinct A_id)=");
		sql.append(String.valueOf(aID.size()));
		
		try	{
		c = db.rawQuery(sql.toString(), null);
		}catch(SQLException e)	{
			Log.e("SQL ERROR", e.getMessage() + "222222 c2aERROR");
		}
		
		
		if(c.moveToFirst())	{
			do	{
				int id = c.getInt(c.getColumnIndex("C_id"));
				cID.add(id);

			}while(c.moveToNext());
		}		
		
		champions.clear();
		
		
		/*
		 * Find champions using champion_id
		 */
		for(int j=0; j<cID.size(); j++)	{
			
			try	{
			c = db.rawQuery("SELECT * from champion_db where C_id = "+ cID.get(j), null);
			}catch(SQLException e)	{
				Log.e("SQL ERROR", e.getMessage() + "222222 C ERROR");
			}
			if(c.moveToFirst())	{
				int indexName = c.getColumnIndex("name");
				int indexPath = c.getColumnIndex("path");
				do	{
					String name = c.getString(indexName);
					int path = c.getInt(indexPath);
					
					champions.add(new Champion(name, path));
				}while(c.moveToNext());
			}
		}

		return champions;
	}

}

