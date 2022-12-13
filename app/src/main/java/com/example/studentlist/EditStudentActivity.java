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

import com.example.studentlist.model.Model;
import com.example.studentlist.model.Student;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

public class EditStudentActivity extends AppCompatActivity {
    Student selectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Object givenStudent = getIntent().getSerializableExtra("student");
        this.selectedStudent = givenStudent instanceof Student ? ((Student) givenStudent) : null;

        TextView id = findViewById(R.id.edit_studentdetails_id);
        TextView name = findViewById(R.id.edit_studentdetails_name);
        TextView phone = findViewById(R.id.edit_studentdetails_phone);
        TextView address = findViewById(R.id.edit_studentdetails_address);
        TextView cb_text = findViewById(R.id.edit_studentdetails_cb_text);
        CheckBox cb = findViewById(R.id.edit_person_checked_input);
        ImageView img = findViewById(R.id.studentdetails_image);

        String originalId = null;

        // if given a student - means we need to edit. otherwise - new student
        if (this.selectedStudent != null) {
            // populate fields
            originalId = this.selectedStudent.id;
            id.setText(this.selectedStudent.id);
            name.setText(this.selectedStudent.name);
            phone.setText(this.selectedStudent.phone);
            address.setText(this.selectedStudent.address);
            img.setImageResource(this.getResources().getIdentifier(this.selectedStudent.imgUrl,
                    "drawable", getPackageName()));

            cb.setChecked(this.selectedStudent.cb);
            cb_text.setText(this.selectedStudent.cb ? "checked" : "not checked");
        }

        // save details logic
        Button saveButton = findViewById(R.id.edit_studentdetails_save_button);
        String finalOriginalId = originalId;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student newStudent = new Student(name.getText().toString(),
                        id.getText().toString(), "", cb.isChecked(),
                        phone.getText().toString(), address.getText().toString());

                if (finalOriginalId != null){
                    Model.getInstance().updateStudentDetails(finalOriginalId, newStudent);
                    Snackbar snackbar = Snackbar
                            .make(view, "Saved Details!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
                else {
                    // create new
                    Model.getInstance().addStudent(newStudent);
                    Snackbar snackbar = Snackbar
                            .make(view, "New Student Created!", Snackbar.LENGTH_SHORT);
                    snackbar.show();

                }
            }
        });


        // Title with back icon
        setTitle(this.selectedStudent != null ?  "Edit Student" : "New Student");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), StudentRecyclerList.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}