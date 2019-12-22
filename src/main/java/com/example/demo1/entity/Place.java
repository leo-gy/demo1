package com.example.demo1.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel(value = "地点表",description = "地点表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")

    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "位置")
    private Location loc;

}
