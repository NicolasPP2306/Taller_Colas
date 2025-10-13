
package taller_colas;

import java.util.Deque;
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
            turno=1, cod_tramite=1,
            valor_consignaciones=0, valor_retiros=0;
        
        String option, servicio, usuario, ced;
        String servicios[] = {"Cajero","Asesoria"};
        String tramites_cajero[] = {"Consignación","Retiro"};
        String tramites_asesoria[] = {"Abrir cuenta","Solicitar credito"};
        String usuarios[] = {"Cliente","Preferencial", "General"};
        String menu[] = {"Asignar turno", "Pasar a taquilla", "Atender en taquilla", "Estadísticas", "Exit"};
        String estadisticas[] = {"Usuarios por tramite", "Porcentaje por tramite", "Dinero total", 
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
                    JOptionPane.showMessageDialog(null, "Turno asignado! \nTurno #" + turno);
                    turno++;
                }
                case "Pasar a taquilla" -> {
                    servicio = (String)JOptionPane.showInputDialog(null,"Seleccione servicio:",
                              "Servicios",1,null,servicios, servicios[0]);
                    if (servicio.equals("Cajero")){
                        while (!Cliente.isEmpty() || !Preferencial.isEmpty() || !General.isEmpty()) {
                            int c = 0;
                            int p = 0;
                            int g = 0;
                            Usuario u;
                            while(!Cliente.isEmpty() && c <= 3) {
                                u = (Usuario) Cliente.DeQueue();
                                if (u.getServicio().equals("Cajero")) {
                                    Cajero.EnQueue(u);
                                    c++;
                                }
                                else {
                                    Cliente.EnQueue(u);
                                }

                            }
                            while(!Preferencial.isEmpty() && p <= 2) {
                                u = (Usuario)Preferencial.DeQueue();
                                if (u.getServicio().equals("Cajero")) {
                                    Cajero.EnQueue(u);
                                    p++;
                                }
                                else {
                                    Preferencial.EnQueue(u);
                                }
                            }
                            while(!General.isEmpty() && g <= 1) {
                                u = (Usuario)General.DeQueue();
                                if (u.getServicio().equals("Cajero")) {
                                    Cajero.EnQueue(u);
                                    g++;
                                }
                                else {
                                    General.EnQueue(u);
                                }
                            } 
                        }
                    }
                    else {
                        while(!Cliente.isEmpty() || !Preferencial.isEmpty() || !General.isEmpty()) {
                            
                            Usuario u_c = (Usuario)Cliente.DeQueue();
                            if (u_c.getServicio().equals("Asesoria")) {
                                Asesoria.EnQueue(u_c);
                            }
                            else {
                                Cliente.EnQueue(u_c);
                            }
                            
                            Usuario u_p = (Usuario)Preferencial.DeQueue();
                            if (u_p.getServicio().equals("Asesoria")) {
                                Asesoria.EnQueue(u_p);
                            }
                            else {
                                Preferencial.EnQueue(u_p);
                            }
                            
                            Usuario u_g = (Usuario)General.DeQueue();
                            if (u_g.getServicio().equals("Asesoria")) {
                                Asesoria.EnQueue(u_g);
                            }
                            else {
                                Cliente.EnQueue(u_g);
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Turnos organizados");
                }
                case "Atender en taquilla" -> {
                    servicio = (String)JOptionPane.showInputDialog(null,"Seleccione servicio a atender",
                           "Servicios",1,null,servicios, servicios[0]);
                    int valor = 0;
                    Usuario u;
                    String tramite;
                    if(servicio.equals("Cajero")) { 
                        if (!Cajero.isEmpty()) {
                            u = (Usuario)Cajero.DeQueue();
                            tramite = (String)JOptionPane.showInputDialog(null,"Cedula = " + u.getCed() + "\nSeleccione el tramite a realizar",
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
                                    valor = 0;
                                }
                                else {
                                    valor_retiros+=valor;
                                    JOptionPane.showMessageDialog(null, "Dinero retirado");
                                }
                            }
                        } 
                        else {
                            JOptionPane.showMessageDialog(null, "No hay turnos de Cajero por atender");
                            break;
                        }   
                    }
                    else {
                        if (!Asesoria.isEmpty()) {
                            u = (Usuario)Asesoria.DeQueue();
                            tramite = (String)JOptionPane.showInputDialog(null,"Cedula = " + u.getCed() + "\nSeleccione el tramite a realizar",
                                "Tramites",1,null,tramites_asesoria,tramites_asesoria[0]);
                            if(tramite.equals("Abrir cuenta")) {
                                JOptionPane.showMessageDialog(null, "Cuenta creada");
                            }
                            else {
                                valor = Integer.parseInt(JOptionPane.showInputDialog("Digite valor de credito a solicitar"));
                                JOptionPane.showMessageDialog(null, "Credito Aprobado!");
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "No hay turnos de Asesoria por atender");
                            break;
                        }
                    } 
                    Tramite t = new Tramite(cod_tramite, u, tramite, valor);
                    Transacciones.EnQueue(t);
                    cod_tramite++;
                }
                case "Estadísticas" -> { 
                    String estadistica = (String)JOptionPane.showInputDialog(null,"Seleccione una opción:", 
                            "estadisticas",1,null,estadisticas, estadisticas[0]); 
                    switch (estadistica){ 
                        case "Usuarios por tramite"-> { 
                            servicio = (String)JOptionPane.showInputDialog(null,"Seleccione servicio:",
                                "Servicios",1,null,servicios, servicios[0]); 
                            if(servicio.equals("Cajero")){ 
                                JOptionPane.showMessageDialog(null, "Cantidad usuarios en cajero: " + Size(Cajero) ); 
                            }
                            else{ JOptionPane.showMessageDialog(null, "Cantidad usuarios en asesoría: " + Size(Asesoria) ); 
                            } 
                        } 
                        case "Porcentaje por tramite" -> {
                            String cedula = JOptionPane.showInputDialog("Ingrese la cédula del usuario:");
                            if (buscarUsuario(Transacciones, cedula)) {
                                int total = 0;
                                int trans_cajero = 0;
                                int trans_asesoria = 0;
                                Queue aux = new Queue();
                                while (!Transacciones.isEmpty()) {
                                    Tramite t = (Tramite)Transacciones.DeQueue();
                                    if (t.getUsuario().getCed().equals(cedula)) {
                                        if (t.getTramite().equals("Consignación") || t.getTramite().equals("Retiro")) {
                                            trans_cajero++;
                                        }
                                        else {
                                            trans_asesoria++;
                                        }
                                    }
                                    aux.EnQueue(t);
                                }
                                while (!aux.isEmpty()) {
                                    Transacciones.EnQueue(aux.DeQueue());
                                }

                                total = trans_cajero + trans_asesoria;
                                double porc_cajero = (trans_cajero * 100)/total;
                                JOptionPane.showMessageDialog(null, "Transacciones por usuario: \n" +
                                        "Cajero = " + porc_cajero + "\nAsesorias = " + (100 - porc_cajero)); 
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                            }
                        }
                        case "Dinero total" -> {
                            double totalBanco = valor_consignaciones - valor_retiros; 
                            JOptionPane.showMessageDialog(null, "Total de dinero en el banco: " + totalBanco); 
                        } 
                        case "Valor promedio cred. hipotecario" -> {
                            Queue aux = new Queue();
                            int cant_creditos = 0;
                            int total_creditos = 0;
                            while (!Transacciones.isEmpty()) {
                                Tramite t = (Tramite)Transacciones.DeQueue();
                                if (t.getTramite().equals("Solicitar credito")) {
                                    total_creditos += t.getValor();
                                    cant_creditos++;
                                }
                                aux.EnQueue(t);
                            }
                            while (!aux.isEmpty()) {
                                Transacciones.EnQueue(aux.DeQueue());
                            }
                            JOptionPane.showMessageDialog(null, "Valor promedio de creditos = " + 
                                    (total_creditos/cant_creditos));
                        }
                        case "Transacciones por usuario" -> {
                            String cedula = JOptionPane.showInputDialog("Ingrese la cédula del usuario:");
                            if (buscarUsuario(Transacciones, cedula)){
                                int total = 0;
                                Queue aux = new Queue();
                                while (!Transacciones.isEmpty()) {
                                    Tramite t = (Tramite)Transacciones.DeQueue();
                                    if (t.getUsuario().getCed().equals(cedula)) {
                                        total++;
                                    }
                                    aux.EnQueue(t);
                                }
                                while (!aux.isEmpty()) {
                                    Transacciones.EnQueue(aux.DeQueue());
                                }
                                JOptionPane.showMessageDialog(null, "Transacciones por usuario: \n" + total); 
                            }
                        }
                    } 
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
    
    public static boolean buscarUsuario(Queue q, String ced) {
        Queue aux = new Queue();
        while (!q.isEmpty()) {
            Tramite t = (Tramite)q.DeQueue();
            if(t.getUsuario().getCed().equals(ced)) {
                return true;
            }
            aux.EnQueue(t);
        }
        while(!aux.isEmpty()) {
            q.EnQueue(aux.DeQueue());
        }
        return false;
    }
}
