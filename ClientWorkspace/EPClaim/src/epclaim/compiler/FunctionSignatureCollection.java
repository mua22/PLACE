package epclaim.compiler;

import java.util.ArrayList;

import epclaim.utils.CommonStringUtils;

public class FunctionSignatureCollection {
	private ArrayList<FunctionSignature> functionSignatureCollection;

	public ArrayList<FunctionSignature> getFunctionSignatureCollection() {
		return functionSignatureCollection;
	}

	public void setFunctionSignatureCollection(
			ArrayList<FunctionSignature> functionSignatureCollection) {
		this.functionSignatureCollection = functionSignatureCollection;
	}

	public FunctionSignatureCollection() {
		super();
		this.functionSignatureCollection = new ArrayList<FunctionSignature>();
	}

	@Override
	public String toString() {
		String str = CommonStringUtils.CurlyBraceOpen(false);
		for(FunctionSignature kno:functionSignatureCollection)
			str+=kno;
		str+=CommonStringUtils.CurlyBraceClose(false);
		return str;
	}
	
	public boolean add(FunctionSignature e) {
		return functionSignatureCollection.add(e);
	}
	
}
