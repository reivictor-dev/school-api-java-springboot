package com.school.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.school.data.vo.v1.GradeVO;
import com.school.model.Grade;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    @Mapping(target = "key", source = "id")
    @Mapping(target = "studentId", source = "student.id")
    GradeVO toVO(Grade grade);

    @Mapping(target = "id", source = "key")
    @Mapping(target = "student.id", source = "studentId")
    Grade toEntity(GradeVO gradeVO);

    @Mapping(target = "key", source = "id")
    List<GradeVO> toVOList(List<Grade> grades);

    @Mapping(target = "id", source = "key")
    List<Grade> toEntityList(List<GradeVO> grades);
}
