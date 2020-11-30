# Day 17 Homeowork
### > Homework.java
```java
package com.mega.homework;

import java.util.Scanner;

interface Transportation {
	/**
	 * 성인 최소 나이는 {@value}
	 */
	int REQUIRED_ADULT_AGE = 20;
	
	/**
	 * @param age 나이
	 * @param km 거리
	 * @return 요금
	 */
	
	/* public abstract */ int getCharge(int age, int km);
}


class Bus implements Transportation{
	public static final int BASE_CHARGE = 1000;
	public static final String NAME = "BUS";
	
	public int getCharge(int age, int km) {
		return BASE_CHARGE + (int)(age < REQUIRED_ADULT_AGE ? km / 10 * 100 * 0.8 : km / 10 * 100);
	};
}

class Taxi implements Transportation{
	public static final int BASE_CHARGE = 4000;
	public static final String NAME = "TAXI";
	
	public int getCharge(int age, int km) {
		return BASE_CHARGE + (km - 10) * 100;
	};
}

class Subway implements Transportation{
	public static final int BASE_CHARGE_ADULT = 1250;
	public static final int BASE_CHARGE_KID = 750;
	public static final String NAME = "SUBWAY";
	
	public int getCharge(int age, int km) {
		if(age < REQUIRED_ADULT_AGE) {
			return BASE_CHARGE_KID + km / 10 * 50;
		}
		
		return BASE_CHARGE_ADULT + km / 10 * 100;
	};
}

class Train implements Transportation{
	public static final int BASE_CHARGE = 15000;
	public static final String NAME = "TRAIN";
	
	public int getCharge(int age, int km) {
		int totalCharge = BASE_CHARGE + km / 30 * 1000;
		return age < REQUIRED_ADULT_AGE ? (int)(totalCharge * 0.5) : totalCharge;
	};
}

public class Homework {
	public static final int ADULT = Transportation.REQUIRED_ADULT_AGE + 1;
	public static final int KID = Transportation.REQUIRED_ADULT_AGE - 1;
	
	public static void main(String[] args) {
		Transportation[] ts = { new Bus(), new Taxi(), new Subway(), new Train() };
		Scanner sc = new Scanner(System.in);
		
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("거리(km) : ");
		int km = sc.nextInt();
		
		System.out.println("\n==== " + km + "km 요금표 ====\n");
		
		for(Transportation t : ts) {
			if(t instanceof Bus){
				System.out.println("----" + Bus.NAME + "----");
			}
			else if(t instanceof Taxi){
				System.out.println("----" + Taxi.NAME + "----");
			}
			else if(t instanceof Subway){
				System.out.println("----" + Subway.NAME + "----");
			}
			else{
				System.out.println("----" + Train.NAME + "----");
			}
			System.out.println("미성년자 : " + t.getCharge(KID, km) + (age < Transportation.REQUIRED_ADULT_AGE ? " <해당>" : ""));
			System.out.println("성인 : " + t.getCharge(ADULT, km) + (age >= Transportation.REQUIRED_ADULT_AGE ? " <해당>" : ""));
			System.out.println();
		}
	}
	
}
```
### 실행결과 1
```
나이 : 21
거리(km) : 500

==== 500km 요금표 ====

----BUS----
미성년자 : 5000
성인 : 6000 <해당>

----TAXI----
미성년자 : 53000
성인 : 53000 <해당>

----SUBWAY----
미성년자 : 3250
성인 : 6250 <해당>

----TRAIN----
미성년자 : 15500
성인 : 31000 <해당>
```
### 실행결과 2
```
나이 : 17
거리(km) : 6000

==== 6000km 요금표 ====

----BUS----
미성년자 : 49000 <해당>
성인 : 61000

----TAXI----
미성년자 : 603000 <해당>
성인 : 603000

----SUBWAY----
미성년자 : 30750 <해당>
성인 : 61250

----TRAIN----
미성년자 : 107500 <해당>
성인 : 215000
```