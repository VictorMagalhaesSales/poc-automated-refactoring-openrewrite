package auto.code.refactoring.recipes;

import java.util.regex.Pattern;

import org.openrewrite.Cursor;
import org.openrewrite.ExecutionContext;
import org.openrewrite.NlsRewrite.Description;
import org.openrewrite.NlsRewrite.DisplayName;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.J.ClassDeclaration;
import org.openrewrite.java.tree.JavaCoordinates;

public class RefactorRecipe extends Recipe {

	@Override
	public @DisplayName String getDisplayName() {
		return "Refactor Recipe";
	}

	@Override
	public @Description String getDescription() {
		return "Recipe built for refactoring operations.";
	}
	
	
	@Override
	public TreeVisitor<?, ExecutionContext> getVisitor() {
		return new JavaIsoVisitor<ExecutionContext>() {
			@Override
			public ClassDeclaration visitClassDeclaration(ClassDeclaration classDecl, ExecutionContext p) {				
				if(isNotApplicableClassForRefactoring(classDecl))
					return super.visitClassDeclaration(classDecl, p);
				
				JavaTemplate methodTemplate = 
						JavaTemplate.builder("void helloWorld() { System.out.println(\"Hello World!\"); }")
						.build();
				
				Cursor cursor = new Cursor(getCursor(), classDecl.getBody());
				JavaCoordinates coordinates = classDecl.getBody().getCoordinates().lastStatement();
				
				classDecl = classDecl.withBody(methodTemplate.apply(cursor, coordinates, new Object[0]));
				
				return super.visitClassDeclaration(classDecl, p);
			}
			
			private boolean isNotApplicableClassForRefactoring(ClassDeclaration classDecl) {				
				boolean hasHelloWorldMethod = classDecl.getBody().getStatements().stream()
						.filter(stmt -> stmt instanceof J.MethodDeclaration)
						.map(J.MethodDeclaration.class::cast)
						.anyMatch(method -> method.getSimpleName().equals("helloWorld"));
				boolean isNotAnApplicableClass = classDecl.getSimpleName().equals("Application");
				boolean isRecipeClass = classDecl.getExtends() != null 
									&& classDecl.getExtends().getType()
										.isAssignableFrom(Pattern.compile("org.openrewrite.Recipe"));
				boolean isTestClass = classDecl.getImplements() != null 
						&& classDecl.getImplements().stream()
							.anyMatch(typeTree -> typeTree.getType()
								.isAssignableFrom(Pattern.compile("org.openrewrite.test.RewriteTest")));
				
				if(getCursor().getParent().getValue() instanceof J.CompilationUnit cu) {
					// This might be useful for something
					cu.getPackageDeclaration();
				}
				
				return hasHelloWorldMethod || isNotAnApplicableClass || isRecipeClass || isTestClass;
			}
			
			@Override
			public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext p) {
				if(method.getSimpleName().equals("methodToBeModified"))
					return method.withName(method.getName().withSimpleName("modifiedMethod"));
				
				return super.visitMethodDeclaration(method, p);
			};
		};
	}
}
