package com.example.studentlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentlist.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        setTitle("Student details");
        Student selectedStudent = (Student) getIntent().getSerializableExtra("student");

        TextView id = findViewById(R.id.studentdetails_id);
        TextView name = findViewById(R.id.studentdetails_name);
        TextView phone = findViewById(R.id.studentdetails_phone);
        TextView address = findViewById(R.id.studentdetails_address);
        CheckBox cb = findViewById(R.id.studentdetails_cb);
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
    }
}