package dao;

import bean.OrderItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderItemDAO {
    public OrderItemDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf-8","root","root");
    }

    public void insert(OrderItem oi){
        String sql = "insert into orderitem values(null,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1,oi.getProduct().getId());
            ps.setInt(2,oi.getNum());
            ps.setInt(3,oi.getOrder().getId());

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
