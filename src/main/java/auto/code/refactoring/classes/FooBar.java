package auto.code.refactoring.classes;

public class FooBar {

	private String firstVariable = "First";
	private String secondVariable = "Second";
	
	String helloWorld() {
		System.out.println("Hello World!");
		return "Hello World!";
	}
	
	void methodToBeModified() {
		System.out.println("This method will be changed...");
	}

}
