# Day 13 Homework
### > Homework1.java
```java
package day13;

import java.util.Scanner;

public class Homework1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Person[] humans = new Person[2];
		String name;
		int age;
		
		for(int i=0; i<humans.length; ++i) {
			System.out.print(i + ") 이름 : ");
			name = sc.next();
			System.out.print(i + ") 나이 : ");
			age = sc.nextInt();
			
			humans[i] = new Person(name, age);
		}
		humans[1].greet(humans[0]);
		humans[1].greet("꼬리선", 17);
		humans[1].greet("꼬부기");
		
		humans[0].greet(humans[1]);
		humans[0].greet("푸린", 7);
		humans[0].greet("고라파덕");
		
	}
}
```
### > Person.java
```java
package day13;

public class Person {
	String name;
	int age;
	
	Person(){
		this(null, 0);
	}
	
	Person(String name){
		this(name, 0);
	}
	
	Person(int age){
		this(null, age);
	}
	
	Person(String name, int age){
		setInfo(name, age);
	}
	
	void setInfo(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	void greet(String name) {
		System.out.print(this.name + " : ");
		System.out.println(name + "님 안녕하세요!");
	}
	
	void greet(String name, int age) {
		System.out.print(this.name + " : ");
		
		if(this.age >= age) {
			System.out.println(name + "(아)야 안녕?");
			return;
		}
		
		System.out.println(name + "님 안녕하세요~");
	}
	
	void greet(Person person) {
		System.out.print(this.name + " : ");
		
		if(this.age >= person.age) {
			System.out.println(person.name + "(아)야 안녕?");
			return;
		}
		
		System.out.println(person.name + "님 안녕하세요~");
	}	
}
```
### 실행 결과
```
0) 이름 : 피카츄
0) 나이 : 12
1) 이름 : 파이리
1) 나이 : 15
파이리 : 피카츄(아)야 안녕?
파이리 : 꼬리선님 안녕하세요~
파이리 : 꼬부기님 안녕하세요!
피카츄 : 파이리님 안녕하세요~
피카츄 : 푸린(아)야 안녕?
피카츄 : 고라파덕님 안녕하세요!
```
### > Homework2.java
```java
package day13;

import java.util.Scanner;

public class Homework2 {
	public static void main(String[] args) {
		Student[] students = new Student[4];
		Scanner sc = new Scanner(System.in);
		String name;
		int kr, en, ma;
		
		for(int i=0; i<students.length; ++i) {
			// 생성
			System.out.print("이름을 입력하세요 : ");
			name = sc.next();
			System.out.print("국어 점수를 입력하세요 : ");
			kr = sc.nextInt();
			System.out.print("영어 점수를 입력하세요 : ");
			en = sc.nextInt();
			System.out.print("수학 점수를 입력하세요 : ");
			ma = sc.nextInt();
			
			students[i] = new Student(name, kr, en, ma);
		}
		
		// 정보 총 출력
		for(Student st : students) {
			st.printInfo();
		}
		
	}
}
```
### > Student.java
```java
package day13;

/*
 * void setInfo(이름, 국, 영, 수)
 * 	- 인자를 모두 멤버에 저장
 * 	- 평균, 학점 자동 계산
 * 
 * void setGrade()
 * 	- 평균 기준으로 학점 저장
 * 
 * void setAvg()
 * 	- 평균 계산하여 저장
 * 
 * Student()
 * Student(이름)
 * Student(국, 영, 수)
 * Student(이름, 국, 영, 수)
 * 		==> this() 생성자 활용
 * 
 * ** 추가 메서드
 * boolean isValid(점수)
 * 	==> 들어온 점수가 0 이상 100 이하라면 true, 아니라면 false 리턴
 * 
 * void setKr(국)
 * void setEn(영)
 * void setMa(수)
 * 	==> isValid()를 활용하여 인자값을 검사, 올바른 점수라면 저장
 * 
 * Main 메서드 
 * 	==> Student 4칸 배열 생성
 * 	==> 다양하게 생성자 실행
 * 	==> 모든 정보 출력
 */

public class Student {
	String name;
	String grade;
	int en, kr, ma;
	double avg;
	
	Student(){
		this(null, 0, 0, 0);
	}
	
	Student(String name){
		this(name, 0, 0, 0);

	}
	Student(int kr, int en, int ma){
		this(null, kr, en, ma);
		
	}
	
	Student(String name, int kr, int en, int ma){
		setInfo(name, kr, en, ma);
	}
	
	
	boolean isValid(int score) {
		if(score>= 0 && score<= 100) {
			return true;
		}
		
		return false;
	}
	
	void setKr(int kr) {
		if(!isValid(kr)) {
			return;
		}
		this.kr = kr;
	}
	
	void setEn(int en) {
		if(!isValid(en)) {
			return;
		}
		this.en = en;
	}
	
	void setMa(int ma) {
		if(!isValid(ma)) {
			return;
		}
		this.ma = ma;
	}
	
	void setGrade(double avg) {
		switch((int)avg / 10) {
		case 10: case 9:
			this.grade = "A";
			return;
		case 8:
			this.grade = "B";
			return;
		case 7:
			this.grade = "C";
			return;
		case 6:
			this.grade = "D";
			return;
		default:
			this.grade = "F";
		}
	}

	void setAvg() {
		this.avg = (this.kr + this.en + this.ma) / 3.0;
		setGrade(this.avg);
	}
	
	void setInfo(String name, int kr, int en, int ma) {
		this.name = name;
		setKr(kr);
		setEn(en);
		setMa(ma);
		setAvg();
	}
	
	void printInfo() {
		System.out.println("===== 학생 정보 출력 =====");
		System.out.println("이름 : " + this.name);
		System.out.println("평균 성적 : " + this.avg);
		System.out.println("학점 : " + this.grade);
		System.out.println();
	}
	
}
```
### 실행 결과
```
이름을 입력하세요 : 홍길동
국어 점수를 입력하세요 : 91
영어 점수를 입력하세요 : 88
수학 점수를 입력하세요 : 94
이름을 입력하세요 : 김영희
국어 점수를 입력하세요 : 99
영어 점수를 입력하세요 : 73
수학 점수를 입력하세요 : 81
이름을 입력하세요 : 김철수
국어 점수를 입력하세요 : 56
영어 점수를 입력하세요 : 66
수학 점수를 입력하세요 : 59
이름을 입력하세요 : 박찬호
국어 점수를 입력하세요 : 92
영어 점수를 입력하세요 : 91
수학 점수를 입력하세요 : 78
===== 학생 정보 출력 =====
이름 : 홍길동
평균 성적 : 91.0
학점 : A

===== 학생 정보 출력 =====
이름 : 김영희
평균 성적 : 84.33333333333333
학점 : B

===== 학생 정보 출력 =====
이름 : 김철수
평균 성적 : 60.333333333333336
학점 : D

===== 학생 정보 출력 =====
이름 : 박찬호
평균 성적 : 87.0
학점 : B

```