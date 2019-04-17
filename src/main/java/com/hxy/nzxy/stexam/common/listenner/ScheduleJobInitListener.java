package com.hxy.nzxy.stexam.common.listenner;

import com.hxy.nzxy.stexam.common.quartz.utils.QuartzManager;
import com.hxy.nzxy.stexam.common.service.JobService;
import com.hxy.nzxy.stexam.common.quartz.utils.QuartzManager;
import com.hxy.nzxy.stexam.common.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hxy.nzxy.stexam.common.quartz.utils.QuartzManager;
import com.hxy.nzxy.stexam.common.service.JobService;

@Component
@Order(value = 3)
public class ScheduleJobInitListener implements CommandLineRunner {

	@Autowired
    JobService scheduleJobService;

	@Autowired
    QuartzManager quartzManager;

	@Override
	public void run(String... arg0) throws Exception {
		try {
			scheduleJobService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}