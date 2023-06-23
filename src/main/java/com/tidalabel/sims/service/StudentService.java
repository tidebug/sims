package com.tidalabel.sims.service;

import com.tidalabel.sims.dao.StudentDao;
import com.tidalabel.sims.pojo.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentDao studentDao;

    public Student login(String stu_ref, String stu_passwd) {
        return studentDao.login(stu_ref, stu_passwd);
    }
}
