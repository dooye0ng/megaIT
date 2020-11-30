# Day 10 Homework
### > src/day10/Homework1.java
```java
package day10;

import java.util.Scanner;

public class Homework1 {
	public static void main(String[] args) {
		FunctionFactory funcFac = new FunctionFactory();
		Scanner sc = new Scanner(System.in);
		
		// strGugudan() test
		System.out.print("gugudan : ");
		int gugu = sc.nextInt();
		System.out.println("=====" + gugu + "=====");
		System.out.println(funcFac.strGugudan(gugu));

		// getAverage() test
		System.out.print("korean : ");
		int kr = sc.nextInt();
		System.out.print("english : ");
		int en = sc.nextInt();
		System.out.print("math : ");
		int ma = sc.nextInt();
		
		System.out.print("Average : ");
		System.out.println(funcFac.getAverage(kr, en, ma));

		// getRandomPokemon() test
		System.out.println();
		System.out.println("Randomly Choosen Pokemon : " + funcFac.getRandomPokemon());

		// getMax() test
		int[] arr = { 10, 25, 13, 72, 35, 41, 35, 22, 62 };
		System.out.print("\nArray status : ");
		for(int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println("\nMax number in arr is : " + funcFac.getMax(arr));
	}
}
```
### > src/day10/FunctionFactory.java
```java
package day10;

public class FunctionFactory {
	
	String strGugudan(int num) {
		String res = "";
		for(int i=1; i<10; ++i) {
			res += (Integer.toString(num) + " X " + Integer.toString(i) + " = " + Integer.toString(num * i) + "\n");
		}
		return res;
	}
	
	double getAverage(int kr, int en, int ma) {
		double res = (kr + en + ma) / 3.0;
		
		return res;
	}
	
	String getRandomPokemon() {
		String[] pokemons = {"피카츄", "라이츄", "파이리", "꼬부기"};
		int rand = (int)(Math.random() * pokemons.length);
		String res = pokemons[rand];
		
		return res;
	}
	
	int getMax(int[] arr) {
		int res = arr[0];
		
		for(int num : arr) {
			if(num > res) {
				res = num;
			}
		}
		
		return res;
	}
}
```
### 실행 결과
```
gugudan : 8
=====8=====
8 X 1 = 8
8 X 2 = 16
8 X 3 = 24
8 X 4 = 32
8 X 5 = 40
8 X 6 = 48
8 X 7 = 56
8 X 8 = 64
8 X 9 = 72

korean : 81
english : 97
math : 99
Average : 92.33333333333333

Randomly Choosen Pokemon : 파이리

Array status : 10 25 13 72 35 41 35 22 62 
Max number in arr is : 72
```