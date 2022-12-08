package com.example.studentlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.studentlist.model.Model;
import com.example.studentlist.model.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        data = Model.getInstance().getAllStudents();
        ListView list = findViewById(R.id.studentlist_list);
        list.setAdapter(new StudentListAdapter());
    }

    class StudentListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {
            return view;
        }
    }
}