package myUtil_Set;


import java.util.Iterator;
import java.util.TreeMap;

public class MyTreeSet<E> extends MyAbstractSet<E> {

	
	 
	protected TreeMap <E, Object> map;   // non-private for testing purposes
	
	// Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();
	
	
    /**
     * Constructs a new, empty set;  
     */
    public MyTreeSet() {
    	map = new TreeMap<E, Object>();
    }
    
    
    // addAll
	@Override
    public Iterator<E> iterator(){
		return this.map.keySet().iterator();
    }
       
	@Override
	public boolean contains(E o) {
		for(E e:map.keySet()) {
			if(e.equals(o)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
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
	
	
	 

}
