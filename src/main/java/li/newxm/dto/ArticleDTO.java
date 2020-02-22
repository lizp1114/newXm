package li.newxm.dto;

import li.newxm.model.User;
import lombok.Data;

@Data
public class ArticleDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer view_count;
    private Integer like_count;
    private Integer comment_count;
    private String tag;
    private User user;
}
