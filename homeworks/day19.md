# Day 19 Homeowork
### > Quiz01.java
```java
package com.mega.homework;

public class Quiz01 {
	private static final int NUMBER = 100000;
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		for(int i=1; i<=NUMBER; ++i) {
			System.out.println(i);
		}
		long end = System.currentTimeMillis();
		
		System.out.println("총 걸린 시간 : " + (end - start) * 0.001 + " 초");
	}
}
```
### 실행결과
```
1
2
3
.
.
.
100000
총 걸린 시간 : 0.299 초
```
### > Quiz02.java
```java
package com.mega.homework;

import java.util.Calendar;
import java.util.Scanner;

public class Quiz02 {
	private static final int SAT = 7;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calendar cal = Calendar.getInstance();
		String[] days = {"일", "월", "화", "수", "목", "금", "토"};
		String[][] calArr = new String[5][7];
		
		System.out.print("년도 : ");
		int year = sc.nextInt();
		System.out.print("월 : ");
		int month = sc.nextInt();
		
		if(year < 0 || month < 1 || month > 12) {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		
		int curWeek, curDay, totalDaysOfMonth;
		
		cal.set(year, month-1, 1);
		
		totalDaysOfMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		System.out.println("\n< " + year + "년 " + month + "월 >");
		for(String day : days) {			
			System.out.print(day + '\t');
		}
		System.out.println();
		
		for(int i=1; i<=totalDaysOfMonth; ++i) {
			curDay = cal.get(Calendar.DAY_OF_WEEK);
			curWeek = cal.get(Calendar.WEEK_OF_MONTH);
			// row : week, col : day
			calArr[curWeek-1][curDay-1] = curDay == SAT ? i + "\t\n" : i + "\t";
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		for(String[] week : calArr) {
			for(String day : week) {
				if(day == null) {
					System.out.print("\t");
					continue;
				}
				System.out.print(day);
			}
		}
	}
}
```
### 실행결과 1
```
년도 : 2020
월 : 11

< 2020년 11월 >
일	월	화	수	목	금	토	
1	2	3	4	5	6	7	
8	9	10	11	12	13	14	
15	16	17	18	19	20	21	
22	23	24	25	26	27	28	
29	30
```
### 실행결과 2
```
년도 : 1997
월 : 2

< 1997년 2월 >
일	월	화	수	목	금	토	
						1	
2	3	4	5	6	7	8	
9	10	11	12	13	14	15	
16	17	18	19	20	21	22	
23	24	25	26	27	28
```
### > Quiz03.java
```java
package com.mega.homework;

import java.util.Scanner;

public class Quiz03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inputStr;
		int aCnt = 0;
		int totalCnt = 0;
		System.out.println("아무거나 입력하세요(종료 : -1) : ");
		
		while(true) {
			inputStr = sc.next();
			if(inputStr.equals("-1")) {
				break;
			}
			
			for(int i=0; i<inputStr.length(); ++i) {
				if(inputStr.charAt(i) == ' ' || inputStr.charAt(i) == '\n') {
					continue;
				}
				
				if(inputStr.charAt(i) == 'a') {
					++aCnt;
				}
				
				++totalCnt;
			}
		}

		System.out.println("입력한 'a'의 총 개수 : " + aCnt);
		System.out.println("비출력 문자를 제외한 총 문자 개수 : " + totalCnt);
	}
}
```
### 실행결과 
```
아무거나 입력하세요(종료 : -1) : 
Hello
Java Python
Orange

apple
-1
입력한 'a'의 총 개수 : 4
비출력 문자를 제외한 총 문자 개수 : 26
```
### > Quiz04.java
```java
package com.mega.homework;

import java.util.Scanner;

public class Quiz04 {
	private static final char ALPHA_SIGN = '@';
	private static final char BLANK = ' ';
	
	public static boolean isValidPassword(String password) {
		if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isValidEmail(String email) {
		if(email.indexOf(BLANK) > -1) {
			return false;
		}
		// '@'가 처음(0) 있거나 (아이디가 없음), 없을 경우(-1)
		if(email.indexOf(ALPHA_SIGN) < 1) {
			return false;
		}
		
		// 정규 표현식 공부할게요...
		if(!email.endsWith("com") && !email.endsWith("co.kr") && !email.endsWith("net")) {
			return false;
		}
		
		if(!email.contains("naver") && !email.contains("gmail") && !email.contains("hanmail")) {
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean badEmail = true, badPassword = true;
		String email="", password="";
		
		while(badEmail) {			
			System.out.print("e-mail : ");
			email = sc.nextLine().trim();
			if(!isValidEmail(email)) {
				System.out.println("잘못된 이메일 형식입니다.");
				continue;
			}
			badEmail = false;
		}
		
		while(badPassword) {			
			System.out.print("password : ");
			password = sc.nextLine();
			if(!isValidPassword(password)) {
				System.out.println("잘못된 패스워드 형식입니다.");
				continue;
			}
			// 한번 더 확인
			System.out.print("[재확인] password : ");
			String password2 = sc.nextLine();
			if(!password.equals(password2)) {
				System.out.println("패스워드가 일치하지 않습니다.");
				System.out.println("다시 입력해주세요.");
				continue;
			}
			badPassword = false;
		}
		
		System.out.println();
		System.out.println("id : " + email.substring(0, email.indexOf(ALPHA_SIGN)));
		System.out.println("pw : " + password.substring(0,2) + password.substring(2).replaceAll("[a-z]|[A-Z]|[$@#!%*?&]|[0-9]", "*"));
		System.out.println("email : " + email);
	}
}
```
### 실행결과 1
```
e-mail : megaStudent1@gmail.com
password : Password12#@
[재확인] password : Password12#@

id : megaStudent1
pw : Pa**********
email : megaStudent1@gmail.com
```
### 실행결과 2
```
e-mail : @naver.com
잘못된 이메일 형식입니다.
e-mail : dff@hello
잘못된 이메일 형식입니다.
e-mail : good@gmail.com
password : password
잘못된 패스워드 형식입니다.
password : 234
잘못된 패스워드 형식입니다.
password : Abc!2
잘못된 패스워드 형식입니다.
password : ABCde12#$
[재확인] password : ABcdefg
패스워드가 일치하지 않습니다.
다시 입력해주세요.
password : aPple23#
[재확인] password : aPple23#

id : good
pw : aP******
email : good@gmail.com
```