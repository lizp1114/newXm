package li.newxm.service;

import li.newxm.dto.ArticleDTO;
import li.newxm.model.Article;

import java.util.List;

public interface ArticleService {
    void ArticlecCreate(Article article);

    List<ArticleDTO> listAll();
}
