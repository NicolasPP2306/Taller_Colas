
package taller_colas;

public class Node {
    private Object data;
    private Node link; 

    public Node(Object data) {
        this.data = data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public Object getData() {
        return data;
    }

    public Node getLink() {
        return link;
    }
    
}
