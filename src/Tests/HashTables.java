package Tests;

public class HashTables {
    private Node[] table;
    private static class Node{
        private Integer key;
        private String value;
        private Node next;

        public Node(Integer key, String value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    private int hash(Integer key){
        return Math.abs(key.intValue())%10;
    }
    public HashTables() {
        table = new Node[10];
        for(int i = 0;i<10;++i){
            table[i] = new Node(i,"",null);
        }
    }
    public String get(Integer key){
        for(Node node = table[hash(key)];node!=null;node = node.next){
            if(node.key.equals(key))return node.value;
        }
        return null;
    }
    public void put(Integer key, String value){
        int hash = hash(key);
        for(Node node = table[hash];node!=null;node=node.next){
            if(node.key.equals(key)){
                node.value = value;
                return;
            }
            table[hash] = new Node(key,value,table[hash]);
        }
    }

    public static void main(String[] args) {
        HashTables hashTables = new HashTables();
        for(int i = 0;i<11;++i){
            hashTables.put(i,i+"-ый");
        }
        for(int i = 0;i<11;++i){
            System.out.println(hashTables.get(i));
        }
    }
}
