package xyz.shi.dao;

import xyz.shi.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    //返回所有对象
    List<User> queryAllList(String sql);
    // 查找多条记录
    List<User> queryList(String sql,Object ...params);
    // 查找一条记录
    User queryOne(String sql, Object... parameters);
    // 修改(删除)一条记录
    int update(String sql, Object... parameters) ;
}
