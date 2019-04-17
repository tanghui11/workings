package com.hxy.nzxy.stexam.common.listenner;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@Order(value = 2)
public class UserCacheListener implements CommandLineRunner {
    @Autowired
    private RedisService redisService;
    @Autowired
    private CommonService commonService;

    /**
     * 是否启用缓存
     */
    @Value("${chache.user.enabled}")
    private String enabled;
    @Override
    public void run(String... arg0) throws Exception {
        try {
            if(enabled.equals("true")) {
                List<UserDO> list = commonService.listAllUserCache(null);
                for (UserDO item : list) {
                    // 将字典添加到数据字典工具类
                    UserUtil.set(item.getId().toString(), item.getName(), item.getWorkerName());
                }
                System.out.println("=================================================");
                System.out.println("==============系统缓存用户信息完成！==============");
                System.out.println("=================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
