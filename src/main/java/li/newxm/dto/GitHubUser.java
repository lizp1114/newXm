package li.newxm.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private long id;
    private String name;
    private String bio;
    private String avatar_url;

}
