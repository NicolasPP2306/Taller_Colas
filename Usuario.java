package taller_colas;

public class Usuario {
    private String cedula;
    private String servicio;
    private String tipo_usuario;
    private int turno;

    public Usuario(String cedula, String servicio, String tipo_usuario, int turno) {
        this.cedula = cedula;
        this.servicio = servicio;
        this.tipo_usuario = tipo_usuario;
        this.turno = turno;
    }

    public String getCedula() {
        return cedula;
    }

    public String getServicio() {
        return servicio;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public int getTurno() {
        return turno;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "Usuario{" + "cedula=" + cedula + ", servicio=" + servicio + ", tipo_usuario=" + tipo_usuario + ", turno=" + turno + '}';
    }
}
