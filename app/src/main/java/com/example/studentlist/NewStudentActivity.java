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

public class NewStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        // Hide delete button
        Button deleteButton = findViewById(R.id.edit_studentdetails_delete_button);
        deleteButton.setVisibility(View.GONE);

        // Title with back icon
        setTitle("New Student");

        TextView id = findViewById(R.id.edit_studentdetails_id);
        TextView name = findViewById(R.id.edit_studentdetails_name);
        TextView phone = findViewById(R.id.edit_studentdetails_phone);
        TextView address = findViewById(R.id.edit_studentdetails_address);
        TextView cb_text = findViewById(R.id.edit_studentdetails_cb_text);
        CheckBox cb = findViewById(R.id.edit_person_checked_input);
        cb.setOnClickListener(view -> cb_text.setText(cb.isChecked() ? "checked" : "not checked"));

        ImageView img = findViewById(R.id.studentdetails_image);

        img.setImageResource(this.getResources().getIdentifier("@drawable/avatar_icon",
                "drawable", getPackageName()));

        // save details logic
        Button saveButton = findViewById(R.id.edit_studentdetails_save_button);
        saveButton.setOnClickListener(view -> {
            Student newStudent = new Student(name.getText().toString(),
                    id.getText().toString(), "@drawable/avatar_icon", cb.isChecked(),
                    phone.getText().toString(), address.getText().toString());
            try {
                // create new
                Model.getInstance().addStudent(newStudent);
                Snackbar snackbar = Snackbar
                        .make(view, "New Student Created!", Snackbar.LENGTH_SHORT);
                snackbar.show();
            } catch (Exception e) {
                Snackbar snackbar = Snackbar
                        .make(view, "Error: " + e.getMessage(), Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        // Cancel details logic
        Button cancelButton = findViewById(R.id.edit_studentdetails_cancel_button);
        cancelButton.setOnClickListener(view -> navigateToStudentList());

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