/**
 * epclaim.compiler
 * 
 */
package epclaim.compiler;

import java.util.ArrayList;

/**
 * @author Usman
 * Creates a Tree Like Structure for nested Conditions. 
 *
 */
public class Condition {
	
	/*
	 * type 
	 * o for none
	 * 1 for and
	 * 2 for or
	 */
	private int type;
	private ArrayList<Condition> childConditionNodes;
	private ArrayList<FunctionSignature> conditions;
	public Condition() {
		super();
		this.type = 0;
		this.childConditionNodes = new ArrayList<Condition>();
		this.conditions = new ArrayList<FunctionSignature>();
	}
	public Condition(FunctionSignature condition){
		this();
		this.addCondition(condition);
	}
	public String toString(){
		String str = "";
		if(this.type==0)
		{
			for(FunctionSignature con:this.conditions){
				str+=con;
			}
		}else {
			if(this.type==1){//and condition started
				str+="(and";
				for(Condition c: this.childConditionNodes)
					str+=c.toString();
				str+=")";
			}else {
				if(this.type==2){//and condition started
					str+="(or";
					for(Condition c: this.childConditionNodes)
						str+=c.toString();
					str+=")";
				}
			}
		}
		return str;
	}

	public boolean addCondition(FunctionSignature e) {
		return conditions.add(e);
	}

	public boolean addChildCondition(Condition e) {
		return childConditionNodes.add(e);
	}
	public void setAndType(){
		this.type=1;
	}
	public void setOrType(){
		this.type=2;
	}
}
