package com.accp.action;

import com.accp.biz.PunishmentBiz;
import com.accp.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/c")
public class PunishmentAction {
    @Resource
    private PunishmentBiz punishmentBiz;
    @RequestMapping("Loadlogin")
    public String login(){
        return "login";
    }
    @RequestMapping("login")
    public String loging(Model  model, User user, HttpSession session){
        try {
            User user1=punishmentBiz.login(user.getName(),user.getPsw());
            session.setAttribute("user",user1);

            return "index";
        }catch (Exception e){
            session.setAttribute("user",e.getMessage());
            return "login";
        }
    }
    @RequestMapping("page")
    public String pageinf(Model model,Integer pageSize,Integer pageNum){
        model.addAttribute("page",punishmentBiz.pageInfoUser(pageNum,pageSize));
        return "index";
    }
}
