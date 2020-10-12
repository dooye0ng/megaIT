# Day 15 Homeowork
### > Homework.java
```java
package com.mega.homework;

class Book{
	private String title;
	private String author;
	private String publisher;
	
	Book(String title, String author, String publisher){
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
	
	Book(String title){
		this(title, "미상", "미상");
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public String getInfo() {
		return "Title : " + title + "\nAuthor : " + author + "\nPublisher : "+ publisher;
	}
}

class Novel extends Book{
	private int price;
	private String genre;
	
	Novel(String title, String author, String publisher, int price, String genre){
		super(title, author, publisher);
		this.price = price;
		this.genre = genre;
	}
	
	Novel(String title){
		this(title, "미상", "미상", 0, "미상");
	}

	public int getPrice() {
		return price;
	}

	public Novel setPrice(int price) {
		this.price = price;
		return this;
	}

	public String getGenre() {
		return genre;
	}

	public Novel setGenre(String genre) {
		this.genre = genre;
		return this;
	}
	
	public String getNovelInfo() {
		return (super.getInfo() + "\nGenre : " + genre + "\nPrice : " + price);
	}
}

class Comic extends Novel{
	private String hero;
	
	public Comic(String title, String author, String publisher, int price, String genre, String hero) {
		super(title, author, publisher, price, genre);
		this.hero = hero;
	}
	
	public Comic(String title) {
		this(title, "미상", "미상", 0, "미상", "미상");
	}

	public String getHero() {
		return hero;
	}

	public Comic setHero(String hero) {
		this.hero = hero;
		return this;
	}
	
	public String getComicInfo() {
		return super.getNovelInfo() + "\nHero : " + hero;
	}
	
}

class Textbook extends Book{
	private String subject;
	
	public Textbook(String title, String author, String publisher, String subject){
		super(title, author, publisher);
		this.subject = subject;
	}
	
	public Textbook(String subject){
		this("미상", "미상", "미상", subject);
	}

	public String getSubject() {
		return subject;
	}

	public Textbook setSubject(String subject) {
		this.subject = subject;
		return this;
	}
	
	public String getTextbookInfo() {
		return super.getInfo() + "\nSubject : " + subject;
	}
}

public class Homework {
	public Homework(){
		Book b1 = new Book("여행 추천지", "앨리스", "하늘");
		
		System.out.println(b1.getInfo());
		System.out.println();
		Novel n1 = new Novel("맛집 추천");
		n1.setAuthor("밥밥이");
		n1.setPublisher("맛맛출판");
		
		n1.setGenre("맛집 탐방")
		.setPrice(15000);
		System.out.println(n1.getNovelInfo());
		System.out.println();
		
		Comic c1 = new Comic("데드풀");
		c1.setHero("데드풀");
		System.out.println(c1.getComicInfo());
		System.out.println();
		
		Textbook t1 = new Textbook("과학책", "과학 선생님", "학교", "과학");
		System.out.println(t1.getTextbookInfo());
		
	}
	
	public static void main(String[] args) {
		new Homework();
	}

}
```
### 실행결과
```
Title : 여행 추천지
Author : 앨리스
Publisher : 하늘

Title : 맛집 추천
Author : 밥밥이
Publisher : 맛맛출판
Genre : 맛집 탐방
Price : 15000

Title : 데드풀
Author : 미상
Publisher : 미상
Genre : 미상
Price : 0
Hero : 데드풀

Title : 과학책
Author : 과학 선생님
Publisher : 학교
Subject : 과학
```

### "is-A" 와 "has-A" 관계
is-A 관계는 말 그대로 " ~이 A 이다" 라는 것이 성립되는 관계이고, has-A 관계는 "~이 A를 가지고(포함하고) 있다"라는 것이 성립되는 관계이다.  

예를 들어, Person과 Student가 있을 때 *Person is Student*는 성립이 안되고, *Student is Person*은 성립이 된다. 이렇듯 B is A 가 성립할 때, B가 A를 상속할 수 있다.  

그리고 Pencilcase과 Pen이 있을 때, *Pencilcase is Pen* 혹은 *Pen is Pencilcase*은 모두 성립하지 않는다. 하지만 *Pencilcase has Pen*은 성립한다. 이와 같이 Pencilcase 클래스는 Pen 클래스를 포함할 수 있고, 이는 상속과는 무관하다.