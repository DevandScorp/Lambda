package Tests;

public class QueueTest {
    private  Node first,last;

    public QueueTest(String node) {
        last = new Node(node);
        first = last;
    }

    private  class Node{
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
        }
    }
    public void push(String str){
        Node oldLast = last;
        last = new Node(str);
        last.next = null;
        if(first==null) {
            first = last;
        }
        else{
            oldLast.next = last;
        }
    }
    public  void show(){
        while(first!=null){
            System.out.println(first.data);
            first = first.next;
        }
    }
    public String pop(){
        String s = first.data;
        first = first.next;
        return s;
    }
    public static void main(String[] args) {
        QueueTest test = new QueueTest("First");
        test.push("Second");
        test.push("Third");
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
    }
}
