import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WordNet 
{
	// Class that contains the relationships
	class Synset 
	{
		List<Integer> relations;
		String word;
		
		public Synset ()
		{
			this.relations = new ArrayList<Integer>();
			word = null;
		}
		
		public Synset (String word) 
		{
			this.word = word;
			this.relations = new ArrayList<Integer>();
		}
		
		public void addRelation(int a)
		{
			relations.add(a);
		}
	}
	
	// Actual WordNet class
	private HashMap<Integer,Synset> wordNet = new HashMap<Integer,Synset>();
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms)
	{
		BufferedReader reader = new BufferedReader(new FileReader(synsets));
		String line;
		String[] wordSet = new String[3];
		
		// Creates a hashmap of synsets
		while ((line = reader.readLine()) != null) {
			  wordSet = this.parseLine(line);
			  wordNet.put(Integer.getInteger(wordSet[0]),new Synset(wordSet[1]));
			}
		reader.close();
	}
	
	private static String[] parseLine(String line)
	{
			String[] pWords = new String[3];
			int i = 0;
			
			while(line != null && !line.isEmpty())
			{
				int space = line.indexOf(' ');
				String tLine = line.substring(0,space);
				line = line.substring(space);
				pWords[i] = tline;
			}
			
			return pWords;
	}

	// returns all WordNet nouns
	public Iterable<String> nouns()

	// is the word a WordNet noun?
	public boolean isNoun(String word)

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB)

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB)

	// for unit testing of this class
	public static void main(String[] args)
	
}
