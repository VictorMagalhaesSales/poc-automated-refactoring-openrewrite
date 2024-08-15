package auto.code.refactoring.recipes;

import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Stream;

import org.openrewrite.ExecutionContext;
import org.openrewrite.ScanningRecipe;
import org.openrewrite.SourceFile;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaParser;
import org.openrewrite.java.tree.J;

public class NewClassScannerRecipe extends ScanningRecipe<NewClassScannerRecipe.State> {

    @Override
    public String getDisplayName() {
        return "Create java file";
    }

    @Override
    public String getDescription() {
        return "Create a java file that display the number of project java methods.";
    }

	@Override
	public State getInitialValue(ExecutionContext ctx) {
		return new State();
	}

	@Override
	public TreeVisitor<?, ExecutionContext> getScanner(State acc) {
		return new JavaIsoVisitor<>() {
			public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext p) {
				acc.methodsCount += 1;
				return super.visitMethodDeclaration(method, p);
			};
		};
	}
	
	@Override
    public Collection<? extends SourceFile> generate(State acc, ExecutionContext ctx) {
		JavaParser parser = JavaParser.fromJavaVersion()
				.build();
		
		Stream<SourceFile> javaFile = parser.parse("""
		package auto.code.refactoring.classes;
		
		public class NewClassTest {
           public void newMethodTest() {
              System.out.println("Project methods: %s");
           }
        }
		""".formatted(acc.methodsCount));
		
		return javaFile
			.map(c -> (SourceFile) c.withSourcePath(Path.of("src/main/java/auto/code/refactoring/classes/NewClassTest.java")))
			.toList();
    }
	
//	@Override
//    public TreeVisitor<?, ExecutionContext> getVisitor(State acc) {
//        return new JavaIsoVisitor<>() {
//           public J.ClassDeclaration visitClassDeclaration(J.ClassDeclaration classDecl, ExecutionContext p) {
//        	   return super.visitClassDeclaration(classDecl, p);
//           };
//        };
//    }
	
	class State{
		Long methodsCount = 0L;
	}
}