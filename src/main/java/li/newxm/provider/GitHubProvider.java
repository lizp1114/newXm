package li.newxm.provider;

import com.alibaba.fastjson.JSON;
import li.newxm.dto.AccessTokenDTO;
import li.newxm.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class GitHubProvider{
    /*以post请求方式将*/
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            String tokenStr = str.split("&")[0].split("=")[1];
           // System.out.println(tokenStr);
            return tokenStr;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String resStr = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(resStr, GitHubUser.class);
           // System.out.println(gitHubUser.getName());
            return gitHubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
