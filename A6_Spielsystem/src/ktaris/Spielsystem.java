package ktaris;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Spielsystem {
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);
    private Krieger spielerKrieger;
    private ArrayList<Krieger> alleKrieger;
    private int leben;
    private int runde;
    
    private ArrayList<Gegenstand> verfuegbareWaffen;
    private ArrayList<Tiere> verfuegbareTiere;

    public Spielsystem(Volk v1, Volk v2) {
        this.alleKrieger = new ArrayList<>();
        this.leben = 100;
        this.runde = 1;
        
        // Füge alle Krieger aus beiden Völkern zur Liste hinzu
        alleKrieger.addAll(v1.getKrieger());
        alleKrieger.addAll(v2.getKrieger());
        
        // Sammle alle verfügbaren Waffen und Tiere
        verfuegbareWaffen = new ArrayList<>();
        verfuegbareTiere = new ArrayList<>();
        
        for (Krieger k : alleKrieger) {
            for (Gegenstand g : k.getGegenstaende()) {
                if (g instanceof Tiere) {
                    verfuegbareTiere.add((Tiere)g);
                } else if (!(g instanceof Spielzeug)) {
                    verfuegbareWaffen.add(g);
                }
            }
        }
    }

    public void spielStart() {
        System.out.println("=== Willkommen im Spiel ===");
        erstelleSpieler();
        
        System.out.println("\n=== Spiel startet ===");
        System.out.println("Spieler: " + spielerKrieger.getName());
        System.out.println("Leben: " + leben);
        System.out.println("Macht: " + spielerKrieger.getMacht());

        while (leben > 0) {
            System.out.println("\n=== Runde " + runde + " ===");
            ereignisAusfuehren();
            
            if (leben > 0) {
                System.out.println("\nMöchten Sie weiterspielen? (j/n)");
                String antwort = scanner.next().toLowerCase();
                if (!antwort.equals("j")) {
                    System.out.println("Spiel beendet!");
                    break;
                }
                runde++;
            }
        }
    }

    private void erstelleSpieler() {
        System.out.println("Bitte geben Sie Ihren Namen ein:");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Name darf nicht leer sein. Bitte erneut eingeben:");
            name = scanner.nextLine().trim();
        }

        System.out.println("Bitte geben Sie Ihr Alter ein:");
        int alter = 0;
        while (alter <= 0) {
            try {
                alter = Integer.parseInt(scanner.nextLine());
                if (alter <= 0) {
                    System.out.println("Alter muss positiv sein. Bitte erneut eingeben:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben:");
            }
        }

        spielerKrieger = new Krieger(name, alter);

        // Basis-Ausrüstung für den Spieler
        System.out.println("\nWählen Sie Ihre Startwaffe:");
        for (int i = 0; i < verfuegbareWaffen.size(); i++) {
            System.out.println("[" + (i+1) + "] " + verfuegbareWaffen.get(i).getTyp() + 
                             " (Macht: " + verfuegbareWaffen.get(i).getFaktor() + ")");
        }

        int waffenWahl = 0;
        while (waffenWahl < 1 || waffenWahl > verfuegbareWaffen.size()) {
            try {
                waffenWahl = Integer.parseInt(scanner.nextLine());
                if (waffenWahl < 1 || waffenWahl > verfuegbareWaffen.size()) {
                    System.out.println("Bitte eine Zahl zwischen 1 und " + verfuegbareWaffen.size() + " wählen:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben:");
            }
        }

        Gegenstand startWaffe = verfuegbareWaffen.get(waffenWahl - 1);
        spielerKrieger.addGegenstand(startWaffe);
        System.out.println("\nSie haben " + startWaffe.getTyp() + " als Startwaffe gewählt!");
    }

    private void kaempfen(Krieger gegner) {
        System.out.println("\n=== Kampf beginnt ===");
        System.out.println("Gegner: " + gegner.getName() + " (Macht: " + gegner.getMacht() + ")");
        System.out.println("Du: " + spielerKrieger.getName() + " (Macht: " + spielerKrieger.getMacht() + ")");

        System.out.println("\nWähle deine Aktion:");
        System.out.println("[1] Angreifen");
        System.out.println("[2] Verteidigen");
        System.out.println("[3] Fliehen");

        int wahl = 0;
        while (wahl < 1 || wahl > 3) {
            try {
                wahl = Integer.parseInt(scanner.nextLine());
                if (wahl < 1 || wahl > 3) {
                    System.out.println("Bitte eine Zahl zwischen 1 und 3 wählen:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben:");
            }
        }

        switch (wahl) {
            case 1: // Angreifen
                int spielerSchaden = random.nextInt(spielerKrieger.getMacht() + 1);
                int gegnerSchaden = random.nextInt(gegner.getMacht() + 1);
                
                System.out.println("\nDu greifst an!");
                System.out.println("Du verursachst " + spielerSchaden + " Schaden!");
                System.out.println("Gegner verursacht " + gegnerSchaden + " Schaden!");
                
                leben -= gegnerSchaden;
                System.out.println("Deine verbleibenden Lebenspunkte: " + leben);
                
                if (spielerSchaden > gegnerSchaden) {
                    System.out.println("Du gewinnst den Kampf!");
                    if (!gegner.getGegenstaende().isEmpty() && random.nextInt(100) < 30) {
                        Gegenstand beute = gegner.getGegenstaende().get(random.nextInt(gegner.getGegenstaende().size()));
                        gegner.removeGegenstand(beute);
                        spielerKrieger.addGegenstand(beute);
                        System.out.println("Du erbeutest: " + beute.getTyp());
                    }
                } else {
                    System.out.println("Du verlierst den Kampf!");
                }
                break;

            case 2: // Verteidigen
                int verteidigungsBonus = 5;
                gegnerSchaden = Math.max(0, random.nextInt(gegner.getMacht() + 1) - verteidigungsBonus);
                System.out.println("\nDu verteidigst dich!");
                System.out.println("Gegner verursacht " + gegnerSchaden + " Schaden!");
                leben -= gegnerSchaden;
                System.out.println("Deine verbleibenden Lebenspunkte: " + leben);
                break;

            case 3: // Fliehen mit 50% chance
                System.out.println("\nDu versuchst zu fliehen...");
                if (random.nextInt(100) < 50) {
                    System.out.println("Flucht erfolgreich!");
                } else {
                    int fluchtSchaden = random.nextInt(gegner.getMacht() / 2 + 1);
                    System.out.println("Flucht gescheitert!");
                    System.out.println("Du erleidest " + fluchtSchaden + " Schaden!");
                    leben -= fluchtSchaden;
                    System.out.println("Deine verbleibenden Lebenspunkte: " + leben);
                }
                break;
        }
    }

    public void ereignisAusfuehren() {
        int zahl = random.nextInt(100) + 1;
        System.out.println("\n--- Würfelwurf: " + zahl + " ---");

        if (zahl == 1) {
            System.out.println("Game over - Du hast verloren!");
            leben = 0;
        }
        // aufrufen der treffen funktion von krieger
        else if (zahl >= 2 && zahl <= 10) {
            if (!alleKrieger.isEmpty()) {
                Krieger bewohner = alleKrieger.get(random.nextInt(alleKrieger.size()));
                System.out.println("Du triffst einen freundlichen Bewohner: " + bewohner.getName() + "!");
                spielerKrieger.treffen(bewohner);
            }
        }
        else if (zahl >= 11 && zahl <= 21) {
            int heilung = 10;
            leben = Math.min(100, leben + heilung);
            System.out.println("Du ruhst dich aus und regenerierst " + heilung + " Leben.");
            System.out.println("Aktuelle Lebenspunkte: " + leben);
        }
        else if (zahl >= 22 && zahl <= 30) {
            if (!alleKrieger.isEmpty()) {
                Krieger gegner = alleKrieger.get(random.nextInt(alleKrieger.size()));
                System.out.println("Ein Kampf beginnt gegen " + gegner.getName() + "!");
                kaempfen(gegner);
            }
        }
        else if (zahl >= 31 && zahl <= 40) {
            if (!verfuegbareWaffen.isEmpty()) {
                Gegenstand waffe = verfuegbareWaffen.get(random.nextInt(verfuegbareWaffen.size()));
                spielerKrieger.addGegenstand(waffe);
                System.out.println("Du findest eine Waffe: " + waffe.getTyp() + " (Macht: " + waffe.getFaktor() + ")");
            }
        }
        else if (zahl >= 41 && zahl <= 51) {
            if (!verfuegbareTiere.isEmpty()) {
                Tiere tier = verfuegbareTiere.get(random.nextInt(verfuegbareTiere.size()));
                spielerKrieger.addGegenstand(tier);
                System.out.println("Du findest ein Haustier: " + tier);
            }
        }
        else if (zahl >= 52 && zahl <= 62) {
            int heilung = 30;
            leben = Math.min(100, leben + heilung);
            System.out.println("Du findest ein Camp und heilst dich mit Bandagen.");
            System.out.println("Heilung: +" + heilung + " Leben");
            System.out.println("Aktuelle Lebenspunkte: " + leben);
        }
        else if (zahl >= 63 && zahl <= 80) {
            int schaden = random.nextInt(50) + 1;
            leben -= schaden;
            System.out.println("Du läufst durch einen Dornenbusch!");
            System.out.println("Schaden: -" + schaden + " Leben");
            System.out.println("Verbleibende Lebenspunkte: " + leben);
        }
        else if (zahl >= 81 && zahl <= 99) {
            System.out.println("Nichts Besonderes passiert.");
        }
        // bei d100 gewinnt man
        else if (zahl == 100) {
            System.out.println("Glückwunsch! Du hast gewonnen!");
            System.out.println("=== SPIEL GEWONNEN ===");
            leben = -1;
        }
        // wenn man stirbt verliert man
        if (leben <= 0) {
            System.out.println("GAME OVER - Keine Lebenspunkte mehr übrig!");
            leben = 0;
        }
    }
}