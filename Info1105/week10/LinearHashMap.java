package week10;

import java.util.ArrayList;

public class LinearHashMap<K, V> implements Map<K, V> {

	private class HashMapEntry implements Entry<K, V> {

		private K key;
		private V value;

		public HashMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}
	}

	private HashMapEntry[] items;
	private int numberOfItems;
	private HashMapEntry DEFUNCT = new HashMapEntry(null, null);

	@SuppressWarnings("unchecked")
	public LinearHashMap(int capacity) {
		items = (LinearHashMap<K, V>.HashMapEntry[]) new LinearHashMap.HashMapEntry[capacity];
		this.numberOfItems = 0;
	}

	private int hash(K key) {
		return key.hashCode();
	}

	private int compress(int hash) {
		return Math.abs(hash) % items.length;
	}

	@Override
	public int size() {
		return numberOfItems;
	}

	@Override
	public boolean isEmpty() {
		return (numberOfItems == 0);
	}

	@Override
	public V get(K key) {
		int hash = hash(key);
		int index = compress(hash);
		int index2 = index;

		do {
			if (items[index] != null && !items[index].equals(DEFUNCT)) {
				if (items[index].key.equals(key)) {
					if (items[index].value != null) {
						return items[index].value;
					}
				}
			}
			index = (index + 1) % items.length;
		} while (index != index2);
		return null;
	}

	@Override
	public V put(K key, V value) {
		int hashedKey = hash(key);
		int index = compress(hashedKey);
		int startIndex = index;

		do {
			if (items[index] == null || items[index].equals(DEFUNCT)) {
				items[index] = new HashMapEntry(key, value);
				numberOfItems++;
				return null;
			} else if (items[index].key.equals(key)) {
				V oldValue = items[index].getValue();
				items[index].value = value;
				return oldValue;
			}
			index = (index + 1) % items.length;
		} while (index != startIndex);
		throw new RuntimeException("Table is full");
	}

	@Override
	public V remove(K key) {
		int hash = hash(key);
		int index = compress(hash);
		int startIndex = index;
		do {
			if (items[index] != null && !items[index].equals(DEFUNCT)) {
				if (items[index].key.equals(key)) {
					V returnValue = items[index].value;
					items[index] = DEFUNCT;
					numberOfItems--;
					return returnValue;
				}
			}
			index = (index + 1) % items.length;
		} while (index != startIndex);
		return null;
	}

	@Override
	public Iterable<K> keySet() {
		ArrayList<K> list = new ArrayList<K>();

		for (HashMapEntry k : items) {
			if (k != null) {
				if (!k.equals(DEFUNCT)) {
					list.add(k.getKey());
				}
			}
		}
		return list;
	}

	@Override
	public Iterable<V> values() {
		ArrayList<V> list = new ArrayList<V>();

		for (HashMapEntry value : items) {
			if (value != null) {
				if (!value.equals(DEFUNCT))
					list.add(value.getValue());
			}
		}
		return list;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> list = new ArrayList<Entry<K, V>>();

		for (HashMapEntry entry : items) {
			if (entry != null) {
				if (!entry.equals(DEFUNCT))
					list.add(entry);
			}
		}
		return list;
	}

}
