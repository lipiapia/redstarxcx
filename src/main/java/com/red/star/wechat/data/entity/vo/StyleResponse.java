package com.red.star.wechat.data.entity.vo;

import lombok.Data;

import javax.persistence.Table;

/**
 * @ClassName StyleResponse
 * @Description 返回图片模板样式entity
 * @Author xuquanyu
 * @Date 2018/8/2018:08
 * @Version 1.0
 **/
@Data
@Table(name = "sys_param")
public class StyleResponse {

    private String styleCode;

    private String styleName;
}
