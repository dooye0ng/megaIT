# Day 08 Homework
### 문제 1. Scanner를 사용하여 6개의 데이터를 입력 받고, 이들을 nums 배열에 저장하세요.
### 문제 2. 입력 받은 값 중, 20 이상 100 이하인 원소만 출력하세요.
### 문제 3. 입력 받은 값 중, 최댓값과 최솟값을 출력하세요.
```java
public class Homework_123{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] nums = new int[6];
		
		// 문제 1번
		System.out.print("정수 6개를 입력해주세요: ");
		for(int i=0; i<nums.length; ++i) {
			nums[i] = sc.nextInt();
		}
		
		// 문제 2번
		System.out.println("< 20이상 100이하인 수 >");
		for(int i=0; i<nums.length; ++i) {
			if(nums[i] >= 20 && nums[i] <= 100) {
				System.out.println(nums[i]);
			}
		}
		
		// 문제 3번
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i=0; i<nums.length; ++i) {
			if(nums[i] > max) {
				max = nums[i];
			}
			if(nums[i] < min) {
				min = nums[i];
			}
		}
		
		System.out.println("최댓값: " + max);
		System.out.println("최솟값: " + min);
	}
}
```
```
정수 6개를 입력해주세요: 15 29 27 29 94 513
< 20이상 100이하인 수 >
29
27
29
94
최댓값: 513
최솟값: 15
```
### 문제 4. 오름차순(작은->큰)으로 정렬하여 모든 원소를 출력하세요.
```java
public class Homewor_4{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] nums = new int[6];

		System.out.print("정수 6개를 입력해주세요: ");
		for(int i=0; i<nums.length; ++i) {
			nums[i] = sc.nextInt();
		}
		
		// 버블 정렬
		int tmp;
		for(int i=0; i<nums.length; ++i) {
			for(int j=0; j < nums.length - (i + 1); ++j) {
				if(nums[j] > nums[j+1]) {
					tmp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = tmp;
				}
			}
		}
		
		for(int i=0; i<nums.length; ++i) {
			System.out.print(nums[i] + " ");
		}
		
	}
}

```
```
정수 6개를 입력해주세요: 9 5 4 8 7 6 
4 5 6 7 8 9 
```

### 추가문제 하-1: int 형 6칸짜리 배열을 생성하세요
```java
public class Homework_ha1{
	public static void main(String[] args) {
		int[] arr = new int[6]; 
	}
}
```
### 추가문제 하-2: 다음 출력 결과를 예상하세요.
```java
public class Homework_ha2{
	public static void main(String[] args) {
		int[] a = new int[2]; 
		System.out.println(a[0]); // 답 : 0 
		System.out.println(a[1]); // 답 : 0 
		
		double[] b = new double[2];
		System.out.println(b[0]); // 답 : 0.0
		System.out.println(b[1]); // 답 : 0.0

		String[] c = new String[2];
		System.out.println(c[0]); // 답 : null
		System.out.println(c[1]); // 답 : null


		char[] d = new char[2];
		System.out.println(d[0]); // 답 : ' '
		System.out.println(d[1]); // 답 : ' '


		boolean[] e = new boolean[2];
		System.out.println(e[0]); // 답 : false
		System.out.println(e[1]); // 답 : false 
	}
}
```
### 추가문제 하-3: 사용자에게 배열의 칸 개수를 입력 받고, 해당 정수의 크기만큼 정수형 배열을 생성하세요.
### 추가문제 하-4: (3)번에서 생성된 배열에 다음 기능을 추가하세요. 

> ㄱ.  0  ~ n-1 까지의 숫자를 배열에 순서대로 저장하세요.
>입력 :  3  ===> 결과 :  [0,  1,  2] 입력 :  5  ===> 결과 :  [0,  1,  2,  3,  4]
> ㄴ. 배열의 가장 마지막 원소부터 0번 원소까지 역순으로 출력하세요.
> ㄷ.  for문을 사용하여 배열에 저장된 실제 원소들을 역순으로 재배치 하세요.  (난이도 중) 
> sysout(배열[0]);  // 3  sysout(배열[1]);  // 2  sysout(배열[2]);  // 1  sysout(배열[3]);  // 0  (for문을 쓰되 for문의 실행 횟수가 n/2이 되도록하세요.  (5칸 배열이면 2회만에,  10칸 배열이면 5회만에 for문이 종료되도록))

