# PoC: Automated Code Refactoring with OpenRewrite

## Study notes
![image](https://github.com/user-attachments/assets/d2415419-d7b1-4b15-a374-5ecf13706985)

#### Recipe & JavaVisitor
```java
public class MyRecipe extends Recipe {
	@Override
	public String getDisplayName() {
		return "My recipe";
	}
	@Override
	public String getDescription() {
		return "Recipe built for studies";
	}
	@Override
	public TreeVisitor<?, ExecutionContext> getVisitor() {
		return new JavaIsoVisitor<>() {
		    
			@Override
			public J.MethodDeclaration visitMethodDeclaration(
			    J.MethodDeclaration method, ExecutionContext p) {
				System.out.println(method.getSimpleName());
				return super.visitMethodDeclaration(method, p);
			};
			
		};
	}
}
```

### JavaVisitor
```java
class JavaVisitor<P> extends TreeVisitor<J, P> {
  J visitStatement(Statement statement) {...}
  J visitTypeName(NameTree name) {...}
  J visitAnnotatedType(J.AnnotatedType annotatedType)  {...}
  J visitAnnotation(J.Annotation annotation) {...}
  J visitArrayAccess(J.ArrayAccess arrayAccess) {...}
  J visitArrayType(J.ArrayType arrayType) {...}
  J visitAssert(J.Assert azzert) {...}
  J visitAssignment(J.Assignment assign) {...}
  J visitAssignmentOperation(J.AssignmentOperation assignOp) {...}
  J visitBinary(J.Binary binary) {...}
  ...
}
```

