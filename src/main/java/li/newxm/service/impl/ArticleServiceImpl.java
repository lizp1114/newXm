package li.newxm.service.impl;

import li.newxm.mapper.ArticleMapper;
import li.newxm.model.Article;
import li.newxm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void ArticlecCreate(Article article) {
        articleMapper.articlecCreates(article);
    }
}
