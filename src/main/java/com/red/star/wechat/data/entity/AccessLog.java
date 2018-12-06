package com.red.star.wechat.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "lg_access")
public class AccessLog {
    /**
     * 主键
     */
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 服务器IP
     */
    private String ip;
    /**
     * 服务器应用
     */
    private String service;

    /**
     * 客户端请求IP
     */
    private String cIp;
    /**
     * 请求user-agent
     */
    private String ua;
    /**
     * 请求地址
     */
    private String url;

    /**
     * 入库时间
     */
    private Date time;
    /**
     * 请求详情
     */
    private String memo;

    private Integer fansId;

    private String openId;

    private Integer userType;

    private String vipOpenId;


    @Transient
    @JsonIgnore
    private Integer page = 1;

    @Transient
    @JsonIgnore
    private Integer rows = 10;

    //起始行,从o开始
    @Transient
    @JsonIgnore
    private int start;

    //当前需求行数
    @Transient
    @JsonIgnore
    private int length;

    @Transient
    private Date startTime;

    @Transient
    private Date endTime;


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


}
