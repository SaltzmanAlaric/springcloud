package com.study.bean;

import com.study.bean.po.SchoolPO;
import com.study.bean.po.StudentPO;
import com.study.bean.vo.SchoolStudentVO;
import com.study.bean.vo.StudentVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface Converter {

    Converter INSTANCE = Mappers.getMapper(Converter.class);

    @Mappings({
            @Mapping(source = "name", target = "studentName"),
            @Mapping(source = "age", target = "studentAge")
    })
    StudentVO po2Vo(StudentPO studentPO);
    List<StudentVO> poList2VoList(List<StudentPO> studentPO);

    @Mappings({
            @Mapping(source = "studentName", target = "name"),
            @Mapping(source = "studentAge", target = "age")
    })
    StudentPO vo2Po(StudentVO studentVO);

    @Mappings({
            @Mapping(source = "schoolPO.name", target = "schoolName"),
            @Mapping(source = "studentPO.name", target = "studentName")
    })
    SchoolStudentVO mergeVo(SchoolPO schoolPO, StudentPO studentPO);
}
