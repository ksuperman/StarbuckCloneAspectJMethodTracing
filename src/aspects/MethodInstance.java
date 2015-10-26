package aspects;

import java.util.ArrayList;

public class MethodInstance {

	private String className;
	private ArrayList<String> modifiers;
	private String returnType;
	private String methodName;
	private ArrayList<String> parameters;
	private String jointPointLongString;
	
	public MethodInstance(String className, ArrayList<String> modifiers,String returnType, String methodName,
			ArrayList<String> parameters, String jointPointLongString) {
		this.className = className;
		this.modifiers = modifiers;
		this.returnType = returnType;
		this.methodName = methodName;
		this.parameters = parameters;
		this.jointPointLongString = jointPointLongString;
	}
	
	public String getReturnType() {
		return returnType;
	}
	
	public ArrayList<String> getModifiers() {
		return modifiers;
	}

	public String getJointPointLongString() {
		return jointPointLongString;
	}
	
	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}
	
	public void setModifiers(ArrayList<String> modifiers) {
		this.modifiers = modifiers;
	}

	public void setJointPointLongString(String jointPointLongString) {
		this.jointPointLongString = jointPointLongString;
	}
	
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String getUMLSequenceDiagramMethodFormat() {
		String methodString = "";
		boolean first = true;
		methodString = methodName;
		methodString += "(";
		if(parameters != null) {
			for(String parameter : parameters) {
				if(first) {
					methodString += "\"" + parameter + "\"" ;
					first = false;
				}
				else
					methodString += ", \"" + parameter + "\"";
			}
		}
		methodString += ")";
		return methodString;
	}
	
	@Override
	public String toString() {
		String methodString = "";
		boolean first = true;
		methodString = className + " :";
		if(modifiers != null) {
			for(String modifier : modifiers) {
				methodString += " " + modifier;
			}
		}
		methodString += " " + methodName;
		methodString += "(";
		if(parameters != null) {
			for(String parameter : parameters) {
				if(first) {
					methodString += "\"" + parameter + "\"" ;
					first = false;
				}
				else
					methodString += ", \"" + parameter + "\"";
			}
		}
		methodString += ")";
		return methodString;
	}

}