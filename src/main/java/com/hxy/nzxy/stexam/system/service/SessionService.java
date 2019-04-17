package com.hxy.nzxy.stexam.system.service;

import java.util.Collection;
import java.util.List;

import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.domain.UserOnline;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.domain.UserOnline;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;

import com.hxy.nzxy.stexam.system.domain.UserOnline;

@Service
public interface SessionService {
	List<UserOnline> list();

	List<UserDO> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
