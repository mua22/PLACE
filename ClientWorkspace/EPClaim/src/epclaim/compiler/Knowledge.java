package epclaim.compiler;

import java.util.ArrayList;

public class Knowledge {
	private String statement;
	private ArrayList<String> parameters;
	
	/**
	 * productionTime is used for temporal converter
	 */
	private int productionTime = 0;

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
	/*
	 * Constructor for depe cloning
	 */
	public Knowledge(Knowledge knowledge){
		this(knowledge.getStatement(),knowledge.parameters);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj.getClass()!=this.getClass())
			return false;
		if (obj == this)
	        return true;
		Knowledge kno = (Knowledge)obj;
		if(!this.statement.equals(kno.statement))
			return false;
		if(!this.parameters.equals(kno.parameters))
			return false;
		return true;
	}

	/**
	 * Used in Temporal Planning
	 * @return the productionTime
	 */
	public int getProductionTime() {
		return productionTime;
	}

	/**
	 * Used in Temporal Planning
	 * @param productionTime the productionTime to set
	 */
	public void setProductionTime(int productionTime) {
		this.productionTime = productionTime;
	}
	
}
