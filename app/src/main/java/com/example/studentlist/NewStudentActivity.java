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

public class NewStudentActivity extends AppCompatActivity implements StudentAction{

    private TextView id;
    private TextView name;
    private TextView phone;
    private TextView address;
    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);

        // Title with back icon
        setTitle("New Student");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Hide delete button
        Button deleteButton = findViewById(R.id.edit_studentdetails_delete_button);
        deleteButton.setVisibility(View.GONE);

        id = findViewById(R.id.edit_studentdetails_id);
        name = findViewById(R.id.edit_studentdetails_name);
        phone = findViewById(R.id.edit_studentdetails_phone);
        address = findViewById(R.id.edit_studentdetails_address);
        TextView cb_text = findViewById(R.id.edit_studentdetails_cb_text);
        cb = findViewById(R.id.edit_person_checked_input);
        cb.setOnClickListener(view -> cb_text.setText(cb.isChecked() ? "checked" : "not checked"));
        ImageView img = findViewById(R.id.studentdetails_image);
        img.setImageResource(this.getResources().getIdentifier("@drawable/avatar_icon",
                "drawable", getPackageName()));

        // save details logic
        Button saveButton = findViewById(R.id.edit_studentdetails_save_button);
        saveButton.setOnClickListener(this::onSave);

        // Cancel details logic
        Button cancelButton = findViewById(R.id.edit_studentdetails_cancel_button);
        cancelButton.setOnClickListener(view -> onCancel());
    }

    @Override
    public void onCancel() {
        Intent i = new Intent(this, StudentRecyclerList.class);
        startActivity(i);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), StudentRecyclerList.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    @Override
    public void onSave(View view) {
        Student newStudent = new Student(name.getText().toString(),
                id.getText().toString(), "@drawable/avatar_icon", cb.isChecked(),
                phone.getText().toString(), address.getText().toString());
        try {
            // create new
            Model.getInstance().addStudent(newStudent);
            Snackbar.make(view, "New Student Created!", Snackbar.LENGTH_SHORT).show();
        } catch (Exception e) {
            Snackbar.make(view, "Error: " + e.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    }
}