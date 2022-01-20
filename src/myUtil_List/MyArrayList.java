package myUtil_List;

public class MyArrayList<E> implements MyList<E> {

	private static final int DEFAULT_CAPACITY = 10;
	
	int size;  // non-private for testing purpose
	E [] elementData; // non-private for testing purpose
	
	/**
	 * Create an empty list of capacity capa
	 * 
	 * @param capa the initial capacity. Assume greater than 0.
	 */
	
	@SuppressWarnings({"unchecked"})
    public MyArrayList(int capa) {
		this.elementData = (E[])new Object [capa]; // generic for Array, have to do this
	}

	/**
	 * Create an empty list of default capacity
	 * 
	 */
	
    @SuppressWarnings({"unchecked"})
    public MyArrayList() {
		this.elementData = (E[])new Object [DEFAULT_CAPACITY];
	}

	@Override
	public boolean isEmpty() {
		for(E e:elementData) {
			if(!(e==null)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean contains(E o) {
		for(E e: elementData) {
			if(o.equals(e)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void add(E o) {
		ensureCapacity(size + 1);
		elementData[size++]=o;
	}
	
	@Override
	public void remove(E o) {
		for(int i=0; i<size; i++) {
			if(o.equals(elementData[i])) {
				fastRemove(i);
				break;
			}
		}
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return elementData[index];	
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		E previousElement = elementData[index];
		elementData[index]=element;
		return previousElement;
	}

	@Override
	public void add(int index, E element) {
		rangeCheck(index); //make sure the index is within the array size.
		ensureCapacity(size + 1); //make sure we can grow the array.
		System.arraycopy(elementData, index, elementData, index + 1, size - index); //shift the array to the right.
		elementData[index]=element; //change the element of the index.
		size++; //increase the size.
	}

	@Override
	public E remove(int index) {
		rangeCheck(index); //make sure the index is within the array size.
		E e = elementData[index]; //copy the element at the index.
		fastRemove(index);
		return e; //return the element.
	}

	@Override
	public int indexOf(E o) {
		for(int i=0; i<elementData.length; i++) {
			if(o.equals(elementData[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E o) {
		for(int i=size; i>=0; i--) {
			if(o.equals(elementData[i])) {
				return i;
			}
		}
		return 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	private void ensureCapacity(int index) {
		if(index>=elementData.length) {
			E[] copy = (E[]) new Object[elementData.length+DEFAULT_CAPACITY];
			for(int i=0; i<elementData.length; i++) {
				copy[i]=elementData[i];
			}
			elementData=copy;
		}
	}

	private void rangeCheck(int index) {
		if(index>=size) {
			throw new ArrayIndexOutOfBoundsException("index does not exists in MyArrayList");
		}
	}
	
	private void fastRemove(int index) {
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1); //shift everything to the left.
		elementData[--size]=null; //set the last element to null (it was copied during shift).
	}
}
