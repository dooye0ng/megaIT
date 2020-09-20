# Day03_3 Assignment  

### 1. 원시자료형 8가지 종류와 각 크기(byte 크기)
> #### 정수형
> - byte: 1byte
> - short: 2byte
> - int: 4byte
> - long: 8byte
> #### 실수형
> - float: 4byte
> - double: 8byte
> #### 문자형
> - char: 2byte
> #### 논리형
> - boolean: 1byte
> 
### 2. 16진수 FF를 10진수로 출력하는 코드를 작성

> ``` java
> class HexToDec {
>    public static void main(String[] args) {
>		String sHex = "FF";
>		String sNum = "";
>		
>		int num = Integer.parseInt(sHex,16);
>		sNum = Integer.toString(num);
>		
>		System.out.println("16진수 FF는 10진수로 " + sNum + "입니다"> );
>	}
> }
> ```
> ~~~
> > 16진수 FF는 10진수로 255입니다.
> ~~~
### 3. 이름 이니셜의 아스키코드 값을 출력하는 코드를 작성

> ``` java
> public class Ascii {
>	public static void main(String[] args) {
>		String ascii_C = Integer.toString('C');
>		String ascii_D = Integer.toString('D');
>		String ascii_Y = Integer.toString('Y');
>		
>		System.out.println("이니셜: C D Y");
>		System.out.println("ASCII: " + ascii_C + " " + ascii_D + " " + ascii_Y);
>
>	}
> }
> ```
> ~~~
> > 이니셜: C D Y
> > ASCII: 67 68 89
> ~~~

### 4. Scanner를 활용하여 체중과 키를 입력 받고, BMI 지수를 출력  
> (BMI = 체중(kg) / 신장(m)^2)  
>   
>  ex)  
> 키: 150  
> 몸무게: 50  
> BMI: 22.2222  
> ``` java
> import java.util.Scanner;
> 
> public class Assign {
>	public static void main(String[] args) {
>		Scanner sc = new Scanner(System.in);
>		double weight = 0, height = 0, bmi = 0;
>		
>		System.out.println("키와 몸무게를 입력하세요");
>		
>		System.out.print("키(cm): ");
>		height = sc.nextDouble() / 100;
>		
>		System.out.print("몸무게(kg): ");
>		weight = sc.nextDouble();
>		
>		bmi = weight / (height * height);
>		System.out.printf("BMI: %.4f", bmi);
>
>	}
> }
> ```
> ~~~
> > 키와 몸무게를 입력하세요
> > 키(cm): 178
> > 몸무게(kg): 84
> > BMI: 26.5118
> ~~~
### 5. main() 메서드가 하나의 클래스에 1개만 있어야 하는 이유
> main() 메서드는 프로그램의 시작점을 의미하기 때문에 만약 main() 메서드가 두개 이상 존재하면 프로그램을 어디서부터  
> 시작해야할지 알 수 없기 때문에 main() 메서드는 한 프로그램 내에 하나만 존재해야 한다고 생각합니다.  