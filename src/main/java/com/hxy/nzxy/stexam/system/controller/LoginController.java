package com.hxy.nzxy.stexam.system.controller;

import com.hxy.nzxy.stexam.common.annotation.Log;
import com.hxy.nzxy.stexam.common.controller.BaseController;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.MD5Utils;
import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.domain.SchoolDO;
import com.hxy.nzxy.stexam.school.school.service.SchoolSchoolService;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.domain.DeptWorkerDO;
import com.hxy.nzxy.stexam.system.domain.UserDO;
import com.hxy.nzxy.stexam.system.service.MenuService;
import com.hxy.nzxy.stexam.system.domain.MenuDO;
import com.hxy.nzxy.stexam.common.controller.BaseController;
import com.hxy.nzxy.stexam.common.domain.Tree;
import com.hxy.nzxy.stexam.common.utils.MD5Utils;
import com.hxy.nzxy.stexam.common.utils.R;
import com.hxy.nzxy.stexam.common.utils.ShiroUtils;
import com.hxy.nzxy.stexam.common.utils.StringUtils;
import com.hxy.nzxy.stexam.system.dao.UserDao;
import com.hxy.nzxy.stexam.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;

    @Autowired
    private UserDao userDao;
  //  @Autowired
  //  private SchoolSchoolService schoolSchoolService;

    @Value("${edu-system.name}")
    private String eduSystemName;

    @Value("${edu-system.sub-name}")
    private String eduSystemSubName;

    @Value("${edu-system.sub-logo}")
    private String eduSystemSubLogo;

    @Value("${page.size}")
    private String pageSize;

    @GetMapping({"/", ""})
    String welcome(Model model) {
        return "redirect:/login";
    }

    //@Log("请求访问主页")
    @RequestMapping({"/index"})
    String index(Model model, HttpSession session, ModelMap map, HttpServletRequest request) {
        //进入主界面
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        model.addAttribute("username", getUser().getName());
        model.addAttribute("workerName", getUser().getWorkerName());
        model.addAttribute("loginIP", getIp(request));
        Calendar now = Calendar.getInstance();
        model.addAttribute("year", now.get(Calendar.YEAR));
        model.addAttribute("eduSystemName", eduSystemName);
        model.addAttribute("eduSystemSubLogo", eduSystemSubLogo);
        model.addAttribute("eduSystemSubName", eduSystemSubName);
        model.addAttribute("pageSize", pageSize);
      //  String workerid = ShiroUtils.getUser().getWorkerid();
       // SchoolDO school= schoolSchoolService.getDept(workerid);
    //    request.getSession().setAttribute("school",school);
        return "index_v1";
    }

    private String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("eduSystemName", eduSystemName);
        model.addAttribute("eduSystemSubLogo", eduSystemSubLogo);
        model.addAttribute("eduSystemSubName", eduSystemSubName);
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password, HttpServletRequest request) {
      /*  String captchaId = (String) request.getSession().getAttribute("vrifyCode");
        String parameter = request.getParameter("vrifyCode");

        if(!parameter.equals(captchaId))
        {
            return R.error("错误的验证码");
        }*/
        password = MD5Utils.encrypt(password);

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("用户或密码错误");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    @GetMapping("/403")
    String error403() {
        return "403";
    }

}
