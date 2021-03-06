package myUtil_Set;

import java.util.HashMap;
import java.util.Iterator;

public class MyHashSet<E> extends MyAbstractSet<E> {

	
	private HashMap <E, Object> map;   
	
	/** Dummy value to associate with an Object in the backing Map
	    used as vlaues for all the keys in the backing Map
	*/
    private static final Object PRESENT = new Object();
	
	
    /**
     * Constructs a new, empty set; 
     */
    public MyHashSet() {
        map = new HashMap<>();
    }
    
    /**
	 * Create an empty set of default capacity
	 * 
	 */
    public MyHashSet(int initialCapacity) {
        super(); // can be skipped
    	this.map = new HashMap<>(initialCapacity);
    }
    
     
	@Override
    public Iterator<E> iterator(){
    	return this.map.keySet().iterator();
    }
       
	@Override
	public boolean contains(E o) { 
		for(E e:map.keySet()) {
			if(o.equals(e)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	// Hint. add e into map as key, with the Dummy object PRESENT as value.
	// Hint. look Map API for the return type of put
	public boolean add(E e) { 
		if(map.put(e, PRESENT)==null) {
			size++;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(E o) {
		if(map.remove(o)!=null) {
			size--;
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		map.clear();
		size=0;
	}
	
	//Other methods, such as the following size(), isEmpty(), addAll(),   
	//are inherited from MyAbstractSet
     
}
