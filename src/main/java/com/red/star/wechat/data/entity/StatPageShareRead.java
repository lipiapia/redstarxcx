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
 * @Description: 转发阅读日志（双十一）
 * @Author: AMGuo
 * @CreateDate: 2018/10/16 上午11:33
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stat_page_share_read")
@ApiModel(value = "StatPageShareRead", description = "转发阅读日志（双十一）")
public class StatPageShareRead {

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

    @ApiModelProperty(value = "分享者openid")
    private String shareOpenId;

    @ApiModelProperty(value = "分享者unionid")
    private String shareUnionId;

    @ApiModelProperty(value = "阅读者openid")
    private String readOpenId;

    @ApiModelProperty(value = "阅读者vipOpenId")
    private String readVipOpenId;

    @ApiModelProperty(value = "阅读者fansId", example = "0")
    private Integer readFansId;

    @ApiModelProperty(value = "阅读者unionId")
    private String readUnionId;

    @ApiModelProperty(value = "源分享者openId-若为第一次转发，则和share_open_id一致")
    private String sourceOpenId;

    @ApiModelProperty(value = "源分享者unionid-若为第一次转发，则和share_union_id一致")
    private String sourceUnionId;

    @ApiModelProperty(value = "阅读者ip")
    private String ip;

    @ApiModelProperty(value = "阅读的url")
    private String url;

    @ApiModelProperty(value = "转发层级", example = "1")
    private Integer layer;

    public StatPageShareRead(Date createTime, Date updateTime, String shareOpenId, String shareUnionId, String readOpenId, Integer readFansId, String readVipOpenId, String sourceOpenId, String sourceUnionId, String ip, String url, Integer layer) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.shareOpenId = shareOpenId;
        this.shareUnionId = shareUnionId;
        this.readOpenId = readOpenId;
        this.readFansId = readFansId;
        this.readVipOpenId = readVipOpenId;
        this.sourceOpenId = sourceOpenId;
        this.sourceUnionId = sourceUnionId;
        this.ip = ip;
        this.url = url;
        this.layer = layer;
    }
}
