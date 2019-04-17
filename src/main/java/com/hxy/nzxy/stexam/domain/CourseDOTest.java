package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;


/**
 * 课程管理
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:25
 */
public class CourseDOTest    {
	private static final long serialVersionUID = 1L;
	
	//编号
	private String id;
	//名称
	private String name;
	//得分
	private String score;

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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public CourseDOTest(String id, String name, String score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}

	public CourseDOTest() {
	}
}

