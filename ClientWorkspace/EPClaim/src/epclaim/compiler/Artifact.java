package epclaim.compiler;

import java.util.ArrayList;

public class Artifact {
	private String artifactName;
	private KnowledgeCollection knowledgeCollection;
	private String parent = "";
	private ArrayList<String> connections;
	public String getArtifactName() {
		return artifactName;
	}

	public void setArtifactName(String artifactName) {
		this.artifactName = artifactName;
	}

	public KnowledgeCollection getKnowledgeCollection() {
		return knowledgeCollection;
	}

	public void setKnowledgeCollection(KnowledgeCollection knowledgeCollection) {
		this.knowledgeCollection = knowledgeCollection;
	}

	@Override
	public String toString() {
		return "Artifact:" + artifactName+"\nParent:"+parent+"\nConnections:"+connections;
	}

	public Artifact(String artifactName) {
		super();
		this.artifactName = artifactName;
		this.knowledgeCollection = new KnowledgeCollection();
		this.connections = new ArrayList<String>();
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public ArrayList<String> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<String> connections) {
		this.connections = connections;
	}

	
	
}
