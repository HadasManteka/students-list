package com.example.studentlist.model;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model instance = new Model();
    List<Student> data = new LinkedList<>();

    private Model() {
        for (int i=0; i<20; i++) {
            addStudent(new Student("name" + i, "" + i, "@drawable/avatar_icon", false,
                    "0534284962", "Rishon"));
        }
    }

    public static Model getInstance() {
        return instance;
    }

    public List<Student> getAllStudents() {
        return data;
    }

    public void addStudent (Student st) {
        data.add(st);
    }
}
