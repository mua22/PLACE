/**
 * epclaim.planner
 * 
 */
package epclaim.planner;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

import JSHOP2Wrapper.JShop2Planner;
import epclaim.centralsystem.CentralSystem;
import epclaim.compiler.Action;
import epclaim.compiler.Agent;
import epclaim.compiler.FunctionSignature;
import epclaim.compiler.FunctionSignatureCollection;
import epclaim.compiler.Knowledge;
import epclaim.compiler.KnowledgeCollection;
import epclaim.compiler.grammar.ParseException;

/**
 * @author Usman
 *
 */
public class PLACEPlan implements Serializable{
	private float cost;
	private Agent agent;
	private FunctionSignatureCollection actionCalls;
	private ArrayList<PlacePlanAction>  placeActions;
	/**
	 * Initial State of the agent
	 */
	//private KnowledgeCollection initialState;
	public PLACEPlan(FunctionSignatureCollection actionCalls, float cost,Agent agent) {
		super();
		this.actionCalls = actionCalls;
		this.cost = cost;
		this.agent = agent;
	}
	
	/**
	 * Be Careful to use this constructor as you need to set agent later for post processing of the plan
	 * @param actionCalls
	 * @param cost
	 */
	public PLACEPlan(FunctionSignatureCollection actionCalls, float cost) {
		super();
		this.actionCalls = actionCalls;
		this.cost = cost;
		this.agent = null;
	}
	@Override
	public String toString() {
		String str = "";
		str+= "Agent: "+agent+"\n";
		str+=agent.getState()+"\n";
		if(placeActions==null) str+="Plan Actions Not Set";
		else {
			for(PlacePlanAction pAction:this.placeActions)
				str+=pAction+"\n-------------------------------------------";
			//this.convertToTemporal();
			this.temporalConverter();
			str+="\n------------AFTER TEMPORAL CONVERSION-----------\n";
			for(PlacePlanAction pAction:this.placeActions)
				str+=pAction+"\n-------------------------------------------";
		}
		Writer domainWriter;
		try{
		domainWriter = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream("F:\\placeplantest.txt"), "utf-8"));
		domainWriter.write(str);
		domainWriter.close();
		}catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for(FunctionSignature fs:actionCalls.getFunctionSignatureCollection())
		{
			str+=Float.toString(fs.getStartTime())+": "+fs+" ["+fs.getDuration()+"]"+"\n";
		}*/
		//return "PLACEPlan [cost=" + cost + ", actionCalls=" + actionCalls + "]";
			return str;
	}
	/**
	 * 
	 * @param actionCalls
	 * @param agent
	 */
	public PLACEPlan(FunctionSignatureCollection actionCalls,Agent agent) {
		this(actionCalls, 0,agent);
		
	}
	/**
	 * Be Careful to use this constructor as you need to set agent later for post processing of the plan
	 * @param actionCalls
	 */
	public PLACEPlan(FunctionSignatureCollection actionCalls) {
		this(actionCalls, 0,null);
		
	}
	
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	/**
	 * Sets the PlacePlan Actions
	 * @throws Exception if the agent is not set
	 */
	public void setPlacePlanActions() throws Exception{
		if(agent==null) throw new Exception("Agent is not set for the plan");
		placeActions = new ArrayList<PlacePlanAction>();
		for(FunctionSignature actionCall:this.actionCalls.getFunctionSignatureCollection()){
			Action action = agent.getActionByCall(actionCall);
			PlacePlanAction planAction = new PlacePlanAction(actionCall, action);
			placeActions.add(planAction);
			
		}
		//this.temporalConverter();
	}
	
	public ArrayList<PlacePlanAction> temporalConverter(){
		ArrayList<Knowledge> I = this.getInitialState().getCollection(); //The Initial State
		ArrayList<PlacePlanAction> temporalActions = new ArrayList<PlacePlanAction>();
		PlacePlanAction P1 = this.placeActions.get(0); //get P1(First Action) to set its start time to 0
		P1.setStartTime(0);
		temporalActions.add(P1);
		//line 3-5 set production Time of First Action's Add Effects
		int p1EndTime = (int)P1.getEndTime();
		ArrayList<Knowledge> p1AddEffects = P1.getAddEffectsCollection().getCollection();
		for(int j=0;j<p1AddEffects.size();j++){
			p1AddEffects.get(j).setProductionTime(p1EndTime); //set the production time for all the add effects to be the end of the first action. 
			
		}
		I.addAll(p1AddEffects);	
		for(int i=1;i<this.placeActions.size();i++){ //line 6
			int MaxT = 0; 
			int PreT = 0;
			PlacePlanAction Pi = this.placeActions.get(i); //ithAction
			for(int k=0;k<temporalActions.size();k++){
				if(MaxT<temporalActions.get(k).getEndTime())
					MaxT = (int)temporalActions.get(k).getEndTime();
			}
			
			//for loop to calculate Max PreT
			for(int l=0;l<Pi.getConditionsCollection().getCollection().size();l++){
				Knowledge knowledgeToCheckForProductionTime = Pi.getConditionsCollection().getCollection().get(l);
				Knowledge preConditionInInitialState = this.searchInKnowledgeArrayList(I, knowledgeToCheckForProductionTime);
				int pttoCheck = preConditionInInitialState.getProductionTime();
				if(PreT < pttoCheck)
					PreT = pttoCheck;
			}
			ArrayList<Knowledge> piAddEffects = Pi.getAddEffectsCollection().getCollection();
			ArrayList<Knowledge> piDeleteEffects = Pi.getDeleteEffectsCollection().getCollection();
			//causal link threat check
				for (PlacePlanAction temporalAction : temporalActions) {
					ArrayList<Knowledge> tmpActionDeleteEffects = temporalAction.getDeleteEffectsCollection().getCollection();
					for(Knowledge delEffect: tmpActionDeleteEffects){
						if(piAddEffects.contains(delEffect))
							{
								int endTTemp = (int)temporalAction.getEndTime();
								if(PreT < endTTemp)
								PreT = endTTemp;
							}
					}
					ArrayList<Knowledge> tmpActionConditions = temporalAction.getConditionsCollection().getCollection();
					for(Knowledge tmpActionCondition : tmpActionConditions){
						if(piDeleteEffects.contains(tmpActionCondition))
						{
							int endTTemp = (int)temporalAction.getEndTime();
							if(PreT < endTTemp)
							PreT = endTTemp;
						}
					}
				}
			//causal link threat check end
			Pi.setStartTime(PreT);
			//ArrayList<Knowledge> piAddEffects = Pi.getAddEffectsCollection().getCollection();
			for(int k=0;k<piAddEffects.size();k++){
				piAddEffects.get(k).setProductionTime((int)Pi.getEndTime());
			}
			I.addAll(piAddEffects);
			temporalActions.add(Pi);
		}
		
		this.placeActions = temporalActions;
		return this.placeActions;
	}
	
	private Knowledge searchInKnowledgeArrayList(ArrayList<Knowledge> list,Knowledge knowledge){
		for(int i=0;i<list.size();i++){
			if(list.get(i).equals(knowledge))
				return list.get(i);
		}
		
		return new Knowledge("dummy Statement"); //This line should never have been reached
	}
	/**
	 * I, P and D,
	 * where I is the initial state, P is the total order plan which is to be converted
	 *	and D is the domain description file which is needed to extract the information
	 *	about the durations of all the actions. 
	 * Temporal Converter as given on P-73 of the Dr. Adnan Thesis
	 * @return 
	 */
