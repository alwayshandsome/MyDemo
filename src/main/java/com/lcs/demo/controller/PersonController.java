package com.lcs.demo.controller;

import com.lcs.demo.pojo.Login;
import com.lcs.demo.pojo.Person;
import com.lcs.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PersonController {
    @Autowired
    PersonService personService;

    /**
     * 登录
     * @param request
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username = "+username+"\n"+"password = "+password);
        Login login = new Login(username,password);
        model.addAttribute("login",login);
        boolean b = personService.login(login.getUsername(),login.getPassword());
        if (b)
            return "hello";
        else
            return "error";
    }

    /**
     * 通过名字查询所有数据信息
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/name",method = RequestMethod.GET)
    public String findByName(String name, Model model){
        List<Person> person = personService.findByName(name);
        model.addAttribute("person",person);
        return "index";
    }

    /**
     * 通过地址查询所有数据信息
     * @param address
//     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/address", method = RequestMethod.POST)
//    public String findByAddress(String address,Model model){
    public String findByAddress(@Param("data") String address){
        System.out.println("****");
//        address = "A020307";
        System.out.println("address = "+address);
        List<Person> person = personService.findByAddress(address);
        System.out.println("person = "+person);
        String s = String.valueOf(person);
        System.out.println("s = "+s);
//        model.addAttribute("person",person);
        return "ajax调用成功";
    }

    /**
     * 通过姓名和地址查询所有数据信息
     * @param name
     * @param address
     * @param model
     * @return
     */
    @RequestMapping(value = "/na_add",method = RequestMethod.GET)
    public String finByNa_Ad(String name,String address,Model model){
        List<Person> person = personService.findByNa_Ad(name,address);
        model.addAttribute("person",person);
        return "index";
    }

    /**
     * 增
     * @param name
     * @param age
     * @param address
     * @param model
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(String name,int age,String address,Model model){
        int a = personService.add(name,age,address);
        return "index";
    }

    /**
     * 删
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public String delete(String name,Model model){
        int a = personService.delete(name);
        model.addAttribute("person",a);
        return "index";
    }

    /**
     * 改
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public int modify(int id,String name){
        int a = personService.modify(id,name);
        return a;
    }
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/page/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//    @Bean
//    public ErrorPageFilter errorPageFilter() {
//        return new ErrorPageFilter();
//    }
//    @Bean
//    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(filter);
//        filterRegistrationBean.setEnabled(false);
//        return filterRegistrationBean;
//    }
}