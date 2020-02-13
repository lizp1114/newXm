package li.newxm.controller;

import li.newxm.dto.AccessTokenDTO;
import li.newxm.dto.GitHubUser;
import li.newxm.model.User;
import li.newxm.provider.GitHubProvider;
import li.newxm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeContrller {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.sercet}")
    private String client_secret;

    @Value("${github.redirect.url}")
    private String redirect_uri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = gitHubProvider.getUser(accessToken);

        /*根据返回的值是否为空判断用户是否登录成功*/
        if (gitHubUser!=null){
            //setAttribute():在JSP内置对象session和request都有这个方法，这个方法作用就是保存数据，然后还可以用getAttribute方法来取出。
            User user = new User();
            user.setName(gitHubUser.getName());
            user.setACCOUNT_ID(String.valueOf(gitHubUser.getId()));
            user.setToken(UUID.randomUUID().toString());
            user.setGmt_Create(System.currentTimeMillis());
            user.setGmt_Modified(user.getGmt_Create());
            userService.insertUser(user);
            request.getSession().setAttribute("user",gitHubUser);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }
}
