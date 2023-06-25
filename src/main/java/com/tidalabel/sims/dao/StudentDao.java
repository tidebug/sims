package com.tidalabel.sims.dao;

import com.tidalabel.sims.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentDao {
    public Student login(@Param("stu_ref")String stu_ref, @Param("stu_passwd")String stu_passwd);
    List<Student> findByPage(int start, int rows, Map<String, String[]> condition);

    List<Student> findAll();

    Student findStudentidAndPassword(String id, String password);

    Student findStudentById(String s_id);

    void addStudent(Student student);

    void updateInfo(Student student);

    void updatePassword(String studentid, String newpassword);


    void addSelectCourse(String studentid, String courseid);

    void deleteStudentById(String studentid);

    int findTotalCount(Map<String, String[]> condition);

    void addStudentAllInfo(Student updateStudent);


}
