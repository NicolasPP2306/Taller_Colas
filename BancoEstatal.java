package taller_colas;

import javax.swing.JOptionPane;

public class BancoEstatal {

    public static void main(String[] args) {
        Queue cajero = new Queue();
        Queue asesoria = new Queue();
        
        Queue cliente = new Queue();
        Queue preferencial = new Queue();
        Queue general = new Queue();
        
        Queue transacciones = new Queue();
        
        String servicio, tipo_usuario;
        int cedula,turno_cliente = 1, turno_preferencial= 1, turno_general = 1, cod_tramite = 1;
        
        String option;
        int valor_consignaciones = 0, valor_retiros = 0;
        
        String menu_principal[] = {"Asignar turno","Pasar a taquilla","Atender taquilla","Estadisticas","Exit"};
        String servicios[] = {"Cajero","Asesoria"};
        String tipos_usuario[] = {"Cliente","Preferencial","General"};
        String tramites_cajero[] = {"Consignar dinero","Retirar dinero"};
        String tramites_asesoria[] = {"Abrir cuenta","Solicitar credito"};
        String estadisticas[] = {"Usuarios por atender","Tramites por usuario","Dinero total","Promedio Créditos","Transacciones por persona","--ATRÁS--"};
        
        
        do{
            option = (String)JOptionPane.showInputDialog(null,"Seleccione una opcion", 
                    "Menu principal",1,null, menu_principal,menu_principal[0]);
            switch(option) {
                case "Asignar turno":
                    cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cedula"));
                    servicio = (String)JOptionPane.showInputDialog(null,"Servicio a solicitar",
                            "Servicios",1,null,servicios,servicios[0]);
                    tipo_usuario = (String)JOptionPane.showInputDialog(null,"Tipo de usuario",
                            "Usuarios",1,null,tipos_usuario,tipos_usuario[0]);
                    Usuario usuario = new Usuario(cedula, servicio, tipo_usuario, 0);
                    switch (tipo_usuario) {
                        case "Cliente":
                            usuario.setTurno(turno_cliente);
                            cliente.EnQueue(usuario);
                            turno_cliente++;
                            JOptionPane.showMessageDialog(null, "Turno asignado! \nC-0"+usuario.getTurno());
                            break;
                        case "Preferencial":
                            usuario.setTurno(turno_preferencial);
                            preferencial.EnQueue(usuario);
                            turno_preferencial++;
                            JOptionPane.showMessageDialog(null, "Turno asignado! \nP-0"+usuario.getTurno());
                            break;
                        case "General":
                            usuario.setTurno(turno_general);
                            general.EnQueue(usuario);
                            turno_general++;
                            JOptionPane.showMessageDialog(null, "Turno asignado! \nG-0"+usuario.getTurno());
                            break;
                    }
                    break;
                    
                case "Pasar a taquilla":
                    if(cliente.IsEmpty() && preferencial.IsEmpty() && general.IsEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay turnos pendientes por pasar a taquilla");
                    }
                    else{
                        while(!cliente.IsEmpty() || !preferencial.IsEmpty() || !general.IsEmpty()) {
                            PasarTaquilla(cliente, cajero, "Cajero", 3);
                            PasarTaquilla(preferencial, cajero, "Cajero", 2);
                            PasarTaquilla(general, cajero, "Cajero", 1);

                            PasarTaquilla(cliente, asesoria, "Asesoria", 1);
                            PasarTaquilla(preferencial, asesoria, "Asesoria", 1);
                            PasarTaquilla(general, asesoria, "Asesoria", 1);
                        }
                        JOptionPane.showMessageDialog(null, "Turnos por taquilla ordenados");
                        break;
                    }
                    break;
                case "Atender taquilla":
                    Usuario u = null; 
                    String tramite = "";
                    int valor = 0;
                    servicio = (String)JOptionPane.showInputDialog(null,"Seleccion el servicio a atender",
                            "servicios",1,null,servicios,servicios[0]);
                    
                    if(servicio.equals("Cajero")) {
                        if(!cajero.IsEmpty()){
                            u = (Usuario)cajero.DeQueue();
                            tramite = (String)JOptionPane.showInputDialog(null,"Cedula = "+u.getCedula()+"\nSeleccione el tramite a realizar",
                                    "Tramites",1,null,tramites_cajero,tramites_cajero[0]);
                            if(tramite.equals("Consignar dinero")) {
                                valor = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor a consignar"));
                                valor_consignaciones += valor;
                                JOptionPane.showMessageDialog(null, "Valor consignado");
                            }
                            else {
                                valor = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor a retirar"));
                                if(valor > valor_consignaciones) {
                                    JOptionPane.showMessageDialog(null, "Cajero con fondos insuficientes, vuelva despues");
                                    valor = 0;
                                }
                                else {
                                    valor_retiros += valor;
                                    JOptionPane.showMessageDialog(null, "Retiro exitoso");
                                }
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Turnos pendientes por pasar a taquilla");
                            break;
                        }
                    }
                    else {
                        if(!asesoria.IsEmpty()) {
                            u = (Usuario)asesoria.DeQueue();
                            tramite = (String)JOptionPane.showInputDialog(null,"Cedula = "+u.getCedula()+"\nSeleccione el tramite a realizar",
                                    "Tramites",1,null,tramites_asesoria,tramites_asesoria[0]);
                            if(tramite.equals("Abrir cuenta")) {
                                JOptionPane.showMessageDialog(null, "Cuenta creada con exito");
                            }
                            else {
                                valor = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor del credito"));
                                JOptionPane.showMessageDialog(null, "Credito aprobado!");
                                
                            }   
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Turnos pendientes por pasar a taquilla");
                            break;
                        }
                    }
                    Tramite t = new Tramite(cod_tramite, u, tramite, valor);
                    transacciones.EnQueue(t);
                    cod_tramite++;
                    break;
                    
                case "Estadisticas":
                    String estadistica;
                    do {
                        estadistica = (String)JOptionPane.showInputDialog(null, "Seleccione una estadistica",
                            "Estadisticas",1,null,estadisticas,estadisticas[0]);
                        switch(estadistica) {
                            case "Usuarios por atender":
                                JOptionPane.showMessageDialog(null, "USUARIOS POR ATENDER" +
                                        "\nCajero = " + Size(cajero) + " En espera \nAsesoria = " + Size(asesoria) + " En espera");
                                break;
                            case "Tramites por usuario":
                                cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cedula"));
                                if (buscarUsuario(transacciones, cedula)) {
                                    float porc_cajero = PorcentajePorTramite(transacciones, cedula);
                                    JOptionPane.showMessageDialog(null, "TRAMITES POR USUARIO" +
                                            "\nCajero = " + porc_cajero + "% \nAsesoria = " + (100 - porc_cajero));
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                                }
                                break;

                            case "Dinero total":
                                JOptionPane.showMessageDialog(null, "DINERO TOTAL EN EL BANCO: \n" +
                                        "$"+(valor_consignaciones - valor_retiros));
                                break;
                                
                            case "Promedio Créditos":
                                float promedio = PromedioCreditos(transacciones);
                                JOptionPane.showMessageDialog(null, "PROMEDIO DE CREDITOS \n$"+promedio );
                                break;
                            
                            case "Transacciones por persona":
                                cedula = Integer.parseInt(JOptionPane.showInputDialog("Digite la cedula"));
                                if(buscarUsuario(transacciones, cedula)) {
                                    JOptionPane.showMessageDialog(null, "CANT. TRAMITES\n" + TransaccionesXPersona(transacciones, cedula));
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Usuario no encontrado");
                                }
                                break;


                        }   
                    }while (!estadistica.equals("--ATRÁS--"));
                    break;
                    
                case "Exit":
                    JOptionPane.showMessageDialog(null, "Hasta pronto!");
                    break;
            }
        } while(!option.equals("Exit"));
    }
    public static String toString(Queue q) {
        Queue aux = new Queue();
        String text="";
        while(!q.IsEmpty())
        {
            Usuario u = (Usuario)q.DeQueue();
            text=text+u.toString()+"\n";
            aux.EnQueue(u);
        }
        while(!aux.IsEmpty())
            q.EnQueue(aux.DeQueue());
        return text;
    }
    
    public static int Size(Queue q)
    {
        Queue aux = new Queue();
        int count=0;
        while(!q.IsEmpty()) {
            aux.EnQueue(q.DeQueue());
            count++;
        }
        while(!aux.IsEmpty()) {
            q.EnQueue(aux.DeQueue());
        }
        return count;
    }
    
    public static void PasarTaquilla(Queue origen, Queue destino, String servicio, int ciclo){
        Queue aux = new Queue();
        int cant = 0;
        while(!origen.IsEmpty()) {
            Usuario u = (Usuario)origen.DeQueue();
            if(u.getServicio().equals(servicio) && cant <= ciclo){
                destino.EnQueue(u);
                cant++;
            }
            else {
                aux.EnQueue(u);
            }
        }
        while(!aux.IsEmpty()){
            origen.EnQueue(aux.DeQueue());
        }
    }
    
    public static boolean buscarUsuario(Queue q, int cedula) {
        Queue aux = new Queue();
        boolean encontrado = false;
        while (!q.IsEmpty()) {
            Tramite t = (Tramite)q.DeQueue();
            if(t.getUsuario().getCedula()==cedula) {
                encontrado = true;
            }
            aux.EnQueue(t);
        }
        while(!aux.IsEmpty()) {
            q.EnQueue(aux.DeQueue());
        }
        return encontrado;
    }
    public static float PorcentajePorTramite(Queue transacciones, int cedula) {
        Queue aux = new Queue();
        int cant_cajero = 0;
        int total = 0;
        while(!transacciones.IsEmpty()) {
            Tramite t = (Tramite)transacciones.DeQueue();
            if(t.getUsuario().getCedula()==cedula) {
                total++;
                if(t.getUsuario().getServicio().equals("Cajero")) {
                    cant_cajero++;
                }
            }
            aux.EnQueue(t);
        }
        while(!aux.IsEmpty()) {
            transacciones.EnQueue(aux.DeQueue());
        }
        if(total == 0) {
            return 0;
        }
        return (cant_cajero * 100)/total;
    }
    
    public static float PromedioCreditos(Queue transacciones) {
        Queue aux = new Queue();
        int cant_creditos = 0;
        int monto_creditos = 0;
        while(!transacciones.IsEmpty()){
            Tramite t = (Tramite)transacciones.DeQueue();
            if(t.getTipo_tramite().equals("Solicitar credito")) {
                monto_creditos += t.getValor();
                cant_creditos++;
            }
            aux.EnQueue(t);
        }
        while(!aux.IsEmpty()){
            transacciones.EnQueue(aux.DeQueue());
        }
        if(cant_creditos == 0){
            return 0;
        }
        return monto_creditos/cant_creditos;
    }
    
    public static int TransaccionesXPersona(Queue transacciones, int cedula) {
        Queue aux = new Queue();
        int contador = 0;
        while(!transacciones.IsEmpty()){
            Tramite t = (Tramite)transacciones.DeQueue();
            if(t.getUsuario().getCedula()==cedula) {
                contador++;
            }
            aux.EnQueue(t);
        }
        while(!aux.IsEmpty()) {
            transacciones.EnQueue(aux.DeQueue());
        }
        
        return contador;
        
    }
}
