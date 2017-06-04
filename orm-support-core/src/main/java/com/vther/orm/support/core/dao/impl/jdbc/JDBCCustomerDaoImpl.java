package com.vther.orm.support.core.dao.impl.jdbc;

import com.vther.orm.support.core.dao.CustomerDao;
import com.vther.orm.support.core.entity.Customer;
import com.vther.orm.support.core.type.OrmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Profile(OrmType.JDBC)
public class JDBCCustomerDaoImpl implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void createCustomer(Customer customer) {
        String sql = "INSERT INTO t_customer (customerId,name,age,registerTime,version) VALUES(?,?,?,?,?)";
        this.jdbcTemplate.update(sql,
                customer.getCustomerId(),
                customer.getName(),
                customer.getAge(),
                customer.getRegisterTime(),
                0);
    }

    @Override
    public void updateCustomerName(Long customerId, String customerName) {

    }

    @Override
    public Customer findByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public List<Customer> findByCustomerName(String customerName) {
        return null;
    }

    @Override
    public List<Customer> findByPage(Integer pageNo, Integer pageSize) {
        return null;
    }

    /*
      public void addUser(User user) {
15         String sql = "insert into user values(?,?,?)";
16         this.getJdbcTemplate().update(sql, user.getId(), user.getUsername(),
17                 user.getPassword());
18     }
19
20     public void deleteUser(int id) {
21         String sql = "delete from user where id=?";
22         this.getJdbcTemplate().update(sql, id);
23
24     }
25
26     public void updateUser(User user) {
27         String sql = "update user set username=?,password=? where id=?";
28         this.getJdbcTemplate().update(sql, user.getUsername(),
29                 user.getPassword(), user.getId());
30     }
31
32     public String searchUserName(int id) {// 简单查询，按照ID查询，返回字符串
33         String sql = "select username from user where id=?";
34         // 返回类型为String(String.class)
35         return this.getJdbcTemplate().queryForObject(sql, String.class, id);
36
37     }
38
39     public List<User> findAll() {// 复杂查询返回List集合
40         String sql = "select * from user";
41         return this.getJdbcTemplate().query(sql, new UserRowMapper());
42
43     }
44
45     public User searchUser(int id) {
46         String sql="select * from user where id=?";
47         return this.getJdbcTemplate().queryForObject(sql, new UserRowMapper(), id);
48     }
49
50     class UserRowMapper implements RowMapper<User> {
51 　　　　　//rs为返回结果集，以每行为单位封装着
52         public User mapRow(ResultSet rs, int rowNum) throws SQLException {
53
54             User user = new User();
55             user.setId(rs.getInt("id"));
56             user.setUsername(rs.getString("username"));
57             user.setPassword(rs.getString("password"));
58             return user;
59         }
60
61     }
     */
}
