package epclaim.compiler;

import java.util.ArrayList;

import epclaim.utils.CommonStringUtils;

public class Agent {
	private String agentName;
	private KnowledgeCollection knowledgeCollection;
	private KnowledgeCollection goalCollection;
	private Environment environment;
	public ActionCollection getActionCollection() {
		return actionCollection;
	}

	public ActivityCollection getActivityCollection() {
		return activityCollection;
	}

	private ActionCollection actionCollection;
	private ActivityCollection activityCollection;
	public KnowledgeCollection getKnowledgeCollection() {
		if(this.knowledgeCollection==null)
			this.knowledgeCollection = new KnowledgeCollection();
		KnowledgeCollection environmentKnowledge = this.environment.getGlobalKnowledgeCollection();
		
		return this.knowledgeCollection.mergeKnowledgeCollection(environmentKnowledge);
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public KnowledgeCollection getGoalCollection() {
		if(this.goalCollection==null)
			return new KnowledgeCollection();
		return goalCollection;
	}

	public void setGoalCollection(KnowledgeCollection goalCollection) {
		this.goalCollection = goalCollection;
	}

	public void setKnowledgeCollection(KnowledgeCollection knowledgeCollection) {
		this.knowledgeCollection = knowledgeCollection;
	}
	
	public void setActionCollection(ActionCollection actionCollection) {
		this.actionCollection = actionCollection;
	}
	public void setActivityCollection(ActivityCollection activityCollection) {
		this.activityCollection = activityCollection;
	}
	public String getName() {
		return agentName;
	}

	public void setName(String name) {
		this.agentName = name;
	}

	public Agent(String agentName) {
		super();
		this.agentName = agentName;
		this.actionCollection = new ActionCollection();
		this.activityCollection = new ActivityCollection();
		this.knowledgeCollection = new KnowledgeCollection();
	}

	@Override
	public String toString() {
		String str=CommonStringUtils.CurlyBraceOpen(true);
		//str+= "knowledge=" + knowledgeCollection;
		//str+="actions="+actionCollection;
		str+="activities="+activityCollection;
		str+=CommonStringUtils.CurlyBraceClose(true);
		return str;
		//return "Agent [agentName=" + agentName+"\n Knowledge =" + knowledgeCollection+"\nActions = " + actionCollection;
	}
	
}
