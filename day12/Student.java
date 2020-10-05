package day12;

public class Student {
	String name;
	int kr, en, ma;
	double avg;
	char grade;
	
	void printInfo() {
		System.out.println("===== 학생 정보 =====");
		System.out.println("이름 : " + name);
		System.out.println("국어 성적 : " + kr);
		System.out.println("영어 성적 : " + en);
		System.out.println("수학 성적 : " + ma);
		System.out.println("평균 성적 : " + avg);
		System.out.println("학점 : " + grade);
		System.out.println();
	}
	
	void setGrade() {
		if(avg >= 90) {
			grade = 'A';
		}
		else if(avg >= 80) {
			grade = 'B';
		}
		else if(avg >= 70) {
			grade = 'C';
		}
		else if(avg >= 60) {
			grade = 'D';
		}
		else {
			grade = 'F';
		}
	}
	
	void setInfo(String n, int k, int e, int m) {
		name = n;
		kr = k;
		en = e;
		ma = m;
		avg = (kr + en + ma) / 3.0;
		setGrade();
	}
}