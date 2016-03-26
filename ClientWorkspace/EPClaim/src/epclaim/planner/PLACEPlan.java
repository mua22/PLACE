/**
 * epclaim.planner
 * 
 */
package epclaim.planner;

import epclaim.compiler.FunctionSignature;
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
		String str = "";
		for(FunctionSignature fs:actionCalls.getFunctionSignatureCollection())
		{
			str+=Float.toString(fs.getStartTime())+": "+fs+" ["+fs.getDuration()+"]"+"\n";
		}
		//return "PLACEPlan [cost=" + cost + ", actionCalls=" + actionCalls + "]";
			return str;
	}
	public PLACEPlan(FunctionSignatureCollection actionCalls) {
		this(actionCalls, 0);
		
	}
	
}
