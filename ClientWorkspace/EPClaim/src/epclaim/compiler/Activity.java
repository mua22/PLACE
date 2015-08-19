package epclaim.compiler;

public class Activity extends Action {
	private FunctionSignatureCollection effects;
	public Activity(String name) {
		super(name);
		effects = new FunctionSignatureCollection();
	}
	public boolean addEffect(FunctionSignature e) {
		return effects.add(e);
	}
}
