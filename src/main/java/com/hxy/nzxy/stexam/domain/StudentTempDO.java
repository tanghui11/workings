package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生基本信息临时表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentTempDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//类别
	private String type;
	//准考证号
	private String ksZkz;
	//姓名
	private String ksXm;
	//英文名
	private String ksExm;
	//性别
	private String ksXb;
	//户籍代码
	private String hjDm;
	//面貌代码
	private String mmDm;
	//民族代码
	private String mzDm;
	//学历代码
	private String xlDm;
	//职业代码
	private String zhiyDm;
	//专业代码
	private String zyDm;
	//第一专业
	private String firstZy;
	//集体代码
	private String jtdmDm;
	//身份证号
	private String ksSfz;
	//出生日期
	private Date ksBirthda;
	//联系电话
	private String ksPhone;
	//邮编
	private String ksZip;
	//联系地址
	private String ksAddress;
	//报名市县
	private String ksBmxs;
	//报名时间
	private Date ksBmsj;
	//
	private String ksZdyxx;
	//停考时间
	private Date ksTksj;
	//延期毕业
	private String ksYqby;
	//考生区县
	private String ksQx;
	//市县代码
	private String sxDm;
	//修改标志
	private String modified;
	//旧准考证号
	private String ksOldzkz;
	//专业名称
	private String zyMc;
	//密码
	private String mm;
	//手机1
	private String sj1;
	//手机2
	private String sj2;
	//电话
	private String zzdh;
	//预报名号
	private String ybmh;
	//报名点代码
	private String bmddm;
	//工作单位
	private String gzdw;
	//电子邮件
	private String email;
	//
	private String msdm;
	//
	private String hkszd;
	//
	private String gzdwszd;
	//
	private String flag;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//0无效,1有效
	private Integer enabledFlag;

	/**
	 * 设置：类别
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：类别
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：准考证号
	 */
	public void setKsZkz(String ksZkz) {
		this.ksZkz = ksZkz;
	}
	/**
	 * 获取：准考证号
	 */
	public String getKsZkz() {
		return ksZkz;
	}
	/**
	 * 设置：姓名
	 */
	public void setKsXm(String ksXm) {
		this.ksXm = ksXm;
	}
	/**
	 * 获取：姓名
	 */
	public String getKsXm() {
		return ksXm;
	}
	/**
	 * 设置：英文名
	 */
	public void setKsExm(String ksExm) {
		this.ksExm = ksExm;
	}
	/**
	 * 获取：英文名
	 */
	public String getKsExm() {
		return ksExm;
	}
	/**
	 * 设置：性别
	 */
	public void setKsXb(String ksXb) {
		this.ksXb = ksXb;
	}
	/**
	 * 获取：性别
	 */
	public String getKsXb() {
		return ksXb;
	}
	/**
	 * 设置：户籍代码
	 */
	public void setHjDm(String hjDm) {
		this.hjDm = hjDm;
	}
	/**
	 * 获取：户籍代码
	 */
	public String getHjDm() {
		return hjDm;
	}
	/**
	 * 设置：面貌代码
	 */
	public void setMmDm(String mmDm) {
		this.mmDm = mmDm;
	}
	/**
	 * 获取：面貌代码
	 */
	public String getMmDm() {
		return mmDm;
	}
	/**
	 * 设置：民族代码
	 */
	public void setMzDm(String mzDm) {
		this.mzDm = mzDm;
	}
	/**
	 * 获取：民族代码
	 */
	public String getMzDm() {
		return mzDm;
	}
	/**
	 * 设置：学历代码
	 */
	public void setXlDm(String xlDm) {
		this.xlDm = xlDm;
	}
	/**
	 * 获取：学历代码
	 */
	public String getXlDm() {
		return xlDm;
	}
	/**
	 * 设置：职业代码
	 */
	public void setZhiyDm(String zhiyDm) {
		this.zhiyDm = zhiyDm;
	}
	/**
	 * 获取：职业代码
	 */
	public String getZhiyDm() {
		return zhiyDm;
	}
	/**
	 * 设置：专业代码
	 */
	public void setZyDm(String zyDm) {
		this.zyDm = zyDm;
	}
	/**
	 * 获取：专业代码
	 */
	public String getZyDm() {
		return zyDm;
	}
	/**
	 * 设置：第一专业
	 */
	public void setFirstZy(String firstZy) {
		this.firstZy = firstZy;
	}
	/**
	 * 获取：第一专业
	 */
	public String getFirstZy() {
		return firstZy;
	}
	/**
	 * 设置：集体代码
	 */
	public void setJtdmDm(String jtdmDm) {
		this.jtdmDm = jtdmDm;
	}
	/**
	 * 获取：集体代码
	 */
	public String getJtdmDm() {
		return jtdmDm;
	}
	/**
	 * 设置：身份证号
	 */
	public void setKsSfz(String ksSfz) {
		this.ksSfz = ksSfz;
	}
	/**
	 * 获取：身份证号
	 */
	public String getKsSfz() {
		return ksSfz;
	}
	/**
	 * 设置：出生日期
	 */
	public void setKsBirthda(Date ksBirthda) {
		this.ksBirthda = ksBirthda;
	}
	/**
	 * 获取：出生日期
	 */
	public Date getKsBirthda() {
		return ksBirthda;
	}
	/**
	 * 设置：联系电话
	 */
	public void setKsPhone(String ksPhone) {
		this.ksPhone = ksPhone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getKsPhone() {
		return ksPhone;
	}
	/**
	 * 设置：邮编
	 */
	public void setKsZip(String ksZip) {
		this.ksZip = ksZip;
	}
	/**
	 * 获取：邮编
	 */
	public String getKsZip() {
		return ksZip;
	}
	/**
	 * 设置：联系地址
	 */
	public void setKsAddress(String ksAddress) {
		this.ksAddress = ksAddress;
	}
	/**
	 * 获取：联系地址
	 */
	public String getKsAddress() {
		return ksAddress;
	}
	/**
	 * 设置：报名市县
	 */
	public void setKsBmxs(String ksBmxs) {
		this.ksBmxs = ksBmxs;
	}
	/**
	 * 获取：报名市县
	 */
	public String getKsBmxs() {
		return ksBmxs;
	}
	/**
	 * 设置：报名时间
	 */
	public void setKsBmsj(Date ksBmsj) {
		this.ksBmsj = ksBmsj;
	}
	/**
	 * 获取：报名时间
	 */
	public Date getKsBmsj() {
		return ksBmsj;
	}
	/**
	 * 设置：
	 */
	public void setKsZdyxx(String ksZdyxx) {
		this.ksZdyxx = ksZdyxx;
	}
	/**
	 * 获取：
	 */
	public String getKsZdyxx() {
		return ksZdyxx;
	}
	/**
	 * 设置：停考时间
	 */
	public void setKsTksj(Date ksTksj) {
		this.ksTksj = ksTksj;
	}
	/**
	 * 获取：停考时间
	 */
	public Date getKsTksj() {
		return ksTksj;
	}
	/**
	 * 设置：延期毕业
	 */
	public void setKsYqby(String ksYqby) {
		this.ksYqby = ksYqby;
	}
	/**
	 * 获取：延期毕业
	 */
	public String getKsYqby() {
		return ksYqby;
	}
	/**
	 * 设置：考生区县
	 */
	public void setKsQx(String ksQx) {
		this.ksQx = ksQx;
	}
	/**
	 * 获取：考生区县
	 */
	public String getKsQx() {
		return ksQx;
	}
	/**
	 * 设置：市县代码
	 */
	public void setSxDm(String sxDm) {
		this.sxDm = sxDm;
	}
	/**
	 * 获取：市县代码
	 */
	public String getSxDm() {
		return sxDm;
	}
	/**
	 * 设置：修改标志
	 */
	public void setModified(String modified) {
		this.modified = modified;
	}
	/**
	 * 获取：修改标志
	 */
	public String getModified() {
		return modified;
	}
	/**
	 * 设置：旧准考证号
	 */
	public void setKsOldzkz(String ksOldzkz) {
		this.ksOldzkz = ksOldzkz;
	}
	/**
	 * 获取：旧准考证号
	 */
	public String getKsOldzkz() {
		return ksOldzkz;
	}
	/**
	 * 设置：专业名称
	 */
	public void setZyMc(String zyMc) {
		this.zyMc = zyMc;
	}
	/**
	 * 获取：专业名称
	 */
	public String getZyMc() {
		return zyMc;
	}
	/**
	 * 设置：密码
	 */
	public void setMm(String mm) {
		this.mm = mm;
	}
	/**
	 * 获取：密码
	 */
	public String getMm() {
		return mm;
	}
	/**
	 * 设置：手机1
	 */
	public void setSj1(String sj1) {
		this.sj1 = sj1;
	}
	/**
	 * 获取：手机1
	 */
	public String getSj1() {
		return sj1;
	}
	/**
	 * 设置：手机2
	 */
	public void setSj2(String sj2) {
		this.sj2 = sj2;
	}
	/**
	 * 获取：手机2
	 */
	public String getSj2() {
		return sj2;
	}
	/**
	 * 设置：电话
	 */
	public void setZzdh(String zzdh) {
		this.zzdh = zzdh;
	}
	/**
	 * 获取：电话
	 */
	public String getZzdh() {
		return zzdh;
	}
	/**
	 * 设置：预报名号
	 */
	public void setYbmh(String ybmh) {
		this.ybmh = ybmh;
	}
	/**
	 * 获取：预报名号
	 */
	public String getYbmh() {
		return ybmh;
	}
	/**
	 * 设置：报名点代码
	 */
	public void setBmddm(String bmddm) {
		this.bmddm = bmddm;
	}
	/**
	 * 获取：报名点代码
	 */
	public String getBmddm() {
		return bmddm;
	}
	/**
	 * 设置：工作单位
	 */
	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}
	/**
	 * 获取：工作单位
	 */
	public String getGzdw() {
		return gzdw;
	}
	/**
	 * 设置：电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮件
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：
	 */
	public void setMsdm(String msdm) {
		this.msdm = msdm;
	}
	/**
	 * 获取：
	 */
	public String getMsdm() {
		return msdm;
	}
	/**
	 * 设置：
	 */
	public void setHkszd(String hkszd) {
		this.hkszd = hkszd;
	}
	/**
	 * 获取：
	 */
	public String getHkszd() {
		return hkszd;
	}
	/**
	 * 设置：
	 */
	public void setGzdwszd(String gzdwszd) {
		this.gzdwszd = gzdwszd;
	}
	/**
	 * 获取：
	 */
	public String getGzdwszd() {
		return gzdwszd;
	}
	/**
	 * 设置：
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**
	 * 获取：
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * 设置：操作员
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 获取：操作员
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * 设置：操作日期
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：操作日期
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：0无效,1有效
	 */
	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	/**
	 * 获取：0无效,1有效
	 */
	public Integer getEnabledFlag() {
		return enabledFlag;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	private String picture;
}
