package myUtil_List;

public class MyLinkedList <E> implements MyList <E>{
    private Node head;  // instance variable.

    // Default constructor
    
    public MyLinkedList() {
        this.head=null;
    }
    
    /*
     * public method that returns the size of the LinkedList
     */

    public int size() {
        Node curr = this.head;
        int len = 0;
        while (curr != null)
        {
            curr = curr.getNext();  // or just curr =curr.next;
            len++;
        }
        return len;
    }

   /* assume list is not empty, and index is valid [0, len()-1] */ 
    public  E get(int index)
    {
        Node curr = this.head;
        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }
        return curr.data;
    }

    /**
     * appends the specified element to the begining of this list.
     */
    public void add(E data) {
        Node newNode = new Node(data);

        // insert at the begining. Order matters.
        // also works if the list is empty.
        newNode.next = head;    // newNode.setNext(this.head);   will be null if list is empty
        head = newNode;
    }

    /** inserts the specified element after the specified position in this list
    *assume list is not empty.  index is valid [0 - len()-1]
    * 
    */
    public void add(int index, E data) {
        Node curr = this.head;

        // crawl to the requested index 
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        // insert after curr, and before curr.next;  order matters!!!
        Node newNode = new Node(data);

        // set the new node's next-node reference to curr node's next-node reference
        newNode.next = curr.next;

        // now set curr node's next-node reference to the new node
        curr.next = newNode;
    }

    // removes the element with data dat in this list.
    // assume the list is not empty
    // assume the node to be deleted exists in the list, and no duplicated keys.
    public void remove(E dat) {
        if (head.data == dat)
        {
            head = head.next;
        }
        else{
            Node curr = head;
            while (curr.next.data != dat)
            {
                curr = curr.next;
            }
            // curr is the one preceding the one to be deleted
            // remove curr.next
            curr.next = curr.next.next; // by-pass curr.next; gabage collector will recycle the deleted node.
        }
    }
    
    @Override
    public String toString() {
        String output = "";
        if (head != null) {
            Node curr = head;
            while (curr != null) {
               output += "" + curr.getData() + "   ";
               curr = curr.getNext();
            }
        }
        return output;
    }
    
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
	public boolean contains(E o) {
		 Node curr = this.head;
	     for(int i = 0; i < this.size(); i++) {
	    	 if(this.get(i).equals(o)) {
	    		 return true;
	    	 }
	     }
	     return false;
	}

	@Override
	public E set(int index, E element) {
		if(index>=0 && index < this.size()) {
			Node node = (MyLinkedList<E>.Node) this.get(index);
			E data = node.getData();
			node.setData(element);
			return data;
		}else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public E remove(int index) {
		if(index>=0 && index < this.size()) {
			Node node = (MyLinkedList<E>.Node) this.get(index);
			Node previous = (MyLinkedList<E>.Node) this.get(index - 1);
			Node next =  (MyLinkedList<E>.Node) this.get(index + 1);
			E data = node.getData();
			previous.setNext(next);
			node = null;
			return data;
		}else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public int indexOf(E o) {
		for(int i = 0; i < this.size(); i++) {
			if(this.get(i).equals(o))
				return i;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E o) {
		for(int i = this.size(); i >= 0; i--) {
			if(this.get(i).equals(o))
				return i;
		}
		return -1;
	}

    /*
     * private class Node that holds data, and the value of next Node
     */
    private class Node {
        // two attributes (instance variables)

        // data carried by this node. 
        E data;
        // reference to the next node in the chain, or null if there isn't one.
        Node next;

        // Node constructor
        public Node(E dataValue) {
            this.data = dataValue;
            this.next = null;
        }

        // these methods should be self-explanatory
        public E getData() {
            return this.data;
        }

        public void setData(E dataValue) {
            this.data = dataValue;
        }

        public Node getNext() {
            return this.next;
        }

        public void setNext(Node nextNode) {
            this.next = nextNode;
        }

    }

    public static void main(String[] args) { //test with PSVM

        MyLinkedList ll = new MyLinkedList<Character>();

        ll.add('S'); System.out.printf("insert S: (%d)\t%s\n", ll.size(), ll.toString());
        ll.add('B'); System.out.printf("insert B: (%d)\t%s\n", ll.size(), ll.toString());
        ll.add('A'); System.out.printf("insert A: (%d)\t%s\n", ll.size(), ll.toString());
        ll.add('L'); System.out.printf("insert L: (%d)\t%s\n", ll.size(), ll.toString());
        ll.add('U'); System.out.printf("insert U: (%d)\t%s\n", ll.size(), ll.toString());
        ll.add('K'); System.out.printf("insert K: (%d)\t%s\n", ll.size(), ll.toString());
        ll.add('R'); System.out.printf("insert R: (%d)\t%s\n", ll.size(), ll.toString());
        ll.add('O'); System.out.printf("insert O: (%d)\t%s\n", ll.size(), ll.toString());
        ll.add('Y'); System.out.printf("insert Y: (%d)\t%s\n", ll.size(), ll.toString());
        System.out.println(ll.size());

        char[] removeList ={'B','S','A','O','R','K','Y','U','L'};
        for (int i=0;i<removeList.length ; i++)
        {
            char ele = removeList[i];
            ll.remove((Character)ele);
            System.out.printf("remove %c: (%d)\t%s\n", ele, ll.size(), ll);
        }
        // insert again
        char[] insertList ={'S','B','A','L','U','K','R','O','Y'};
        for (int i=0;i<insertList.length ; i++)
        {
            char ele = insertList[i];
            ll.add(ele); 
            System.out.printf("insert %c: (%d)\t%s\n", ele, ll.size(), ll);
        }

        char v = (char) ll.get(0);  System.out.println("\nget(0): " + v);
        v = (char) ll.get(3);  System.out.println("get(3): " + v);
        v = (char) ll.get(6);  System.out.println("get(6): " + v);
        v = (char) ll.get(7);  System.out.println("get(7): " + v);	
        v = (char) ll.get(8);  System.out.println("get(8): " + v);

        ll.add(2,'x'); System.out.printf("\ninsert x after index 2: (%d)\t%s\n", ll.size(), ll);
        ll.add(0,'y'); System.out.printf("insert y after index 0: (%d)\t%s\n", ll.size(), ll);
        ll.add(6,'z'); System.out.printf("insert z after index 6: (%d)\t%s\n", ll.size(), ll);
        v = (char) ll.get(0);  System.out.println("\nget(0): " + v);
        v = (char) ll.get(3);  System.out.println("get(3): " + v);
        v = (char) ll.get(6);  System.out.println("get(6): " + v);
        v = (char) ll.get(7);  System.out.println("get(7): " + v);
       	v = (char) ll.get(8);  System.out.println("get(8): " + v + "\n");


    }
}