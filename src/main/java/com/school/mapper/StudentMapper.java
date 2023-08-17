package com.school.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.school.data.vo.v1.StudentVO;
import com.school.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "id", target = "key")
    @Mapping(target = "grades", source = "grades")
    StudentVO toVO(Student student);

    @Mapping(source = "key", target = "id")
    Student toEntity(StudentVO studentVO);

    @Mapping(source = "id", target = "key")
    List<StudentVO> toVOList(List<Student> students);

    @Mapping(source = "key", target = "id")
    List<Student> toEntityList(List<StudentVO> students);
}
