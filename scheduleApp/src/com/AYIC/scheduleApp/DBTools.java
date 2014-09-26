package com.AYIC.scheduleApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

public class DBTools extends SQLiteOpenHelper
{
	public DBTools(Context applicationcontext)
	{
        super(applicationcontext, "workoutApp.db", null, 1);
    }
    
	//creates the database with tables
	public void onCreate(SQLiteDatabase database)
	{
		String query = "CREATE TABLE userLogin (userNumber INTEGER PRIMARY KEY, firstName TEXT, "
				+ "email TEXT, password TEXT)";
				
		database.execSQL(query);
	}

	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) 
	{
		String query = "DROP TABLE IF EXISTS userLogin";

		database.execSQL(query);

        onCreate(database);
	}
	
	//inserts new username login
	public void insertLogin(HashMap<String, String> queryValues)
	{
		SQLiteDatabase database = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		values.put("firstName", queryValues.get("firstName"));
		values.put("email", queryValues.get("email"));
		values.put("password", queryValues.get("password"));

		database.insert("userLogin", null, values);

		System.out.println("successfully Inserted Into Database!");
		
		database.close();
	}
	
	//gets all user logins
	public ArrayList<HashMap<String, String>> getAllUserLogins()
	{
		ArrayList<HashMap<String, String>> userArrayList = new ArrayList<HashMap<String, String>>();
		
		String selectQuery = "SELECT * FROM userLogin";
		
		SQLiteDatabase database = this.getWritableDatabase();
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				HashMap<String, String> loginMap = new HashMap<String, String>();
				
				loginMap.put("userNumber", cursor.getString(0));
				loginMap.put("firstName",  cursor.getString(1));
				loginMap.put("email",  cursor.getString(2));
				loginMap.put("password",  cursor.getString(3));
				
				userArrayList.add(loginMap);
			}while(cursor.moveToNext());
		}
		
		return userArrayList;
	}

	public boolean canLogin(String enteredEmail, String enteredPassword)
	{
		HashMap<String, String> loginMap = new HashMap<String, String>();
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM userLogin where email='"+enteredEmail+"'";
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				loginMap.put("userNumber", cursor.getString(0));
				loginMap.put("firstName",  cursor.getString(1));
				loginMap.put("email",  cursor.getString(2));
				loginMap.put("password",  cursor.getString(3));
				
			}while(cursor.moveToNext());
		}
		String mapsEmail = loginMap.get("email");
		String mapsPassword = loginMap.get("password");
		
		if(enteredEmail.equals(mapsEmail))
			if(enteredPassword.equals(mapsPassword))
				return true;
		
		return false;
	}
	//checks if they can register, if the user is already entered
	public boolean canRegister(String enteredEmail)
	{
		
		HashMap<String, String> registerMap = new HashMap<String, String>();
		
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM userLogin where email='"+enteredEmail+"'";
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				registerMap.put("email", cursor.getString(0));
				registerMap.put("firstName",  cursor.getString(1));
				registerMap.put("email",  cursor.getString(2));
				registerMap.put("password",  cursor.getString(3));
				
			}while(cursor.moveToNext());
		}
		String mapsEmail = registerMap.get("email");
		
		if(enteredEmail.equals(mapsEmail))
				return true;
		
		return false;
	}
	
	//gets the first name for user in the user greeting
	public String getFirstName(String enteredEmail)
	{
		HashMap<String, String> loginMap = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM userLogin where email='"+enteredEmail+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				loginMap.put("userNumber", cursor.getString(0));
				loginMap.put("firstName",  cursor.getString(1));
				loginMap.put("email",  cursor.getString(2));
				loginMap.put("password",  cursor.getString(3));
				
			}while(cursor.moveToNext());
		}
		
		String userFirstName = loginMap.get("firstName");
		return userFirstName;
	}
	
	//gets user number to pass through events
	public String getUserNumber(String enteredEmail)
	{
		HashMap<String, String> loginMap = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		
		String selectQuery = "SELECT * FROM userLogin where email='"+enteredEmail+"'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		
		if(cursor.moveToFirst())
		{
			do
			{
				loginMap.put("userNumber", cursor.getString(0));
				loginMap.put("firstName",  cursor.getString(1));
				loginMap.put("email",  cursor.getString(2));
				loginMap.put("password",  cursor.getString(3));
				
			}while(cursor.moveToNext());
		}
		
		String userNumber = loginMap.get("userNumber");
		return userNumber;
	}
}
