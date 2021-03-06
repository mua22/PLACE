/**
 * epclaim.compiler.executioner
 * 
 */
package epclaim.compiler.executioner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

import epclaim.centralsystem.CentralSystem;
import epclaim.compiler.Agent;
import epclaim.compiler.Knowledge;
import epclaim.planner.PlacePlanAction;

/**
 * @author Usman
 *
 */
public class PlaceActionExecutioner extends TimerTask {

	private Agent agent;
	private PlacePlanAction action;
	
	public PlaceActionExecutioner(Agent agent, PlacePlanAction action) {
		super();
		this.agent = agent;
		this.action = action;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		int sleepTime = (CentralSystem.tickTime * (int)action.getDuration())-50;
		System.out.println("Starting: " + action.getName() );
		boolean condition = true;
		ArrayList<Knowledge> agentKnowledge = agent.getKnowledgeCollection().getCollection();
		ArrayList<Knowledge> actionConditions = action.getConditionsCollection().getCollection();
		//System.out.println(agentKnowledge);
		//System.out.println(actionConditions);
		for(Knowledge knowledgeCondition:actionConditions){
			if(!agentKnowledge.contains(knowledgeCondition))
				condition = false;
		}
		//condition = true;
		if(!condition){
			agent.haltExecution();
		}else {
			action.perform(agent,action.getActionCall());
		}
		//agent.haltExecution();
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(action.getConditionsCollection().getCollection() );
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	//@Override
	/*public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(this);
		t1.start();

	}*/

}
