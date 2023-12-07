package xyz.shi.service;

import xyz.shi.entity.User;

import java.util.List;

public interface UserService {
    //根据userId查询一个user
    User getUser(String id);

    //根据name和password查询一个user
    Boolean ableLogin (String name , String password);

    //查找多条user对象
    List<User> getUserList(Object... parameters);

    //查找所有user对象
    List<User> getAllUser();

    //增加一个user
    int addUser(Object... parameters);

    //删除一个user
    int deleteUser(int userId);

    //修改一个user的信息
    int updateUser(Object... parameters);

}
