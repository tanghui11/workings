package com.hxy.nzxy.stexam.common.domain;

/**
 * 通用类型
 */
public class CommonDO {
	// 编号
	private String id;
	// 名称
	private String name;

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

	@Override
	public String toString() {
		return "CommonDO{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
