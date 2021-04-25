

public class Node {
    char data;
    Byte isEndNode;
    Node left, mid, right;

    public Node(char data){
        this.data = data;
        this.isEndNode = 0;
        this.left = this.mid = this.right = null;
    }
}