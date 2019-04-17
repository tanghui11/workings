package com.hxy.nzxy.stexam.common.aspect;
import com.hxy.nzxy.stexam.common.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by huguoju on 2016/12/29.
 * 拦截设置本地线程变量
 */
@Aspect
@Component
public class DataSourceAop {
	private static Logger log = LoggerFactory.getLogger(DataSourceAop.class);
	//	@Before("execution(* com.hxy.nzxy.stexam.*..service.impl..*(..)) || execution(* mybatis.*.mapper..*.get*(..))|| execution(* mybatis.*.mapper..*.get*(..))")
//@Before("execution(* com.hxy.nzxy.stexam.*.service.impl..*.*(..))|| execution(* com.hxy.nzxy.stexam.*.*.service.impl..*.*(..))")
	//@Before("execution(* com.hxy.nzxy.stexam.common.dao.CommonDao.*(..)) || execution(* com.hxy.nzxy.stexam.system.dao.UserDao.*(..))")
	@Before("execution(* com.hxy.nzxy.stexam..*.dao..*count*(..))||execution(* com.hxy.nzxy.stexam..*.dao..*list*(..))||execution(* com.hxy.nzxy.stexam..*.dao..*get*(..))||execution(* com.hxy.nzxy.stexam..*.dao..if*(..))||execution(* com.hxy.nzxy.stexam..*.dao..*List*(..))" +
			"||execution(* com.hxy.nzxy.stexam..*.dao.*seach*(..))||execution(* com.hxy.nzxy.stexam..*.dao..*serch*(..))||execution(* com.hxy.nzxy.stexam..*.dao..Children*(..))||execution(* com.hxy.nzxy.stexam..*.dao..verifyChildren*(..))")
	public void setReadDataSourceType() {
		DataSourceContextHolder.read();
		log.info("dataSource切换到：Read");
	}
	@Before("execution(* com.hxy.nzxy.stexam..*.dao..*save*(..))||execution(* com.hxy.nzxy.stexam..*.dao..*update*(..))||execution(* com.hxy.nzxy.stexam..*.dao..*batch*(..))||execution(* com.hxy.nzxy.stexam..*.dao..*remove*(..))||execution(* com.hxy.nzxy.stexam..*.dao..*del*(..))")
	public void setWriteDataSourceType() {
		DataSourceContextHolder.write();
		log.info("dataSource切换到：write");
	}
}
