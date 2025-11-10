import java.util.InputMismatchException;
import java.util.Scanner;
public class PE_05CasconFernando {
    Scanner sc = new Scanner(System.in);
    int total = 0;
    public static void main(String[] args) {
        PE_05CasconFernando p = new PE_05CasconFernando();
        p.principal();
    }

    public void principal() {
        int mainMenu = 0;
        String ticket = "______________________________________\n============ NOVA COMANDA ============\n______________________________________\n";
        String client = "";

        do {
            System.out.println("______________________________________");
            System.out.println("===== GESTIÓ COMANDES RESTAURANT ====="); 
            System.out.println("______________________________________");
            System.out.println("1. Crear nova comanda.");
            System.out.println("2. Actualitzar comanda anterior.");
            System.out.println("3. Visualitzar últim ticket.");
            System.out.println("4. Sortir");
            mainMenu = sc.nextInt();
            sc.nextLine();

            switch (mainMenu) {
                case 1:
                    System.out.println("______________________________________");
                    System.out.println("============ NOVA COMANDA ============");
                    System.out.println("______________________________________");
                    System.out.print("Introdueix el nom del client: ");
                    client = sc.nextLine();

                    ticket = (ticket + "Client: " + client + "\nProducte\tQuantitat\tPreu unit.\tSubtotal\n");
                    ticket = (ticket + "---------------------------------------------------------\n");
                    
                    ticket = calcularPrecio(ticket);
                    System.out.println(ticket);
                    break;
            }

        } while (mainMenu != 4);
      

        sc.close();
    }

    public String LlegirDades(String ticket) {
        String producte = "", altreProducte = "";
        int quantitat = 0;
        double preuUnitari = 0, subtotal = 0;
        boolean validation = true;

        do {
            System.out.print("Introdueix el producte: ");
            producte = sc.nextLine();

            do {
                try {
                    System.out.print("Preu unitari: ");
                    preuUnitari = sc.nextDouble();
                    validation = false;

                } catch (InputMismatchException e) {
                    System.out.println("Format invàlid.");
                    sc.nextLine();
                }
            } while (validation);
                
            validation = true;

            do {
                try {
                    System.out.print("Quantitat: ");
                    quantitat = sc.nextInt();
                    sc.nextLine();
                    validation = false;

                } catch (InputMismatchException e) {
                    System.out.println("Format invàlid.");
                    sc.nextLine();
                }
            } while (validation);                

            System.out.print("Vols afegir un altre producte? (s/n): ");
            altreProducte = sc.nextLine();         

            subtotal = preuUnitari * quantitat;
            ticket += String.format("%-15s %-15d %-15s %s\n", producte, quantitat, String.format("%.2f€", preuUnitari), String.format("%.2f€", subtotal));

            total += subtotal;

        } while (altreProducte.equalsIgnoreCase("s"));
        
        return ticket;
    }

    public String calcularPrecio(String ticket){
        ticket = LlegirDades(ticket);
        double iva = 0.1;
        double ivaTotal = 0; 
        double preuFinal = 0;

        ivaTotal = total*iva;
        preuFinal = total + ivaTotal;

        return ticket;
    }
}