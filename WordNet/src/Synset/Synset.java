package Synset;

import java.util.ArrayList;

public class Synset {
	private ArrayList<Integer> relations = new ArrayList<Integer>();
	private String noun;
	private String glossary;

	public Synset(String noun, String glossary) {
		this.noun = noun;
		this.glossary = glossary;
	}

	public void addRelation(int a) {
		relations.add(a);
	}

	public String getNoun() {
		return this.noun;
	}

	public String getGlossary() {
		return this.glossary;
	}

	public ArrayList<Integer> getRelations() {
		return this.relations;
	}
}
