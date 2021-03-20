 
package doublylinkedlis;

public class Doublylinkedlis {
 
  private Node head;
  private Node tail;
  private int size = 0;
  static class Node{
    //data
    int i;
    // next node in the list
    Node next;
    // previous node in the list
    Node prev;
    Node(int i){
      this.i = i;
    }
    public void displayData(){
      System.out.print(" " + i);
    }
  }
  // constructor
  public Doublylinkedlis(){
    this.head = null;
    this.tail = null;
  }

  public boolean isEmpty(){
    return head == null;
  }
    
  public void insertFirst(int i){
    //Create a new node
    Node newNode = new Node(i);
    // if first insertion tail should
    // also point to this node
    if(isEmpty()){
        tail = newNode;
    }else{
        head.prev = newNode;
    }
    newNode.next = head;
    head = newNode;
    size++;
  }
    

  public void insertLast(int i){
    Node newNode = new Node(i);
    // if first insertion head should
    // also point to this node
    if(isEmpty()){
      head = newNode;
    }else{
      tail.next = newNode;
      newNode.prev = tail;
    }
    tail = newNode;
    size++;
  }
    
  public void insertAtIndex(int i, int index){
    if(!isValidIndex(index)){
      throw new IndexOutOfBoundsException("Index " + index +" not valid for linked list of size " + size);
    }
    Node newNode = new Node(i);
    Node current = head;
    //insert at the start
    if(index == 0){
      insertFirst(i);
    }
    // insert at last
    else if(index == size){
      insertLast(i);
    }else{
      for(int j = 0; j < index && current.next != null; j++){
        current = current.next;
      }
      newNode.next = current;
      current.prev.next = newNode;
      newNode.prev = current.prev;
      current.prev = newNode;
      size++;    
    }      
  }
    
  public Node deleteFirst(){
    if(head == null){
      throw new RuntimeException("List is empty");
    }
    Node first = head;
    if(head.next == null){
      tail = null;
    }else{
      // previous of next node (new first) becomes null
      head.next.prev = null;
    }
    head = head.next;
    size--;
    return first;
  }
    
  public Node deleteLast(){
    if(tail == null){
      throw new RuntimeException("List is empty");
    }
    Node last = tail;
    if(head.next == null){
      head = null;
    }else{
      // next of previous node (new last) becomes null
      tail.prev.next = null;
    }
    tail = tail.prev;
    size--;
    return last;
  }

  public Node deleteAtIndex(int index){
    if(!isValidIndex(index+1)){
      throw new IndexOutOfBoundsException("Index " + index +" not valid for linked list of size " + size);
    }
    Node current = head;
    //remove at the start
    if(index == 0){
      return deleteFirst();
    }
    // remove at last
    else if(index == size-1){
      return deleteLast();
    }else{
      for(int j = 0; j < index && current.next != null; j++){
        current = current.next;
      }
      current.prev.next = current.next;
      current.next.prev = current.prev;
      size--;
    }
    return current;
  }
    
  private boolean isValidIndex(int index){
    return index >= 0 && index <= size;
  }
    
  // Method for forward traversal
  public void displayForward(){
    Node current = head;
    while(current != null){
      current.displayData();
      current = current.next;
    }
    System.out.println("");
  }
    
  // Method to traverse and display all nodes
  public void displayBackward(){
    Node current = tail;
    while(current != null){
      current.displayData();
      current = current.prev;
    }
    System.out.println("");
  }
    
  public static void main(String[] args) {
    Doublylinkedlis list = new Doublylinkedlis();        
    list.insertFirst(1);        
    list.insertFirst(2);
    list.insertLast(3);
    list.insertLast(4);
    list.displayForward();
    list.insertAtIndex(5, 3);
    System.out.println("Linked list backward traversal");
    list.displayBackward();
    System.out.println("Linked list forward traversal");
    list.displayForward();
    Node node = list.deleteAtIndex(2);
    System.out.println("Node with value "+ node.i + " deleted");
    list.displayForward();
  }
}
   


