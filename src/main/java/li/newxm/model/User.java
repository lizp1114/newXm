package li.newxm.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String ACCOUNT_ID;
    private String token;
    private Long gmt_Create;
    private Long gmt_Modified;
    private String avatar_url;
}
