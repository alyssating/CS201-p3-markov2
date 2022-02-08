import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov {

    // create two constructors and implement two methods similar to those in setTraining and getFollows

    private Map<WordGram, ArrayList<String>> myMap;
	
	public EfficientWordMarkov(){
		this(3);
	}

	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	@Override
	public void setTraining(String text) {
        myWords = text.split("\\s+");

		myMap.clear();
		for (int i = 0; i < myWords.length - myOrder + 1; i++) {
			// WordGram wg = wg.shiftAdd(myWords[i]); // idk what I'm calling shiftAdd on
        
            WordGram wg = new WordGram(myWords, i, myOrder);

			// add it to myMap
			if (!myMap.containsKey(wg)) {
				myMap.put(wg, new ArrayList<String>());
			}

			if(i == myWords.length - myOrder ) {
				myMap.get(wg).add(PSEUDO_EOS);

			} else {
				myMap.get(wg).add(myWords[i + myOrder]);	
			}
        }
	}

	@Override
	public ArrayList<String> getFollows(WordGram key) {
		// TODO: Implement getFollows

		if (!myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		} else {
			return myMap.get(key);
		}
	}

    /*
    1. use wordgram objects as keys in an instance variable map of type HashMap<WordGram, ArrayList<String>>
    2. the instance variable String myTest from BaseMarkov becomes String[] myWords in BaseWordMarkov
    3. there should be two constructors, designed just like those in EfficientMarkov and using the same default order of 3
    4. In creating an array of words in the setTraining method, you can use text.split("\\s+") to process the String passed 
       to setTraining into an array of "words" separated by whitespace
    5. Instead of using substring() to create each key, you'll create a new WordGram for every key in the map. Consider
       whether the shiftAdd method might be convenient to use
    6. instead of using a one-character String to follow each key, you'll use the appropriate String in the myWords array
       as the string that follows each key
    7. getFollows is essentially the same, just looking up in a different map
    */


}
