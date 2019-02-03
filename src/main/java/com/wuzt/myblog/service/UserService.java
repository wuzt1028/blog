package com.wuzt.myblog.service;

import com.github.pagehelper.PageInfo;
import com.wuzt.myblog.model.User;

/**
 * @Auther: wuzt
 * @Date: 2018/6/19 17:48
 * @Description:
 */
public interface UserService {

    PageInfo<User> findAllUser(int pageNum, int pageSize);

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
