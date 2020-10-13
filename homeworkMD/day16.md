# Day 15 Homeowork
### > Homework.java
```java
package com.mega.homework;
/*
 * < Sniper VS Tank >
 * - 저격수, 탱크 두 캐릭터 중 아무거나 랜덤하게 2개 생성
 *   (탱크 vs 탱크, 저 vs 탱, 저 vs 저)
 * - 두 객체가 서로 죽을 때까지 서로 공격을 반복
 * - 첫번째, 혹은 두번째 플레이어가 이겼는지 마지막 승자 출력! 
 *  e.g. 플레이어1(탱크)의 승리!
 */

class Unit { // 부모 클래스
	private String name;
	private double hp; // 체력, 공격력
	private int att;
	private boolean alive; // true:아직 살아있음 (선택사항)
	private int id;
	
	public Unit() {}
	public Unit(String name) {
		this.name = name;
	}
	
	public Unit(String name, int hp, int att, int id) {
		setName(name);
		setHp(hp);
		setAtt(att);
		setId(id);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAtt(int att) {
		this.att = att;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void attack(Unit enemy) {	// 중요. 객체가 Sniper일 수도, Tank일 수도 있기 때문
		
	}
	
	// hp는 int형으로 보여주는 것이 보기 좋아서 int형으로 형변환
	public String getInfo() {
		return "[Player " + getId() + 
				"]\n이름 : " + name +
				"\nHP : " + (int)hp +
				"\nATK : " + att;
	}
	public double getHp() {
		return hp;
	}
	
	public void setHp(double hp) {
		this.hp = hp > 0 ? hp : 0;
		setAlive();
	}
	
	public String getName() {
		return name;
	}
	
	public int getAtt() {
		return att;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public void setAlive() {
		alive = hp > 0 ? true : false;
	}
	
	public int getId() {
		return id;
	}

}
class Sniper extends Unit { // 자식 클래스
	// 객체 생성되면, 자동으로 name은 "저격수", hp는 400, att는 100
	public Sniper(int id) {
		super("저격수", 400, 100, id);
	}
	
	// attack 오버라이드 
	// 1. 10% 확률로 헤드샷 (상대 즉사)
	// 2. 나머지 확률로 평타(일반 공격, 상대 hp를 100만큼 깎는다.)
	
	@Override
	public void attack(Unit enemy) {
		boolean isHeadshot = Math.random() < 0.1 ? true : false;
		System.out.print("[" + getName() + "] Player " + getId() + " 의 공격!");
		
		if(isHeadshot) {
			System.out.println(" Headshot !");
			enemy.setHp(0);
		}
		else {
			System.out.println(" Normal Attack");
			enemy.setHp(enemy.getHp() - getAtt());
		}
	}
	
}

class Tank extends Unit {
	// 객체 생성되면, 자동으로 name은 "탱크", hp는 4000, att는 50
	public Tank(int id) {
		super("탱크", 4000, 50, id);
	}
	
	// attack 오버라이드 
	// 1. 30% 확률로 상대의 hp 30% 감소
	// 2. 나머지 확률로 평타(일반 공격, 상대 hp를 50만큼 깎는다.)
	@Override
	public void attack(Unit enemy) {
		boolean isCritical = Math.random() < 0.3 ? true : false;
		
		System.out.print("[" + getName() + "] Player " + getId() + " 의 공격!");
		if(isCritical) {
			System.out.println(" Special Attack !!!");
			enemy.setHp(enemy.getHp() * 0.7);
		}
		else {
			System.out.println(" Normal Attack");
			enemy.setHp(enemy.getHp() - getAtt());
		}

	}
}

public class Homework {
	// 두명만 있어도 되지만 final 연습...
	public static final int MAX_PLAYER = 2;
	public static final int TANK = 0;
	public static final int SNIPER= 1;
	
	// 두 타입의 객체를 랜덤하게 2개 생성
	// 무한 반복문을 사용하여 둘 중 하나가 죽을 때까지 서로를 공격
	// 단, 죽은 객체가 공격하면 안됨
	
	public static void main(String[] args) {
		boolean isGameContinue = true;
		int round = 0;
		Unit[] units = new Unit[MAX_PLAYER];
		
		for(int i=0; i<MAX_PLAYER; ++i) {
			switch((int)(Math.random() * 2)) {
			
			case TANK:
				units[i] = new Tank(i+1);
				System.out.println("[Player " + (i+1) + "] : Tank");
				break;
			case SNIPER:
				units[i] = new Sniper(i+1);
				System.out.println("[Player " + (i+1) + "] : Sniper");
				break;
			default:
				units[i] = null;
			}
		}
		
		System.out.println("\n======== Game Start ========\n");
		while(isGameContinue) {
			System.out.println("-------- Round " + ++round + "--------");
			loop:for(Unit u1 : units) {
				for(Unit u2 : units) {
					if(u1 == u2) {
						continue;
					}
					u1.attack(u2);
					if(!u2.isAlive()) {
						break loop;
					}
				}
			}
			System.out.println("\n< 현재 unit들 정보 >");
			for(Unit u : units) {
				System.out.println(u.getInfo() + "\n");
			}
			
			// 한명 죽으면 끝
			for(Unit u : units) {
				if(!u.isAlive()) {
					isGameContinue = false;
				}
			}
		}
		System.out.println("\n======= Game Over ========");
		for(Unit u : units) {
			if(u.isAlive()) {
				System.out.println("Player " + u.getId() + "(이)가 승리했습니다 !");
			}
			else {
				System.out.println("Player " + u.getId() + "(이)가 패배했습니다...");
			}
		}
		
	}
}
```
### 실행 결과 1 (헤드샷)
```
[Player 1] : Tank
[Player 2] : Sniper

======== Game Start ========

-------- Round 1--------
[탱크] Player 1 의 공격! Normal Attack
[저격수] Player 2 의 공격! Normal Attack

< 현재 unit들 정보 >
[Player 1]
이름 : 탱크
HP : 3900
ATK : 50

[Player 2]
이름 : 저격수
HP : 350
ATK : 100

-------- Round 2--------
[탱크] Player 1 의 공격! Special Attack !!!
[저격수] Player 2 의 공격! Headshot !

< 현재 unit들 정보 >
[Player 1]
이름 : 탱크
HP : 0
ATK : 50

[Player 2]
이름 : 저격수
HP : 244
ATK : 100


======= Game Over ========
Player 1(이)가 패배했습니다...
Player 2(이)가 승리했습니다 !

```
### 실행 결과 2
```
[Player 1] : Sniper
[Player 2] : Sniper

======== Game Start ========

-------- Round 1--------
[저격수] Player 1 의 공격! Normal Attack
[저격수] Player 2 의 공격! Normal Attack

< 현재 unit들 정보 >
[Player 1]
이름 : 저격수
HP : 300
ATK : 100

[Player 2]
이름 : 저격수
HP : 300
ATK : 100

-------- Round 2--------
[저격수] Player 1 의 공격! Normal Attack
[저격수] Player 2 의 공격! Normal Attack

< 현재 unit들 정보 >
[Player 1]
이름 : 저격수
HP : 200
ATK : 100

[Player 2]
이름 : 저격수
HP : 200
ATK : 100

-------- Round 3--------
[저격수] Player 1 의 공격! Normal Attack
[저격수] Player 2 의 공격! Normal Attack

< 현재 unit들 정보 >
[Player 1]
이름 : 저격수
HP : 100
ATK : 100

[Player 2]
이름 : 저격수
HP : 100
ATK : 100

-------- Round 4--------
[저격수] Player 1 의 공격! Normal Attack

< 현재 unit들 정보 >
[Player 1]
이름 : 저격수
HP : 100
ATK : 100

[Player 2]
이름 : 저격수
HP : 0
ATK : 100


======= Game Over ========
Player 1(이)가 승리했습니다 !
Player 2(이)가 패배했습니다...
```
### 실행 결과 3
```
[Player 1] : Tank
[Player 2] : Tank

======== Game Start ========

-------- Round 1--------
[탱크] Player 1 의 공격! Special Attack !!!
[탱크] Player 2 의 공격! Special Attack !!!

< 현재 unit들 정보 >
[Player 1]
이름 : 탱크
HP : 2800
ATK : 50

[Player 2]
이름 : 탱크
HP : 2800
ATK : 50

-------- Round 2--------
[탱크] Player 1 의 공격! Normal Attack
[탱크] Player 2 의 공격! Normal Attack

< 현재 unit들 정보 >
[Player 1]
이름 : 탱크
HP : 2750
ATK : 50

[Player 2]
이름 : 탱크
HP : 2750
ATK : 50

-------- Round 3--------
[탱크] Player 1 의 공격! Normal Attack
[탱크] Player 2 의 공격! Normal Attack

< 현재 unit들 정보 >
[Player 1]
이름 : 탱크
HP : 2700
ATK : 50

[Player 2]
이름 : 탱크
HP : 2700
ATK : 50

.
.
.

-------- Round 21--------
[탱크] Player 1 의 공격! Normal Attack
[탱크] Player 2 의 공격! Special Attack !!!

< 현재 unit들 정보 >
[Player 1]
이름 : 탱크
HP : 302
ATK : 50

[Player 2]
이름 : 탱크
HP : 1
ATK : 50

-------- Round 22--------
[탱크] Player 1 의 공격! Normal Attack

< 현재 unit들 정보 >
[Player 1]
이름 : 탱크
HP : 302
ATK : 50

[Player 2]
이름 : 탱크
HP : 0
ATK : 50


======= Game Over ========
Player 1(이)가 승리했습니다 !
Player 2(이)가 패배했습니다...

```
### 실행결과 4
```
[Player 1] : Tank
[Player 2] : Sniper

======== Game Start ========

-------- Round 1--------
[탱크] Player 1 의 공격! Special Attack !!!
[저격수] Player 2 의 공격! Headshot !

< 현재 unit들 정보 >
[Player 1]
이름 : 탱크
HP : 0
ATK : 50

[Player 2]
이름 : 저격수
HP : 280
ATK : 100


======= Game Over ========
Player 1(이)가 패배했습니다...
Player 2(이)가 승리했습니다 !
```