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