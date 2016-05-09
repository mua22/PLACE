/**
 * 
 */
package epclaim.tasks;

import epclaim.compiler.Agent;
import epclaim.compiler.FunctionSignature;

/**
 * @author Usman
 *
 */
public interface IPerformable {
	public boolean perform(Agent agent,FunctionSignature actionCall);
}
