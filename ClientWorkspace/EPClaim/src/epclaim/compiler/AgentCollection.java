package epclaim.compiler;

import java.util.ArrayList;
import java.util.HashMap;

public class AgentCollection {
	private HashMap<String, Agent> agents;

	public AgentCollection() {
		super();
		this.agents = new HashMap<String, Agent>();
	}

	public Agent put(String arg0, Agent arg1) {
		return agents.put(arg0, arg1);
	}
	public ArrayList<Agent> getAgentsList(){
		return new ArrayList<Agent>(agents.values());
	}
	@Override
	public String toString() {
		return "AgentCollection [agents=" + agents.toString() + "]";
	}
	
}
