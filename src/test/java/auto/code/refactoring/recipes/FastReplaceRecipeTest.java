package auto.code.refactoring.recipes;

import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

public class FastReplaceRecipeTest implements RewriteTest {
	
	@Override
	public void defaults(RecipeSpec spec) {
		spec.recipe(new FastReplacementRecipe());
	}
	
	@Test
	void shouldReplaceOldStringFormat() {
		rewriteRun(
			java("""
				public class FooSimple {
					void replacementTest() {
						System.out.println(String.format("format", "test"));
					}
				}
			""",
			"""
			public class FooSimple {
				void replacementTest() {
					System.out.println("format".formatted("test"));
				}
			}
		""")
		);
	}
}
