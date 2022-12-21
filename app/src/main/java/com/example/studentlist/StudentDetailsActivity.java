package com.example.studentlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentlist.model.Student;

import java.util.Objects;

public class StudentDetailsActivity extends AppCompatActivity {

    Student selectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        // Title with back icon
        setTitle("Student Details");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        this.selectedStudent = (Student) getIntent().getSerializableExtra("student");

        TextView id = findViewById(R.id.studentdetails_id);
        TextView name = findViewById(R.id.studentdetails_name);
        TextView phone = findViewById(R.id.studentdetails_phone);
        TextView address = findViewById(R.id.studentdetails_address);
        CheckBox cb = findViewById(R.id.person_checked_input);
        cb.setClickable(false);
        TextView cb_text = findViewById(R.id.studentdetails_cb_text);
        ImageView img = findViewById(R.id.studentdetails_image);

        id.setText(selectedStudent.id);
        name.setText(selectedStudent.name);
        phone.setText(selectedStudent.phone);
        address.setText(selectedStudent.address);
        cb.setChecked(selectedStudent.cb);
        cb_text.setText(cb.isChecked() ? "checked" : "not checked");

        img.setImageResource(this.getResources().getIdentifier(selectedStudent.imgUrl,
                "drawable", getPackageName()));

        Button editBut = findViewById(R.id.studentdetails_save_button);
        editBut.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), EditStudentActivity.class);
            i.putExtra("student", selectedStudent);
            startActivity(i);
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), StudentRecyclerList.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}