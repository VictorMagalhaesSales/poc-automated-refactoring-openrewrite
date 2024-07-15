package auto.code.refactoring.recipes;

import org.openrewrite.java.template.RecipeDescriptor;

import com.google.errorprone.refaster.annotation.AfterTemplate;
import com.google.errorprone.refaster.annotation.BeforeTemplate;

@RecipeDescriptor(
        name = "Fast Replacement recipe for String",
        description = "Make string format replacement."
)
public class FastReplacement {
	
	@BeforeTemplate
	String oldStringFormat(String arg1, String arg2) {
		return String.format(arg1, arg2);
	}
	
	@AfterTemplate
	String newStringFormat(String arg1, String arg2) {
		return arg1.formatted(arg2);
	}

}
