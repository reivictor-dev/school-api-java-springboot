package com.school.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "math", "chemistry", "science", "english" })
public class GradeVO extends RepresentationModel<GradeVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long key;

    private Double math;

    private Double chemistry;

    private Double science;

    private Double english;

    @JsonProperty("studentId")
    private Long studentId;

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

    public Double getMath() {
        return math;
    }

    public void setMath(Double math) {
        this.math = math;
    }

    public Double getChemistry() {
        return chemistry;
    }

    public void setChemistry(Double chemistry) {
        this.chemistry = chemistry;
    }

    public Double getScience() {
        return science;
    }

    public void setScience(Double science) {
        this.science = science;
    }

    public Double getEnglish() {
        return english;
    }

    public void setEnglish(Double english) {
        this.english = english;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + ((math == null) ? 0 : math.hashCode());
        result = prime * result + ((chemistry == null) ? 0 : chemistry.hashCode());
        result = prime * result + ((science == null) ? 0 : science.hashCode());
        result = prime * result + ((english == null) ? 0 : english.hashCode());
        result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
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
        if (math == null) {
            if (other.math != null)
                return false;
        } else if (!math.equals(other.math))
            return false;
        if (chemistry == null) {
            if (other.chemistry != null)
                return false;
        } else if (!chemistry.equals(other.chemistry))
            return false;
        if (science == null) {
            if (other.science != null)
                return false;
        } else if (!science.equals(other.science))
            return false;
        if (english == null) {
            if (other.english != null)
                return false;
        } else if (!english.equals(other.english))
            return false;
        if (studentId == null) {
            if (other.studentId != null)
                return false;
        } else if (!studentId.equals(other.studentId))
            return false;
        return true;
    }

}
