package dao;

import bean.Product;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

//    public static void main(String[] args) {
//
//        System.out.println(new ProductDAO().getProduct(1).getName());
//
//    }

    public ProductDAO(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf-8","root","root");
    }

    public Product getProduct(int id){
        Product result = null;

        String sql = "select * from product where id = ?";
        try (Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                result = new Product();
                result.setId(id);

                String name = rs.getString(2);
                float price = rs.getFloat(3);

                result.setName(name);
                result.setPrice(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Product> ListProduct(){
        return ListProduct(0,Short.MAX_VALUE);
    }

    public List<Product> ListProduct(int start , int count) {
        List<Product> products = new ArrayList<Product>();

        String sql = "select * from product order by id desc limit ?,? ";

        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Product product = new Product();
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);

                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                products.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
