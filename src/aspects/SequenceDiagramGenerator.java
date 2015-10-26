package aspects;

import java.util.Stack;

public interface SequenceDiagramGenerator {
	
	public String getParseString();
	
	public String getParseStringBody();
	
	public void instanciateNewMethodFlow(Stack<MethodInstance> executionStacks);
	
	public void endMethodFlow(Stack<MethodInstance> executionStacks);
	
	public void renderSequenceDiagram();
}
