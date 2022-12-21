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

import java.util.Objects;

public class EditStudentActivity extends AppCompatActivity {

    private Student selectedStudent;
    private TextView id;
    private TextView name;
    private TextView phone;
    private TextView address;
    private CheckBox cb;
    private String originalId;
    private TextView cb_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        // Title with back icon
        setTitle("Edit Student");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        Object givenStudent = getIntent().getSerializableExtra("student");
        this.selectedStudent = givenStudent instanceof Student ? ((Student) givenStudent) : null;

        id = findViewById(R.id.edit_studentdetails_id);
        name = findViewById(R.id.edit_studentdetails_name);
        phone = findViewById(R.id.edit_studentdetails_phone);
        address = findViewById(R.id.edit_studentdetails_address);
        cb_text = findViewById(R.id.edit_studentdetails_cb_text);
        cb = findViewById(R.id.edit_person_checked_input);
        cb.setOnClickListener(view -> {
            setText(cb);
        });

        // populate fields
        originalId = this.selectedStudent.id;
        id.setText(this.selectedStudent.id);
        name.setText(this.selectedStudent.name);
        phone.setText(this.selectedStudent.phone);
        address.setText(this.selectedStudent.address);
        cb.setChecked(this.selectedStudent.cb);
        setText(cb);

        ImageView img = findViewById(R.id.studentdetails_image);
        img.setImageResource(this.getResources().getIdentifier(this.selectedStudent.imgUrl,
                "drawable", getPackageName()));

        Button deleteButton = findViewById(R.id.edit_studentdetails_delete_button);
        deleteButton.setVisibility((originalId != null) ? View.VISIBLE : View.GONE);

        // save details logic
        Button saveButton = findViewById(R.id.edit_studentdetails_save_button);
        saveButton.setOnClickListener(this::onSave);

        // Delete details logic
        deleteButton.setOnClickListener(view -> {
            Model.getInstance().deleteStudent(this.selectedStudent.id);
            navigateToStudentList();
        });

        // Cancel details logic
        Button cancelButton = findViewById(R.id.edit_studentdetails_cancel_button);
        cancelButton.setOnClickListener(view -> navigateToStudentList());
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

    private void onSave(View view) {
        Student updatedStudent = new Student(name.getText().toString(),
                id.getText().toString(), "@drawable/avatar_icon", cb.isChecked(),
                phone.getText().toString(), address.getText().toString());
        try {
            Model.getInstance().updateStudentDetails(originalId, updatedStudent);
            Snackbar.make(view, "Saved Details!", Snackbar.LENGTH_SHORT).show();
            originalId = id.getText().toString();
        } catch (Exception e) {
            Snackbar.make(view, "Error: " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void setText(CheckBox cb) {
        cb_text.setText(cb.isChecked() ? "checked" : "not checked");
    }
}