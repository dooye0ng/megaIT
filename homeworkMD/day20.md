# Day 20 Homeowork
### > Quiz01.java
```java
package com.mega.homework;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *  User 클래스 
 *   	필드 : 아이디, 패스워드, 이메일 
 *   	메서드 : 
 *   	1. 생성자(String, String, String)
 *   	2. Getter Setter
 *   	3. toString 오버라이드 
 *   	4. hashCode(), equals() 오버라이드 (자동 완성 사용)
 *   	5. String getHiddenPassword() : 앞 두글자, 나머지 '*' 처리 
 *   	
 *  main
 *  	메뉴)
 *  		1. 회원 가입
 *  		2. 로그인  ==> 아이디, 패스워드 입력 받고 "성공!" / "실패" 출력
 *  		3. 모든 회원 조회 ==> 모든 회원들의 모든 정보 (비밀번호는 getHiddenPassword() 사용)
 *  		4. 회원 탈퇴 ==> 아이디, 패스워드 입력 받고 성공 시 삭제 진행, 없는 아이디라면 "미등록 회원" 출력
 *  		5. 회원 조회 (id로 검색) ==> 아이디 입력 받고 있으면 회원 모든 정보 출력, 없는 아이디라면 "미등록 회원" 출력
 * 			0. 종료
 */

class User {
	private String id, pw, email;
	
	public User(String id, String pw, String email) {
		setId(id);
		setPw(pw);
		setEmail(email);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}
	
	public String getHiddenPassword() {
		StringBuffer hidden = new StringBuffer();
		
		hidden.append(getPw().substring(0,2));
		
		for(int i=0; i<getPw().length()-2; ++i) {
			hidden.append('*');
		}
		
		return hidden.toString();
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return  "id : " + getId() +
				"/ pw : " + getHiddenPassword() +
				"/ email : " + getEmail();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj instanceof User) {
			User user = (User)obj;
			return getId().equals(user.getId()) && getPw().equals(user.getPw());
		}
		
		return false;
	}
}

public class Quiz01 {
	private static Scanner sc = new Scanner(System.in);
	
	private static void showUser(ArrayList<User> users, String id) {
		for(User u : users) {
			if(u == null){
				continue;
			}

			if(id.equals(u.getId())) {
				System.out.println(u.toString());
				return;
			}
		}
		System.out.println("미등록 회원");
	}
	
	private static void manageUser(ArrayList<User> users, boolean in) {
		System.out.println("--- 로그인 ---");
		
		System.out.print("id : ");
		String id = sc.next();
		for(User user : users) {
			if(user == null){
				continue;
			}
			
			if(id.equals(user.getId())) {
				System.out.print("pw : ");
				String pw = sc.next();
				
				if(pw.equals(user.getPw())) {
					if(in) {
						System.out.println("로그인 성공!");
					}
					else {
						users.remove(user);
						System.out.println("회원탈퇴 성공!");
					}
					return;
				}
			}
		}
		
		// 로그인이면(in = true) "일치하는 ~~", 회원탈퇴면(in = false) "미등록 ~~"
		System.out.println(in ? "일치하는 회원 정보가 없습니다." : "미등록 회원입니다.");
	}
	
	private static User signUp() {
		User u = null;
		
		System.out.println("--- 회원 가입 ---");
		System.out.print("사용하실 아이디를 입력하세요 : ");
		String id = sc.next();
		System.out.print("사용하실 패스워드를 입력하세요 : ");
		String pw = sc.next();
		System.out.print("사용하실 이메일을 입력하세요 : ");
		String email = sc.next();
		
		u = new User(id, pw, email);
		System.out.println("회원가입 성공!");
		return u;
	}
	
	public static void main(String[] args) {
		
		ArrayList<User> users = new ArrayList<>();
		String menu = "\n1. 회원가입\n"
					+ "2. 로그인\n"
					+ "3. 모든 회원 조회\n" 
					+ "4. 회원탈퇴\n"
					+ "5. 회원 정보 조회 (id로 검색)\n"
					+ "0. 종료\n\n"
					+ "메뉴 선택 : ";
		
		String cmd;
		
		loop:while(true) {
			System.out.print(menu);
			cmd = sc.next();
			
			switch(cmd) {
			case "0":
				break loop;
			case "1":
				users.add(signUp());
				break;
			case "2":
				if(users.isEmpty()) {
					System.out.println("로그인 가능한 회원정보가 없습니다.");
					continue;
				}
				
				manageUser(users, true); // 2번째 인자 : 로그인일때는 true, 회원 탈퇴일 때는 false
				break;
			case "3":
				if(users.isEmpty()) {
					System.out.println("조회 가능한 회원정보가 없습니다.");
					continue;
				}
				
				for(User user : users) {
					System.out.println(user.toString());
				}
				
				break;
			case "4":
				if(users.isEmpty()) {
					System.out.println("탈퇴할 회원정보가 없습니다.");
					continue;
				}
				
				manageUser(users, false); // 2번째 인자 false -> 회원 탈퇴
				break;
			case "5":
				System.out.print("조회하실 id : ");
				String id = sc.next();
				showUser(users, id);
				break;
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
		
		System.out.println("\n===== 시스템을 종료합니다 =====");
		
	}
}
```
### 실행결과
```

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 1
--- 회원 가입 ---
사용하실 아이디를 입력하세요 : megaZZang
사용하실 패스워드를 입력하세요 : happyJava##
사용하실 이메일을 입력하세요 : javaZZang@gmail.com
회원가입 성공!

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 1
--- 회원 가입 ---
사용하실 아이디를 입력하세요 : javaman33
사용하실 패스워드를 입력하세요 : over$java
사용하실 이메일을 입력하세요 : sarang@hanmail.net
회원가입 성공!

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 3
id : megaZZang/ pw : ha*********/ email : javaZZang@gmail.com
id : javaman33/ pw : ov*******/ email : sarang@hanmail.net

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 5
조회하실 id : javaman33
id : javaman33/ pw : ov*******/ email : sarang@hanmail.net

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 5
조회하실 id : megaroot
미등록 회원

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 2
--- 로그인 ---
id : megaZZang
pw : happyJava##
로그인 성공!

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 4
--- 로그인 ---
id : megaZZang
pw : happyJava##
회원탈퇴 성공!

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 3
id : javaman33/ pw : ov*******/ email : sarang@hanmail.net

1. 회원가입
2. 로그인
3. 모든 회원 조회
4. 회원탈퇴
5. 회원 정보 조회 (id로 검색)
0. 종료

메뉴 선택 : 0

===== 시스템을 종료합니다 =====
```