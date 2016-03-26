/**
 * epclaim.planner
 * 
 */
package epclaim.planner;

import epclaim.compiler.Agent;

/**
 * @author Usman
 * the planner Interface. Every Planner must implement this interface
 */
public interface IPlanner{
	public PLACEPlan getPlan(Agent agent) throws PlanNotFoundException;
}
