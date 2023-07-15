package com.school.unittests.mapper;

import java.util.ArrayList;
import java.util.List;

import com.school.data.vo.v1.StudentVO;
import com.school.model.Student;

public class MockStudent {

    public Student mockEntity() {
        return mockEntity();
    }

    public StudentVO mockVO() {
        return mockVO();
    }

    public List<Student> mockEntityList() {
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            students.add(mockEntity(i));
        }
        return students;
    }

    public List<StudentVO> mockVOList() {
        List<StudentVO> students = new ArrayList<StudentVO>();
        for (int i = 0; i < 10; i++) {
            students.add(mockVO(i));
        }
        return students;
    }

    public Student mockEntity(Integer number) {
        Student student = new Student();
        student.setId(number.longValue());
        student.setFirstName("First Name test" + number);
        student.setLastName("Last Name test" + number);
        student.setAge(number.intValue());
        student.setWeight(number.doubleValue());
        student.setHeight(number.doubleValue());

        return student;
    }

    public StudentVO mockVO(Integer number) {
        StudentVO student = new StudentVO();
        student.setKey(number.longValue());
        student.setFirstName("First Name test" + number);
        student.setLastName("Last Name test" + number);
        student.setAge(number.intValue());
        student.setWeight(number.doubleValue());
        student.setHeight(number.doubleValue());

        return student;
    }
}
