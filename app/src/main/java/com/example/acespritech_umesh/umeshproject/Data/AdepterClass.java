package com.example.acespritech_umesh.umeshproject.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acespritech_umesh.umeshproject.R;

import java.util.ArrayList;

/**
 * Created by acespritech-umesh on 14/7/17.
 */

public class AdepterClass extends BaseAdapter {
    Context context;
    ArrayList<Employee> employees;
    LayoutInflater layoutInflater;

    public AdepterClass(Context context, ArrayList<Employee> employees) {
        this.context = context;
        this.employees = employees;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView name, department, salary,DataFound;
        int siz=employees.size();
        if (siz==0)
        {

        }
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adepter, parent, false);
        }
        name = (TextView) convertView.findViewById(R.id.adeptername);
        department = (TextView) convertView.findViewById(R.id.adepter_department);
        salary = (TextView) convertView.findViewById(R.id.adepetr_salary);
        Employee employee = (Employee) getItem(position);

        name.setText(employee.getName());
        department.setText(employee.getDepartment());
        salary.setText(employee.getSalary() + "");

        notifyDataSetChanged();
        return convertView;
    }
}
