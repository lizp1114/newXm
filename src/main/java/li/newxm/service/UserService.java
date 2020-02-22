package li.newxm.service;

import li.newxm.model.User;

public interface UserService {
    void insertUser(User user);

    User queryToken(String token);


    User queryById(Integer creator);
}
