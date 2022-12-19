package com.example.studentlist.model;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model instance = new Model();
    List<Student> data = new LinkedList<>();

    private Model() {
        for (int i = 0; i < 20; i++) {
            try {
                addStudent(new Student("name" + i, "" + i, "@drawable/avatar_icon", false,
                        "0534284962", "Nofah 35, Tel aviv"));
            }
            catch (Exception e) {continue;}
        }
    }

    public static Model getInstance() {
        return instance;
    }

    public List<Student> getAllStudents() {
        return data;
    }

    public void addStudent(Student st) throws Exception {
        if (data.stream().noneMatch(student -> student.id.equals(st.id))) {
            data.add(st);
        } else {
            throw new Exception("Id already exists");
        }
    }

    public boolean deleteStudent(String studentId) {
        return data.removeIf(student -> student.id.equals(studentId));
    }

    public void updateStudentDetails(String originId, Student newStudent) throws Exception {
        // if ID is not changed OR the new id does not exist already --> you can update originId
        if (newStudent.id.equals(originId)
                || data.stream().noneMatch(student -> student.id.equals(newStudent.id))) {
            Student foundStudent = data.stream().filter(student -> student.id.equals(originId))
                    .findFirst().get();

            foundStudent.setId(newStudent.id);
            foundStudent.setName(newStudent.name);
            foundStudent.setAddress(newStudent.address);
            foundStudent.setPhone(newStudent.phone);
            foundStudent.setCb(newStudent.cb);
        } else {
            throw new Exception("Can't save, id already exists: " + newStudent.id);
        }
    }
}
