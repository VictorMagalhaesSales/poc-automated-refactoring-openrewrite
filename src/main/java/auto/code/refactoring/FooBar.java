package auto.code.refactoring;

public class FooBar {
	
	String helloWorld() {
		System.out.println("Hello World!");
		return "Hello World!";
	}
	
	void methodToBeModified() {
		System.out.println("This method will be changed...");
	}

}
