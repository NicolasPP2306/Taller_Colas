
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
        
        int cajeros=3, asesores=2, id, turno=0;
        String option, tramite, usuario;
        String tramites[] = {"Cajero","Asesoria"};
        String usuarios[] = {"Cliente","Preferencial", "General"};
        String menu[] = {"Asignar turno", "Pasar a taquilla", "Attend", "Statistics", "Exit"};
        String estadisticas[] = {"Users per tramit", "Tramit made", "Total money", 
                "Credit Mortgage value average", "Transactions per user"};
        
        
        do {
            option = (String)JOptionPane.showInputDialog(null,"Selected:",
                    "Menu",1,null,menu, menu[0]);
            switch(option) {
                case "Asignar turno" -> {
                    id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese cedula:"));
                    tramite = (String)JOptionPane.showInputDialog(null,"Selected:",
                              "Tramite",1,null,tramites, tramites[0]);
                    usuario = (String)JOptionPane.showInputDialog(null,"Selected:",
                              "Tramite",1,null,usuarios, usuarios[0]);
                    User user = new User(id, tramite, usuario, turno);
                    switch(usuario) {
                        case "Cliente":
                            Cliente.EnQueue(user);
                            break;
                        case "Preferencial":
                            Preferencial.EnQueue(user);
                            break;
                        case "General":
                            General.EnQueue(user);
                            break;
                    }
                    turno++;
                }
                case "Pasar a taquilla" -> {
                    tramite = (String)JOptionPane.showInputDialog(null,"Selected:",
                              "Tramite",1,null,tramites, tramites[0]);
                    if (tramite.equals("Cajero")){
                        int c = 0;
                        int p = 0;
                        int g = 0;
                        while(c <= 3) {
                            Cajero.EnQueue(Cliente.DeQueue());
                            c++;
                        }
                        while(p <= 2) {
                            Cajero.EnQueue(Preferencial.DeQueue());
                            p++;
                        }
                        while(g <= 3) {
                            Cajero.EnQueue(General.DeQueue());
                            g++;
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
            User u = (User)q.DeQueue();
            text=text+u.toString()+"\n";
            aux.EnQueue(u);
        }
        while(!aux.isEmpty())
            q.EnQueue(aux.DeQueue());
        return text;
    }
    
}
