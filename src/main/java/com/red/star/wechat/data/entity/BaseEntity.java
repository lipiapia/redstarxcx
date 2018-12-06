package com.red.star.wechat.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by xulonglong on 2017/6/15.
 */
@Data
public class BaseEntity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", example = "0")
    private Integer id;

    @Transient
    @JsonIgnore
    private Integer page = 1;

    @Transient
    @JsonIgnore
    private Integer rows = 10;

    /**
     * 起始行,从o开始
     */
    @Transient
    @JsonIgnore
    private int start;

    /**
     * 当前需求行数
     */
    @Transient
    @JsonIgnore
    private int length;

    public Integer getPage() {
        if (this.start != 0) {
            return this.getStart() / this.getLength() + 1;
        } else {
            return 1;
        }
    }

    public Integer getRows() {
        return this.getLength();
    }

    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    protected Date createTime;

    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    protected Date updateTime;

}
