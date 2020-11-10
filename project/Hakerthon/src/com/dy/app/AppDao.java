package com.dy.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AppDao {
	private static final String URL = "jdbc:mysql://127.0.0.1/myapp";
	private static final String USERNAME= "root";
	private static final String PASSWORD = "root";
	private static AppDao instance;
	private Connection conn;
	
	private AppDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static AppDao getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new AppDao();
		}
		return instance;
	}
	
	public ArrayList<ProductVo> getAllProducts() throws SQLException {
		String sql = "SELECT user_id, name, price, content, regdate FROM app_products";
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		ProductVo product;
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			product = new ProductVo();
			product.setName(rs.getString("name"));
			product.setUser_id(rs.getString("user_id"));
			product.setContent(rs.getString("content"));
			product.setPrice(rs.getInt("price"));
			product.setReg_date(rs.getString("regdate"));	
			list.add(product);
		}
		return list;
	}
	
	public int registerUser(String id, String password) throws SQLException {
		String sql = "INSERT INTO app_users(id, pw) VALUES(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2,password);
		int rows = ps.executeUpdate();
		
		return rows;
	}
	
	public boolean isUser(String id, String pw) throws SQLException {
		String sql = "SELECT id, pw FROM app_users WHERE id = ? && pw = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	public int getAllProductsCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM app_products";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	public UserVo getUserInfo(String id) throws SQLException{
		UserVo user = new UserVo();
		String sql = "SELECT id, pw, rating, products FROM app_users WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			user.setId(rs.getString("id"));
			user.setPassword(rs.getString("pw"));
			user.setRating(rs.getString("rating"));
			user.setProducts(rs.getInt("products"));
		}
		return user;
	}
	
	public int deleteUserAccount(UserVo userVo) throws SQLException {
		String sql = "DELETE FROM app_users WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userVo.getId());
		return ps.executeUpdate();
	}
	
	public int getProducts(String id) throws SQLException {
		String sql = "SELECT products FROM app_users WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);	
	}
	
	
	public int getUserProducts(String id) throws SQLException {
		String sql = "SELECT COUNT(*) FROM app_products WHERE user_id = (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}
	
	public int updateUserProducts(String id) throws SQLException {
		String sql = "UPDATE app_users SET products = ? WHERE id = ?";
		int count = getUserProducts(id);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, count);
		ps.setString(2, id);
		int row = ps.executeUpdate();
		return row;
	}
	
	
	public int registerProduct(ProductVo productVo) throws SQLException {
		String sql = "INSERT INTO app_products(user_id, name, price, content) VALUES(?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, productVo.getUser_id());
		ps.setString(2, productVo.getName());
		ps.setInt(3, productVo.getPrice());
		ps.setString(4, productVo.getContent());
		int row = ps.executeUpdate();
		updateUserProducts(productVo.getUser_id());
		
		return row;
	}
	
	public ArrayList<ProductVo> getProductsById(String id) throws SQLException{
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		ProductVo product;
		
		String sql = "SELECT user_id, name, price, content, regdate FROM app_products WHERE user_id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			product = new ProductVo();
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("price"));
			product.setReg_date(rs.getString("regdate"));
			product.setUser_id(rs.getString("user_id"));
			product.setContent(rs.getString("content"));
			list.add(product);
		}
		
		return list;
	}
	
	
	
}
