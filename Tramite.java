package taller_colas;

public class Tramite {
    private int codigo;
    private Usuario usuario;
    private String tipo_tramite;
    private int valor;

    public Tramite(int codigo, Usuario usuario, String tipo_tramite, int valor) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.tipo_tramite = tipo_tramite;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTipo_tramite() {
        return tipo_tramite;
    }

    public int getValor() {
        return valor;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setTipo_tramite(String tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Tramite{" + "codigo=" + codigo + ", usuario=" + usuario + ", tipo_tramite=" + tipo_tramite + ", valor=" + valor + '}';
    }
}
