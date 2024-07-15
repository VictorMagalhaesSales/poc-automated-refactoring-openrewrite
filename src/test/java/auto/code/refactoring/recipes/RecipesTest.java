package auto.code.refactoring.recipes;

import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;


public class RecipesTest implements RewriteTest {
	
	@Override
	public void defaults(RecipeSpec spec) {
		spec.recipes(new AnalysisRecipe(), new RefactorRecipe());
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
				
				
				public class SecondClass extends org.openrewrite.Recipe {

					@Override
					public String getDisplayName() {
						return "Analysis Recipe";
					}
				
					@Override
					public String getDescription() {
						return "Recipe built for analysis operations";
					}			
				}
			""")
		);
	}
}
