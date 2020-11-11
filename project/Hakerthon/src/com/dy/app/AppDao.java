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
	
	public String getImgNameByRegdate(String regdate) throws SQLException {
//		System.out.println(regdate);
		String sql = "SELECT img_name FROM app_products WHERE regdate = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, regdate);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getString(1);
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
	public String getIdByRegdate(String regdate) throws SQLException {
		String sql = "SELECT user_id FROM app_products WHERE regdate = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, regdate);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getString(1);
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
		String sql = "INSERT INTO app_products(user_id, name, price, content, img_name) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, productVo.getUser_id());
		ps.setString(2, productVo.getName());
		ps.setInt(3, productVo.getPrice());
		ps.setString(4, productVo.getContent());
		ps.setString(5, productVo.getImg_name());
		int row = ps.executeUpdate();
		updateUserProducts(productVo.getUser_id());
		
		return row;
	}
	
	public int deleteProductByRegdate(String regdate) throws SQLException {
		String sql = "DELETE FROM app_products WHERE regdate = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, regdate);
		return ps.executeUpdate();
	}
	public int countUpSoldById(String id) throws SQLException {
		String sql = "UPDATE app_users SET sold = sold + 1 WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		return ps.executeUpdate();
	}
	
	public int getTotalSoldById(String id) throws SQLException {
		String sql = "SELECT sold FROM app_users WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	public UserVo updateUserVoById(String id) throws SQLException {
		UserVo userVo = new UserVo();
		String sql = "SELECT no, id, pw, rating, sold FROM app_users WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			userVo.setNo(rs.getInt("no"));
			userVo.setId(rs.getString("id"));
			userVo.setPassword(rs.getString("pw"));
			userVo.setRating(rs.getString("rating"));
			userVo.setSold(rs.getInt("sold"));
		}
		return userVo;
	}
	
	public int updateRatingById(String id) throws SQLException{
		int totalSold = getTotalSoldById(id);
		
		String level;
		if(totalSold <= 5) {
			return 0;
		}
		
		if(totalSold > 50) {
			level = "King";
		}
		else if(totalSold > 20) {
			level = "Adult";
		}
		else{
			level = "Newbie";
		}
		
		String sql = "UPDATE app_users SET rating = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, level);
		ps.setString(2, id);
		return ps.executeUpdate();
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
