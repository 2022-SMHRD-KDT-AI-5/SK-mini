package models;

// 부모클래스
public class Animal {
	
//	기능자체를 일반적으로 물려주겠다 -->> 상속 extends
//	강제적으로 꼭 구현하도록 물려주겠다 -->> 인터페이스 implements
//	물려주기는 하나 구체적인 내용은 알아서 했으면 한다 -->> 추상클래스 abstract
	
	public void eat() {
		System.out.println("먹다");
	}
	
	public void move() {
		System.out.println("움직이다");
	}

	public void a() {
		System.out.println("이 메소드는 Animal에 있습니다");
	}
}
