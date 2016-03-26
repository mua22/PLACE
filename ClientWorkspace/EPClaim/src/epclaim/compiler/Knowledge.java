package epclaim.compiler;

import java.util.ArrayList;

public class Knowledge {
	private String statement;
	private ArrayList<String> parameters;

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
		
	}

	public Knowledge(String statement, ArrayList<String> parameters) {
		this(statement);
		this.parameters = parameters;
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public Knowledge(String statement) {
		super();
		this.statement = statement;
		this.parameters = new ArrayList<String>();
	}

	@Override
	public String toString() {
		String temp = "";
		for(int i=0;i<parameters.size();i++){
			if(i==parameters.size()-1)
				temp +=parameters.get(i);
			else temp +=parameters.get(i)+",";
		}
		return statement + "("+temp+ ");";
	}

	public boolean addParameter(String e) {
		return this.parameters.add(e);
	}
	

}
