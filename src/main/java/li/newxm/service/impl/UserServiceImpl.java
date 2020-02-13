package li.newxm.service.impl;

import li.newxm.mapper.UserMapper;
import li.newxm.model.User;
import li.newxm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
         userMapper.insertUser(user);
    }
}
