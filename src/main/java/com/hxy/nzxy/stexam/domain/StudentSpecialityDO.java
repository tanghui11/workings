package com.hxy.nzxy.stexam.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 考生报考专业信息表
 * 
 * @author lzl
 * @email 7414842@qq.com
 * @date 2018-08-07 13:43:28
 */
public class StudentSpecialityDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Long id;
	//准考证编号
	private String studentid;
	//地州市考办编号
	private Long regionid;
	//县区考办编号
	private Long childRegionid;
	//助学组织编号
	private Long schoolid;
	//学院编号
	private Long collegeid;
	//教学点编号
	private Long teachSiteid;
	//毕业院校
	private String gradSchool;
	//毕业证书号
	private String gradCertificate;
	//原学专业编号
	private String gradSpecialityid;
	//原学历
	private String education;
	//专业招生备案编号
	private Long schoolSpecialityRegid;
	//专业开设编号
	private Long specialityRecordid;
	//状态
	private String status;
	//毕业申请状态
	private String graduate;
	//打印毕业证
	private String printCertificate;
	//申请人
	private Long applyOperator;
	//申请日期
	private Date applyDate;
	//审核状态
	private String auditStatus;
	//审核人
	private Long auditOperator;
	//审核日期
	private Date auditDate;
	//毕业审核状态
	private String gradAuditStatus;
	//毕业审核人
	private Long gradAuditOperator;
	//毕业审核日期
	private Date gradAuditDate;
	//旧准考证
	private String oldStudentid;
	//操作员
	private String operator;
	//操作日期
	private String updateDate;
	//数据标记
	private Integer dbFlag;
	private String createDate;
	private Integer enabledFlag;
	private String  schoolSpecialityRegName;
	//原学专业编号
	private String gradSpecialityName;

	//专业编号
	private String specialityid;
	//姓名
	private String name;

	//专业名称
	private String specialityName;

    public float getGradeT() {
        return gradeT;
    }

    public void setGradeT(float gradeT) {
        this.gradeT = gradeT;
    }

    //招生年份
    private float gradeT;

	private String regYear;
		//招生季节
	private String regSeason;
	private String keyValue;
	//组织名称
	private String schoolName;
	//学院名称
	private String collegeName;
	//招生教学点
	private String teachName;
    //专业层次
	private  String classify;
    //助学方式
	private  String type;
    //学制
	private  String educateLength;
	private  String studentSpecialityid;

	public String getStudentSpecialityid() {
		return studentSpecialityid;
	}

	public void setStudentSpecialityid(String studentSpecialityid) {
		this.studentSpecialityid = studentSpecialityid;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getRegYear() {
		return regYear;
	}

	public void setRegYear(String regYear) {
		this.regYear = regYear;
	}

	public String getRegSeason() {
		return regSeason;
	}

	public void setRegSeason(String regSeason) {
		this.regSeason = regSeason;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityid(String specialityid) {
		this.specialityid = specialityid;
	}

	public String getGradSpecialityName() {
		return gradSpecialityName;
	}

	public void setGradSpecialityName(String gradSpecialityName) {
		this.gradSpecialityName = gradSpecialityName;
	}

	public String getSchoolSpecialityRegName() {
		return schoolSpecialityRegName;
	}

	public void setSchoolSpecialityRegName(String schoolSpecialityRegName) {
		this.schoolSpecialityRegName = schoolSpecialityRegName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(Integer enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	/**
	 * 设置：编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：准考证编号
	 */
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	/**
	 * 获取：准考证编号
	 */
	public String getStudentid() {
		return studentid;
	}
	/**
	 * 设置：地州市考办编号
	 */
	public void setRegionid(Long regionid) {
		this.regionid = regionid;
	}
	/**
	 * 获取：地州市考办编号
	 */
	public Long getRegionid() {
		return regionid;
	}
	/**
	 * 设置：县区考办编号
	 */
	public void setChildRegionid(Long childRegionid) {
		this.childRegionid = childRegionid;
	}
	/**
	 * 获取：县区考办编号
	 */
	public Long getChildRegionid() {
		return childRegionid;
	}
	/**
	 * 设置：助学组织编号
	 */
	public void setSchoolid(Long schoolid) {
		this.schoolid = schoolid;
	}
	/**
	 * 获取：助学组织编号
	 */
	public Long getSchoolid() {
		return schoolid;
	}
	/**
	 * 设置：学院编号
	 */
	public void setCollegeid(Long collegeid) {
		this.collegeid = collegeid;
	}
	/**
	 * 获取：学院编号
	 */
	public Long getCollegeid() {
		return collegeid;
	}
	/**
	 * 设置：教学点编号
	 */
	public void setTeachSiteid(Long teachSiteid) {
		this.teachSiteid = teachSiteid;
	}
	/**
	 * 获取：教学点编号
	 */
	public Long getTeachSiteid() {
		return teachSiteid;
	}
	/**
	 * 设置：毕业院校
	 */
	public void setGradSchool(String gradSchool) {
		this.gradSchool = gradSchool;
	}
	/**
	 * 获取：毕业院校
	 */
	public String getGradSchool() {
		return gradSchool;
	}
	/**
	 * 设置：毕业证书号
	 */
	public void setGradCertificate(String gradCertificate) {
		this.gradCertificate = gradCertificate;
	}
	/**
	 * 获取：毕业证书号
	 */
	public String getGradCertificate() {
		return gradCertificate;
	}
	/**
	 * 设置：原学专业编号
	 */
	public void setGradSpecialityid(String gradSpecialityid) {
		this.gradSpecialityid = gradSpecialityid;
	}
	/**
	 * 获取：原学专业编号
	 */
	public String getGradSpecialityid() {
		return gradSpecialityid;
	}
	/**
	 * 设置：原学历
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * 获取：原学历
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * 设置：专业招生备案编号
	 */
	public void setSchoolSpecialityRegid(Long schoolSpecialityRegid) {
		this.schoolSpecialityRegid = schoolSpecialityRegid;
	}
	/**
	 * 获取：专业招生备案编号
	 */
	public Long getSchoolSpecialityRegid() {
		return schoolSpecialityRegid;
	}
	/**
	 * 设置：专业开设编号
	 */
	public void setSpecialityRecordid(Long specialityRecordid) {
		this.specialityRecordid = specialityRecordid;
	}
	/**
	 * 获取：专业开设编号
	 */
	public Long getSpecialityRecordid() {
		return specialityRecordid;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：毕业申请状态
	 */
	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}
	/**
	 * 获取：毕业申请状态
	 */
	public String getGraduate() {
		return graduate;
	}
	/**
	 * 设置：打印毕业证
	 */
	public void setPrintCertificate(String printCertificate) {
		this.printCertificate = printCertificate;
	}
	/**
	 * 获取：打印毕业证
	 */
	public String getPrintCertificate() {
		return printCertificate;
	}
	/**
	 * 设置：申请人
	 */
	public void setApplyOperator(Long applyOperator) {
		this.applyOperator = applyOperator;
	}
	/**
	 * 获取：申请人
	 */
	public Long getApplyOperator() {
		return applyOperator;
	}
	/**
	 * 设置：申请日期
	 */
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	/**
	 * 获取：申请日期
	 */
	public Date getApplyDate() {
		return applyDate;
	}
	/**
	 * 设置：审核状态
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	/**
	 * 获取：审核状态
	 */
	public String getAuditStatus() {
		return auditStatus;
	}
	/**
	 * 设置：审核人
	 */
	public void setAuditOperator(Long auditOperator) {
		this.auditOperator = auditOperator;
	}
	/**
	 * 获取：审核人
	 */
	public Long getAuditOperator() {
		return auditOperator;
	}
	/**
	 * 设置：审核日期
	 */
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	/**
	 * 获取：审核日期
	 */
	public Date getAuditDate() {
		return auditDate;
	}
	/**
	 * 设置：毕业审核状态
	 */
	public void setGradAuditStatus(String gradAuditStatus) {
		this.gradAuditStatus = gradAuditStatus;
	}
	/**
	 * 获取：毕业审核状态
	 */
	public String getGradAuditStatus() {
		return gradAuditStatus;
	}
	/**
	 * 设置：毕业审核人
	 */
	public void setGradAuditOperator(Long gradAuditOperator) {
		this.gradAuditOperator = gradAuditOperator;
	}
	/**
	 * 获取：毕业审核人
	 */
	public Long getGradAuditOperator() {
		return gradAuditOperator;
	}
	/**
	 * 设置：毕业审核日期
	 */
	public void setGradAuditDate(Date gradAuditDate) {
		this.gradAuditDate = gradAuditDate;
	}
	/**
	 * 获取：毕业审核日期
	 */
	public Date getGradAuditDate() {
		return gradAuditDate;
	}
	/**
	 * 设置：旧准考证
	 */
	public void setOldStudentid(String oldStudentid) {
		this.oldStudentid = oldStudentid;
	}
	/**
	 * 获取：旧准考证
	 */
	public String getOldStudentid() {
		return oldStudentid;
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
	 * 设置：数据标记
	 */
	public void setDbFlag(Integer dbFlag) {
		this.dbFlag = dbFlag;
	}
	/**
	 * 获取：数据标记
	 */
	public Integer getDbFlag() {
		return dbFlag;
	}



	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getTeachName() {
		return teachName;
	}

	public void setTeachName(String teachName) {
		this.teachName = teachName;
	}

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEducateLength() {
        return educateLength;
    }

    public void setEducateLength(String educateLength) {
        this.educateLength = educateLength;
    }

	private  String studentName;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSourceCourseid() {
		return sourceCourseid;
	}

	public void setSourceCourseid(String sourceCourseid) {
		this.sourceCourseid = sourceCourseid;
	}

	private String courseid;
	private String courseName;
	private String grade;
	private String sourceCourseid;
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	private String direction;

	public String getCertificate_no() {
		return certificate_no;
	}

	public void setCertificate_no(String certificate_no) {
		this.certificate_no = certificate_no;
	}

	private String certificate_no;

	private String ename;
	private String pinyin;
	private String home_type;
	private String politics;
	private String nation;
	private String profession;
	private String birthday;
	private String native_place;
	private String kjh;
	private String certificate_type;
	private String phone;
	private String mphone;
	private String address;

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getHome_type() {
		return home_type;
	}

	public void setHome_type(String home_type) {
		this.home_type = home_type;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNative_place() {
		return native_place;
	}

	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}

	public String getKjh() {
		return kjh;
	}

	public void setKjh(String kjh) {
		this.kjh = kjh;
	}

	public String getCertificate_type() {
		return certificate_type;
	}

	public void setCertificate_type(String certificate_type) {
		this.certificate_type = certificate_type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPost_code() {
		return post_code;
	}

	public void setPost_code(String post_code) {
		this.post_code = post_code;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getIdcard_pic() {
		return idcard_pic;
	}

	public void setIdcard_pic(String idcard_pic) {
		this.idcard_pic = idcard_pic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChild_regionid() {
		return child_regionid;
	}

	public void setChild_regionid(String child_regionid) {
		this.child_regionid = child_regionid;
	}

	private String post_code;
	private String pic;
	private String idcard_pic;
	private String email;
	private String child_regionid;

}
