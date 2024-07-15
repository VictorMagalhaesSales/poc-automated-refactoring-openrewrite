package auto.code.refactoring.recipes;

import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;


public class AnalysisRecipeTest implements RewriteTest {
	
	@Override
	public void defaults(RecipeSpec spec) {
		spec.recipe(new AnalysisRecipe());
	}
	
	@Test
	void testRecipe() {
		rewriteRun(
			java("""
				public class FooBar {

					private String firstVariable = "First";
					private String secondVariable = "Second";
					
					String helloWorld() {
						System.out.println("Hello World!");
						return "Hello World!";
					}
					
					void methodToBeModified(String arg1) {
						System.out.println("This method will be changed...");
					}				
				}
			""")
		);
	}
}
