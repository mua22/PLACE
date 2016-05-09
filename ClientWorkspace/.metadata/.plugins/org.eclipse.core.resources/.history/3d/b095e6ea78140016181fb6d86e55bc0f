package epclaim.tasks;

import java.util.ArrayList;

import epclaim.compiler.Action;
import epclaim.compiler.Agent;
import epclaim.compiler.FunctionSignature;

public abstract class PrimitiveTask extends Action{

	private static PrimitiveTask moveAgent;
	public PrimitiveTask(FunctionSignature message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PrimitiveTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static PrimitiveTask getMoveAgent(){
		if(moveAgent!=null)
			return moveAgent;
		moveAgent = new MoveAgentTask();
		return moveAgent;
	}	
	public abstract boolean perform(Agent agent);
}
