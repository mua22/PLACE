package epclaim.compiler;

import java.util.ArrayList;

public class ActivityCollection {
	private ArrayList<Activity> activities;

	public ActivityCollection() {
		super();
		activities = new ArrayList<Activity>();
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public boolean add(Activity e) {
		return activities.add(e);
	}

	@Override
	public String toString() {
		return "Activities [activities=" + activities + "]";
	}
}
