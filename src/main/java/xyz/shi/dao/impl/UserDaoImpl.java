package xyz.shi.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import xyz.shi.dao.UserDao;
import xyz.shi.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import xyz.shi.util.jdbcUtil;
public class UserDaoImpl implements UserDao {
    private QueryRunner qr =  new QueryRunner();
    // 返回所有对象
    @Override
    public List<User> queryAllList(String sql) {
        Connection connection = null;
        try {
            connection =jdbcUtil.getConnection();
            return qr.query(connection, sql, new BeanListHandler<User>(User.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbcUtil.closeResource(null,null, connection);
        }
    }
    // 返回多个对象(即查询的结果是多行)
    @Override
    public List<User> queryList(String sql, Object... params) {
        Connection connection = null;
        try {
            connection =jdbcUtil.getConnection();
            return qr.query(connection, sql, new BeanListHandler<User>(User.class), params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbcUtil.closeResource(null,null, connection);
        }
    }
    // 查询单行结果
    @Override
    public User queryOne(String sql, Object... parameters) {
        Connection connection = null;

        try {
             connection =jdbcUtil.getConnection();
            return qr.query(connection, sql, new BeanHandler<User>(User.class), parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbcUtil.closeResource(null,null, connection);
        }

    }
    // 修改(增删改通用)
    @Override
    public int update(String sql, Object... parameters) {
        Connection connection = null;
        try {
            connection =jdbcUtil.getConnection();
            return  qr.update(connection,sql,parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
        jdbcUtil.closeResource(null,null, connection);
    }
    }
}
