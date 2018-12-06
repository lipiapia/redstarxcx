package com.red.star.wechat.data.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author nofish.yan@gmail.com
 * @date 2018/7/12.
 * API客户实体端
 */
@Data
@Table(name = "t_api_client")
public class ApiClient extends BaseEntity {

    /**
     * 接口调用方appId
     */
    private String appId;

    /**
     * 接口调用方appSecret
     */
    private String appSecret;

    /**
     * 可用状态 0 可用 1删除
     */
    private Integer status;

}
