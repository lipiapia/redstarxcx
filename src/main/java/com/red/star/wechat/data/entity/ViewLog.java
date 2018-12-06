package com.red.star.wechat.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Table(name = "lg_view")
public class ViewLog {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer page;

	private String eventId;

	private String openId;

	private Date optDate;

	private Date optTime;

	private String ip;

	private String network;

	private String models;

	private String longitude;

	private String latitude;

	private Integer optType;

	private Integer dataType;

	private Integer dataId;

	private String mallCode;

	private String optUnit;

	private String fromId;

	private String toId;

	private String attr;

	private Long optMillis;

	private Date createTime;

	/**
	 * 操作内容所属商场
	 */
	private String ownOmsCode;

	@Transient
	private Long stayTime;

	@Transient
	private Map<String,Object> attrMap = new HashMap<String,Object>();

    /**
     * 粉丝ID
     */
	@Transient
	private Integer fansId;

	@Transient
	private String omsCode;

}
