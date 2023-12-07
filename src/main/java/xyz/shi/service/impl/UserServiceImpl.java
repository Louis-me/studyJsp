package xyz.shi.service.impl;

import xyz.shi.dao.UserDao;
import xyz.shi.dao.impl.UserDaoImpl;
import xyz.shi.entity.User;
import xyz.shi.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    //创建UserDaoImpl对象
    private UserDao userDao = new UserDaoImpl();

    //查询所有
    @Override
    public List<User> getAllUser() {
        String sql ="select * from `users`";
        try {
            return  userDao.queryAllList(sql);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    //根据userId查询一个user
    @Override
    public User getUser(String userId) {
        String sql = "select * from `users` where id = ? ";
        try {
            return  userDao.queryOne(sql,userId);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    //根据userId和password查询一个user
    @Override
    public Boolean ableLogin (String name , String password) {
        String sql ="select * from `users` where name = ? and password = ? ";
        User user = userDao.queryOne(sql, name, password);

        return user != null ? true : false;

    }

    //根据条件查询多个user
    @Override
    public List<User> getUserList(Object... parameters) {
        String sql ="select * from `users` where ? = ? ";
        try {
            return  userDao.queryList(sql,parameters);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<User> getUserJz() {
        String sql ="SELECT *FROM `users` WHERE id LIKE 'jz%'";
        try {
            return  userDao.queryList(sql);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    //增加一个user
    @Override
    public int addUser(Object... parameters) {
//        String sql ="insert into `users` values (null,?,?,?,?,?,?,?,?,DEFAULT,?) ";
        String sql ="insert into `users` (name, password) values (?, ?)";
        return  userDao.update(sql, parameters);
    }

    //删除一个user
    @Override
    public int deleteUser(int userId) {
        String sql ="delete from `users` where id = ? ";
        return  userDao.update(sql,userId);
    }

    //修改一个user的信息
    @Override
    public int updateUser(Object... parameters) {
        String sql ="update `users` set name = ?,password = ? where id = ? ";
        return  userDao.update(sql,parameters);
    }

}
