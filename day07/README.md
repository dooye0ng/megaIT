# Day07 Homework  
### 문제 1. 30 ~ 100 까지의 모든 정수의 합을 출력하세요  
```java
public class Homework_1 {
	public static void main(String[] args) {

		int sum = 0;
		
		for(int i=30; i<=1000; ++i) {
			sum += i;
		}
		
		System.out.println("30~100까지의 합: " + sum);
	}
}
```
```
30~100까지의 합: 500065
```

### 문제 2. 1 ~ 45의 랜덤수를 총 6개 출력하세요
```java
public class Homework_2 {
	public static void main(String[] args) {

		for(int i=0; i<6; ++i) {
			int randomNum = (int)(Math.random() * 45) + 1;
			
			System.out.println(i + 1 + "번째 랜덤 수: " + randomNum);
		}
	}
}
```
```
1번째 랜덤 수: 29
2번째 랜덤 수: 10
3번째 랜덤 수: 11
4번째 랜덤 수: 3
5번째 랜덤 수: 17
6번째 랜덤 수: 13
```

### 문제 3. 구구단 2단 ~ 9단까지를 모두 출력하세요
```java
public class Homework_3 {
	public static void main(String[] args) {
		
		for(int i=2; i<10; ++i) {
			for(int j=1; j<10; ++j) {
				System.out.println(i + " X " + j + " = " + i*j);
			}
		}

	}
}
```
```
2 X 1 = 2
2 X 2 = 4
2 X 3 = 6
2 X 4 = 8
2 X 5 = 10
2 X 6 = 12
2 X 7 = 14
2 X 8 = 16
2 X 9 = 18
3 X 1 = 3
3 X 2 = 6
3 X 3 = 9
    .
    .
    .
9 X 5 = 45
9 X 6 = 54
9 X 7 = 63
9 X 8 = 72
9 X 9 = 81

```
### 문제 4. 사용자에게 정수 n을 입력받고 "*"을 n번 출력하세요
```java
public class Homework_4 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("정수를 입력하세요: ");
		int num = sc.nextInt();
		
		for(int i=0; i<num; ++i) {
			System.out.print("*");
		}

	}
}

```
```
정수를 입력하세요: 5
*****

정수를 입력하세요: 7
*******
```
### 문제 5. 사용자에게 정수 n을 입력받고 n번째 피보나치 수를 출력하세요
```java
import java.util.Scanner;

public class Homework {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("n번째 피보나치 수(1 이상 입력): ");
		int num = sc.nextInt();
		int answer = 1;
		
		if(num > 2){
			int tmp, x=1, y=1;
			
			for(int i=0; i < num-2; ++i) {
				tmp = y;
				y += x;
				x = tmp;
			}
			
			answer = y;
		}
		
		System.out.println(num + "번째 피보나치 수는 " + answer + "입니다.");
	}
}
```
```
n번째 피보나치 수(1 이상 입력): 1
1번째 피보나치 수는 1입니다.

n번째 피보나치 수(1 이상 입력): 6
6번째 피보나치 수는 8입니다.

n번째 피보나치 수(1 이상 입력): 10
10번째 피보나치 수는 55입니다.
```
