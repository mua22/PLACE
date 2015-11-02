package epclaim.compiler;

import java.util.HashMap;

public class Environment {
	private HashMap<String, Artifact> artifacts;
	private String environmentName;
	private KnowledgeCollection knowledgeCollection;
	public String getEnvironmentName() {
		return environmentName;
	}
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
		this.knowledgeCollection = new KnowledgeCollection();
	}
	public Environment(String environmentName) {
		super();
		this.environmentName = environmentName;
		this.artifacts = new HashMap<String, Artifact>();
	}
	public Artifact addArtifact(String key, Artifact value) {
		return artifacts.put(key, value);
	}
	@Override
	public String toString() {
		return "\nEnvironment [\n\tartifacts=" + artifacts + ", \n\tenvironmentName="
				+ environmentName + "]\n"+"Knowledge = ["+knowledgeCollection;
	}
	public KnowledgeCollection getKnowledgeCollection() {
		return knowledgeCollection;
	}
	public void setKnowledgeCollection(KnowledgeCollection knowledgeCollection) {
		this.knowledgeCollection = knowledgeCollection;
	}
	
}
