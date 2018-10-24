package Tests;

public class Test {
    private  Node last;

    public Test(String node) {
        last = new Node(node);
    }

    private  class Node{
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
        }
    }
    public void push(String str){
        Node node = new Node(str);
        node.next = last ;
        last = node;
    }
    public  void show(){
        while(last !=null){
            System.out.println(last .data);
            last  = last .next;
        }
    }
    public String pop(){
        if(last  == null)return null;
        String s = last .data;
        last  = last.next;
        return s;
    }
    public static void main(String[] args) {
        Test test = new Test("First");
        test.push("Second");
        test.push("Third");
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
    }
}
