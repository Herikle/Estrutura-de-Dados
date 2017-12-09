
package skiplist;


public class Node implements Comparable<Node> {   
        private int key;
        private Object element;
        private Node left;
        private Node right;
        private Node above;
        private Node below;
        private final boolean posinfinity;
        private final boolean neginfinity;
        public Node(boolean posinfinity, boolean neginfinity)
        {
            this.posinfinity = posinfinity;
            this.neginfinity = neginfinity;
            this.right = null;
            this.left = null;
            this.above = null;
            this.below = null;
            this.element = null;
        }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getAbove() {
        return above;
    }

    public void setAbove(Node above) {
        this.above = above;
    }

    public Node getBelow() {
        return below;
    }

    public void setBelow(Node below) {
        this.below = below;
    }
    public boolean isPosInfinity()
    {
        return this.posinfinity;
    }
     public boolean isNegInfinity()
    {
        return this.neginfinity;
    }
        
        @Override
        public int compareTo(Node t) {
            
            if(this.posinfinity)
            {
                return 1;
            }
            else if(this.neginfinity)
            {
                return 0;
            }           
            return t.key > this.key ? 0 : 1;
        }     
}
