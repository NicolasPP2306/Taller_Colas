
package taller_colas;

public class Tramite {
    private int codigo;
    private Usuario usuario;
    private String tramite;
    private int valor;

    public Tramite(int codigo, Usuario usuario, String tramite, int valor) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.tramite = tramite;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTramite() {
        return tramite;
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

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Tramite{" + "codigo=" + codigo + ", usuario=" + usuario + ", tramite=" + tramite + ", valor=" + valor + '}';
    }
}
