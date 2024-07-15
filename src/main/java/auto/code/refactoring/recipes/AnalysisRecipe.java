package auto.code.refactoring.recipes;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.J.VariableDeclarations.NamedVariable;

public class AnalysisRecipe extends Recipe {

	@Override
	public String getDisplayName() {
		return "Analysis Recipe";
	}

	@Override
	public String getDescription() {
		return "Recipe built for analysis operations";
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
