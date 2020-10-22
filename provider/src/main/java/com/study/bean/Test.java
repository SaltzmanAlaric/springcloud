package com.study.bean;

import com.study.bean.po.StudentPO;
import com.study.bean.vo.StudentVO;
import com.study.fegin.provider.entity.vo.Converter;

public class Test {

    public static void main(String[] args) {
        StudentPO studentPO = new StudentPO();
        studentPO.setId(10);
        studentPO.setName("test");
        studentPO.setAge(24);
        studentPO.setClassName("教室名");
        StudentVO studentVO = Converter.INSTANCE.po2Vo(studentPO);
        // StudentVO(id=10, studentName=test, studentAge=24, schoolName=null)
        System.out.println(studentVO);

        studentVO.setId(9);
        studentVO.setStudentName("tom");
        studentVO.setSchoolName("daXue");
        studentVO.setStudentAge(18);
        StudentPO studentPO1 = Converter.INSTANCE.vo2Po(studentVO);
        System.out.println(studentPO1);

    }
}
