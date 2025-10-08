
package taller_colas;

public class StateBank {

    public static void main(String[] args) {
        Queue CashRegister = new Queue();
        Queue Advisor = new Queue();
        
        Queue Client = new Queue();
        Queue Preferencial = new Queue();
        Queue General = new Queue();
        Queue Transactions  = new Queue();
        
        int registers=3, advisors=2;
        String option;
        String menu[] = {"Assign turn", "Pass to tramit", "Attend", "Statistics", "Exit"};
        String statistics[] = {"Users per tramit", "Tramit made", "Total money", 
                "Credit Mortgage value average", "Transactions per user"};
        
        
    }
    
    public static String toString(Queue q) {
        String text = "";
        return text;
    }
    
}
