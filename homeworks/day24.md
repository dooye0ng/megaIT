# Day 24 Homework
### > Homework01.java
```java
package day24.quiz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

class WordBook {
	private HashMap<String, String> wordMap;
	private static Scanner sc = new Scanner(System.in);
	
	public WordBook() {
		loadMap();
		if(null == wordMap) {
			wordMap = new HashMap<String, String>();
		}
	}
	
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
	
	// #### 추가 ####
	public void saveMap() {
		FileOutputStream fOut = null;
		ObjectOutputStream oOut = null;
		
		try {			
			fOut = new FileOutputStream("words.w");
			oOut = new ObjectOutputStream(fOut);
			
			oOut.writeObject(wordMap);
		} catch(IOException e) {
			
		} finally {
			try {
				if(null != oOut) {
					oOut.close();
				}
				if(null != fOut) {
					fOut.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		System.out.println("file saved!");
	}
	
	
	// #### 추가 ####
	public void loadMap() {
		FileInputStream fIn = null;
		ObjectInputStream oIn = null;
		
		try {
			fIn = new FileInputStream("words.w");
			oIn = new ObjectInputStream(fIn);
			
			Object tmp = oIn.readObject();

			if(null == tmp || !(tmp instanceof HashMap<?, ?>)) {
				System.out.println("loading failed!");
			}
			else {
				System.out.println("file loaded!");		
				setWordMap((HashMap<String, String>)tmp);
			}
		} catch(Exception e) {
			System.out.println("file created!");
		} finally {
			try {
				if(null != oIn) {
					oIn.close();
				}
				if(null != fIn) {
					fIn.close();
				}
			}catch(IOException e) {
				System.out.println("IOException");
			}
		}
	}
	
	// #### 추가 ####
	public void setWordMap(HashMap<String, String> map) {
		if(map != null && map instanceof HashMap<?, ?>) {
			this.wordMap = map;
			return;
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
	String cmd, kor, eng;
	WordBook myBook = new WordBook();
	
	//myBook.loadMap();	// #### 추가 ####
	
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
	
	myBook.saveMap();	// #### 추가 ####
	System.out.println("시스템을 종료합니다.");
	/*
	 * 21 일 차 homework에 추가기능 (영단어) 
	 *   추가기능1) 프로그램이 종료 되면 map 객체를 words.w 에 저장 
	 *   추가기능2) 프로그램이 실행 될때 words.w 에 저장된 map 객체를 꺼냄 
	 *   		 없는 경우 파일을 읽지 말고 map 객체 생성하여 프로그램 실행
	 */
	}
}	

```
### 실행결과
```
file created!

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
입력 : 3

--- 모든 단어 조회 ---
손 = hand

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 0

시스템을 종료합니다.
file saved!

file loaded!

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 1

--- 단어 추가 ---
한국어 : 발
영어 : feet
추가 성공!

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 3

--- 모든 단어 조회 ---
손 = hand
발 = feet

1. 단어 추가
2. 단어 검색
3. 모든 단어 보기
4. 퀴즈풀기
0. 종료
입력 : 0

시스템을 종료합니다.
file saved!
```