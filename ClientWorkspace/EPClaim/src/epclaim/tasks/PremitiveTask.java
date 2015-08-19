package epclaim.tasks;

import epclaim.compiler.Agent;
import epclaim.compiler.FunctionSignature;

public abstract class PremitiveTask {
	protected FunctionSignature functionSignature;

	public FunctionSignature getFunctionSignature() {
		return functionSignature;
	}

	public void setFunctionSignature(FunctionSignature functionSignature) {
		this.functionSignature = functionSignature;
	}

	public PremitiveTask(FunctionSignature functionSignature) {
		super();
		this.functionSignature = functionSignature;
	}

	public PremitiveTask() {
		super();
		this.functionSignature = null;
	}
	
	@Override
	public String toString() {
		return  functionSignature.toString();
	}

	public abstract boolean perform(Agent agent);
}
