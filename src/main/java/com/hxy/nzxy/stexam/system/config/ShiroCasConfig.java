package com.hxy.nzxy.stexam.system.config;

import com.hxy.nzxy.stexam.system.controller.SystemBaseController;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.shiro.ShiroCasRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hdwang on 2017/6/20.
 * shiro+cas 配置
 */
@Configuration
public class ShiroCasConfig {
//    private static final Logger logger = LoggerFactory.getLogger(ShiroCasConfig.class);
//    //配置文件是否开启单点登录
//
//    // cas server地址
//    public static final String casServerUrlPrefix = "http://localhost:8088/cas";
//    // Cas登录页面地址
//    public static final String casLoginUrl = casServerUrlPrefix + "/login";
//    // Cas登出页面地址
//    public static final String casLogoutUrl = casServerUrlPrefix + "/logout";
//    // 当前工程对外提供的服务地址
//    public static final String shiroServerUrlPrefix = "http://localhost:8080";
//    // casFilter UrlPattern
//    public static final String casFilterUrlPattern = "/cas";
//    // 登录地址
//    public static final String loginUrl = casLoginUrl + "?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
//    // 登出地址
//    public static final String logoutUrl = casLogoutUrl + "?service=" + shiroServerUrlPrefix;
//    // 登录成功地址
//    public static final String loginSuccessUrl = "/index";
//    // 权限认证失败跳转地址
//    public static final String unauthorizedUrl = "/error/403.html";
//
//
//    @Bean
//    public EhCacheManager getEhCacheManager() {
//        EhCacheManager em = new EhCacheManager();
//        em.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
//        return em;
//    }
//
//    @Bean(name = "myShiroCasRealm")
//    public ShiroCasRealm myShiroCasRealm(EhCacheManager cacheManager) {
//        ShiroCasRealm realm = new ShiroCasRealm();
//        realm.setCacheManager(cacheManager);
//        //realm.setCasServerUrlPrefix(ShiroCasConfiguration.casServerUrlPrefix);
//        // 客户端回调地址
//        //realm.setCasService(ShiroCasConfiguration.shiroServerUrlPrefix + ShiroCasConfiguration.casFilterUrlPattern);
//        return realm;
//    }
//
//    @Bean
//    public SessionDAO sessionDAO() {
//        MemorySessionDAO sessionDAO = new MemorySessionDAO();
//        return sessionDAO;
//    }
//
//    //注册单点登出listener
//    @Bean
//    public ServletListenerRegistrationBean singleSignOutHttpSessionListener() {
//        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
//        bean.setListener(new SingleSignOutHttpSessionListener());
////        bean.setName(""); //默认为bean name
//        bean.setEnabled(true);
//        //bean.setOrder(Ordered.HIGHEST_PRECEDENCE); //设置优先级
//        return bean;
//    }
//
//    /**
//     * 注册单点登出filter
//     *
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean singleSignOutFilter() {
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setName("singleSignOutFilter");
//        bean.setFilter(new SingleSignOutFilter());
//        bean.addUrlPatterns("/*");
//        bean.setEnabled(true);
//        //bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }
//
//    /**
//     * 注册DelegatingFilterProxy（Shiro）
//     *
//     * @return
//     * @author SHANHY
//     * @create 2016年1月13日
//     */
//    @Bean
//    public FilterRegistrationBean delegatingFilterProxy() {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//        //  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        return filterRegistration;
//    }
//
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
//        daap.setProxyTargetClass(true);
//        return daap;
//    }
//
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroCasRealm myShiroCasRealm) {
//        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
//        dwsm.setRealm(myShiroCasRealm);
////      <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
//        dwsm.setCacheManager(getEhCacheManager());
//        // 指定 SubjectFactory
//        dwsm.setSubjectFactory(new CasSubjectFactory());
//        return dwsm;
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
//        aasa.setSecurityManager(securityManager);
//        return aasa;
//    }
//
//    /**
//     * CAS过滤器
//     *
//     * @return
//     * @author SHANHY
//     * @create 2016年1月17日
//     */
//    @Bean(name = "casFilter")
//    public CasFilter getCasFilter() {
//        CasFilter casFilter = new CasFilter();
//        casFilter.setName("casFilter");
//        casFilter.setEnabled(true);
//        // 登录失败后跳转的URL，也就是 Shiro 执行 CasRealm 的 doGetAuthenticationInfo 方法向CasServer验证tiket
//        casFilter.setFailureUrl(loginUrl);// 我们选择认证失败后再打开登录页面
//        return casFilter;
//    }
//
//    /**
//     * ShiroFilter<br/>
//     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
//     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
//     *
//     * @param securityManager
//     * @param casFilter
//     * @param userDao
//     * @return
//     * @author SHANHY
//     * @create 2016年1月14日
//     */
//    @Bean(name = "shiroFilter")
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager, CasFilter casFilter, UserDao userDao) {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 必须设置 SecurityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//        shiroFilterFactoryBean.setLoginUrl(loginUrl);
//        // 登录成功后要跳转的连接
//        shiroFilterFactoryBean.setSuccessUrl(loginSuccessUrl);
//        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
//        // 添加casFilter到shiroFilter中
//        Map<String, Filter> filters = new HashMap<>();
//        filters.put("casFilter", casFilter);
//        // filters.put("logout",logoutFilter());
//        shiroFilterFactoryBean.setFilters(filters);
//        loadShiroFilterChain(shiroFilterFactoryBean, userDao);
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * 加载shiroFilter权限控制规则（从数据库读取然后配置）,角色/权限信息由MyShiroCasRealm对象提供doGetAuthorizationInfo实现获取来的
//     *
//     * @author SHANHY
//     * @create 2016年1月14日
//     */
//    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean, UserDao userDao) {
//        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//
//        // authc：该过滤器下的页面必须登录后才能访问，它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
//        // anon: 可以理解为不拦截
//        // user: 登录了就不拦截
//        // roles["admin"] 用户拥有admin角色
//        // perms["permission1"] 用户拥有permission1权限
//        // filter顺序按照定义顺序匹配，匹配到就验证，验证完毕结束。
//        // url匹配通配符支持：? * **,分别表示匹配1个，匹配0-n个（不含子路径），匹配下级所有路径
//
//        //1.shiro集成cas后，首先添加该规则
//        filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
//        //2.不拦截的请求
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        filterChainDefinitionMap.put("/fonts/**", "anon");
//        filterChainDefinitionMap.put("/img/**", "anon");
//        filterChainDefinitionMap.put("/docs/**", "anon");
//        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/upload/**", "anon");
//        filterChainDefinitionMap.put("/files/**", "anon");
//        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/", "anon");
//        filterChainDefinitionMap.put("/**", "authc");
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//    }
}
