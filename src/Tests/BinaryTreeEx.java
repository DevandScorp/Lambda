package Tests;

public class BinaryTreeEx {
    private Node root;

    private class Node {
        private Integer data;
        private Node left;
        private Node right;

        public Node(Integer data) {
            this.data = data;
        }
    }

    public void put(Integer integer) {
        root = put(root, integer);
    }

    private Node put(Node node, Integer data) {
        if (node == null) return new Node(data);
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = put(node.left, data);
        }
        if (cmp > 0) {
            node.right = put(node.right, data);
        } else if (cmp == 0) {
            node.data = data;
        }
        return node;
    }
    public void show(){
        show(root);
    }
    private void show(Node node) {
        if (node != null) {
            show(node.left);
            System.out.println(node.data);
            show(node.right);
        }
    }
    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }
    private Node deleteMin(Node node){
        if(node.left == null) return node.right;//идем,пока не находим нулевую левую ссылку
        //если нашли-заменяем на правую ветку(т.к. слева уже ничего нет,а значит отец тих двух поддеревьев и есть
        //наименьший элемент
        node.left = deleteMin(node.left);
        return node;
    }
    public void delete(Integer key){
        root = delete(root,key);
    }
    private Node delete(Node node, Integer key){
        if(node == null) return null;
        int cmp = key.compareTo(node.data);
        if(cmp<0) node.left = delete(node.left,key);
        else if(cmp > 0) node.right = delete(node.right,key);
        else if(cmp==0){
            if(node.right == null) return node.left;
            if(node.left == null) return node.right;
            Node t = node;
            node = min(t.right);//наименьший правый элемент
            node.right = deleteMin(t.right);//присваиваем правому наименьший
            node.left = t.left;


        }
        return node;
    }
    public static void main(String[] args) {
        BinaryTreeEx ex = new BinaryTreeEx();
        ex.put(2);
        ex.put(1);
        ex.put(4);
        ex.put(3);
        ex.delete(1);
        ex.show();
    }
}
