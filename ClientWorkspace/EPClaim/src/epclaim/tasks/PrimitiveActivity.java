/**
 * epclaim.tasks
 * 
 */
package epclaim.tasks;

import java.util.ArrayList;

import javax.lang.model.type.PrimitiveType;

import epclaim.compiler.Activity;
import epclaim.compiler.FunctionSignature;

/**
 * @author Usman
 *
 */
public class PrimitiveActivity extends Activity {

	/**
	 * @param name
	 */
	private static PrimitiveActivity moveToCell1,moveToCell2,moveToCell3;
	public PrimitiveActivity(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public PrimitiveActivity(FunctionSignature message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Returns the MoveCell1 Activity Object. If the agent is already in the goal cell, do nothing
	 */
	public static PrimitiveActivity getMoveToCell1(){
		if(moveToCell1!=null)
			return moveToCell1;
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("cell");
		FunctionSignature fs = new FunctionSignature("MoveToCell1", variables);
		moveToCell1 = new PrimitiveActivity(fs);
		moveToCell1.addCondition(new FunctionSignature("agentIn", new String[] {"cell"}));
		return moveToCell1;
		
	}
	/*
	 * if the agent is in the neighbouring cell, move to the goal cell in a single step
	 */
	public static PrimitiveActivity getMoveToCell2(){
		if(moveToCell2!=null)
			return moveToCell2;
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("artifactFrom");
		FunctionSignature fs = new FunctionSignature("MoveToCell2", variables);
		moveToCell2 = new PrimitiveActivity(fs);
		moveToCell2.addCondition(new FunctionSignature("agentIn", new String[] {"artifactFrom"}));
		moveToCell2.addCondition(new FunctionSignature("connected", new String[] {"artifactFrom","artifactTo"}));
		moveToCell2.addAction(PrimitiveTask.getMoveAgent());
		return moveToCell2;
		
	}
	public static PrimitiveActivity getMoveToCell3(){
		if(moveToCell3!=null)
			return moveToCell3;
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("artifactFrom");
		FunctionSignature fs = new FunctionSignature("MoveToCell2", variables);
		moveToCell3 = new PrimitiveActivity(fs);
		moveToCell3.addCondition(new FunctionSignature("agentIn", new String[] {"artifactFrom"}));
		moveToCell3.addCondition(new FunctionSignature("connected", new String[] {"artifactFrom","artifactTo"}));
		moveToCell3.addAction(PrimitiveTask.getMoveAgent());
		return moveToCell3;
		
	}
}
