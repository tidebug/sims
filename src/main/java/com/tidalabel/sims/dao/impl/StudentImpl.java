package com.tidalabel.sims.dao.impl;

import com.tidalabel.sims.dao.StudentDao;
import com.tidalabel.sims.pojo.Student;

import java.util.List;
import java.util.Map;

public class StudentImpl implements StudentDao {
    @Override
    public Student login(String stu_ref, String stu_passwd) {
        return null;
    }

    @Override
    public List<Student> findByPage(int start, int rows, Map<String, String[]> condition) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }

    @Override
    public Student findStudentidAndPassword(String id, String password) {
        return null;
    }

    @Override
    public Student findStudentById(String s_id) {
        return null;
    }

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public void updateInfo(Student student) {

    }

    @Override
    public void updatePassword(String studentid, String newpassword) {

    }

    @Override
    public void addSelectCourse(String studentid, String courseid) {

    }

    @Override
    public void deleteStudentById(String studentid) {

    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        return 0;
    }

    @Override
    public void addStudentAllInfo(Student updateStudent) {

    }
}
