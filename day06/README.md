# Day06 Homework  

### 실행 코드
- Scanner는 main 메서드 제일 위에서 한번 선언한걸로 계속 사용했습니다.

```java
import java.util.Scanner;

public class Assign {
  public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
//  ============ 문제 1-1 ============
//	정수 1개를 입력 받고, 2,3,5의 배수인지 각각 판별하세요
//	14 : 2의 배수
//	15 : 3의 배수 5의 배수
//	30 : 2의 배수 3의 배수 5의 배수
//	17 : 해당 사항 없음
	
	System.out.println("============ 문제 1-1 ============");
	String answer1 = "";
	System.out.print("정수를 입력하세요: ");
	
	int num = sc.nextInt();
	
	if(num % 2 != 0 && num % 3 != 0 && num % 5 != 0) {
		answer1 = "해당 사항 없음";
	}
	else {
		if(num % 2 == 0) {
			answer1 += "2의 배수 ";
		}
		if(num % 3 == 0) {
			answer1 += "3의 배수 ";
		}
		if(num % 5 == 0) {
			answer1 += "5의 배수 ";
		}
	}
	
	System.out.println(answer1);
	System.out.println();
	
//  ============ 문제 1-2 ============ 
//	추가문제2) 놀이동산에 놀이기구가 4개 있습니다.
//	나이와 키를 입력 받아 탑승 가능한 놀이기구의 이름과 개수를 출력하세요.
//	회전목마 : 80cm 이상 200cm 미만
//	
//	유령의집 : 15세 이상, 110 cm 이상
//	롤러코스터 : 13세 이상 60세 미만, 140 cm 이상
//	자이로드롭 : 10세 이상, 130 cm 이상
	
	System.out.println("============ 문제 1-2 ============");
	
	int count = 0;
	System.out.print("나이: ");
	int age = sc.nextInt();
	System.out.print("키: ");
	int height = sc.nextInt();
	
	if(height >= 80 && height < 200) {
		System.out.println("회전목마 이용 가능합니다.");
		count++;
		
		if(age > 15 && height >= 110) {
			System.out.println("유령의 집 이용 가능합니다.");
			count++;
		}
		
		if(age >= 13 && age < 60 && height >= 140) {
			System.out.println("롤러코스터 이용 가능합니다.");
			count++;
		}
		
		if(age >= 10 && height >= 130) {
			System.out.println("자이로드롭 이용 가능합니다.");
			count++;
		}
	}
	System.out.println("총  " + count + "개 탈 수 있습니다.");
	System.out.println();
	
//	============ 문제 2 ============ 
// 	다음을 switch-case 문을 사용하여 완성하세요.
//	< 사칙 연산 계산기 만들기 >
//	두 수와 기호(+, -, *, /)를 입력 받고
//	결과를 출력하세요.
	
	System.out.println("============ 문제 2 ============");
	String operator;
	double n1, n2;
	double answer2 = 0;
	
	System.out.print("식을 입력하세요(숫자1 기호 숫자2): ");
	n1 = sc.nextDouble();
	operator = sc.next();
	n2 = sc.nextDouble();
	
	switch(operator) {
	case "+":
		answer2 = n1 + n2;
		break;
	case "-":
		answer2 = n1 - n2;
		break;
	case "*":
		answer2 = n1 * n2;
		break;
	case "/":
		answer2 = n1 / n2;
		break;		
	}
	
	System.out.println(n1 + " " + operator + " " + n2 + " = " + answer2);
	System.out.println();
	
	
//	============ 문제 3 ============ 
//	UP & Down 게임을 만드세요.
	
	System.out.println("============ 문제 3 ============");
	
	int pickedNumber = (int)(Math.random() * 100) + 1;
	int userNumber = -1;
	String message;
	
	while(userNumber != pickedNumber) {
		System.out.print("Guess Number: ");
		userNumber = sc.nextInt();
		
		if(userNumber < pickedNumber) {
			message = "UP";
		}
		else if(userNumber > pickedNumber) {
			message = "DOWN";
		}
		else {
			message = "CORRECT";
		}
		System.out.println(message);
	}
	System.out.println();
	
//	============ 문제 4 ============
//	다음 코드는 에러가 납니다. 그 이유를 주관적인 생각을 담아 설명하세요.
//	
//	int a;
//	int num = 10;
//	if(num < 10) {
//		a = 0;
//	}
//	++a;
//	
//	에러가 나는 이유는 num < 10이 false이기 때문에 if문이 실행되지 않고, 따라서 a가 초기화
//	되지 않은 상태이기 때문에 ++해줄 기준이 없어서 에러가 나는거 같습니다
	
	
//	============ 문제 5 ============
//	while문 나머지 문제를 푸세요
//	문제 5-1) 100부터 1까지 출력하세요
	
	System.out.println("============ 문제 5-1 ============");
	int n = 100;
	while(n > 0) {
		System.out.println(n--);
	}
	System.out.println();


//	문제 5-2) 
//	 사용자가 -1을 입력할 때 까지 정수를 모두 입력 받는다.
//	-1 입력 후 반복이 끝나면 사용자가 입력한 모든 정수의 합을 출력하세요.
	
	System.out.println("============ 문제 5-2 ============");
	int sum = 0;
	int input = 0;
	
	while(input != -1) {
		sum += input;
		System.out.print("숫자를 입력하세요: ");
		input = sc.nextInt();
	}
	
	System.out.println("총 합: " + sum);
  }
}
```
### 실행 결과
```
============ 문제 1-1 ============
정수를 입력하세요: 5
5의 배수 

============ 문제 1-2 ============
나이: 14
키: 180
회전목마 이용 가능합니다.
롤러코스터 이용 가능합니다.
자이로드롭 이용 가능합니다.
총  3개 탈 수 있습니다.

============ 문제 2 ============
식을 입력하세요(숫자1 기호 숫자2): 2 * 8
2.0 * 8.0 = 16.0

============ 문제 3 ============
Guess Number: 50
UP
Guess Number: 70
DOWN
Guess Number: 60
DOWN
Guess Number: 55
DOWN
Guess Number: 52
UP
Guess Number: 53
CORRECT

============ 문제 4 ============
(주석: 에러가 나는 이유는 num < 10이 false 이기 때문에 if문이 실행되지 않고,  
따라서 a가 초기화 되지 않은 상태이기 때문에 ++해줄 기준이 없어서 에러가 나는거 같습니다)

============ 문제 5 ============
100
99
98
.
.
.
1

============ 문제 5-2 ============
숫자를 입력하세요: 9
숫자를 입력하세요: 1
숫자를 입력하세요: 6
숫자를 입력하세요: -1
총 합: 16
```