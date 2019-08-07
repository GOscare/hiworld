package dao;

import bean.User;

import java.sql.*;

public class UserDAO {
    public UserDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf-8","root","root");
    }

    public User getUser(String name , String password) {
        User result = null;

        String sql = "select * from user where name = ? and password = ?";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1,name);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                result = new User();
                result.setId(rs.getInt(1));
                result.setName(name);
                result.setPassword(password);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

//    public static void main(String[] args) {
//        System.out.println(new UserDAO().getUser("tom","123").getName());
//    }
}
