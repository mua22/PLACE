/**
 * epclaim.planner
 * 
 */
package epclaim.planner;

import epclaim.compiler.FunctionSignatureCollection;

/**
 * @author Usman
 *
 */
public class PLACEPlan {
	private float cost;
	private FunctionSignatureCollection actionCalls;
	public PLACEPlan(FunctionSignatureCollection actionCalls, float cost) {
		super();
		this.actionCalls = actionCalls;
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "PLACEPlan [cost=" + cost + ", actionCalls=" + actionCalls + "]";
	}
	public PLACEPlan(FunctionSignatureCollection actionCalls) {
		this(actionCalls, 0);
		
	}
	
}
