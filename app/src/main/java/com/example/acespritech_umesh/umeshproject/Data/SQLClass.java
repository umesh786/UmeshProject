package com.example.acespritech_umesh.umeshproject.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by acespritech-umesh on 14/7/17.
 */

public class SQLClass extends SQLiteOpenHelper {
    public SQLClass(Context context) {
        super(context, "MyTable", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Employee(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,department TEXT,salary NUMBER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Employee");
        onCreate(db);
    }

    public void Createdata(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put("id", employee.getId());
        contentValues.put("name", employee.getName());
        contentValues.put("department", employee.getDepartment());
        contentValues.put("salary", employee.getSalary());

        sqLiteDatabase.insert("Employee", null, contentValues);
    }
    public ArrayList<Employee> getAll()
    {
        ArrayList<Employee> list = new ArrayList<Employee>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Employee", null);

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setName(cursor.getString(1));
                employee.setDepartment(cursor.getString(2));
                employee.setSalary(cursor.getString(3));
                list.add(employee);
                Log.d("<>MSize",list.size()+"");
            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public int updateData(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", employee.getId());
        values.put("name", employee.getName());
        values.put("department", employee.getDepartment());
        values.put("salary", employee.getSalary());

        return sqLiteDatabase.update("Employee", values, "id=?", new String[]{String.valueOf(employee.getId())});
    }

    public void deleteData(Employee employee) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("Employee", "id=?", new String[]{String.valueOf(employee.getId())});
        sqLiteDatabase.close();

    }
}
