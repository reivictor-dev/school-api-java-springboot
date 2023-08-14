package com.school.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.school.model.Student;

public class GradeVO extends RepresentationModel<GradeVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long key;

    private double math;

    private double chemistry;

    private double science;

    private double english;

    private Student student;

    public GradeVO() {

    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getChemistry() {
        return chemistry;
    }

    public void setChemistry(double chemistry) {
        this.chemistry = chemistry;
    }

    public double getScience() {
        return science;
    }

    public void setScience(double science) {
        this.science = science;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        long temp;
        temp = Double.doubleToLongBits(math);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(chemistry);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(science);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(english);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((student == null) ? 0 : student.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GradeVO other = (GradeVO) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (Double.doubleToLongBits(math) != Double.doubleToLongBits(other.math))
            return false;
        if (Double.doubleToLongBits(chemistry) != Double.doubleToLongBits(other.chemistry))
            return false;
        if (Double.doubleToLongBits(science) != Double.doubleToLongBits(other.science))
            return false;
        if (Double.doubleToLongBits(english) != Double.doubleToLongBits(other.english))
            return false;
        if (student == null) {
            if (other.student != null)
                return false;
        } else if (!student.equals(other.student))
            return false;
        return true;
    }

}