```java
import java.util.Scanner;

public class Homework_ha34{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("배열의 크기를 입력해주세요: ");
		int num = sc.nextInt();
		int[] nums = new int[num];
		System.out.print("(ㄱ): ");
		for(int i=0; i<nums.length; ++i) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
		
		// (ㄱ): 원소 저장
		for(int i=0; i<num; ++i) {
			nums[i] = i;
		}
		
		// (ㄴ): 역순 출력
		System.out.print("(ㄴ) 출력만 : ");
		for(int i=num; i>0; --i) {
			System.out.print(nums[i-1] + " ");
		}
		System.out.println();
		
		// (ㄷ): 역순 재배치
		for(int i=0; i<num/2; ++i) {
			int left = i, right = num - (i+1);
			
			int tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;
		}
		System.out.print("(ㄷ) 실제 배열 배치 : ");
		for(int i=0; i<num; ++i) {
			System.out.print(nums[i] + " ");
		}
	}
}
```
```
배열의 크기를 입력해주세요: 8
(ㄱ): 0 0 0 0 0 0 0 0 
(ㄴ) 출력만 : 7 6 5 4 3 2 1 0 
(ㄷ) 실제 배열 배치 : 7 6 5 4 3 2 1 0 
```
### 추가문제 하-5: 사용자에게 배열의 칸 개수를 입력 받고, 해당 정수의 크기만큼 String형 배열을 생성하고, 사용자에게 입력받은 단어들을 순차적으로 배열에 저장하세요.
```java
import java.util.Scanner;

public class Homework_ha5{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("칸 개수를 입력하세요: ");
		int num = sc.nextInt();
		
		String[] words = new String[num];
		
		for(int i=0; i<num; ++i) {
			System.out.print("단어를 입력하세요: ");
			words[i] = sc.next();
		}
		
		for(int i=0; i<num; ++i) {
			System.out.print(words[i] + " ");
		}
	}
}
```
```
칸 개수를 입력하세요: 4
단어를 입력하세요: Home
단어를 입력하세요: Hello
단어를 입력하세요: Java
단어를 입력하세요: Python
Home Hello Java Python 
```
### 추가문제 중-1: https://www.acmicpc.net/problem/8958
```java
import java.util.Scanner;

public class Homework_jng2{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		int[] answer = new int[tc];
		
		String quizRes;
		
		for(int t=0; t<tc; ++t) {
			quizRes = sc.next();
			int score = 0, con = 0;
			
			for(int i=0; i<quizRes.length(); ++i) {
				if(quizRes.charAt(i) == 'O') {
					score += ++con;
				}
				else {
					con = 0;
				}
			}
			answer[t] = score;
		}
		
		for(int i=0; i<tc; ++i) {
			System.out.println(answer[i]);
		}
	}
}
```
```
5
OOXXOXXOOO
OOXXOOXXOO
OXOXOXOXOXOXOX
OOOOOOOOOO
OOOOXOOOOXOOOOX
10
9
7
55
30
```
### 추가문제 중-2: dates 배열은 다음과 같이 1~12월의 최대 일자가 들어있습니다. 
> int[] dates =  {31,  28,  31,  30,  31,  30,  31,  31,  30,  31,  30,  31};
> 
> [1] dates 배열을 활용하여 1/1일부터 사용자에게 입력 받은 월/일 까지 며칠이 소요되는지 출력하세요. 
> 단, 사용자에게 해는 따로 입력받지 않기때문에 윤년은 없다고 가정합니다. 
> 예)
> 월 :  12 일 :  31  ==>  364일 소요!
> 월 :  4 일 :  12  ==>  101일
> 원리) 
> 4/12 의 결과를 계산하려면 1  /  1  ~  1  /  31  =>  31  (dates[0])  2  /  1  ~  2  /  28  =>  28  (dates[1])  3  /  1  ~  3  /  31  =>  31  (dates[2])  4  /  1  ~  4  /  12  =>  12  (사용자가 입력한 일)  =>  31  +  28  +  31  +  12  -  1  =  101일
> 
> [2] 시작월/일과 목표 월/일 을 각각 입력 받고 d-day 계산기를 만드세요. 단, year는 입력받지 않기때문에 d-day의 최댓값은 364일로 가정합니다.
> 예)
> 시작 :  9/26 목표 :  11/23  ==> 결과 : d-day 58  !!!
> 시작 :  1/1 목표 :  12/31  ==> 결과 : d-day 364  !!!
> 시작 :  12/31 목표 :  1/1  ==> 결과 : d-day 1  !!!
> 시작 :  4/12 목표 :  4/11  ==> 결과 : d-day 364  !!!
> 원리)
> start :  1/1  ~ 시작 월/일까지 며칠이 소요되는지 계산합니다. end :  1/1  ~ 목표 월/일까지 며칠이 소요되는지 계산합니다. end-start 를 합니다. 이때 음수가 나온다면 목표일이 시작일보다 앞서있다는 의미입니다.  (즉 목표일이 이듬해) 이 경우 +365를 하면 됩니다.
```java
import java.util.Scanner;

public class Homwork_jng2{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] dates = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		
		// [1]번
		System.out.print("월: ");
		int mon = sc.nextInt();
		System.out.print("일: ");
		int day = sc.nextInt();
		
		int res = 0;
		
		for(int i=0; i<mon-1; ++i) {
			res += dates[i];
		}
		res += (day-1);
		
		System.out.println(res + "일 소요!");
		System.out.println();
		
		// [2]번
		System.out.print("시작 월: ");
		int startMonth = sc.nextInt();
		System.out.print("시작 일: ");
		int startDay = sc.nextInt();
		
		System.out.print("목표 월: ");
		int endMonth = sc.nextInt();
		System.out.print("목표 일: ");
		int endDay = sc.nextInt();
		
		int start=0, end=0;
		
		for(int i=0; i<startMonth-1; ++i) {
			start += dates[i];
		}
		start += (startDay-1);
		
		for(int i=0; i<endMonth-1; ++i) {
			end += dates[i];
		}
		end += (endDay-1);
		
		int d_day = end - start;
		if(d_day < 0) {
			d_day += 365;
		}
		
		System.out.println("d-day " + d_day + " !!");
	}
}
```
```
월: 4
일: 12
101일 소요!

시작 월: 4
시작 일: 12
목표 월: 4
목표 일: 11
d-day 364 !!
```
### 추가문제 중-3: 아래 조건을 만족하세요
> "김",  "이",  "박",  "최",  "한" 등의 대한민국 성씨를 저장할 배열을 만들고, 성씨들을 저장하세요. 
> "피카츄",  "라이츄",  "파이리",  "꼬부기",  "버터풀",  "야도란",  "피죤투" 를 저장할 배열을 만들고 이름들을 저장하세요. 
> 
> (1) 총 10개의 성+이름 조합을 출력하세요.  (Math.random()을 사용하며, 중복 조합을 허용합니다) 
> 
> (2) 조합가능한 모든 이름을 출력하세요.
```java
public class Homework_jng3{
	public static void main(String[] args) {
		String[] lastName = {"김", "이", "박", "최", "한"};
		String[] firstName = {"피카츄", "라이츄", "파이리", "꼬부기", "버터풀", "야도란", "피죤투"};
		
		// 1번
		
		int randLast;
		int randFirst;
		
		for(int i=0; i<10; ++i) {
			randLast = (int)(Math.random() * lastName.length);
			randFirst = (int)(Math.random() * firstName.length);
			
			String fullName = lastName[randLast] + " " + firstName[randFirst];
			System.out.println(i + "." +fullName);
		}
		System.out.println();
		
		// 2번
		int no = 0;
		for(int i=0; i<lastName.length; ++i) {
			for(int j=0; j<firstName.length; ++j) {
				String fullName = lastName[i] + " " + firstName[j];
				System.out.println(++no + "." + fullName);
			}
		}
	}
}
```
```
0.최 파이리
1.김 야도란
2.최 라이츄
3.한 라이츄
4.이 파이리
5.김 피카츄
6.한 피카츄
7.이 라이츄
8.이 버터풀
9.김 꼬부기

1.김 피카츄
2.김 라이츄
3.김 파이리
4.김 꼬부기
5.김 버터풀
6.김 야도란
7.김 피죤투
8.이 피카츄
9.이 라이츄
10.이 파이리
11.이 꼬부기
12.이 버터풀
13.이 야도란
14.이 피죤투
15.박 피카츄
16.박 라이츄
17.박 파이리
18.박 꼬부기
19.박 버터풀
20.박 야도란
21.박 피죤투
22.최 피카츄
23.최 라이츄
24.최 파이리
25.최 꼬부기
26.최 버터풀
27.최 야도란
28.최 피죤투
29.한 피카츄
30.한 라이츄
31.한 파이리
32.한 꼬부기
33.한 버터풀
34.한 야도란
35.한 피죤투
```
### 추가문제 중-4: https://www.acmicpc.net/problem/4344
```java
import java.util.Scanner;

public class Homework_jng4{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		
		for(int t=0; t<tc; ++t) {			
			int n = sc.nextInt();
			int sum = 0;
			double avg = 0.0;
			
			int[] scores = new int[n];
			
			for(int i=0; i<n; ++i) {
				scores[i] = sc.nextInt();
				sum += scores[i];
			}
			
			avg = (double)sum / n;
			
			int overAvg = 0;
			
			for(int i=0; i<n; ++i) {
				if(scores[i] > avg) {
					++overAvg;
				}
			}
			
			double res = 100 * (double)overAvg / n;
			System.out.printf("%.3f%%\n",res);
		}
	}
}
```
```
5
5 50 50 70 80 100
> 40.000%
7 100 95 90 80 70 60 50
> 57.143%
3 70 90 80
> 33.333%
3 70 90 81
> 66.667%
9 100 99 98 97 96 95 94 93 91
> 55.556%
```
### 추가문제 중-5: 호텔관리 프로그램 만들기
> step1) 사용자에게 호텔의 방 개수를 입력 받습니다.
> step2) 방개수와 동일한 크기의 배열을 생성합니다.  (5개면 5칸짜리 배열 생성)
> step3) 사용자 메뉴를 메시지로 보여주고 메뉴를 선택 받습니다.
> < 메뉴 > 
> (1) 체크인
> (2) 체크아웃
> (3) 현황 보기
> (0) 종료하기
>
> [1] 체크인
> 방 호수(1번부터 시작)를 입력 받습니다. 방이 이미 입실 중이면 "입실 중인 방은 체크인 하실 수 없습니다."를 출력하세요. 빈 방인 경우 "입실 완료!"를 출력하고 메뉴로 돌아갑니다.
> 
> [2] 체크아웃
> 
> 방 호수(1번부터 시작)를 입력 받습니다. 방이 빈 방이면 "빈 방은 체크아웃 하실 수 없습니다."를 출력하세요. 체크인 상태인 경우 "퇴실 완료!"를 출력하고 메뉴로 돌아갑니다.
> 
>  [3]  '방호수 - 상태'를 출력합니다.
>  출력 예) 
>  1호 : 빈 방
>  2호 : 입실 중
>  3호 : 입실 중
>  4호 : 빈 방
>  5호 : 빈 방
>
> [0] 종료 반복을 종료하고 '영업 종료' 를 출력합니다. 
> 
> (힌트 : 사용자가 4호에 입실한다면 [3]번칸에 1을 저장하고 퇴실한다면 0을 저장합니다. 즉 투숙객이 있음은 1로 없으면 0으로 표시합니다.) step4) 사용자가 메뉴에서 0을 입력할 때까지  (3) 과정을 진행합니다.
```java
import java.util.Scanner;

public class matrix_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("방의 개수를 입력하세요: ");
		int roomNum = sc.nextInt();
		int[] rooms = new int[roomNum];
		int menu;
		
		while(true) {
			System.out.println("\n=========");
			System.out.println("< MENU >");
			System.out.println("1. 체크 인");
			System.out.println("2. 체크 아웃");
			System.out.println("3. 현황 보기");
			System.out.println("0. 종료하기");
			
			System.out.print("> 메뉴 선택: ");
			menu = sc.nextInt();
			
			if(menu == 0) {
				break;
			}
			else if(menu == 1) {
				System.out.print("몇 호실에 입실하세요? : ");
				int checkIn = sc.nextInt();
				if(rooms[--checkIn] != 0) {
					System.out.println("입실 중인 방은 체크인 하실 수 없습니다.");
					continue;
				}
				rooms[checkIn] = 1;
				System.out.println("입실 완료!");
			}
			else if(menu == 2) {
				System.out.print("몇 호실 체크 아웃하시나요? : ");
				int checkOut = sc.nextInt();
				if(rooms[--checkOut] == 0) {
					System.out.println("빈 방은 체크 아웃 하실 수 없습니다.");
					continue;
				}
				rooms[checkOut] = 0;
				System.out.println("퇴실 완료!");
			}
			else if(menu == 3) {
				for(int i=0; i<roomNum; ++i) {
					String status = "입실 중";
					if(rooms[i] == 0) {
						status = "빈 방";
					}
					System.out.println(i+1 + "호 : " + status);
				}
			}
			else {
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		
		System.out.println("\n영업 종료");
	}
}
```

