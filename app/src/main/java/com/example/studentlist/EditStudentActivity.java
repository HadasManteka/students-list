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
        cb.setOnClickListener(view -> {
            cb_text.setText(cb.isChecked() ? "checked" : "not checked");
        });

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
            cb.setChecked(this.selectedStudent.cb);
            cb_text.setText(this.selectedStudent.cb ? "checked" : "not checked");
            img.setImageResource(this.getResources().getIdentifier(this.selectedStudent.imgUrl,
                    "drawable", getPackageName()));
        } else {
            img.setImageResource(this.getResources().getIdentifier("@drawable/avatar_icon",
                    "drawable", getPackageName()));
        }


        String finalOriginalId = originalId;

        // Show delete button only on edit student
        Button deleteButton = findViewById(R.id.edit_studentdetails_delete_button);
        deleteButton.setVisibility((finalOriginalId != null) ? View.VISIBLE : View.GONE);

        // save details logic
        Button saveButton = findViewById(R.id.edit_studentdetails_save_button);
        saveButton.setOnClickListener(view -> {
            Student newStudent = new Student(name.getText().toString(),
                    id.getText().toString(), "@drawable/avatar_icon", cb.isChecked(),
                    phone.getText().toString(), address.getText().toString());
            try {
                if (finalOriginalId != null) {
                    Model.getInstance().updateStudentDetails(finalOriginalId, newStudent);
                    Snackbar snackbar = Snackbar
                            .make(view, "Saved Details!", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    // create new
                    Model.getInstance().addStudent(newStudent);
                    Snackbar snackbar = Snackbar
                            .make(view, "New Student Created!", Snackbar.LENGTH_SHORT);
                    snackbar.show();

                }
            } catch(Exception e) {
                Snackbar snackbar = Snackbar
                        .make(view, "Error: " + e.getMessage(), Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        // Delete details logic
        deleteButton.setOnClickListener(view -> {
            if (finalOriginalId != null) {
                Model.getInstance().deleteStudent(this.selectedStudent.id);
                navigateToStudentList();
            } else {
                // create new
                Snackbar snackbar = Snackbar
                        .make(view, "Student does not exist!", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        // Cancel details logic
        Button cancelButton = findViewById(R.id.edit_studentdetails_cancel_button);
        cancelButton.setOnClickListener(view -> navigateToStudentList());

        // Title with back icon
        setTitle(this.selectedStudent != null ? "Edit Student" : "New Student");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void navigateToStudentList() {
        Intent i = new Intent(this, StudentRecyclerList.class);
        startActivity(i);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), StudentRecyclerList.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}