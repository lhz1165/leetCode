package org.algorithm.third.no4;

/**
 * @author lhzlhz
 * @create 2/10/2021
 */
/**
 * 不带头结点；
 *
 * @author Yixin Cao
 *
 * A simple implementation of the linked list data structure.
 *
 * By default, it does not have a tail reference.
 *
 * Pay attention to the boundary cases, especially for operations at the tail.
 */
public class LinkedList<T> {
	//Node成为一个数据类型;
	static class Node<T> {
		T element;
		Node<T> next;

		//Node构造参数
		public Node(T a) {
			element = a;
			next = null;
		}
	}

	Node<T> head; // no tail by default,定义一个头指针，表示整个链表为空;

	//构造函数，初始化对象
	public LinkedList(){
		this.head = null;
	}

	// Running time: O( 1 ).
	public void insertFirst(T a) {
		Node<T> newNode = new Node<T>(a);
		newNode.next = head;
		head = newNode;
		// if (tail == null) tail = newNode;
	}

	// Running time: O( n ).
	public void insertLast(T a) {
		if (head == null) {
			insertFirst(a); // lazy and smart
			return;
		}

		Node<T> newNode = new Node<T>(a);
		Node<T> cur = head;
		while (cur.next != null)
			cur = cur.next;
		cur.next = newNode;
		newNode.next = null; // very important.
	}

	// Running time: O( 1 ).
	public void insertAfter(Node<T> insertionPoint, T a) {
		Node<T> newNode = new Node<T>(a);
		newNode.next = insertionPoint.next;
		insertionPoint.next = newNode;
	}

	// Running time: O(  ).
	public void insertAfter(T insertionPoint, T a) {
		// try to implement this.
	}

	// Running time: O( n ).
	public void insertBefore(Node<T> insertionPoint, T a) {
		if (head == insertionPoint) {
			insertFirst(a);
			return;
		}

		Node<T> cur = head;
		// at the end of this while loop,
		// cur will be the node that points to insertionPoint
		while (cur.next != insertionPoint && cur.next != null)
			cur = cur.next;
		Node<T> newNode = new Node<T>(a);
		newNode.next = cur.next;
		cur.next = newNode;
	}

	// Running time: O( 1 ).
	public T removeFirst() {
		if (head == null) {
			System.out.println("underflow");
			return null;
		}
		Node<T> temp = head;
		head = head.next;
		temp.next = null;      // optional but suggested.
		return temp.element;
	}
	//删除链表中的一个元素;
	public T removeNode(Node<T> insertionPoint){
		Node<T>temp=head;
		while(temp.next!=insertionPoint||temp.next!=null){
			temp=temp.next;
		}
		temp.next=insertionPoint.next;
		return insertionPoint.element;
	}

	// Running time: O( n ).
	public T removeLast() {
		if (head == null || head.next == null) // empty list, or a single-element list.
			return removeFirst();

		Node<T> secondToLast = head;
		Node<T> last = secondToLast.next;
		while (last.next != null) {
			secondToLast = last;
			last = last.next;
		}

		secondToLast.next = null;   // very important: many bugs are from omission of this step.
		return last.element;
	}

	// Running time: O( 1 ).
	public boolean isEmpty() {
		return head == null;
	}

	// Running time: O( n ).
	public Node<T> search(T a) {
		Node<T> cur = head;
		while(cur != null && cur.element != a)
			cur = cur.next;
		return cur;
	}

	public String toString() {
		if (head == null) return "The list is empty.";
		StringBuilder sb = new StringBuilder();
		sb.append(head.element);
		Node<T> cur = head.next;
		while ( cur != null ) {
			sb.append(" -> " + cur.element);
			cur = cur.next;
		}
		return sb.toString();
	}

	// reverse the elements in a linked list.
	public void reverse(Node<T> node) {
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		System.out.println(list.isEmpty());
		list.insertFirst(37);
		System.out.println(list.isEmpty());
		list.insertFirst(99);
		list.insertFirst(12);
		list.insertLast(38);
		System.out.println("After inserting 37, 99, 12 in the front and then 38 at the end, we get");
		System.out.println(list);

		Node<Integer> n99 = list.head;
		while(n99.next != null) n99 = n99.next;
		list.insertAfter(n99, 75);
		System.out.println(list);
		list.insertBefore(n99, 55);
		System.out.println(list);

		System.out.println("remove the last, which is " + list.removeLast());
		System.out.println(list);
	}
}

