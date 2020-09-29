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
