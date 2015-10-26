package aspects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class MethodTracer{
	
	private static Stack<MethodInstance> executionStacks = new Stack<MethodInstance>();
	SequenceDiagramGenerator executionSequenceDiagram = null;
	
	public MethodTracer() {
		MethodInstance method = new MethodInstance("Programmer", null , "Output" , "Compile and Execute", null, "");
		executionStacks.push(method);
	}
	
	public void addInstance(String jointPointLongString, String className, String methodName,String methodModifiers,Object[] methodparameters, String returntype) {
		
		MethodInstance currentMethod = null;
		ArrayList<String> methodmodifiersArr = new ArrayList<String>(Arrays.asList(methodModifiers.split(" ")));
		ArrayList<String> methodParametersArr = new ArrayList<String>();
		for(Object parameter : methodparameters) {
			if(parameter != null) {
				if(parameter instanceof int[]){
					for(int number : (int[])parameter) {
						methodParametersArr.add(Integer.toString(number));
					}
				}else if(parameter instanceof String[]) {
					for(String param : (String[])parameter) {
						methodParametersArr.add(param);
					}
				}else {
					methodParametersArr.add(parameter.toString());
				}	
			}
		}
		
		currentMethod = new MethodInstance(className, methodmodifiersArr,returntype, methodName, methodParametersArr, jointPointLongString);
		executionStacks.push(currentMethod);
		if(executionSequenceDiagram == null)
			executionSequenceDiagram = new PlantUMLSequenceDiagram(executionStacks);
		else {
			executionSequenceDiagram.instanciateNewMethodFlow(executionStacks);
		}
	}
	
	public void removeInstance(String jointPointLongString, String className, String methodName,String methodModifiers,Object[] methodparameters,String returntype) {
		executionSequenceDiagram.endMethodFlow(executionStacks);
		executionStacks.pop();
	}
	
}