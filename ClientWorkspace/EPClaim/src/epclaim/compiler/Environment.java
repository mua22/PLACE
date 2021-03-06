package epclaim.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import epclaim.tasks.MoveAgentTask;
import epclaim.tasks.PrimitiveActivity;
import epclaim.tasks.PrimitiveTask;

public class Environment {
	private HashMap<String, Artifact> artifacts;
	private String environmentName;
	private KnowledgeCollection globalKnowledgeCollection;
	private AgentCollection agentsCollection;
	public AgentCollection getAgentsCollection() {
		return agentsCollection;
	}
	public void setAgentsCollection(AgentCollection agentsCollection) {
		this.agentsCollection = agentsCollection;
	}
	public String getEnvironmentName() {
		return environmentName;
	}
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
		this.globalKnowledgeCollection = new KnowledgeCollection();
	}
	public Environment(String environmentName) {
		super();
		this.environmentName = environmentName;
		this.artifacts = new HashMap<String, Artifact>();
		this.globalKnowledgeCollection = new KnowledgeCollection();
	}
	public Artifact addArtifact(String key, Artifact value) {
		return artifacts.put(key, value);
	}
	@Override
	public String toString() {
		return "\nEnvironment [\n\tartifacts=" + artifacts + ", \n\tenvironmentName="
				+ environmentName + "]\n"+"Knowledge = ["+globalKnowledgeCollection;
	}
	public KnowledgeCollection getGlobalKnowledgeCollection() {
		KnowledgeCollection knowledgeCollection = new KnowledgeCollection();
		for(Artifact artifact: artifacts.values()){
				
				for(String parameter:artifact.getConnections()){
					Knowledge kno = new Knowledge("connected");
					kno.addParameter(artifact.getArtifactName());
					kno.addParameter(parameter);
					knowledgeCollection.add(kno);
				}
				
			}
		
		return knowledgeCollection;
	}
	public void setGlobalKnowledgeCollection(
			KnowledgeCollection globalKnowledgeCollection) {
		this.globalKnowledgeCollection = globalKnowledgeCollection;
	}
	private static ActionCollection actions;
	public static ActionCollection getActions(){
		if(actions==null)
		{
			actions = new ActionCollection();
			
		}
		ActionCollection premitiveActions = new ActionCollection();
		Action move = PrimitiveTask.getMoveAgent();
		for(Action action:actions.getActions())
			premitiveActions.add(action);
		premitiveActions.add(move);
		return premitiveActions;
	}
	private static ActivityCollection activities;
	public static ActivityCollection getActivities(){
		if(activities==null)
		{
			activities = new ActivityCollection();
			//activities.add(PrimitiveActivity.getMoveToCell1());
			//activities.add(PrimitiveActivity.getMoveToCell2());
			//Action move = new MoveAgentTask();
			
			//activities.add(move);
		}
		
		return activities;
	}
	public HashMap<String, Artifact> getArtifacts() {
		return artifacts;
	}
	public void setArtifacts(HashMap<String, Artifact> artifacts) {
		this.artifacts = artifacts;
	}
	
}
