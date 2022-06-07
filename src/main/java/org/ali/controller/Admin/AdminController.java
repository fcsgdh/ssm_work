package org.ali.controller.Admin;

import org.ali.entity.Admin;
import org.ali.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public String findByUsernameAndPwd(Admin admin, HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("admin")!=null) {
            return "redirect:/index.html";
        }
        boolean flag = adminService.findByUsernameAndPassword(admin);
        if (flag) {
            HttpSession session = req.getSession();
            session.setAttribute("admin",admin);
            session.setMaxInactiveInterval(60 * 60);
            return "redirect:/index.html";
        }
        try {
            resp.setContentType("text/javascript;charset=utf-8");
            resp.getWriter().write("<script>alert('用户名或密码错误')</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/login.html";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().setAttribute("admin",null);
        return "redirect:localhost/login.html";
    }
}
