package li.newxm.service.impl;

import li.newxm.dto.ArticleDTO;
import li.newxm.dto.PageDTO;
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
    public PageDTO listAll(Integer page, Integer size) {
        PageDTO pageDTO = new PageDTO();
        //总条数
        Integer totalCount = articleMapper.count();
        pageDTO.setPagination(totalCount,page,size);
        //页数范围限定
        if(page<1){
            page=1;
        }
        if (page>pageDTO.getTotalpage()){
            page=pageDTO.getTotalpage();
        }
        //
        Integer offset =size*(page-1);

        List<Article> articleList = articleMapper.listAll(offset,size);
        List<ArticleDTO> articleDTOList = new ArrayList<>();

        for (Article article:articleList){
            User user = userService.queryById(article.getCreator());
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article,articleDTO);
            articleDTO.setUser(user);
            articleDTOList.add(articleDTO);
        }
        pageDTO.setArticleDTOS(articleDTOList);

        return pageDTO;
    }
}
