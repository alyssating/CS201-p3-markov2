import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}

	@Override
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();
		
		for (int i = 0; i <= text.length() - myOrder; i++) {
			String str = text.substring(i, i+myOrder);

			// add it to myMap
			if (!myMap.containsKey(str)) {
				myMap.put(str, new ArrayList<String>());
			}

			if(i == text.length() - myOrder) {
				myMap.get(str).add(PSEUDO_EOS);

			} else {
				myMap.get(str).add(text.substring(i + myOrder, i + myOrder + 1));	
			}
		}
	}
	@Override
	public ArrayList<String> getFollows(String key) {
		// TODO: Implement getFollows
		if (myMap.containsKey(key)){
			return myMap.get(key);
		} else {
			throw new NoSuchElementException(key+" not in map");
		}
	}
}	
