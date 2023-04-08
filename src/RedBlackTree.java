public class RedBlackTree {
    private Node root;

    public boolean add(int value){
        if(root != null){
            boolean result = addNode(root, value);
            root = rebalance(root);
            root.color = Color.BLACK;
            return result;
        }else{
            root = new Node();
            root.color = Color.BLACK;
            root.value = value;
            return true;
        }
    }

    private boolean addNode(Node node, int value){
        if(node.value == value){
            return false;
        }else{
            if(node.value > value){
                if(node.leftchild != null){
                    boolean result = addNode(node.leftchild, value);
                    node.leftchild = rebalance(node.leftchild);
                    return result;
                }else{
                    node.leftchild = new Node();
                    node.leftchild.color = Color.RED;
                    node.leftchild.value = value;
                    return true;
                }
            }else{
                if(node.rightChild != null){
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                }else{
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }
        }
    }

    private Node rebalance(Node node){
        Node result = node;
        boolean needRebalance;
        do{
            needRebalance = false;
            if(result.rightChild != null && result.rightChild.color == Color.RED &&
                    (result.leftchild == null || result.leftchild.color == Color.BLACK)){
                needRebalance = true;
                result = rightSwap(result);
            }
            if(result.leftchild != null && result.leftchild.color == Color.RED &&
                    result.leftchild.leftchild != null && result.leftchild.leftchild.color == Color.RED){
                needRebalance = true;
                result = leftSwap(result);
            }

            if(result.leftchild != null && result.leftchild.color == Color.RED &&
                    result.rightChild != null && result.rightChild.color == Color.RED){
                needRebalance = true;
                colorSwap(result);
            }
        }while (needRebalance);
        return result;
    }

    private Node leftSwap(Node node){
        Node leftchild = node.leftchild;
        Node betweenChild = leftchild.rightChild;
        leftchild.rightChild = node;
        node.leftchild = betweenChild;
        leftchild.color = node.color;
        node.color = Color.RED;
        return leftchild;
    }

    private Node rightSwap(Node node){
        Node rightChild = node.rightChild;
        Node betweenChild = rightChild.leftchild;
        rightChild.leftchild = node;
        node.rightChild = betweenChild;
        rightChild.color = node.color;
        node.color = Color.RED;
        return rightChild;
    }

    private void colorSwap(Node node){
        node.leftchild.color = Color.BLACK;
        node.rightChild.color = Color.BLACK;
        node.color = Color.RED;
    }
    private class Node{
        private int value;
        private Color color;
        Node leftchild;
        Node rightChild;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", color" + color +
                    "}";
        }
    }

    private enum Color{
        RED, BLACK
    }
}
