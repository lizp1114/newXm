package li.newxm.controller;

import li.newxm.model.Article;
import li.newxm.model.User;
import li.newxm.service.ArticleService;
import li.newxm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ArticleContrller {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @GetMapping("/publish")
    public String article(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doAtricle(@RequestParam("title")String title, @RequestParam("description")String description,
                            @RequestParam("tag")String tag, HttpServletRequest request, Model model){
        model.addAttribute("title","title");
        model.addAttribute("description","description");
        model.addAttribute("tag","tag");
        /*判断input中是否有值*/
       if(title==null||title==""){
            model.addAttribute("error","标题不为空!");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","内容不为空!");
            return "publish";
        }
        /*判断用户是否登录*/
        Cookie[] cookies = request.getCookies();
        User user=null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userService.queryToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        /*如果未登录显示相对应的信息*/
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        /*添加值*/
        Article article =new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setGmt_create(System.currentTimeMillis());
        article.setGmt_modified(article.getGmt_create());
        article.setCreator(user.getId());
        article.setTag(tag);
        article.toString();
        articleService.ArticlecCreate(article);
        return "redirect:/";
    }
}