```
방의 개수를 입력하세요: 6

=========
< MENU >
1. 체크 인
2. 체크 아웃
3. 현황 보기
0. 종료하기
> 메뉴 선택: 1
몇 호실에 입실하세요? : 3
입실 완료!

=========
< MENU >
1. 체크 인
2. 체크 아웃
3. 현황 보기
0. 종료하기
> 메뉴 선택: 3
1호 : 빈 방
2호 : 빈 방
3호 : 입실 중
4호 : 빈 방
5호 : 빈 방
6호 : 빈 방

=========
< MENU >
1. 체크 인
2. 체크 아웃
3. 현황 보기
0. 종료하기
> 메뉴 선택: 1
몇 호실에 입실하세요? : 6
입실 완료!

=========
< MENU >
1. 체크 인
2. 체크 아웃
3. 현황 보기
0. 종료하기
> 메뉴 선택: 3
1호 : 빈 방
2호 : 빈 방
3호 : 입실 중
4호 : 빈 방
5호 : 빈 방
6호 : 입실 중

=========
< MENU >
1. 체크 인
2. 체크 아웃
3. 현황 보기
0. 종료하기
> 메뉴 선택: 2
몇 호실 체크 아웃하시나요? : 3
퇴실 완료!

=========
< MENU >
1. 체크 인
2. 체크 아웃
3. 현황 보기
0. 종료하기
> 메뉴 선택: 2
몇 호실 체크 아웃하시나요? : 1
빈 방은 체크 아웃 하실 수 없습니다.

=========
< MENU >
1. 체크 인
2. 체크 아웃
3. 현황 보기
0. 종료하기
> 메뉴 선택: 3
1호 : 빈 방
2호 : 빈 방
3호 : 빈 방
4호 : 빈 방
5호 : 빈 방
6호 : 입실 중

=========
< MENU >
1. 체크 인
2. 체크 아웃
3. 현황 보기
0. 종료하기
> 메뉴 선택: 0
영업 종료

```
### 추가문제 6: 로또 생성기 만들기
```java
import java.util.Scanner;

public class matrix_2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] userNumbers = new int[6];
		int[] lottoNumbers = new int[6];
		
		System.out.println("6개의 숫자를 입력해주세요(1 ~ 45)");
		for(int i=0; i<userNumbers.length; ++i) {
			userNumbers[i] = sc.nextInt();
		}
		
		// 로또 번호 생성
		for(int i=0; i<lottoNumbers.length; ++i) {
			int lotto = (int)(Math.random() * 45) + 1;
			
			int j = 0;
			while(j < i) {
				if(lotto == lottoNumbers[j]){
					lotto = (int)(Math.random() * 45) + 1;
					j = 0;
					continue;
				}
				++j;
			}
			lottoNumbers[i] = lotto;
		}
		
		// 로또 번호 정렬
		for(int i=0; i<lottoNumbers.length; ++i) {
			for(int j=0; j<lottoNumbers.length - (i+1); ++j) {
				if(lottoNumbers[j] > lottoNumbers[j+1]) {
					int tmp = lottoNumbers[j];
					lottoNumbers[j] = lottoNumbers[j+1];
					lottoNumbers[j+1] = tmp;
				}
			}
		}
		
		// 당첨 번호 출력
		System.out.println("==== 당첨 번호 ====");
		for(int i=0; i<lottoNumbers.length; ++i) {
			System.out.print(lottoNumbers[i] + " ");
		}
		System.out.println("\n==================");
		
		int match = 0;
		int[] matchNumbers = new int[6];
		
		for(int i=0; i<userNumbers.length; ++i) {
			for(int j=0; j<lottoNumbers.length; ++j) {
				if(userNumbers[i] == lottoNumbers[j]) {
					matchNumbers[match++] = userNumbers[i];
					break;
				}
			}
		}
		System.out.println("\n======= 결과 =======");
		System.out.println(match + "개 일치합니다.");
		System.out.print("일치하는 번호 개수: " + match + "개\n");
		
		for(int i=0; i<match; ++i) {
			for(int j=0; j<match - (i+1); ++j) {
				if(matchNumbers[j] > matchNumbers[j+1]) {
					int tmp = matchNumbers[j];
					matchNumbers[j] = matchNumbers[j+1];
					matchNumbers[j+1] = tmp;
				}
			}
		}
		
		if(match > 0) {
			System.out.print("일치하는 번호: ");
			for(int i=0; i<match; ++i) {
				System.out.print(matchNumbers[i] + " ");
			}
		}
		
	}
}
```
```
6개의 숫자를 입력해주세요(1 ~ 45)
24 26 27 28 44 45 
==== 당첨 번호 ====
1 14 24 29 34 44 
==================

======= 결과 =======
2개 일치합니다.
일치하는 번호 개수: 2개
일치하는 번호: 24 44 
```
