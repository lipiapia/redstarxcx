package com.red.star.wechat.data.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "lg_work_error")
public class WorkErrorLog extends BaseEntity {

	private String ip;
	private String server;
	private String content;
	private String url;
	private Date time;
	private Integer adminId;
	private String adminName;
	private String memo;
}
