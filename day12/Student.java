package day12;

public class Student {
	String name;
	int kr, en, ma;
	double avg;
	char grade;
	
	void printInfo() {
		System.out.println("===== �л� ���� =====");
		System.out.println("�̸� : " + name);
		System.out.println("���� ���� : " + kr);
		System.out.println("���� ���� : " + en);
		System.out.println("���� ���� : " + ma);
		System.out.println("��� ���� : " + avg);
		System.out.println("���� : " + grade);
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