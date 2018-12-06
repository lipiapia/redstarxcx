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
 * @Description: 正常阅读日志（双十一）
 * @Author: AMGuo
 * @CreateDate: 2018/10/16 上午11:33
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stat_page_read")
@ApiModel(value = "StatPageRead", description = "正常阅读日志（双十一）")
public class StatPageRead {

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

    @ApiModelProperty(value = "阅读者openId")
    private String openId;

    @ApiModelProperty(value = "阅读者unionId")
    private String unionId;

    @ApiModelProperty(value = "阅读者vip_open_id")
    private String vipOpenId;

    @ApiModelProperty(value = "阅读者fansId", example = "0")
    private Integer fansId;

    @ApiModelProperty(value = "阅读者ip")
    private String ip;

    @ApiModelProperty(value = "阅读的url")
    private String url;

    public StatPageRead(Date createTime, Date updateTime, String openId, String vipOpenId, Integer fansId, String ip, String url) {
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.openId = openId;
        this.vipOpenId = vipOpenId;
        this.fansId = fansId;
        this.ip = ip;
        this.url = url;
    }
}
