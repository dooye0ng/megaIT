package com.dy.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppDao {
	private static final String URL = "jdbc:mysql://127.0.0.1/myapp";
	private static final String USERNAME= "dba_dy";
	private static final String PASSWORD = "dydy";
	private static AppDao instance;
	private Connection conn;
	
	/**
	 * Constructor for AppDao following Singleton Pattern
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private AppDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	/**
	 * Get instance of AppDao according to Singleton Pattern
	 * <p>
	 * @return instance of AppDao
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static AppDao getInstance() throws ClassNotFoundException, SQLException {
		if(instance == null) {
			instance = new AppDao();
		}
		return instance;
	}
	
	/**
	 * Returns String type of image name searched by it's <code>regdate</code>
	 * <p>
	 * @param regdate product's register date
	 * @return (String) image's name 
	 * @throws SQLException
	 */
	public String getImgNameByRegdate(String regdate) throws SQLException {
		String sql = "SELECT img_name FROM app_products WHERE regdate = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, regdate);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getString(1);
	}
	/**
	 * Returns ArrayList containing all ProductVo in the database
	 * <p>
	 * @return All Products in database with type of ArrayList
	 * @throws SQLException
	 */
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
	
	/**
	 * Registers user in database with id and password
	 * <p>
	 * @param id user's id
	 * @param password user's password
	 * @return affected rows after INSERT INTO databases
	 * @throws SQLException
	 */
	
	public int registerUser(String id, String password) throws SQLException {
		String sql = "INSERT INTO app_users(id, pw) VALUES(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2,password);
		int rows = ps.executeUpdate();
		
		return rows;
	}
	
	/**
	 * Discriminates whether user of this application or not
	 * <p>
	 * @param id user's id
	 * @param pw user's password
	 * @return true if user of this application, false if not
	 * @throws SQLException
	 */
	public boolean isUser(String id, String pw) throws SQLException {
		String sql = "SELECT id, pw FROM app_users WHERE id = ? && pw = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}
	
	/**
	 * Returns total amount of products registered in this application
	 * <p>
	 * @return total amount of products 
	 * @throws SQLException
	 */
	public int getAllProductsCount() throws SQLException {
		String sql = "SELECT COUNT(*) FROM app_products";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	/**
	 * Returns UserVo instance searched by id
	 * <p>
	 * @param id user's id
	 * @return instance of UserVo that matches(with id)
	 * @throws SQLException
	 */
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
	
	/**
	 * Delete user's account in database for good
	 * <p>
	 * @param userVo instance to be deleted
	 * @return affected rows after delete
	 * @throws SQLException
	 */
	public int deleteUserAccount(UserVo userVo) throws SQLException {
		String sql = "DELETE FROM app_users WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, userVo.getId());
		return ps.executeUpdate();
	}
	
	/**
	 * Returns total amount of products belongs to user who identified with id
	 * <p>
	 * @param id user's id
	 * @return one's total amount of products
	 * @throws SQLException
	 */
	public int getUserProducts(String id) throws SQLException {
		String sql = "SELECT COUNT(*) FROM app_products WHERE user_id = (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getInt(1);
	}
	
	/**
	 * Returns id of product's owner
	 * <p>
	 * @param regdate product's registered date
	 * @return user's id who is selling product
	 * @throws SQLException
	 */
	public String getIdByRegdate(String regdate) throws SQLException {
		String sql = "SELECT user_id FROM app_products WHERE regdate = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, regdate);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getString(1);
	}
	
	/**
	 * Update user's total amount of product being sold
	 * <p>
	 * @param id user's id
	 * @return total count of affected rows 
	 * @throws SQLException
	 */
	public int updateUserProducts(String id) throws SQLException {
		String sql = "UPDATE app_users SET products = ? WHERE id = ?";
		int count = getUserProducts(id);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, count);
		ps.setString(2, id);
		return ps.executeUpdate();
	}
	
	/**
	 * Registers product with instance of ProductVo
	 * <p>
	 * 
	 * @param productVo instance of ProductVo
	 * @return total count of affected rows
	 * @throws SQLException
	 */
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
	
	/**
	 * Delete product with regdate of it
	 * <p>
	 * @param regdate product's registered date 
	 * @return total count of affected rows 
	 * @throws SQLException
	 */
	public int deleteProductByRegdate(String regdate) throws SQLException {
		String sql = "DELETE FROM app_products WHERE regdate = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, regdate);
		return ps.executeUpdate();
	}
	
	/**
	 * Update the user's sold count by one's id
	 * <p>
	 * @param id user's id
	 * @return total count of affected rows
	 * @throws SQLException
	 */
	public int countUpSoldById(String id) throws SQLException {
		String sql = "UPDATE app_users SET sold = sold + 1 WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		return ps.executeUpdate();
	}
	
	/**
	 * Returns total count of sold field by one's id
	 * <p>
	 * @param id user's id
	 * @return one's total count of sold 
	 * @throws SQLException
	 */
	public int getTotalSoldById(String id) throws SQLException {
		String sql = "SELECT sold FROM app_users WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	
	/**
	 * Update one's database and return instance of UserVo
	 * <p>
	 * @param id user's id
	 * @return updated instance of UserVo 
	 * @throws SQLException
	 */
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
	
	/**
	 * Update one's rating
	 * <p>
	 * if total sold <= 5 : no update(Baby)
	 * 5 < total sold <= 20 : Newbie
	 * 20 < total sold <= 50 : Adult
	 * 50 <= total sold : King
	 * <p>
	 * @param id user's id
	 * @return total count of affected rows
	 * @throws SQLException
	 */
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
	
	/**
	 * Returns ArrayList containing instance of ProductVo that belongs to user identified by id
	 * <p> 
	 * @param id user's id
	 * @return ArrayList for products
	 * @throws SQLException
	 */
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
