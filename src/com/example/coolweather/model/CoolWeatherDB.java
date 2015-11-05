package com.example.coolweather.model;

import java.util.ArrayList;
import java.util.List;

import com.example.coolweather.db.CoolWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	/**
	 * ���ݿ������
	 */
	public static final String DB_NAME = "cool_weather";
	/**
	 * ���ݿ�İ汾
	 */
	public static final int VERSION = 1;
	
	private static CoolWeatherDB coolWeatherDB;
	
	private SQLiteDatabase db;
	
	/**
	 * �����캯��˽�л�
	 * �������
	 * */
	
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	/**
	 * ��ȡCoolWeatherDB
	 * 
	 * */
	
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB == null){
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	
	public void saveProvince(Province province){
		if(province != null){
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	
	public List<Province> loadProvinces(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null, null);
		while(cursor.moveToNext()){
			Province province = new Province();
			province.setId(cursor.getInt(cursor.getColumnIndex("id")));//����ָ���е�����
			province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
			province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
			list.add(province);
		}
		return list;
	}
	
	/**
	 * ��cityʵ�����洢�����ݿ�֮��
	 * */
	
	public void saveCity(City city){
		if(city != null){
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("provice_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	
	public List<City> loadCity(){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, null, null, null, null, null, null);
		while(cursor.moveToNext()){
			City city = new City();
			city.setId(cursor.getInt(cursor.getColumnIndex("id")));
			city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
			city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
			city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
			list.add(city);
		}
		return list;
	}
	
	public void saveCounty(Country country){
		ContentValues values = new ContentValues();
		if(country != null){
			values.put("country_name", country.getCountryName());
			values.put("country_code", country.getCountryCode());
			values.put("city_id", country.getCityId());
			db.insert("Country", null, values);
		}
	}
	
	public List<Country> loadCountry(){
		List<Country> list = new ArrayList<Country>();
		Cursor cursor = db.query("Country", null, null, null, null, null, null, null);
		while(cursor.moveToNext()){
			Country country = new Country();
			country.setId(cursor.getInt(cursor.getColumnIndex("id")));
			country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
			country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
			country.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
			list.add(country);
		}
		return list;
	}
}
