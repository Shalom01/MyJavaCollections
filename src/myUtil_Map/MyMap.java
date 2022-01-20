package myUtil_Map;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

class MyMap<K, V> extends HashMap<K, V> {
	HashMap<K, V> aMap;
		public MyMap (HashMap<K, V> map){
		aMap = new HashMap<K, V>();
		aMap.putAll(map);
	}
	public void specialAdd(HashMap<K, V> map) {
		int counter = 0;
		Set<K> keySet = map.keySet();
		for (K key: keySet) {
			if (counter %2 == 0) {
				aMap.put(key, map.get(key));
				counter++;
			}
		}
	}
	
	public boolean equals (Object obj) {
		boolean equal = true;
		MyMap<K, V> mymap = (MyMap<K, V>) obj;
		
		if(mymap==this) 
			return equal;
		
		if(mymap==null) 
			return !equal;
		
		equal = Objects.equals(aMap, mymap.aMap);
		return equal;
		}
	
	public String toString() {
		String temp = "";
		for(K key: this.aMap.keySet()) {
		temp = temp + "["+ key.toString() +", "+ aMap.get(key)+ "]";
		}
		return temp. trim();
	}
	public static void main(String[] args) {
		HashMap<Integer, String> mapl = new HashMap<Integer, String>();
		mapl.put(1, "A");

		mapl.put(2, "8");

		mapl.put(3, "8");

		MyMap<Integer, String> mymapl = new MyMap<Integer, String>(mapl);
		HashMap<Integer, String> map2 = new HashMap<Integer, String>();
		map2.put(3, "8");

		map2.put(1, "A");

		map2.put(2, "8");

		MyMap<Integer, String> mymap2 = new MyMap<Integer, String>(map2);
		System.out.println(mymapl.equals(mymap2));

		mymap2. specialAdd(mymapl);
		System.out.println(mymapl.equals(mymap2));

		map2.put(4, "D");

		mymap2 = new MyMap<Integer, String>(map2);

		mymap2. specialAdd(mymapl) ;
		System.out.println(mymapl.equals(mymap2));

	}
}
