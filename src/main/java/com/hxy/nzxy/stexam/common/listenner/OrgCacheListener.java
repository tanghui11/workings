package com.hxy.nzxy.stexam.common.listenner;

import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.OrgUtil;
import com.hxy.nzxy.stexam.common.utils.UserUtil;
import com.hxy.nzxy.stexam.system.domain.OrgDO;
import com.hxy.nzxy.stexam.common.service.CommonService;
import com.hxy.nzxy.stexam.common.service.RedisService;
import com.hxy.nzxy.stexam.common.utils.OrgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

@Configuration
@Order(value = 2)
public class OrgCacheListener implements CommandLineRunner {
    @Autowired
    private RedisService redisService;
    @Autowired
    private CommonService commonService;

    /**
     * 是否启用缓存
     */
    @Value("${chache.org.enabled}")
    private String enabled;
    @Override
    public void run(String... arg0) throws Exception {
        try {
            if(enabled.equals("true")) {
                List<OrgDO> list = commonService.listAllOrg(null);
                for (OrgDO item : list) {
                    // 将字典添加到数据字典工具类
                    OrgUtil.setName(item.getId().toString(), item.getName());
                    OrgUtil.setCode(item.getId().toString(), item.getCode());
                }
                System.out.println("=================================================");
                System.out.println("==============系统缓存机构信息完成！==============");
                System.out.println("=================================================");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
