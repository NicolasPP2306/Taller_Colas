
package taller_colas;

public class Queue {
    private Node first;
    private Node last;

    public Queue() {
    }

    public boolean isEmpty() {
        return first == null && last == null;
    }
    
    public void EnQueue(Object data) {
        if (isEmpty()) {
            first = new Node(data);
            last = first;
        } 
        else  {
           Node n = new Node(data); 
           last.setLink(n);
           last = n;
        }
    }
    
    public Object DeQueue() {
        Object data = null;
        if (!isEmpty()) {
            data = first.getData();
            first = first.getLink();
            if(first == null) {
                last = first;
            }
            return data;
        }
        return data;
    }
}
