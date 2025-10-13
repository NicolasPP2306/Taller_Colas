
package taller_colas;

public class Usuario {
    private String ced;
    private String servicio;
    private String tipo;
    private int turno;

    public Usuario(String ced, String servicio, String tipo, int turno) {
        this.ced = ced;
        this.servicio = servicio;
        this.tipo = tipo;
        this.turno = turno;
    }

    public String getCed() {
        return ced;
    }

    public String getServicio() {
        return servicio;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTurno() {
        return turno;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Usuario{" + "ced=" + ced + ", servicio=" + servicio + ", tipo=" + tipo + ", turno=" + turno + '}';
    }
}
