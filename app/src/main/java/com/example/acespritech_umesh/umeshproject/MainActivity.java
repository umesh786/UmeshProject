package com.example.acespritech_umesh.umeshproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.acespritech_umesh.umeshproject.Data.AdepterClass;
import com.example.acespritech_umesh.umeshproject.Data.Employee;
import com.example.acespritech_umesh.umeshproject.Data.SQLClass;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView DNF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        DNF= (TextView) findViewById(R.id.datafound);
        DNF.setVisibility(View.GONE);

        SQLClass sqlClass = new SQLClass(MainActivity.this);

        final ArrayList<Employee> arraylist = sqlClass.getAll();

        AdepterClass adepterClass = new AdepterClass(MainActivity.this, arraylist);
        if (arraylist.size()==0)
        {
            DNF.setVisibility(View.VISIBLE);
        }
        listView.setAdapter(adepterClass);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("id", arraylist.get(position).getId());
                intent.putExtra("name", arraylist.get(position).getName());
                intent.putExtra("department", arraylist.get(position).getDepartment());
                intent.putExtra("sal", arraylist.get(position).getSalary());

                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateForm.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertbox = new AlertDialog.Builder(MainActivity.this);
        alertbox.setCancelable(true);
        alertbox.setMessage("Are you sure you want Exit ?");
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        alertbox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertbox.show();
    }
}
