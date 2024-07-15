package auto.code.refactoring.recipes;

import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;


public class RefactorRecipeTest implements RewriteTest {
	
	@Override
	public void defaults(RecipeSpec spec) {
		spec.recipe( new RefactorRecipe());
	}
	
	@Test
	void shouldAddHelloWorldMethod() {
		rewriteRun(
			java("""
				public class TestClass {
				
				}			
			""",
			"""
				public class TestClass {
				    void helloWorld() {
				        System.out.println("Hello World!");
				    }
				
				}	
			""")
		);
	}
	
	@Test
	void cantAddHeloWorld_WhenClassAlreadyHaveMethod() {
		rewriteRun(
			java("""
				public class FooBar {

					private String firstVariable = "First";
					private String secondVariable = "Second";
					
					String helloWorld() {
						System.out.println("Hello World!");
						return "Hello World!";
					}			
				}
			""")
		);
	}
	
	@Test
	void cantAddHeloWorld_WhenIsRecipeClass() {
		rewriteRun(
				java("""
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
