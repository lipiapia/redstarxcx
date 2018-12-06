package com.red.star.wechat.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @ProjectName: macalline-work-server
 * @Package: com.red.star.macalline.data.entity
 * @Description: 小程序码扫描日志（双十一）
 * @Author: AMGuo
 * @CreateDate: 2018/10/16 上午11:33
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stat_page_code_scan")
@ApiModel(value = "StatCodeScan", description = "小程序码扫描日志（双十一）")
public class StatPageCodeScan {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", example = "0")
    private Long id;

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
    private Date createTime;

    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    private Date updateTime;

    @ApiModelProperty(value = "扫描人fansId", example = "0")
    private Integer fansId;

    @ApiModelProperty(value = "扫描人vipId", example = "0")
    private Integer vipId;

    @ApiModelProperty(value = "二维码id", example = "0")
    private Integer relationId;

    @ApiModelProperty(value = "二维码所属商场code")
    private String mallCode;

    @ApiModelProperty(value = "二维码对应的渠道", example = "0")
    private Integer channelType;

    @ApiModelProperty(value = "当前扫描人的ip")
    private String ip;

    @ApiModelProperty(value = "扫描二维码跳转的地址")
    private String url;

    @ApiModelProperty(value = "扫描人openid")
    private String openId;

    @ApiModelProperty(value = "二维码uuid")
    private String uuid;

    @ApiModelProperty(value = "扫描人vipOpenId")
    private String vipOpenId;

    @ApiModelProperty(value = "扫描人unionId")
    private String unionId;

    public StatPageCodeScan(Date createTime, Date updateTime, Integer fansId, String ip, String openId, String uuid, String vipOpenId) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.fansId = fansId;
        this.ip = ip;
        this.openId = openId;
        this.uuid = uuid;
        this.vipOpenId = vipOpenId;
    }
}
