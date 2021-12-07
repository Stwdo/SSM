package com.example.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller // 这个注解的主要含义是 Component ，即被Spring 框架发现它
// 同时他换可以给写代码和读代码的人看，告知他们这是一个Spring MVC的控制器
@SessionAttributes({"name", "age"}) // 这个注解中的名字一旦被放到 Model 会被 Spring MVC 自动挪动到 HttpSession 中
public class HomeController {

    @RequestMapping("session")
    public String session(Model model) {
        model.addAttribute("name", "tom");  //放入model中等价于放入request
        model.addAttribute("age", "10");
        return "redirect:/newSession1";
    }
    @RequestMapping("newSession")
    public String newSession(HttpSession httpSession) {
        System.out.println(httpSession.getAttribute("name") + "  " + httpSession.getAttribute("age"));
        return "redirect:/";
    }
    @RequestMapping("newSession1")
    public String newSession1(@SessionAttribute("name") String name, @SessionAttribute("age") Integer age) {
        System.out.println("----------------------------");
        System.out.println(name + "  " + age );
        return "redirect:/";
    }

//    @RequestMapping("/") // 类似于@WebServlet
//    public String home(){ //只要用户调用 / 这个地址，就会转发到 home。html去
//        return "home";

//    }


    @RequestMapping("/abc")
    public String abc(RedirectAttributes redirectAttributes){
        // redirectAttributes 可以让我们在使用重定向的时候依然把参数带过去
        // Model 只能在转发的时候将参数带过去
        redirectAttributes.addAttribute("name", "abcacb");
        return "redirect:/def";
    }

    @RequestMapping("/def")
    public String def(String name){
        System.out.println("=====================" + name + "**************");
        return "/";
    }

    @RequestMapping("/path/{name}/{id}")
    public String path(@PathVariable("id") Integer id, @PathVariable("name") String name){
        System.out.println("=====================" + name + "**************" + id);
        return "/";
    }



    @RequestMapping("/redorect")
    public String redirect(){
        return "redirect:toParam";  //redirect:  固定写法，表示重定向
        // Spring MVC的重定向不能直接跳转到网页
    }
    @RequestMapping("/model")
    public String model(Model model){
        //model  承担了一部分 Request的功能，网Model中放的内容就可以被转发到网页上进行显示
        model.addAttribute("nums", List.of(1, 2, 3));
        model.addAttribute("name", "tom");
        return "home";
    }

    @RequestMapping("/toQQ")
    public String QQ(){
        return "redirect:http://www.qq.com";  //redirect:  胡定写法，表示重定向
        // Spring MVC的重定向不能直接跳转到网页
    }

    @RequestMapping("/toParam") // 类似于@WebServlet
    public String toParam(){ //只要用户调用 / 这个地址，就会转发到 home。html去
        return "param";
    }

    // http://localhost:8080/param?id=1&&name=tom
    // Spring MVC 需要从表单或者超链接中拿到用户输入的内容时，只需要在方法中放入同名参数即可
    // 取和类型自动转换的过程也是由Spring MVC 完成
    @RequestMapping("/param")  // 类似于@WebServlet
    public String param(String name,Integer id){
        System.out.printf("%s, %d\n", name, id);
        return "";


    }

}
