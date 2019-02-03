package com.wuzt.myblog.dao;

import com.wuzt.myblog.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: wuzt
 * @Date: 2018/6/19 17:46
 * @Description:
 */
@Repository
public interface UserDao {

    /*
     * Description:获取所有user
     * @auther: wuzt
     * @date: 2018/7/20 11:33
     */
    List<User> selectUsers();

    /*
     * Description:根据username获取user
     * @auther: wuzt
     * @date: 2018/7/20 11:29
     */
    User selectUsersByUserName(User user);

    /*
     * Description:根据id获取用户
     * @auther: wuzt
     * @date: 2018/8/30 14:05
     */
    User selectUsersById(Integer id);
}
