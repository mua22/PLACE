package epclaim.compiler;

import java.util.ArrayList;

import epclaim.utils.CommonStringUtils;

public class ActionCollection {
	private ArrayList<Action> actions;

	public ActionCollection() {
		super();
		actions = new ArrayList<Action>();
	}

	public boolean add(Action e) {
		return actions.add(e);
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}

	@Override
	public String toString() {
		String str = CommonStringUtils.CurlyBraceOpen(true);
		for(Action kno:actions)
			str+=kno;
		str+=CommonStringUtils.CurlyBraceClose(true);
		return str;
	}
	
}
