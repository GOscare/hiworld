package dao;

import bean.Order;

import java.sql.*;

public class OrderDAO {
    public OrderDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf-8","root","root");
    }

    public void insert(Order o){

        String sql ="insert into order_ values(null,?)";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1,o.getUser().getId());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                int id = rs.getInt(1);
                o.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
