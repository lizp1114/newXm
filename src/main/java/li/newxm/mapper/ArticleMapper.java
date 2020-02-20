package li.newxm.mapper;

import li.newxm.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article values (default,#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},default,default,default,#{tag})")
    void articlecCreates(Article article);
}
