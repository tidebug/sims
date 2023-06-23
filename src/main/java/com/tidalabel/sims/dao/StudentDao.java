package com.tidalabel.sims.dao;

import com.tidalabel.sims.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface StudentDao {
    public Student login(@Param("stu_ref")String stu_ref, @Param("stu_passwd")String stu_passwd);
}
