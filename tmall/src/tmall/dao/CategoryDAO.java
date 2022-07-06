package tmall.dao;

import tmall.bean.Category;
import tmall.util.DBUtil;

import java.sql.*;

/**
 * @author: bytedance
 * @Email: shenhao.leon@bytedance.com
 * @date: 2022-07-06 8:56 下午
 * @desc:
 */
public class CategoryDAO {
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement();) {
            String sql = "select count(*) from Category";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void add(Category bean) {
        String sql = "insert into category values(null, ?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, bean.getName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
        }
    }
}
