package epclaim.compiler;

import java.util.ArrayList;

import epclaim.tasks.PremitiveTask;
import epclaim.tasks.Send;
import epclaim.utils.ActionType;
import epclaim.utils.CommonStringUtils;
import epclaim.utils.ConditionLogic;

public class Action {
	private String name;
	private FunctionSignature message;
	private FunctionSignatureCollection conditions;
	private ConditionLogic conditionLogic;
	private ActionType actionType;
	private Send actionMessage;
	private int duration;
	private ArrayList<PremitiveTask> tasks;
	public int getDuration() {
		return duration;
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

	public ArrayList<PremitiveTask> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<PremitiveTask> tasks) {
		this.tasks = tasks;
	}

	public ConditionLogic getConditionLogic() {
		return conditionLogic;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Send getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(Send actionMessage) {
		this.actionMessage = actionMessage;
	}

	public Action(String name) {
		super();
		this.name = name;
		conditions = new FunctionSignatureCollection();
		this.conditionLogic = ConditionLogic.NOTHING;
		this.tasks = new ArrayList<PremitiveTask>();
		this.actionType = ActionType.ORDERED;
	}

	
	

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public boolean addTask(PremitiveTask e) {
		return tasks.add(e);
	}

	public FunctionSignature getMessage() {
		return message;
	}

	public void setMessage(FunctionSignature message) {
		this.message = message;
	}
	
	public boolean addCondition(FunctionSignature e) {
		return conditions.add(e);
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
				+ ",tasks= "+tasks;
				
				
		return str+CommonStringUtils.CurlyBraceClose(true);
		
//		return "Action [name=" + name + ", message=" + message
//				+ ", conditions=" + conditions + ", conditionLogic="
//				+ conditionLogic 
//				+ ", actionMessage=" + actionMessage + ", duration=" + duration
//				+ ",tasks= "+tasks
//				+ "]";
	}
	

}
