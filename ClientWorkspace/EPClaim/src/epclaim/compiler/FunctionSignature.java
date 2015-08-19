package epclaim.compiler;

import java.util.ArrayList;

public class FunctionSignature {
 private String name;
 private ArrayList<String> variables;
 
public FunctionSignature() {
	super();
	variables = new ArrayList<String>();
}
public FunctionSignature(String name, ArrayList<String> variables) {
	super();
	this.name = name;
	this.variables = variables;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public boolean addVariable(String e) {
	return variables.add(e);
}
public ArrayList<String> getVariables() {
	return variables;
}
public void setVariables(ArrayList<String> variables) {
	this.variables = variables;
}
@Override
public String toString() {
	return name + "(" + variables
			+ ")";
}
 
}
