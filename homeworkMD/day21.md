# Day 20 Homeowork
### > Homework01.java
```java
package day21.quiz;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

class WordBook {
	private HashMap<String, String> wordMap = new HashMap<>();
	private static Scanner sc = new Scanner(System.in);
	
	public WordBook() {}
	
	public void addWord(String kor, String eng) {
		wordMap.put(kor, eng);
	}
	
	public String korToEng(String kor) {
		String eng = wordMap.get(kor);
		if(eng == null) {
			return "없음";
		}
		
		return eng;
	}
	
	public void seeAllWords() {
		Set<Entry<String, String>> eSet = wordMap.entrySet();
		
		for(Entry<String, String> e : eSet) {
			System.out.println(e.getKey() + " = " + e.getValue());
		}
	}
	
	public void startQuiz() {
		int len = wordMap.size();
		String[] korWords = wordMap.keySet().toArray(new String[len]);
		
		int randInt;
		String quit;
		
		while(true) {
			randInt = (int)(Math.random() * len);
			String kor = korWords[randInt];
			String answer = wordMap.get(kor);
			
			System.out.print("[Quiz] " + kor + "(은)는 영어로 무엇일까요? : ");
			String eng = sc.next();
			
	
			System.out.println(eng.equals(answer) ? "[Quiz] 정답입니다!" : "[Quiz] 오답입니다...");
			
			System.out.print("다시 하시겠습니까?(y/n) : ");
			quit = sc.next();
			
			if(quit.equals("n") || quit.equals("N")) {
				break;
			}
		}
	}
	
	
}

public class Homework01 {
	public static void main(String[] args) {
		String menu = "1. 단어 추가\n"
					+ "2. 단어 검색\n"
					+ "3. 모든 단어 보기\n"
					+ "4. 퀴즈풀기\n"
					+ "0. 종료\n입력 : ";
		
		// 키 : 영단어
		// 값 : 그 뜻(한국어)
		
		// 퀴즈 풀기 : 랜덤하게 한글단어 내고 영어 입력받기 
		// ex) "사과"(은)는 영어로 ? ==> 정답/오답 등
		
		Scanner sc = new Scanner(System.in);
		WordBook myBook = new WordBook();
		String cmd, kor, eng;
		
		loop:while(true) {
			System.out.println();
			System.out.print(menu);
			cmd = sc.next();
			
			System.out.println();
			switch(cmd) {
			case "0":
				break loop;
			case "1":			
				System.out.println("--- 단어 추가 ---");
				System.out.print("한국어 : ");
				kor = sc.next();
				System.out.print("영어 : ");
				eng = sc.next();
				
				myBook.addWord(kor, eng);
				System.out.println("추가 성공!");
				break;
			case "2":
				System.out.println("--- 단어 검색 ---");
				System.out.print("검색할 단어(한국어) : ");
				kor = sc.next();
				System.out.println("검색 결과 : " + myBook.korToEng(kor));
				break;
			case "3":
				System.out.println("--- 모든 단어 조회 ---");
				myBook.seeAllWords();
				break;
			case "4":
				System.out.println("--- 단어 퀴즈 ---");
				myBook.startQuiz();
				break;				
			}
		}
		
		System.out.println("시스템을 종료합니다.");
	}
}
```
### 실행결과
```

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 1

--- 단어 추가 ---
한국어 : 사과
영어 : apple
추가 성공!

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 1

--- 단어 추가 ---
한국어 : 손
영어 : hand
추가 성공!

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 1

--- 단어 추가 ---
한국어 : 시계
영어 : watch
추가 성공!

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 3

--- 모든 단어 조회 ---
손 = hand
시계 = watch
사과 = apple

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 2

--- 단어 검색 ---
검색할 단어(한국어) : 오리
검색 결과 : 없음

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 2

--- 단어 검색 ---
검색할 단어(한국어) : 시계
검색 결과 : watch

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 4

--- 단어 퀴즈 ---
[Quiz] 사과(은)는 영어로 무엇일까요? : apple
[Quiz] 정답입니다!
다시 하시겠습니까?(y/n) : y
[Quiz] 시계(은)는 영어로 무엇일까요? : tree
[Quiz] 오답입니다...
다시 하시겠습니까?(y/n) : y
[Quiz] 손(은)는 영어로 무엇일까요? : hand
[Quiz] 정답입니다!
다시 하시겠습니까?(y/n) : y
[Quiz] 손(은)는 영어로 무엇일까요? : feet
[Quiz] 오답입니다...
다시 하시겠습니까?(y/n) : n

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 0

시스템을 종료합니다.

```