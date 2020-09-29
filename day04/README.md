# Day04 Homework  

### 1. 정수 1개를 입력받고 4의 배수이면 "4의 배수", 아니면 "4의 배수 아님"을 출력
```java
import java.util.Scanner;

public class Quiz01{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int num = 0;
      
        System.out.print("정수를 입력하세요: ");
        num = sc.nextInt();
        System.out.println((num%4 == 0) ? "4의 배수" : "4의 배수 아님");
    }
}
```
```
> 정수를 입력하세요: 4
> 4의 배수
```
### 2. 국, 영, 수 입력받고 평균 점수가 65점 이상이면 "1차 합격" 아니면 "1차 불합격" 출력
```java
import java.util.Scanner;

public class Quiz02{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
	  
        int kr, en, ma;
		double avg;
		
		System.out.print("국어 점수를 입력하세요: ");
		kr = sc.nextInt();
		System.out.print("영어 점수를 입력하세요: ");
		en = sc.nextInt();
		System.out.print("수학 점수를 입력하세요: ");
		ma = sc.nextInt();
		
		avg = (kr + en + ma) / 3.0;
		
		System.out.println(avg >= 65 ? "1차 합격" : "1차 불합격");
    }
}
```
```
> 국어 점수를 입력하세요: 87
> 영어 점수를 입력하세요: 74
> 수학 점수를 입력하세요: 91
> 1차 합격

```
### 3. 2번의 국영수가 모드 60 이상이면 "2차 합격" 아니면 "2차 불합격" 출력
```java
public class Quiz03{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
	  
        int kr, en, ma;
		double avg;
		
		System.out.print("국어 점수를 입력하세요: ");
		kr = sc.nextInt();
		System.out.print("영어 점수를 입력하세요: ");
		en = sc.nextInt();
		System.out.print("수학 점수를 입력하세요: ");
		ma = sc.nextInt();
		
		avg = (kr + en + ma) / 3.0;
		
		System.out.println(avg >= 65 ? "1차 합격" : "1차 불합격");

        System.out.println((kr >= 60) && (en >= 60) && (ma >= 60) ? "2차 합격" : "2차 불합격");
    }
}
```
```
> 국어 점수를 입력하세요: 97
> 영어 점수를 입력하세요: 95
> 수학 점수를 입력하세요: 59
> 1차 합격
> 2차 불합격
```
### 4. 시간을 정수(초)로 입력받아 시간, 분, 초로 출력
```java
public class Quiz04{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
	  
        int time;
		int hour, minute, second;
		System.out.print("시간을 입력하세요(초): ");
		time = sc.nextInt();
		
		second = time % 60;
		time /= 60;
		
		minute = time % 60;
		time /= 60;
		
		hour = time;
		
		System.out.println("결과: " + hour + "시간 " + minute + "분 " + second + "초 입니다.");
    }
}
```
```
> 시간을 입력하세요(초): 6543
> 결과: 1시간 49분 3초 입니다.
```
### 5. 정수 1개를 입력받고 4의 배수이면 "true", 4의 배수가 아니면 "false" 출력 (0입력시 "false")
```java
public class Quiz05{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int num;
        System.out.print("정수를 입력하세요: ");
		num = sc.nextInt();
		System.out.println(num == 0 ? "false" : (num % 4 == 0));
    }
}
```
```
> 정수를 입력하세요: 8
> true

> 정수를 입력하세요: 0
> false
```
### 6. 다음의 결과가 어떻게 될까
```java
public class Quiz06{
    public static void main(String[] args){
        int n = 1;
        System.out.println(n++ == 2); 
    }
}
```
```
>  false
```
> 이유: ++가 n 뒤에 있기 때문에(후위) 2와 비교할 당시에 n은 1이기 때문에 false가 된다.
### 7. 정수 해(year)를 입력받아 해당 년도가 윤년인지 평년인지 구분하시오
```java
public class Quiz07{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int year;

        System.out.print("정수 해(년도)를 입력하세요: ");
        year = sc.nextInt();

        // 삼항연산자 활용
        System.out.println((year % 400 == 0) ? "윤년" : (year % 100 == 0) ? "평년" : (year % 4 == 0) ? "윤년" : "평년");
    }
}
```
```
> 정수 해(년도)를 입력하세요: 2020
> 윤년

> 정수 해(년도)를 입력하세요: 4
> 윤년

> 정수 해(년도)를 입력하세요: 1967
> 평년
```