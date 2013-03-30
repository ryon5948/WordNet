package WordNet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Synset.Synset;

public class WordNet 
{
	
	// Actual WordNet class
	private static HashMap<Integer,Synset> wordNet = new HashMap<Integer,Synset>();
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) throws IOException
	{
		BufferedReader reader1 = new BufferedReader(new FileReader(synsets));
		BufferedReader reader2 = new BufferedReader(new FileReader(hypernyms));
		String line;
		
		// Creates a hashmap of synsets
		while ((line = reader1.readLine()) != null) {
			  WordNet.indexNouns(line);
			}
		reader1.close();
		
		// Adds relationships to the hashmap
		while ((line = reader2.readLine()) != null) {
			  WordNet.addRelations(wordNet, line);
			}
		reader1.close();
		
	}
	
	/**
	 * Takes the line read from the file and parses it into the three different sections
	 * used to create the WordNet.
	 * @param line
	 * @return
	 */
	private static void indexNouns(String line)
	{
			int i = 0;
			int id = 0;
			String noun = null;
			String glossary = null;
			
			while(line != null && i < 3)
			{
				int comma = line.indexOf(',');
				String word = i==2? line : line.substring(0,comma); // parsed word
				line = line.substring(comma+1);
				
				// Assigns values to the three needed variables
				if (i==0)
					id = Integer.parseInt(word);
				else if (i==1)
					noun = word;
				else
					glossary = word;
					
				i++;
			}
			
			// Add to WordNet
			Synset s = new Synset(noun,glossary);
			wordNet.put(id,s);
	}
	
	private static void addRelations(HashMap<Integer, Synset> wn, String line)
	{
			ArrayList<Integer> relations = new ArrayList<Integer>();
			boolean isFirst = true;
			Synset s = null;
			
			while(line != null && !line.isEmpty())
			{
				int comma = line.indexOf(',');
				String word;
					
					// If there is a comma at all
					if(comma == -1) {
						word = line;
						line = null;
					}
					else {
						word = line.substring(0,comma);
						line = line.substring(comma+1);
					}
				
				// Word is the id
				if(isFirst)
				{
					s = wn.get(word);
					isFirst = false;
				}
				// Word is the id of a relation
				else
				{
					if(s!=null)
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
			wn = new WordNet("nouns.txt","hypernyms.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(wn);
	}
	
}
