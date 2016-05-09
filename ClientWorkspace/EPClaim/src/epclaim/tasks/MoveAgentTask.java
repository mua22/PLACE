package epclaim.tasks;

import java.util.ArrayList;
import java.util.Arrays;

import epclaim.compiler.Agent;
import epclaim.compiler.Condition;
import epclaim.compiler.FunctionSignature;

public class MoveAgentTask extends PrimitiveTask {
	
	protected MoveAgentTask() {
		super();
		//this.setName("move");
		ArrayList<String> variables = new ArrayList<String>();
		variables.add("?a");
		variables.add("?b");
		FunctionSignature fs = new FunctionSignature("OP_move", variables);
		this.setFunctionSignature(fs); // set Function name and its parameters
		
		//Adding conditions
		variables = new ArrayList<String>();
		variables.add("?a");
		FunctionSignature agentIn = new FunctionSignature("agent_in",variables);
		
		Condition condition = new Condition(agentIn);
		this.addCondition(agentIn);
		this.addDeleteEffect(agentIn);
		this.setCondition(condition);
		variables = new ArrayList<String>();
		variables.add("?a");
		variables.add("?b");
		this.addCondition(new FunctionSignature("connected",variables));
		
		variables = new ArrayList<String>();
		variables.add("?b");
		FunctionSignature artifactTo = new FunctionSignature("agent_in",variables);
		
		this.addAddEffect(artifactTo);
		this.setDuration(1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean perform(Agent agent,FunctionSignature actionCall) {
		// TODO Auto-generated method stub
		//System.out.println("Performing Move: "+ actionCall+ agent.getAgent_in());
		agent.setAgent_in(actionCall.getVariables().get(1));
		//System.out.println("Performing Move: "+ actionCall+ agent.getAgent_in());
		return true;
	}

}
