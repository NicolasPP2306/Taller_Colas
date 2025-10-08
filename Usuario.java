
package taller_colas;

public class Usuario {
    private String ced;
    private String tramite;
    private String tipo;
    private int turno;

    public Usuario(String ced, String tramite, String tipo, int turno) {
        this.ced = ced;
        this.tramite = tramite;
        this.tipo = tipo;
        this.turno = turno;
    }

    public String getCed() {
        return ced;
    }

    public String getTramite() {
        return tramite;
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

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Usuario{" + "ced=" + ced + ", tramite=" + tramite + ", tipo=" + tipo + ", turno=" + turno + '}';
    }
}
