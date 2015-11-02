package epclaim.compiler;

public class PlaceObjectCollection {
	private Environment environment;
	private AgentCollection agentsCollection;
	public PlaceObjectCollection(Environment environment,
			AgentCollection agentsCollection) {
		super();
		this.environment = environment;
		this.agentsCollection = agentsCollection;
	}
	@Override
	public String toString() {
		return "PlaceObjectCollection [environmrnt=" + environment
				+ ", agents=" + agentsCollection + "]";
	}
	public PlaceObjectCollection() {
		super();
		this.agentsCollection = new AgentCollection();
		
	}
	public Agent putAgent(String arg0, Agent arg1) {
		return agentsCollection.put(arg0, arg1);
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	public AgentCollection getAgentsCollection() {
		return agentsCollection;
	}
	public void setAgentsCollection(AgentCollection agentsCollection) {
		this.agentsCollection = agentsCollection;
	}
	public Environment getEnvironment() {
		return environment;
	}
	
	
}
