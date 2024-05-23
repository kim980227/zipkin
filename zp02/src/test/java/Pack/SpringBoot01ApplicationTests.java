package Pack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Random;

//interface A{
//	public void f1();
//}
//
//@Service
//class AImpl implements A{
//	@Override
//	public void f1(){
//		System.out.println("call");
//	}
//}

//@Service
//class A{
//	void f1(){
//		System.out.println("A class");
//	}
//}
//
//class B{
//	@Autowired
//	A a;
//
//	void f2(){
//		System.out.println("B call");
//		a.f1();
//	}
//}

//@Service("tiger")
//class Lion{
//	Lion(){
//		System.out.println("생성자 콜");
//	}
//
//	void f1(){
//		System.out.println("함수 콜");
//	}
//}

//
//@SpringBootTest
//class SpringBoot01ApplicationTests {
//	@Autowired
//	A a;
//
//	@Test
//	void test01() {
//		a.f1();
//	}
//}
//	@Autowired
//	A a;
//
//	@Test
//	void test01(){
//		a.f1();
//
//		B b = new B();
//		b.f2();
//	}
//}
//@SpringBootTest
//class SpringBoot01ApplicationTests implements ApplicationContextAware {
//
//
//	@Test
//	void test01() {
//		System.out.println(1000);
//	}
//
//	ApplicationContext applicationContext;
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		this.applicationContext = applicationContext;
//		Lion lion = applicationContext.getBean("lion", Lion.class);
//		lion.f1();
//	}
//}

class A{
	int a, b;
	A(int a, int b){
		this.a=a;
		this.b=b;
	}

	int getResult(){
		return a*b;
	}
}

interface B{
	public int getNum();
}

@Service
class BImpl implements B{

	@Override
	public int getNum() {
		return new Random().nextInt(10);
	}
}

interface C{
		public A f1();
}

@Service
class CImpl implements C{
	@Autowired
	B b;
	public A f1(){
		return new A(b.getNum(),b.getNum());
	};
}

@SpringBootTest
class SpringBoot01ApplicationTests {

	@Autowired
	C c;

	@Test
	void test01() {
		A a = c.f1();
		System.out.println(a.getResult());
	}
}






