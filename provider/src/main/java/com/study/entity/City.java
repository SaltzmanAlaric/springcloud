package com.study.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("city")
public class City implements Serializable {

    @TableId
    private Integer id;
    private String name;
    private Integer pid;
    private Integer sort;
    @TableField(exist = false)
    private List<City> children;
}
