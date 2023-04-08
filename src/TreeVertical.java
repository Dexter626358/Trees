import java.util.List;

public class TreeVertical {
    Node root;
// обход дерева в глубину. Рекурсивный подход.
    public boolean exists(int value){
        if(root != null){
            Node node = find(root, value);
            if(node != null){
                return true;
            }
        }
        return false;
    }
    private Node find(Node node, int value){
        if(node.value == value){
            return node;
        }else{
            for(Node child: node.children){
                Node result = find(child, value);
                if(result != null){
                    return result;
                }
            }
        }
        return null;
    }
    public class Node{
        int value;
        List<Node> children;
    }
}
