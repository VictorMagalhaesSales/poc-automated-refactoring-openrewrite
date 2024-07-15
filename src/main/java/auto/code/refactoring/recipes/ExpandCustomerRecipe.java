package auto.code.refactoring.recipes;

import org.openrewrite.ExecutionContext;
import org.openrewrite.NlsRewrite.Description;
import org.openrewrite.NlsRewrite.DisplayName;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.MethodMatcher;
import org.openrewrite.java.tree.J;

public class ExpandCustomerRecipe extends Recipe {

	@Override
	public @DisplayName String getDisplayName() {
		return "Expand Customer Recipe";
	}

	@Override
	public @Description String getDescription() {
		return "Expand the `Customer` class with new fields.";
	}
	
	@Override
	public TreeVisitor<?, ExecutionContext> getVisitor() {
		return new JavaIsoVisitor<>() {
			private final MethodMatcher methodMatcher =
					new MethodMatcher("auto.code.refactoring.classes.Customer setCustomerInfo(String lastName)");
			
			private final JavaTemplate methodParamsTemplate = 
				JavaTemplate.builder("Date dateOfBirth, String firstName, #{}")
					.imports("java.util.Date")
					.build();
			
			private final JavaTemplate methodBodyTemplate =
				JavaTemplate.builder(" ")
					.build();
			
			@Override
			public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext p) {
				if(!methodMatcher.matches(method.getMethodType()))
					return super.visitMethodDeclaration(method, p);
				
				method = method.withModifiers(
						method.getModifiers().stream()
						.filter(mod -> mod.getType() != J.Modifier.Type.Abstract)
						.toList()
					);
				
				method = methodParamsTemplate.apply(
						updateCursor(method),
						method.getCoordinates().replaceParameters(),
						method.getParameters().get(0));
				
				method = methodBodyTemplate.apply(
						updateCursor(method),
						method.getCoordinates().replaceBody());
				
				return method;
			};
		};
	}

}
