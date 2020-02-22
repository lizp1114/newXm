package li.newxm.mapper;

import li.newxm.model.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article values (default,#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},default,default,default,#{tag})")
    void articlecCreates(Article article);
    @Select("select * from ARTICLE")
    List<Article> listAll();
}
