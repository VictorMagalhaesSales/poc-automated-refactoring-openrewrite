package auto.code.refactoring;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

public class MyRecipe extends Recipe {

	@Override
	public String getDisplayName() {
		return "My recipe";
	}

	@Override
	public String getDescription() {
		return "Recipe built for studies.";
	}

	@Override
	public TreeVisitor<?, ExecutionContext> getVisitor() {
		return new JavaIsoVisitor<>() {
			@Override
			public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext p) {
				if(method.getSimpleName().equals("methodToBeModified")) {
					return method.withName(method.getName().withSimpleName("modifiedMethod"));
				}
				
				
				return super.visitMethodDeclaration(method, p);
			};
		};
	}

}
