package epclaim.compiler;

import java.util.ArrayList;

public class FunctionSignature {
 private String name;
 private float duration=0;
 private float startTime = 0;
 private ArrayList<String> variables;
 
public FunctionSignature() {
	super();
	variables = new ArrayList<String>();
}
public FunctionSignature(String name,String vars[]){
	this.variables = new ArrayList<String>();
	this.name = name;
	variables = new ArrayList<String>();
	for(String var:vars)
		variables.add(var);
	
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
	
	return this.toString(false);
}
public String toString(boolean isActionCall){
	String str="";
	if(isActionCall)
		str = "(!"+this.name+" ";
	else
	str = "("+this.name+" ";
	int i=1;
	for(String s:this.getVariables())
		if(i++==1)
		str+=""+s;
		else str+=" "+s;
	str+=")";
	return str;
}
public float getDuration() {
	return duration;
}
public void setDuration(float duration) {
	this.duration = duration;
}
public float getStartTime() {
	return startTime;
}
public void setStartTime(float startTime) {
	this.startTime = startTime;
} 
}
