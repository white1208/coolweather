package com.example.coolweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	/**
	 * Province表建表语句
	 */
	public static final String CREAT_PROVINCE = "creat table Province ("
			+ "id integer primary key autoincrement, "
			+ "province_name text, "
			+ "province_code text)";
	/**
	 * City表的建表语句
	 * */
	public static final String CREAT_CITY = "creat table city ("
			+ "id integer primary key autoincrement, "
			+ "city_name text, "
			+ "city_code text, "
			+ "province_id integer)";
	/**
	 * Country表的建表语句
	 * */
	public static final String CREAT_COUNTRY = "creat table country ("
			+ "id integer primary key autoincrement, "
			+ "country_name text, "
			+ "country_code text, "
			+ "city_id integer)";
	
	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory, int version){
		super(context, name, factory, version);
	}
	
	public void onCreat(SQLiteDatabase db){
		db.execSQL(CREAT_PROVINCE);//建表
		db.execSQL(CREAT_CITY);
		db.execSQL(CREAT_COUNTRY);
	}
	
	public void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion){
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
