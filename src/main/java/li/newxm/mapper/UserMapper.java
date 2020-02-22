package li.newxm.mapper;

import li.newxm.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /*插入用户信息*/
    @Insert("insert into user  values (default,#{ACCOUNT_ID},#{name},#{token},#{gmt_Create},#{gmt_Modified},#{avatar_url})")
    void insertUser(User user);

    /*查询用户的token信息*/
    @Select("SELECT * FROM USER WHERE TOKEN = #{token}")
     User queryToken(@Param("token") String token);
}
