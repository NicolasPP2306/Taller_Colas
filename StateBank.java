
package taller_colas;

import javax.swing.JOptionPane;

public class StateBank {
    
    public static void main(String[] args) {
        Queue Cajero = new Queue();
        Queue Asesoria = new Queue();
        
        Queue Cliente = new Queue();
        Queue Preferencial = new Queue();
        Queue General = new Queue();
        Queue Transacciones  = new Queue();
        
        int cajeros=3, asesores=2, 
            turno=0, cod_tramite=1,
            valor_consignaciones=0, valor_retiros=0;
        
        String option, servicio, usuario, ced;
        String servicios[] = {"Cajero","Asesoria"};
        String tramites_cajero[] = {"Consignación","Retiro"};
        String tramites_asesoria[] = {"Abrir cuenta","Solicitar credito"};
        String usuarios[] = {"Cliente","Preferencial", "General"};
        String menu[] = {"Asignar turno", "Pasar a taquilla", "Atender en taquilla", "Estadísticas", "Exit"};
        String estadisticas[] = {"Usuarios por tramite", "Tramites por usuario", "Dinero total", 
                "Valor promedio cred. hipotecario", "Transacciones por usuario"};
        
        
        do {
            option = (String)JOptionPane.showInputDialog(null,"Seleccion una opción:",
                    "Menu",1,null,menu, menu[0]);
            switch(option) {
                case "Asignar turno" -> {
                    ced = JOptionPane.showInputDialog("Ingrese cedula:");
                    servicio = (String)JOptionPane.showInputDialog(null,"Seleccione el servicio a solicitar:",
                              "servicios",1,null,servicios, servicios[0]);
                    usuario = (String)JOptionPane.showInputDialog(null,"Seleccione tipo de usuario",
                              "Usuarios",1,null,usuarios, usuarios[0]);
                    Usuario u = new Usuario(ced, servicio, usuario, turno);
                    switch(usuario) {
                        case "Cliente":
                            Cliente.EnQueue(u);
                            break;
                        case "Preferencial":
                            Preferencial.EnQueue(u);
                            break;
                        case "General":
                            General.EnQueue(u);
                            break;
                    }
                    turno++;
                }
                case "Pasar a taquilla" -> {
                    servicio = (String)JOptionPane.showInputDialog(null,"Seleccione servicio:",
                              "Servicios",1,null,servicios, servicios[0]);
                    if (servicio.equals("Cajero")){
                        int c = 0;
                        int p = 0;
                        int g = 0;
                        while(!Cliente.isEmpty() || c <= 3) {
                            Cajero.EnQueue(Cliente.DeQueue());
                            c++;
                        }
                        while(!Preferencial.isEmpty() || p <= 2) {
                            Cajero.EnQueue(Preferencial.DeQueue());
                            p++;
                        }
                        while(!General.isEmpty() || g <= 1) {
                            Cajero.EnQueue(General.DeQueue());
                            g++;
                        }   
                    }
                    else {
                        while (!Cliente.isEmpty() || !Preferencial.isEmpty() || General.isEmpty()) {
                            Cajero.EnQueue(Cliente.DeQueue());
                            Cajero.EnQueue(Preferencial.DeQueue());
                            Cajero.EnQueue(General.DeQueue());
                        }
                    }
                }
                case "Atender en taquilla" -> {
                    servicio = (String)JOptionPane.showInputDialog(null,"Seleccione servicio a atender",
                               "Servicios",1,null,servicios, servicios[0]);
                    int valor = 0;
                    Usuario u;
                    String tramite;
                    if(servicio.equals("Cajero")) { 
                        u = (Usuario)Cajero.DeQueue();
                        tramite = (String)JOptionPane.showInputDialog(null,"Seleccione el tramite a realizar",
                                "Tramites",1,null,tramites_cajero,tramites_cajero[0]);
                        if(tramite.equals("Consignación")) {
                            valor = Integer.parseInt(JOptionPane.showInputDialog("Digite valor a consignar"));
                            valor_consignaciones += valor;
                            JOptionPane.showMessageDialog(null, "Dinero consignado");
                        }
                        else {
                            valor = Integer.parseInt(JOptionPane.showInputDialog("Digite valor a retirar"));
                            if(valor >= valor_consignaciones) {
                                JOptionPane.showMessageDialog(null, "Cajero con Fondos Insuficientes, vuelva despues");
                                break;
                            }
                            else {
                                valor_retiros+=valor;
                                JOptionPane.showMessageDialog(null, "Dinero retirado");
                            }
                        }
                    }
                    else {
                        u = (Usuario)Asesoria.DeQueue();
                        tramite = (String)JOptionPane.showInputDialog(null,"Seleccione el tramite a realizar",
                                "Tramites",1,null,tramites_asesoria,tramites_asesoria[0]);
                        if(tramite.equals("Abrir cuenta")) {
                            JOptionPane.showMessageDialog(null, "Cuenta creada");
                        }
                        else {
                            valor = Integer.parseInt(JOptionPane.showInputDialog("Digite valor de credito a solicitar"));
                            JOptionPane.showMessageDialog(null, "Credito Aprobado!");
                        }
                    }
                    Tramite t = new Tramite(cod_tramite, u, tramite, valor);
                    cod_tramite++;
                }
            }
        }while(!option.equals("Exit"));
    }
    
    public static String toString(Queue q) {
        Queue aux = new Queue();
        String text="";
        while(!q.isEmpty())
        {
            Usuario u = (Usuario)q.DeQueue();
            text=text+u.toString()+"\n";
            aux.EnQueue(u);
        }
        while(!aux.isEmpty())
            q.EnQueue(aux.DeQueue());
        return text;
    }
    
    public static int Size(Queue q) {
        int size = 0;
        Queue aux = new Queue();
        while (!q.isEmpty()) {
            aux.EnQueue(q.DeQueue());
            size ++;
        }
        while (!aux.isEmpty()) {
            q.EnQueue(aux.DeQueue());
        }
        return size;
    }
    
    public static int TotalMoney(int consignaciones, int retiros) {
        return consignaciones - retiros;
    }
}
