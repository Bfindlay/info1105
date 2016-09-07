package week7;

//This is a combination of the Map and SortedMap ADT.
//It is simplified to only support Integer keys, and String values

public interface SimpleSortedMap {

	//////////////////////////
	// Map ADT methods:
	//////////////////////////

	// Returns the number of entries in the map
	int size();

	// Is the map empty or not
	boolean isEmpty();

	// Returns the value associated with key k, if such an entry
	// exists; otherwise returns null
	String get(Integer k);

	// If there is no entry with key equal to k, then adds entry
	// (k,v) and returns null; else replaces with v the existing
	// value and returns the old value.
	String put(Integer k, String v);

	// Removes the entry with key equal to k and returns its value
	// If no such entry exists, then returns null
	String remove(Integer k);

	// Returns an iterable collection containing all the keys
	// stored in the map
	Iterable<Integer> keySet();

	// Returns an iterable collection containing all the values of
	// entries stored in the map (with repetition if a multiple
	// keys map to the same value
	Iterable<String> values();

	// Returns an iterable collection containing all the key-value
	// entries in the map
	Iterable<SimpleEntry> entrySet();

	//////////////////////////
	// SortedMap ADT methods:
	//////////////////////////

	// Returns the entry having the least key (or null if map is empty).
	SimpleEntry firstEntry();

	// Returns the entry having the greatest key (or null if map is empty).
	SimpleEntry lastEntry();

	// Returns the entry with least key greater than or equal to given key
	// (or null if no such key exists).
	SimpleEntry ceilingEntry(Integer key);

	// Returns the entry with greatest key less than or equal to given key
	// (or null if no such key exists).
	SimpleEntry floorEntry(Integer key);

	// Returns the entry with greatest key strictly less than given key
	// (or null if no such key exists).
	SimpleEntry lowerEntry(Integer key);

	// Returns the entry with least key strictly greater than given key
	// (or null if no such key exists).
	SimpleEntry higherEntry(Integer key);

	// Returns an iterable containing all keys in the range from
	// fromKey (inclusive) to toKey (exclusive)
	Iterable<Integer> subMap(Integer fromKey, Integer toKey);

}