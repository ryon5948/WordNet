import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WordNet 
{
	// Class that contains the relationships
	private class Synset 
	{
		private ArrayList<Integer> relations = new ArrayList<Integer>();
		private String noun;
		private String glossary;
		
		public Synset (String noun, String glossary) 
		{
			this.noun = noun;
			this.glossary = glossary;
		}
		
		public void addRelation(int a)
		{
			relations.add(a);
		}
		
		public String getNoun()
		{
			return this.noun;
		}
		
		public String getGlossary()
		{
			return this.glossary;
		}
		
		public ArrayList<Integer> getRelations()
		{
			return this.relations;
		}
	}
	
	// Actual WordNet class
	private HashMap<Integer,Synset> wordNet = new HashMap<Integer,Synset>();
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) throws IOException
	{
		BufferedReader reader1 = new BufferedReader(new FileReader(synsets));
		BufferedReader reader2 = new BufferedReader(new FileReader(hypernyms));
		String line;
		String[] wordSet = new String[3];
		
		// Creates a hashmap of synsets
		while ((line = reader1.readLine()) != null) {
			  wordSet = this.parseNouns(line);
			  wordNet.put(Integer.getInteger(wordSet[0]),new Synset(wordSet[1],wordSet[2]));
			}
		reader1.close();
		
		// Adds relationships to the hashmap
		while ((line = reader2.readLine()) != null) {
			  this.addRelations(wordNet, line);
			}
		reader1.close();
		
	}
	
	/**
	 * Takes the line read from the file and parses it into the three different sections
	 * used to create the WordNet.
	 * @param line
	 * @return
	 */
	private static String[] parseNouns(String line)
	{
			String[] pWords = new String[3];
			int i = 0;
			
			while(line != null && !line.isEmpty() && i < 3)
			{
				int space = line.indexOf(' ');
				String tLine = line.substring(0,space);
				line = line.substring(space);
				pWords[i] = tLine;
			}
			
			return pWords;
	}
	
	private static void addRelations(HashMap<Integer, Synset> wn, String line)
	{
			ArrayList<Integer> relations = new ArrayList<Integer>();
			boolean isFirst = true;
			
			while(line != null && !line.isEmpty())
			{
				Synset s = null;
				int space = line.indexOf(' ');
				String word = line.substring(0,space);
				line = line.substring(space);
				
				// Word is the id
				if(isFirst)
				{
					s = wn.get(word);
				}
				// Word is the id of a relation
				else
				{
					s.addRelation(Integer.getInteger(word));
				}
			}
	}
/*
	// returns all WordNet nouns
	public Iterable<String> nouns(){}

	// is the word a WordNet noun?
	public boolean isNoun(String word){}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB){}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB){}

	// for unit testing of this class*/
	public static void main(String[] args)
	{
		WordNet wn = null;
		try {
			wn = new WordNet("nouns.txt","synset.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(wn);
	}
	
}
