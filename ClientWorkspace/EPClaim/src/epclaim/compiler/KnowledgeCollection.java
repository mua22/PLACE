package epclaim.compiler;

import java.util.ArrayList;
import java.util.Collection;

import epclaim.utils.CommonStringUtils;

public class KnowledgeCollection {
	private ArrayList<Knowledge> collection;

	public KnowledgeCollection() {
		super();
		collection = new ArrayList<Knowledge>();
	}

	/**
	 * Removes Knowledge from collection
	 * @param knowledge
	 * @return
	 * @see java.util.ArrayList#remove(java.lang.Object)
	 */
	public boolean remove(Knowledge knowledge) {
		return collection.remove(knowledge);
	}

	public boolean add(Knowledge e) {
		return collection.add(e);
	}
	public KnowledgeCollection mergeKnowledgeCollection(KnowledgeCollection kc){
		KnowledgeCollection tempKC = new KnowledgeCollection();
		for(Knowledge kno:this.collection)
			tempKC.add(kno);
		for(Knowledge kno:kc.collection)
			tempKC.add(kno);
		
		return tempKC;
	}
	public ArrayList<Knowledge> getCollection() {
		return collection;
	}

	/**
	 * @param collection Set the array list of the knowledge
	 */
	public void setCollection(ArrayList<Knowledge> collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {
		//String str = CommonStringUtils.CurlyBraceOpen(true);
		String str="\n";
		for(Knowledge kno:collection)
			str+=kno;
		//str+=CommonStringUtils.CurlyBraceClose(true);
		return str+"\n";
	}
	/**
	 * Constructor for deep cloning
	 * @param knowledgeCollection
	 */
	public KnowledgeCollection(KnowledgeCollection knowledgeCollection){
		this();
		for(Knowledge knowledge:knowledgeCollection.getCollection()){
			this.add(new Knowledge(knowledge));
		}
	}
	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean addAll(Collection<? extends Knowledge> c) {
		return collection.addAll(c);
	}
	/**
	 * 
	 * @param c
	 * @return
	 */
	public boolean removeAll(Collection<?> c) {
		return collection.removeAll(c);
	}
	
}