/*	@SuppressWarnings("unchecked")
	private ArrayList<PlacePlanAction> convertToTemporal(){
		int startTime = 0; //The start time of the first Action
		ArrayList<Knowledge> state = this.getInitialState().getCollection(); //I which is the initial state
		ArrayList<PlacePlanAction> temporalActions = new ArrayList<PlacePlanAction>(); 
		temporalActions.add(this.placeActions.get(0)); //Place the first Action as it is
		float MaxT = temporalActions.get(0).getEndTime();
		
		for(int i=1;i<placeActions.size();i++){//loop for each action	for i = 2 to |P | do
			PlacePlanAction action = placeActions.get(i);
			float MaxTForAction = MaxT;
			
			float PreT = 0;
			float MinOrderingT = PreT;
			//Collections.sort(temporalActions);
			//see if the initialState satisfies the Conditions
			if(state.containsAll(action.getConditionsCollection().getCollection())){
				PreT = 0;
				MinOrderingT = PreT;
				//break;
			}else { //set the PreT to the endTime of the action where its conditions are met
				for(int index=0;index<temporalActions.size();index++){
					PlacePlanAction actiontoExecute = temporalActions.get(index);
					state.removeAll(actiontoExecute.getDeleteEffectsCollection().getCollection());
					state.addAll(actiontoExecute.getAddEffectsCollection().getCollection());
					if(state.containsAll(action.getConditionsCollection().getCollection())){
						PreT = action.getEndTime();
						MinOrderingT = PreT;
						break;
					}
				}// end for loop for recondition achieving time
			}//end else for if initial condition meet conditions
			
			//Checking for causal link threat
			for(int index=0;index<temporalActions.size();index++){
				PlacePlanAction actionToCheck = temporalActions.get(index);
				for(Knowledge delKnowledge:actionToCheck.getDeleteEffectsCollection().getCollection()){
					if(action.getConditionsCollection().getCollection().contains(delKnowledge))
						if(PreT<actionToCheck.getEndTime()){
							PreT = actionToCheck.getEndTime();
							MinOrderingT = PreT;
						}
						
				}
			}// end For Loop for checking if causal link threat
			action.setStartTime(PreT);
			temporalActions.add(action);
			
		}
		this.placeActions = temporalActions;
		return this.placeActions;
	}*/
	
	/**
	 * Creates and return the deep copy of the Initial State of the agent 
	 * to simulate the execution for temporal converter
	 * @return
	 */
	private KnowledgeCollection getInitialState() {			
		KnowledgeCollection initialState = new KnowledgeCollection();
			for(Knowledge kno:agent.getState().getCollection()){
				initialState.add(new Knowledge(kno));
			}
		
		return initialState;
	}

	/**
	 * @return the placeActions
	 */
	public ArrayList<PlacePlanAction> getPlaceActions() {
		return placeActions;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		CentralSystem cs = new CentralSystem(new JShop2Planner());
		//String fileName = "F:\\epclaimcodes\\shop2\\casestudyHospital\\hospital.ep";
		//String fileName = "F:\\epclaimcodes\\shop2\\usman\\cleaner.txt";
		String fileName = "F:\\epclaimcodes\\shop2\\temporal\\temporal.ep";
		try {
			cs.compile(fileName); 
			for (Agent agent : cs.getPlaceObjects().getAgentsCollection().getAgentsList()) {
				agent.setEnvironment(cs.getPlaceObjects().getEnvironment());
				agent.setPlanner(cs.getPlanner());
				//agent.planAndExecute();
				PLACEPlan plan= cs.getPlanner().getPlan(agent);
				plan.setPlacePlanActions();
				//plan.convertToTemporal();
				System.out.println(plan);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
