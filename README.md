# 一、项目介绍

## sism(student information management system)



# 二、数据库实现

```mysql
CREATE DATABASE `sims` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */
CREATE TABLE `t_student` (
  `stu_ref` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stu_passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `stu_age` varchar(255) DEFAULT NULL,
  `stu_id` varchar(255) DEFAULT NULL,
  `stu_sex` varchar(255) DEFAULT NULL,
  `stu_phonenumber` varchar(255) DEFAULT NULL,
  `stu_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`stu_ref`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```



# 三、部署

### 推荐使用Tomcat9.0以上版本，把.war包直接丢进webapp下，进入bin目录运行startup.bat 

### 浏览器地址为 http://localhost:8080/sims-0.0.1/
演示地址 https://donnhao.lanzouk.com/i0CZG0zp44uj
