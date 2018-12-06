package com.red.star.wechat.data.entity.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: macalline-work-server
 * @Package: com.red.star.macalline.data.entity.bo
 * @Description:
 * @Author: AMGuo
 * @CreateDate: 2018/10/18 下午4:23
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PageLogBo", description = "日志（包含：小程序码扫描日志、转发阅读日志、正常访问日志）（双十一）")
public class PageLogBo {

    @ApiModelProperty(value = "日志类型（1、正常访问日志.2、转发阅读日志。3、小程序码扫描日志。）", example = "1", required = true)
    private Integer type;

    @ApiModelProperty(value = "uuid（小程序码扫描日志）")
    private String uuid;

    @ApiModelProperty(value = "转发人openId（转发阅读日志）")
    private String fromOpenId;

    @ApiModelProperty(value = "转发人unionId（转发阅读日志）")
    private String fromUnionId;

    @ApiModelProperty(value = "源转发人openId-若为第一次转发，则和fromOpenId一致（转发阅读日志）")
    private String sourceOpenId;

    @ApiModelProperty(value = "源转发人unionid-若为第一次转发，则和fromUnionId一致（转发阅读日志）")
    private String sourceUnionId;

    @ApiModelProperty(value = "转发层级（转发阅读日志）", example = "1")
    private Integer layer;

    @ApiModelProperty(value = "页面url")
    private String url;
}
