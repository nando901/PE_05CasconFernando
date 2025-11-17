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
        String ticket = "";
        String ticketTotal = "";
        String client = "", producte = "", afegir = "";
        double preuUnitari = 0, subtotal = 0, total = 0;
        int quantitat = 0;

        //bucle inicial
        do {            
            mainMenu = menu(); //menu inicial

            switch (mainMenu) {
                //Nova comanda
                case 1:
                    //reinici de dades per la creació d'un nou ticket
                    ticket = "";
                    ticketTotal = "";
                    total = 0;
                    subtotal = 0;

                    principi();
                    client = demanarClient();
                    ticket += generarCapcalera(client); //capcelera del ticket

                    //bucle per anar afegint productes
                    do {
                        producte = demanarProducte();
                        preuUnitari = preuUnitari();
                        quantitat = quantitat();
                        afegir = afegirProducte(); 

                        subtotal = preuUnitari * quantitat;
                        total += subtotal;

                        //actualitzacio del ticket
                        ticket = actualizarTicket(producte, quantitat, preuUnitari, subtotal, ticket);

                    } while (afegir.equalsIgnoreCase("s"));

                    ticketTotal = calculIva(total);

                    System.out.println("\nS'està generant el tiquet...");
                    System.out.println("______________________________________\n=============== TIQUET ===============\n______________________________________\n");
                    System.out.println(ticket + ticketTotal);
                    System.out.println("Comanda enregistrada correctament.");
                    break;

                //actualitzar comanda
                case 2:
                    //comprovar que hi hagi un ticket existent
                    if (ticket.equals("")) {
                        System.out.println("Error, no hi ha cap comanda creada.");

                    } else {
                        do {
                            producte = demanarProducte();
                            preuUnitari = preuUnitari();
                            quantitat = quantitat();
                            afegir = afegirProducte(); 

                            subtotal = preuUnitari * quantitat;
                            total += subtotal;

                            ticket = actualizarTicket(producte, quantitat, preuUnitari, subtotal, ticket);

                        } while (afegir.equalsIgnoreCase("s"));

                        ticketTotal = calculIva(total);
                        System.out.println("______________________________________\n========== TIQUET ACTUALITZAT=========\n______________________________________\n\n");
                        System.out.println(ticket + ticketTotal);
                        System.out.println("Comanda actualitzada correctament.");
                    }
                    break;

                //imprimir ultim ticket
                case 3:
                    if (ticket.equals("")) {
                        System.out.println("Error, crea un ticket primer.");

                    } else {
                        System.out.println("______________________________________\n============ ÚLTIM TIQUET ============\n______________________________________\n");
                        System.out.println(ticket + ticketTotal);
                    }
                    break;

                //finalitzacio de codi
                case 4:
                    System.out.println("______________________________________\n========== FINS LA PROPERA! ==========\n______________________________________\n");
                    break;
            }
        } while (mainMenu != 4);

        sc.close();
    }
    
    public int menu() {
        int choice = 0;

        do {
            System.out.println("______________________________________");
            System.out.println("===== GESTIÓ COMANDES RESTAURANT ====="); 
            System.out.println("______________________________________");
            System.out.println("1. Crear nova comanda.");
            System.out.println("2. Actualitzar comanda anterior.");
            System.out.println("3. Visualitzar últim ticket.");
            System.out.println("4. Sortir");

            try {
                choice = sc.nextInt();
                sc.nextLine();

                if (choice < 1 || choice > 4) {
                    System.out.println("Error, insereix una opció vàlida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Format invàlid.");
                sc.nextLine();
            }
        } while (choice < 1 || choice > 4);
        
        return choice;
    }

    //encapcelament
    public void principi() {
        System.out.println("______________________________________");
        System.out.println("============ NOVA COMANDA ============");
        System.out.println("______________________________________");
    }

    //metode per al format del ticket
    public String formatearColumna(Object valor, int ancho) {
        String texto = "" + valor; 
        String resultado = texto;

        if (texto.length() < ancho) {
            resultado = texto + " ".repeat(ancho - texto.length());
        } else if (texto.length() > ancho) {
            resultado = texto.substring(0, ancho);
        }

        return resultado;
    }

    public String generarCapcalera(String client) {
        String ticket = "Client: " + client + "\n\n";
        ticket += formatearColumna("Producte", 20);
        ticket += formatearColumna("Quantitat", 13);
        ticket += formatearColumna("Preu unit.", 13);
        ticket += formatearColumna("Subtotal", 13) + "\n";
        ticket += "---------------------------------------------------------\n";

    return ticket;
}

    public String demanarClient() {
        String client = "";
        do {
            System.out.print("Introdueix el nom del client: ");
            client = sc.nextLine();

            if (client.equals("")) {
                System.out.println("Error, el nom no pot quedar buit.");
            }
        } while (client.equals(""));

        return client;
    }

    public String demanarProducte() {
        String producte = "";

        do {
            System.out.print("Introdueix el producte: ");
            producte = sc.nextLine();

            if (producte.equals("")) {
                System.out.println("Error, el nom no pot quedar buit.");
            }

        } while (producte.equals(""));

        return producte;
    }

    public double preuUnitari() {
        double preuUnitari = 0;
        boolean validation = true;

        do {
            try {
                System.out.print("Preu unitari: ");
                preuUnitari = sc.nextDouble();

                if (preuUnitari <= 0) {
                    System.out.println("Error, el preu no pot ser 0 o negatiu.");

                } else {
                    validation = false;
                }

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

                if (quantitat <= 0) {
                    System.out.println("Error, la quantitat no pot ser 0 o menor.");

                } else {
                    validation = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Format invàlid.");
                sc.nextLine();
            }
        } while (validation);
        return quantitat;
    }

    public String afegirProducte() {
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

    //metode per calcular el preu final amb l'iva
    public String calculIva(double total) {
        String ticketTotal = "";
        double iva = 0.1;
        double ivaTotal = 0;
        double preuFinal = 0;

        ivaTotal = total * iva;
        preuFinal = total + ivaTotal;
      
        ticketTotal += "---------------------------------------------------------\n";
        ticketTotal += String.format("%-35s %15.2f€\n", "Total sense IVA:", total);
        ticketTotal += String.format("%-35s %15.2f€\n", "IVA (10%):", ivaTotal);
        ticketTotal += String.format("%-35s %15.2f€\n", "TOTAL A PAGAR:", preuFinal);
        ticketTotal += "=========================================================\n";

        return ticketTotal;
    }

    public String actualizarTicket(String producte, int quantitat, double preuUnitari, double subtotal, String ticket) {

        ticket += formatearColumna(producte, 20) + formatearColumna(quantitat, 13) + formatearColumna(String.format("%.2f€", preuUnitari), 13) + formatearColumna(String.format("%.2f€", subtotal), 13) + "\n";
        return ticket;
    }
}