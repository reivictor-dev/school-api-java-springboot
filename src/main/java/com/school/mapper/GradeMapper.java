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

    @Mapping(source = "id", target = "key")
    GradeVO toVO(Grade grade);

    @Mapping(source = "key", target = "id")
    Grade toEntity(GradeVO gradeVO);

    @Mapping(source = "id", target = "key")
    List<GradeVO> toVOList(List<Grade> grades);

    @Mapping(source = "key", target = "id")
    List<Grade> toEntityList(List<GradeVO> grades);
}
