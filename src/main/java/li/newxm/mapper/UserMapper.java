package li.newxm.mapper;

import li.newxm.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user  values (default,#{ACCOUNT_ID},#{name},#{token},#{gmt_Create},#{gmt_Modified})")
    void insertUser(User user);
}
