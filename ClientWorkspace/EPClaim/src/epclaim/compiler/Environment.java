package epclaim.compiler;

import java.util.HashMap;
import java.util.Iterator;

public class Environment {
	private HashMap<String, Artifact> artifacts;
	private String environmentName;
	private KnowledgeCollection globalKnowledgeCollection;
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
	
	
}
