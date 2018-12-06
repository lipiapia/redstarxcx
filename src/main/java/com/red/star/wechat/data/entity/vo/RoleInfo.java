package com.red.star.wechat.data.entity.vo;

import com.red.star.wechat.data.entity.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description: 角色管理
 * @Author: lijing
 * @Date: 2018/12/05
 */
@Data
@Table(name = "t_role")
public class RoleInfo extends BaseEntity{

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String enumValue;
    /**
     * 状态 0:启用 1:禁用
     */
    private Integer status;
}
