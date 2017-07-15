package com.example.acespritech_umesh.umeshproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
 * Created by acespritech-umesh on 14/7/17.
 */
public class CreateForm extends AppCompatActivity {
    Spinner department;
    String stringDepartment = "";
    EditText salary, name;
    ArrayList<String> arrayList;
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_form);
        setTitle("Create Data");

        name = (EditText) findViewById(R.id.name);
        department = (Spinner) findViewById(R.id.department);
        salary = (EditText) findViewById(R.id.salary);
        submit = (TextView) findViewById(R.id.create);
        arrayList = new ArrayList<>();
        arrayList.add("IT");
        arrayList.add("CE");
        arrayList.add("ME");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateForm.this, android.R.layout.simple_list_item_1, arrayList);
        department.setAdapter(adapter);
        Log.d("<>OnCreate", department.getSelectedItem().toString());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")) {
                    name.setFocusable(true);
                    name.setError("Name is Required");
                } else if (salary.getText().toString().equals("")) {
                    salary.setFocusable(true);
                    salary.setError("Salary is Required");
                } else {
                    if (department.getSelectedItemPosition() == 0) {
                        stringDepartment = "IT";
                        Log.d("<>Index", "0");
                    } else if (department.getSelectedItemPosition() == 1) {
                        stringDepartment = "CE";
                        Log.d("<>Index", "1");
                    } else if (department.getSelectedItemPosition() == 2) {
                        stringDepartment = "ME";
                        Log.d("<>Index", "2");
                    }
                    Log.d("<>Bspinner", department.getSelectedItem().toString());

                    SQLClass sqlClass = new SQLClass(CreateForm.this);
                    Employee employee = new Employee();
                    employee.setName(name.getText().toString());
                    employee.setDepartment(stringDepartment);
                    employee.setSalary(salary.getText().toString());
                    sqlClass.Createdata(employee);
                    Toast.makeText(CreateForm.this, "Data Successful Create", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    salary.setText("");
                    startActivity(new Intent(CreateForm.this, MainActivity.class));
                }

            }
        });

    }
}
