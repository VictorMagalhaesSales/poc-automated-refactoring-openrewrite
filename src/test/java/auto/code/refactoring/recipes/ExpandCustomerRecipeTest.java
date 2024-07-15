package auto.code.refactoring.recipes;

import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

public class ExpandCustomerRecipeTest implements RewriteTest {
	
	@Override
	public void defaults(RecipeSpec spec) {
		spec.recipe(new ExpandCustomerRecipe());
	}
	
	
	@Test
	void a() {
		rewriteRun(
			java("""
				package auto.code.refactoring.classes;

				import java.util.Date;
				
				public abstract class Customer {
				    private Date dateOfBirth;
				    private String firstName;
				    private String lastName;
				
				    public abstract void setCustomerInfo(String lastName);
				}
			""")
		);
	}

}
