
package taller_colas;

public class User {
    private int id;
    private String process;
    private String type;
    private int turno;

    public User(int id, String process, String type, int turno) {
        this.id = id;
        this.process = process;
        this.type = type;
        this.turno = turno;
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

    public int getTurno() {
        return turno;
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
    
    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", process=" + process + ", type=" + type + '}';
    }
}
