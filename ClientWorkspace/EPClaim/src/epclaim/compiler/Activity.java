package epclaim.compiler;

import java.util.ArrayList;

public class Activity extends Capability {
	private ArrayList<Action> actions = new ArrayList<Action>();
	private FunctionSignatureCollection effects;
	private FunctionSignatureCollection doCalls;
	private boolean isOrdered = true;
	
	public Activity(String name) {
		super(name);
		effects = new FunctionSignatureCollection();
		doCalls = new FunctionSignatureCollection();
	}
	public boolean addEffect(FunctionSignature e) {
		return effects.add(e);
	}
	
	public boolean addDoCall(FunctionSignature e) {
		return doCalls.add(e);
	}
	
	public FunctionSignatureCollection getDoCalls() {
		return doCalls;
	}
	public void setDoCalls(FunctionSignatureCollection doCalls) {
		this.doCalls = doCalls;
	}
	/**
	 * @param e
	 * @return
	 * @see java.util.ArrayList#add(java.lang.Object)
	 */
	public boolean addAction(Action e) {
		return actions.add(e);
	}
	public Activity(FunctionSignature message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	
	/**
	 * @return the actions
	 */
	public ArrayList<Action> getActions() {
		return actions;
	}
	/**
	 * @param actions the actions to set
	 */
	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	/*
	 * Sets if the action calls are ordered. default is true
	 */
	public void setOrdered(boolean isOrdered) {
		this.isOrdered = isOrdered;
	}
	/*
	 * gets if the activity is ordered
	 */
	public boolean isOrdered() {
		return isOrdered;
	}
}
