package com.pabloc6.calculator6;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLUtils extends SQLiteOpenHelper {

	private static final String DB_NAME = "data.db";
	private static final int DB_VERSION = 1;
	private static final String TABLE = "calc_table";

	
	public SQLUtils(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

    private static final String TAG = "DEBUG->SQLUtils";

	@Override
	public void onCreate(SQLiteDatabase db) {
        final String TAG_LOCAL = TAG + ".onCreate";
        Log.d(TAG_LOCAL, "IN");

        String sql = "create table " + TABLE + "(calc VARCHAR( 100 ));";
//        String sql = "create table " + TABLE + "(calc VARCHAR( 100 ), result REAL);";
//		String sql = "create table " + TABLE + "(sheepName VARCHAR( 10 ), age INTEGER);";
//        String sql = "create table " + TABLE + "(sheepName VARCHAR( 10 ), age INTEGER, color VARCHAR( 10 ));";

        db.execSQL(sql);
    }

    // This method is invoked when the version number specified in the constructor of the class changes.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
        final String TAG_LOCAL = TAG + ".onUpgrade";
        Log.d(TAG_LOCAL, "IN");

        if (newVersion > oldVersion) {
            Log.d(TAG_LOCAL, "Upgrading DB to version " + newVersion);

            switch (newVersion) {
                case 5:
                    db.execSQL("ALTER TABLE " + TABLE + " ADD COLUMN " + "color VARCHAR( 10 )");
                    break;
                case 6:
                    db.execSQL("ALTER TABLE " + TABLE + " ADD COLUMN " + "sex VARCHAR( 1 )");
                    break;
                default:
                    Log.d(TAG_LOCAL, "NO UPDATE. New version not supported " + newVersion);
                    break;
            }
        } else {
            Log.d(TAG_LOCAL, "ERROR - can't downgrade DB. Current version: " + oldVersion
                    + " requested version: " + newVersion);

        }

	}


    public void insertData(String calc) {
//    public void insertData(String calc, float result) {
//    public void insertData(String name, int age, String color, String sex) {
        final String TAG_LOCAL = TAG + ".insertData";
        String sql= "";
        Log.d(TAG_LOCAL, "IN");

		SQLiteDatabase db = this.getWritableDatabase();
//        sql = "INSERT into " + TABLE +
//                " (calc, result) VALUES ('" + calc + "','" + Float.toString(result) + "')";
        sql = "INSERT into " + TABLE + " (calc) VALUES ('" + calc + "')";
/*

        switch (DB_VERSION) {
            case 4:
                sql = "INSERT into " + TABLE + " (sheepName, age) " +
                        "VALUES ('" + name + "','" + Integer.toString(age) + "')";
                break;
            case 5:
                sql = "INSERT into " + TABLE + " (sheepName, age, color) " +
                        "VALUES ('" + name + "','" + Integer.toString(age) + "','" + color + "')";
                break;
            case 6:
                sql = "INSERT into " + TABLE + " (sheepName, age, color, sex) " + "VALUES ('" +
                        name + "','" + Integer.toString(age) + "','" +color + "','" + sex + "')";
                break;
            default:
                Log.d(TAG_LOCAL, "Version not supported " + DB_VERSION);
                break;
        }
*/


        Log.d(TAG_LOCAL, "String sql: " + sql);
		db.execSQL(sql);

		db.close();
	}

	public ArrayList<String> getNames() {
		ArrayList<String> names = new ArrayList<String>();

        final String TAG_LOCAL = TAG + ".getNames";
        Log.d(TAG_LOCAL, "IN");


		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.rawQuery("select * from " + TABLE, null);
		cursor.moveToFirst();

		while(!cursor.isAfterLast()) {
			names.add(cursor.getString(0));

		    cursor.moveToNext();
		}

		cursor.close();
		db.close();

		return names;
	}

    public void deleteTable() {

        final String TAG_LOCAL = TAG + ".getNames";
        Log.d(TAG_LOCAL, "IN");

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from "+ TABLE);
        db.close();
    }

}
