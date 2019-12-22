package com.example.demo1.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "位置表", description = "位置表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Location implements Serializable {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "位置类型")

    private String type;

    @ApiModelProperty(value = "坐标")
    private List<BigDecimal> coordinates;
}
