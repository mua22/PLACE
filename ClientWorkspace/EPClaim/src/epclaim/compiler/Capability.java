package epclaim.compiler;

import java.util.ArrayList;

import epclaim.tasks.IPerformable;
import epclaim.tasks.PrimitiveTask;
import epclaim.tasks.Send;
import epclaim.utils.ActionType;
import epclaim.utils.ConditionLogic;
/*
 * @author Usman
 * Capability in PLACE has two forms one is action and the otherone is activity
 */
public abstract class Capability implements IPerformable{

	protected String name;
	protected FunctionSignature message;
	protected FunctionSignatureCollection conditions;

	protected ConditionLogic conditionLogic;
	protected Send actionMessage;
	protected Condition condition;
	public Capability(String name) {
		super();
		this.name = name;
		conditions = new FunctionSignatureCollection();

		this.conditionLogic = ConditionLogic.NOTHING;
		//this.tasks = new ArrayList<PremitiveTask>();
		condition = new Condition();
	}
	
	public boolean addCondition(FunctionSignature e) {
		return this.conditions.add(e);
	}

	public Capability(FunctionSignature message) {
		this(message.getName());
		this.message = message;
	}

	public static ArrayList<PrimitiveTask> getTasks() {
		ArrayList<PrimitiveTask> tasks = new ArrayList<PrimitiveTask>();
		return tasks;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FunctionSignatureCollection getConditions() {
		return conditions;
	}

	public void setConditions(FunctionSignatureCollection conditions) {
		this.conditions = conditions;
	}

	public Capability() {
		super();
	}

	public Send getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(Send actionMessage) {
		this.actionMessage = actionMessage;
	}
	public FunctionSignature getMessage() {
		return message;
	}

	public void setMessage(FunctionSignature message) {
		this.message = message;
	}
	public String getMessageCall(){
		String str = "";
		str+= "!"+message.getName()+" ";
		for(String var:message.getVariables())
			str+=" ?"+var;
		return str;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
}