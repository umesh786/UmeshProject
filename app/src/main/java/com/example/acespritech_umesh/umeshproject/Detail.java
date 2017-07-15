package com.example.acespritech_umesh.umeshproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acespritech_umesh.umeshproject.Data.Employee;
import com.example.acespritech_umesh.umeshproject.Data.SQLClass;

import java.util.ArrayList;

/**
 * Created by acespritech-umesh on 15/7/17.
 */
public class Detail extends AppCompatActivity {
    TextView ids, names, designations, salarys;
    Spinner department;
    EditText edname, edsalary;
    ArrayList arrayList;
    int delete_id, idu;
    String depar, stringDepartment;
    Menu menu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        ids = (TextView) findViewById(R.id.detailid);
        names = (TextView) findViewById(R.id.detailname);
        designations = (TextView) findViewById(R.id.detaildesignation);
        salarys = (TextView) findViewById(R.id.detailsalary);

        department = (Spinner) findViewById(R.id.updatedesignation);
        edname = (EditText) findViewById(R.id.updatename);
        edsalary = (EditText) findViewById(R.id.updatelsalary);


        Intent intent = getIntent();
        delete_id = intent.getIntExtra("id", 0);
        depar = intent.getStringExtra("department");
        ids.setText(delete_id + "");
        names.setText(intent.getStringExtra("name"));
        designations.setText(intent.getStringExtra("department"));
        salarys.setText(intent.getStringExtra("sal"));

        arrayList = new ArrayList<>();
        arrayList.add("IT");
        arrayList.add("CE");
        arrayList.add("ME");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Detail.this, android.R.layout.simple_list_item_1, arrayList);
        department.setAdapter(adapter);

        edname.setText(intent.getStringExtra("name"));
        edsalary.setText(intent.getStringExtra("sal"));

        edname.setVisibility(View.GONE);
        department.setVisibility(View.GONE);
        edsalary.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.mymenu, menu);
        menu.findItem(R.id.save).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete) {
            AlertDialog.Builder alertbox = new AlertDialog.Builder(Detail.this);
            alertbox.setCancelable(true);
            alertbox.setMessage("Are you sure you want to delete ?");
            alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    SQLClass sqlClass = new SQLClass(Detail.this);
                    sqlClass.deleteData(new Employee(delete_id));
                    Toast.makeText(Detail.this, "Delete Successful", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Detail.this, MainActivity.class));
                }
            });
            alertbox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertbox.show();
        } else if (id == R.id.update) {
            menu.findItem(R.id.update).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            menu.findItem(R.id.save).setVisible(true);

            names.setVisibility(View.GONE);
            designations.setVisibility(View.GONE);
            salarys.setVisibility(View.GONE);
            edname.setVisibility(View.VISIBLE);
            department.setVisibility(View.VISIBLE);
            if (depar.equals("IT")) {
                department.setSelection(0);
            } else if (depar.equals("CE")) {
                department.setSelection(1);
            } else if (depar.equals("ME")) {
                department.setSelection(2);
            }
            edsalary.setVisibility(View.VISIBLE);
        } else if (id == R.id.save) {
            menu.findItem(R.id.update).setVisible(false);
            menu.findItem(R.id.delete).setVisible(false);
            if (edname.getText().toString().equals("")) {
                edname.setFocusable(true);
                edname.setError("Name is Required");
            } else if (edsalary.getText().toString().equals("")) {
                edsalary.setFocusable(true);
                edsalary.setError("Salary is Required");
            } else {
                if (department.getSelectedItemPosition() == 0) {
                    stringDepartment = "IT";
                } else if (department.getSelectedItemPosition() == 1) {
                    stringDepartment = "CE";
                } else if (department.getSelectedItemPosition() == 2) {
                    stringDepartment = "ME";
                }

                SQLClass sqlClass = new SQLClass(Detail.this);
                sqlClass.updateData(new Employee(delete_id, edname.getText().toString(), stringDepartment, edsalary.getText().toString()));
                Toast.makeText(Detail.this, "Data Successful Update", Toast.LENGTH_SHORT).show();
                edname.setText("");
                edsalary.setText("");
                finish();

            }
            startActivity(new Intent(Detail.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
