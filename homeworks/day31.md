# Day 31 Homework

## Quiz.java
```java
package com.mega.dy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * 메뉴)
 * + "[1] 전체 포켓몬 수 조회\n"
				+ "[2] 이름으로 조회 (포켓몬 이름 입력, 포겟몬 모든 정보 출력, 중복 가능)\n"
				+ "[3] 레벨순으로 조회 (모든 포켓몬의 모든 정보, 레벨 높은 순)\n"
				+ "[4] 이름순으로 조회 (모든 포켓몬의 모든 정보, 이름 내림차순)\n"
				+ "[5] 가장 높은 레벨 포켓몬 조회 (레벨이 가장 높은 포켓몬의 모든 정보, 중복시 이름순)\n"
				(6. 새 포켓몬 등록)
				(7. 포켓몬 삭제)
				(8. 모두 레벨업)
				(9. 레벨업 : 번호로 검색 후 레벨 업)
				+ "[0] 종료";
 */
public class Quiz {
	Scanner sc;
	
	private static Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String menu = "[MENU]\n"
			+ "[1] 전체 포켓몬 수 조회\n"
			+ "[2] 이름으로 조회\n"
			+ "[3] 레벨순으로 조회\n"
			+ "[4] 이름순으로 조회\n"
			+ "[5] 가장 높은 레벨 포켓몬 조회\n"
			+ "[6] 새 포켓몬 등록\n"
			+ "[7] 포켓몬 삭제\n"
			+ "[8] 모두 레벨업\n"
			+ "[9] 레벨업 (번호 검색)\n"
			+ "[0] 종료\n"
			+ "입력 : ";
	private String query;
	
	public Quiz() throws SQLException {
		setConn();
		setSc();
		start();
	}
	
	public void setSc() {
		sc = new Scanner(System.in);
	}

	public void setConn() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/studydb", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 오버로딩
	public void setPs(String query) throws SQLException {
		this.ps = conn.prepareStatement(query);
		setRs(ps);
	}
	// 오버로딩
	public void setPs(String query, String name) throws SQLException {
		this.ps = conn.prepareStatement(query);
		ps.setString(1, name);
		setRs(ps);
	}
	
	// 오버로딩
	public void setPs(String query, int no) throws SQLException {
		this.ps = conn.prepareStatement(query);
		ps.setInt(1, no);
		setRs(ps);
	}
	
	// 오버로딩
	public void setPs(String query, String name, int level) throws SQLException {
		this.ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, level);
		setRs(ps);
	}

	public void setRs(PreparedStatement ps) throws SQLException  {
		try {
			this.rs = ps.executeQuery();
		} catch(SQLException e) {
			ps.executeUpdate();
		}
	}
	
	public void printRs(ResultSet rs) throws SQLException {
		boolean found = false;
		
		while(rs.next()) {
			found = true;
			System.out.println("번호 : " + rs.getInt("no"));
			System.out.println("이름 : " + rs.getString("name"));
			System.out.println("레벨 : " + rs.getString("level"));
			System.out.println("등록일자 : " + rs.getString("regdate"));
			System.out.println();
		}
		
		if(!found) {
			System.out.println("없습니당 ");
		}
	}
	
	public void start() throws SQLException {
		String name;
		int level, no;
		
		while(true) {
			
			System.out.print(menu);
			String cmd = sc.next();
			
			switch(cmd) {
			case "0":
				System.out.println("프로그램을 종료합니다");
				return;
			case "1":
				query = "SELECT count(*) FROM pokemon";
				setPs(query);
				if(rs.next()) {
					System.out.println("포켓몬은 총 " + rs.getInt(1) + "마리!");
				}
				break;
			case "2":
				query = "SELECT * FROM pokemon WHERE name = ?";
				System.out.print("검색할 포켓몬의 이름 : ");
				name = sc.next();
				setPs(query, name);
				printRs(rs);
				break;
			case "3":
				query = "SELECT * FROM pokemon ORDER BY level DESC";
				setPs(query);
				printRs(rs);
				break;
			case "4":
				query = "SELECT * FROM pokemon ORDER BY name DESC";
				setPs(query);
				printRs(rs);
				break;
			case "5":
				query = "SELECT * FROM pokemon WHERE level = (SELECT MAX(level) FROM pokemon)";
				setPs(query);
				printRs(rs);
				break;
			case "6":
				query = "INSERT INTO pokemon VALUES(NULL, ?, ?, DEFAULT)";
				System.out.print("이름 : ");
				name = sc.next();
				System.out.print("레벨 : ");
				level = sc.nextInt();
				
				setPs(query, name, level);
				break;
			case "7":
				query = "DELETE FROM pokemon WHERE name = ?";
				System.out.print("이름 : ");
				name = sc.next();
				
				setPs(query, name);
				break;
			case "8":
				query = "UPDATE pokemon SET level = level + 1";
				setPs(query);
				break;
			case "9":
				query = "UPDATE pokemon SET level = level + 1 WHERE no = ?";
				System.out.print("번호 : ");
				no = sc.nextInt();
				setPs(query, no);
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) {
		try {
			new Quiz();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(null != conn) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
}
```