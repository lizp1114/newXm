package li.newxm.service;

import li.newxm.dto.PageDTO;
import li.newxm.model.Article;

public interface ArticleService {
    void ArticlecCreate(Article article);


    /*
     * 分页查询
     * */
    PageDTO listAll(Integer page, Integer size);

}
