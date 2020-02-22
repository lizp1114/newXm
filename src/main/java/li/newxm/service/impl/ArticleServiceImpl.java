package li.newxm.service.impl;

import li.newxm.dto.AccessTokenDTO;
import li.newxm.dto.ArticleDTO;
import li.newxm.mapper.ArticleMapper;
import li.newxm.model.Article;
import li.newxm.model.User;
import li.newxm.service.ArticleService;
import li.newxm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserService userService;
    @Override
    public void ArticlecCreate(Article article) {
        articleMapper.articlecCreates(article);
    }

    @Override
    public List<ArticleDTO> listAll() {
        List<Article> articleList = articleMapper.listAll();
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for (Article article:articleList){
            User user = userService.queryById(article.getCreator());
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article,articleDTO);
            articleDTO.setUser(user);
            articleDTOList.add(articleDTO);
        }
        return articleDTOList;
    }
}
