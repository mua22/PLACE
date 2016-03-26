package epclaim.compiler;

import epclaim.tasks.PrimitiveTask;
import epclaim.utils.ActionType;
import epclaim.utils.CommonStringUtils;
import epclaim.utils.ConditionLogic;

/**
 * Represent the EPClaim Action Object
 * @author Usman
 *
 */
public class Action extends Capability {
	private ActionType actionType;
	private int duration=0;
	private PrimitiveTask moveAgent;
	public int getDuration() {
		return duration;
	}
	
	public Action(FunctionSignature message) {
		this();
		this.message = message;
	}
	
	/**
	 * initializes the collection variables. 
	 */
	public Action() {
		super();
		setFields();
	}

	/**
	 * 
	 */
	private void setFields() {
		this.conditions = new FunctionSignatureCollection();
		this.addEffects = new FunctionSignatureCollection();
		this.deleteEffects = new FunctionSignatureCollection();
		this.message = new FunctionSignature();
	}

	public ConditionLogic getConditionLogic() {
		return conditionLogic;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Action(String name) {
		
		super(name);
		this.setFields();
		this.actionType = ActionType.ORDERED;
	}

	
	

	public ActionType getActionType() {
		return actionType;
	}

	public boolean add(FunctionSignature e) {
		return conditions.add(e);
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	

	
	/**
	 * Sets the Function Signature of the action also sets its name to be the same as of presented in function Signature
	 * e.g. moveAgent(?artifactName)
	 * where moveagent would be set as the name of the Action
	 * @param message
	 */
	public void setFunctionSignature(FunctionSignature message) {
		this.setName(message.getName());
		this.message = message;
	}
	public boolean addCondition(FunctionSignature e) {
		return conditions.add(e);
	}
	public boolean addAddEffect(FunctionSignature e) {
		return this.addEffects.add(e);
	}
	public boolean addDeleteEffect(FunctionSignature e) {
		return this.deleteEffects.add(e);
	}
	public void setConditionLogic(ConditionLogic conditionLogic) {
		
		this.conditionLogic = conditionLogic;
	}
	@Override
	public String toString() {
		String str = "\n";
		str+= CommonStringUtils.tabs() + name+CommonStringUtils.CurlyBraceOpen(true)
				+"message="+message+"\n"
				+"conditions="+conditions
				+ ", actionMessage=" + actionMessage + ", duration=" + duration
				;
				
				
		return str+CommonStringUtils.CurlyBraceClose(true);
		
//		return "Action [name=" + name + ", message=" + message
//				+ ", conditions=" + conditions + ", conditionLogic="
//				+ conditionLogic 
//				+ ", actionMessage=" + actionMessage + ", duration=" + duration
//				+ ",tasks= "+tasks
//				+ "]";
	}

	/* (non-Javadoc)
	 * @see epclaim.tasks.IPerformable#perform(epclaim.compiler.Agent)
	 */
	@Override
	public boolean perform(Agent agent) {
		// TODO Auto-generated method stub
		return false;
	}
	protected FunctionSignatureCollection deleteEffects;
	protected FunctionSignatureCollection addEffects;	
	public FunctionSignatureCollection getDeleteEffects() {
		return deleteEffects;
	}

	public FunctionSignatureCollection getAddEffects() {
		return addEffects;
	}

	public void setDeleteEffects(FunctionSignatureCollection deleteEffects) {
		this.deleteEffects = deleteEffects;
	}

	public void setAddEffects(FunctionSignatureCollection addEffects) {
		this.addEffects = addEffects;
	}
	
}
