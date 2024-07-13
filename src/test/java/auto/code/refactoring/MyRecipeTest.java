package auto.code.refactoring;

import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;


public class MyRecipeTest implements RewriteTest {
	
	@Override
	public void defaults(RecipeSpec spec) {
		spec.recipe(new MyRecipe());
	}
	
	@Test
	void testRecipe() {
		rewriteRun(
			java("""
				public class FooBar {
					
					void helloWorld() {
						System.out.println("Hello World!");
					}
				
				}
			""")
		);
	}
}
