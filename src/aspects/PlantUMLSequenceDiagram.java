package aspects;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import net.sourceforge.plantuml.SourceStringReader;

public class PlantUMLSequenceDiagram implements SequenceDiagramGenerator{
	
	private static String parseStringBody = "";
	
	public PlantUMLSequenceDiagram() {
		parseStringBody = "";
	}
	
	public PlantUMLSequenceDiagram(Stack<MethodInstance> executionStacks) {
		
		MethodInstance currentMethod  = executionStacks.peek();
		MethodInstance previousMethod  = executionStacks.get(executionStacks.size()-2);
		
		String initalClassName = previousMethod.getClassName();
		String destClassName = currentMethod.getClassName();
		String firstMethodName = previousMethod.getUMLSequenceDiagramMethodFormat();
		String secondMethodName = currentMethod.getUMLSequenceDiagramMethodFormat();

		String temp = initalClassName + " -> " + destClassName + " : " + firstMethodName + "\n";
		temp += "activate " + initalClassName + "\n";
		temp += destClassName + " -> " + destClassName + " : " + secondMethodName + "\n";
		temp += "activate " + destClassName + "\n";
		
		parseStringBody = temp;
	}
	
	public String getParseString() {
		return "@startuml\n" + parseStringBody +  "@enduml";
	}
	
	public String getParseStringBody() {
		return parseStringBody;
	}
	
	public void instanciateNewMethodFlow(Stack<MethodInstance> executionStacks) {
		
		MethodInstance currentMethod  = executionStacks.peek();
		MethodInstance previousMethod  = executionStacks.get(executionStacks.size()-2);
		
		String initalClassName = previousMethod.getClassName();
		String destClassName = currentMethod.getClassName();
		String MethodName = currentMethod.getUMLSequenceDiagramMethodFormat();
		
		String temp = initalClassName + " -> " + destClassName + " : " + MethodName + "\n";
		temp += "activate " + destClassName + "\n";

		parseStringBody += temp;
		
		//renderSequenceDiagram();
	}
	
	public void endMethodFlow(Stack<MethodInstance> executionStacks) {
		
		MethodInstance currentMethod  = executionStacks.peek();
		MethodInstance previousMethod  = executionStacks.get(executionStacks.size()-2);
		
		String initalClassName = previousMethod.getClassName();
		String destClassName = currentMethod.getClassName();
		String returnType = currentMethod.getReturnType();
		
		if(executionStacks.size() == 2)
			returnType = "Output";
		String temp = destClassName + " --> " + initalClassName + " : " + returnType + "\n";
		temp += "deactivate " + destClassName + "\n";

		parseStringBody += temp;
		
		if(executionStacks.size() <= 2)
			renderSequenceDiagram();
	}
	
	public void renderSequenceDiagram() {
		try {
			SourceStringReader reader = new SourceStringReader(getParseString());
			String desc = reader.generateImage(new File("/home/rakshithk/sequencediagram.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}