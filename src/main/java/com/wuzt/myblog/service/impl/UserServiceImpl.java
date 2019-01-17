package com.wuzt.myblog.service.impl;

/**
 * @Auther: wuzt
 * @Date: 2018/6/19 17:51
 * @Description:
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuzt.myblog.dao.UserDao;
import com.wuzt.myblog.model.User;
import com.wuzt.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "userService")
@Transactional
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserDao userDao;

    public PageInfo<User> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<User> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

    public User selectUsersByUserName(User user){
        return userDao.selectUsersByUserName(user);
    }

    public User selectUsersById(Integer id){
        return userDao.selectUsersById(id);
    }
}
