# Day 45 Homework

## 콜백(Callback) 패턴
콜백 패턴은 동기 방식에서의 연산 자원 혹은 코드의 중복 등의 단점을 보완해줄 수 있는 디자인 패턴입니다. 예를 들어서 점장이 아르바이트생에게 일을 시킬 때, 일을 시킨 후에 계속 일일이 일을 어디까지 끝냈는지 확인하고, 체크하는 과정에서의 수고를 덜기 위해서 아르바이트생이 스스로 본인이 일을 어디까지 끝냈는지 점장에게 알려주는 구조가 콜백 패턴의 구조라고 할 수 있습니다.  
아래 코드는 마스터가 10명의 슬레이브에게 일을 시킨 후, 슬레이브 각자가 일한 시간을 토대로 마스터의 *MasterCalculator* 인터페이스의 *printWage* 함수를 호출해 자신의 급여를 확인(계산)하는 프로그램입니다. 아래와 같은 방식은 마스터가 일일이 슬레이브의 일한 시간을 추적하고 확인하는 연산의 수고를 덜어낼 수 있습니다.  

```java
public class Master {
	interface MasterCalculator {
		void printWage(int time, int wage);
	}
	
	public static void main(String[] args) {
		MasterCalculator cal = new MasterCalculator() {
			@Override
			public void printWage(int time, int wage) {
				System.out.println("일한 시간 : " + time + ", 급여 : $" + time * 9);
			}
		};
		
		Thread slave;
		for(int i=0; i<10; i++) {
			try {
				slave = new Thread(new Slave(cal));
				System.out.print("Slave " + (i+1) + " >> ");
				slave.start();
				Thread.sleep(((int)(Math.random() * 4) + 1) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Slave implements Runnable{
	Master.MasterCalculator mCal;
	
	Slave(Master.MasterCalculator mCal){
		this.mCal = mCal;
	}
	
	private int calWage(int time) {
		return time * 9;
	}
	
	@Override
	public void run() {
		try {
			int time = (int)(Math.random() * 24) + 1;
			Thread.sleep(time);
			mCal.printWage(time, calWage(time));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
```
### Console
```
Slave 1 >> 일한 시간 : 5, 급여 : $45
Slave 2 >> 일한 시간 : 2, 급여 : $18
Slave 3 >> 일한 시간 : 20, 급여 : $180
Slave 4 >> 일한 시간 : 15, 급여 : $135
Slave 5 >> 일한 시간 : 22, 급여 : $198
Slave 6 >> 일한 시간 : 19, 급여 : $171
Slave 7 >> 일한 시간 : 6, 급여 : $54
Slave 8 >> 일한 시간 : 3, 급여 : $27
```