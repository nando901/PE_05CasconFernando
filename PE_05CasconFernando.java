import java.util.InputMismatchException;
import java.util.Scanner;
public class PE_05CasconFernando {
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        PE_05CasconFernando p = new PE_05CasconFernando();
        p.principal();
    }

    public void principal() {
        int mainMenu = 0;
        String ticket = "______________________________________\n============ NOVA COMANDA ============\n______________________________________\n";
        String client = "", producte = "", afegir = "";
        double preuUnitari = 0, subtotal = 0, total = 0;
        int quantitat = 0;

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

                    ticket += ("Client: " + client + "\nProducte\tQuantitat\tPreu unit.\tSubtotal\n---------------------------------------------------------\n");
                    
                    do {
                        producte = producte();
                        preuUnitari = preuUnitari();
                        quantitat = quantitat();
                        afegir = afegir(); 

                        subtotal = preuUnitari * quantitat;
                        total += subtotal;

                        ticket += String.format("%-15s %-15d %-15s %s\n", producte, quantitat, String.format("%.2f€", preuUnitari), String.format("%.2f€", subtotal));

                    } while (afegir.equalsIgnoreCase("s"));

                    ticket = calculIva(total, ticket);

                    System.out.println(ticket);
                    break;
            }
        } while (mainMenu != 4);

        sc.close();
    }

    public String producte() {
        String producte = "";
        System.out.print("Introdueix el producte: ");
        producte = sc.nextLine();

        return producte;
    }

    public double preuUnitari() {
        double preuUnitari = 0;
        boolean validation = true;

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
        return preuUnitari;
    }

    public int quantitat() {
        int quantitat = 0;
        boolean validation = true;

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
        return quantitat;
    }

    public String afegir() {
        String afegir = "";
        boolean validation = true;

        do {
            System.out.print("Vols afegir un altre producte? (s/n): ");
            afegir = sc.nextLine(); 

            if (afegir.equalsIgnoreCase("s") || afegir.equalsIgnoreCase("n")) {
                validation = false;
                
            } else {
                System.out.println("Error, insereix una opció vàlida.");
            }

        } while (validation == true);

        return afegir;
    }

    public String calculIva(double total, String ticket) {
        double iva = 0.1;
        double ivaTotal = 0;
        double preuFinal = 0;

        ivaTotal = total * iva;
        preuFinal = total + ivaTotal;

        ticket += "---------------------------------------------------------\n";
        ticket += String.format("%-30s %15.2f€\n", "Total sense IVA:", total);
        ticket += String.format("%-30s %15.2f€\n", "IVA (10%):", ivaTotal);
        ticket += String.format("%-30s %15.2f€\n", "TOTAL A PAGAR:", preuFinal);
        ticket += "=========================================================\n";

        return ticket;
    }
}