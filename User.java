
package taller_colas;

public class User {
    private int id;
    private String process;
    private String type;

    public User(int id, String process, String type) {
        this.id = id;
        this.process = process;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getProcess() {
        return process;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", process=" + process + ", type=" + type + '}';
    }
}
