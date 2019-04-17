package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;


/**
 * 专业管理对应
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:26
 */
public class CollegeSpecialityVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//专业编号
	private String id;
	//专业名称
	private String name;
	private String direction;
	//专业注册备案编号
	private String specialityRegid;
	//专业开设编码
	private String specialityRecordid;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialityRegid() {
		return specialityRegid;
	}

	public void setSpecialityRegid(String specialityRegid) {
		this.specialityRegid = specialityRegid;
	}

	public String getSpecialityRecordid() {
		return specialityRecordid;
	}

	public void setSpecialityRecordid(String specialityRecordid) {
		this.specialityRecordid = specialityRecordid;
	}
}
