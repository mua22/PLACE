package epclaim.compiler;

import java.util.ArrayList;

import epclaim.utils.CommonStringUtils;

public class KnowledgeCollection {
	private ArrayList<Knowledge> collection;

	public KnowledgeCollection() {
		super();
		collection = new ArrayList<Knowledge>();
	}

	public boolean add(Knowledge e) {
		return collection.add(e);
	}

	public ArrayList<Knowledge> getCollection() {
		return collection;
	}

	public void setCollection(ArrayList<Knowledge> collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {
		String str = CommonStringUtils.CurlyBraceOpen(true);
		for(Knowledge kno:collection)
			str+=kno;
		str+=CommonStringUtils.CurlyBraceClose(true);
		return str;
	}
	
}
