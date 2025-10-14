package taller_colas;

public class Queue {
    private Node first;
    private Node last;

    public Queue() {
    }
    
    public boolean IsEmpty() {
        return first == null && last==null;
    }
    
    public void EnQueue(Object data) {
        if(IsEmpty()) {
            first = new Node(data);
            last = first;
        }
        else {
            Node n = new Node(data);
            last.setLink(n);
            last = n;
        }
    }
    
    public Object DeQueue() {
        Object data = null;
        if(!IsEmpty()) {
            data = first.getData();
            first = first.getLink();
            if(first == null) {
                last = first;
            }
        }
        return data;
    }

    public Node getFirst() {
        return first;
    }
    
}
