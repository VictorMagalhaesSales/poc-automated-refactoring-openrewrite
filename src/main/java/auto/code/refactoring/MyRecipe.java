package auto.code.refactoring;

import static java.util.Objects.nonNull;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.J.VariableDeclarations.NamedVariable;

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
			public J.ClassDeclaration visitClassDeclaration(J.ClassDeclaration classDecl, ExecutionContext p) {
				System.out.println("========---- Analysing %s ----========".formatted(classDecl.getSimpleName()));
				return super.visitClassDeclaration(classDecl, p);
			};
			
			@Override
			public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext p) {
				System.out.println("Method: "+method.getSimpleName());
				
				if(method.getSimpleName().equals("methodToBeModified")) {
					return method.withName(method.getName().withSimpleName("modifiedMethod"));
				}
				
				return super.visitMethodDeclaration(method, p);
			};

			@Override
			public NamedVariable visitVariable(NamedVariable variable, ExecutionContext p) {
				System.out.println("Variable: %s - %s".formatted(
						variable.getSimpleName(),
						variable.getType().toString()));
				return super.visitVariable(variable, p);
			};
		};
	}

}
