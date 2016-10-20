package week10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerHashMap<V> implements Map<Integer, V> {

	private HashMapEntry[] items;
	private int numberOfItems;

	class HashMapEntry implements Entry<Integer, V> {

		public Integer key;
		public V value;

		public HashMapEntry(Integer key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public Integer getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

	}

	private int hash(Integer key) {

		return key;
	}

	private int compress(int hash) {
		int index = Math.abs(hash) % items.length;
		return index;
	}

	@SuppressWarnings("unchecked")
	public IntegerHashMap(int size) {
		items = (IntegerHashMap<V>.HashMapEntry[]) new IntegerHashMap.HashMapEntry[size];
		this.numberOfItems = 0;
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
	public V get(Integer key) {
		int hash = hash(key);
		int index = compress(hash);
		if (items[index] != null) {
			return (items[index].key == hash) ? items[index].value : null;
		}
		return null;
	}

	@Override
	public V put(Integer key, V value) {
		int hash = hash(key);
		int index = compress(hash);
		if (get(index) != null) {
			if (items[index].key == hash) {
				V old = items[index].value;
				items[index] = new HashMapEntry(key, value);
				return old;
			} else {
				throw new IllegalArgumentException("Key conflict");
			}
		} else {
			items[index] = new HashMapEntry(key, value);
			numberOfItems++;
			return null;
		}
	}

	@Override
	public V remove(Integer key) {
		if (get(key) != null) {
			int i = compress(key);
			V old = items[i].value;
			items[i] = null;
			numberOfItems--;
			return old;
		}
		return null;
	}

	@Override
	public Iterable<Integer> keySet() {
		List<Integer> list = new ArrayList<>();
		list = Arrays.asList(items).stream().filter(e -> e != null).map(e -> e.key)
				.collect(Collectors.toList());
		return (Iterable<Integer>) list;
	}

	@Override
	public Iterable<V> values() {
		List<V> list = new ArrayList<>();
		list = Arrays.asList(items).stream().filter(e -> e != null).map(e -> e.value)
				.collect(Collectors.toList());
		return (Iterable<V>) list;
	}

	@Override
	public Iterable<Entry<Integer, V>> entrySet() {
		List<Entry<Integer, V>> list = new ArrayList<>();
		list = Arrays.asList(items).stream().filter(e -> e != null).collect(Collectors.toList());
		return (Iterable<Entry<Integer, V>>) list;
	}

}
