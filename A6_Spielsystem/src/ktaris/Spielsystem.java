package ktaris;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class Spielsystem {
    private Random random;
    private Krieger aktuellerKrieger;
    private ArrayList<Krieger> alleKrieger;
    private int leben;
    private Volk katarus;
    private Volk arpasian;
    private Scanner scanner;

    public Spielsystem(Krieger spielerKrieger, Volk katarus, Volk arpasian) {
        this.random = new Random();
        this.aktuellerKrieger = spielerKrieger;
        this.leben = 100;
        this.katarus = katarus;
        this.arpasian = arpasian;
        this.scanner = new Scanner(System.in);

        this.alleKrieger = new ArrayList<>();
        this.alleKrieger.addAll(katarus.getKrieger());
        this.alleKrieger.addAll(arpasian.getKrieger());
        this.alleKrieger.remove(spielerKrieger);
    }


    public void spielStart() {
        System.out.println("Willkommen " + aktuellerKrieger.getName() + "! Das Spiel beginnt!");
        System.out.println("Deine aktuelle Macht: " + aktuellerKrieger.getMacht());
        boolean weiterspielen = true;

        while(weiterspielen && isSpielAktiv()) {
            ereignisAusfuehren();

            if(isSpielAktiv()) {
                weiterspielen = spielerWillWeiterspielen();
            }
        }

        if(!isSpielAktiv()) {
            System.out.println("Spiel beendet - Du hast keine Leben mehr!");
        } else {
            System.out.println("Spiel beendet - Danke fürs Spielen!");
        }
        System.out.println("Finale Macht: " + aktuellerKrieger.getMacht());
    }

    private boolean spielerWillWeiterspielen() {
        while(true) {
            System.out.println("\nMöchtest du weiterspielen? (j/n)");
            String antwort = scanner.nextLine().trim().toLowerCase();

            if(antwort.equals("j") || antwort.equals("ja")) {
                return true;
            } else if(antwort.equals("n") || antwort.equals("nein")) {
                return false;
            } else {
                System.out.println("Bitte antworte mit 'j' für ja oder 'n' für nein.");
            }
        }
    }

    public void ereignisAusfuehren() {
        int zahl = random.nextInt(100) + 1;
        System.out.println("\n--- Würfelwurf: " + zahl + " ---");

        if (zahl == 1) {
            System.out.println("Game over - Du hast verloren!");
            leben = 0;
        }
        else if (zahl >= 2 && zahl <= 10) {
            if (!alleKrieger.isEmpty()) {
                Krieger bewohner = alleKrieger.get(random.nextInt(alleKrieger.size()));
                System.out.println("Du triffst " + bewohner.getName() + "!");
                aktuellerKrieger.treffen(bewohner);
            }
        }
        else if (zahl >= 11 && zahl <= 21) {
            int heilung = 10;
            leben += heilung;
            System.out.println("Du ruhst dich aus und regenerierst " + heilung + " Leben.");
        }
        else if (zahl >= 22 && zahl <= 30) {
            if (!alleKrieger.isEmpty()) {
                Krieger gegner = alleKrieger.get(random.nextInt(alleKrieger.size()));
                System.out.println("Kampf gegen " + gegner.getName() + "!");
                int schaden = Math.max(5, gegner.getMacht() / 10);
                leben -= schaden;
                System.out.println("Du verlierst " + schaden + " Leben im Kampf!");
            }
        }
        else if (zahl >= 31 && zahl <= 40) {

            int machtBonus = random.nextInt(15) + 5;
            System.out.println("Du findest eine magische Verstärkung! Deine Macht steigt um " + machtBonus);
            aktuellerKrieger.setMacht(aktuellerKrieger.getMacht() + machtBonus);
        }
        else if (zahl >= 41 && zahl <= 51) {

            int machtBonus = random.nextInt(10) + 1;
            System.out.println("Ein mystischer Geist stärkt dich! Deine Macht steigt um " + machtBonus);
            aktuellerKrieger.setMacht(aktuellerKrieger.getMacht() + machtBonus);
        }
        else if (zahl >= 52 && zahl <= 62) {
            int heilung = 30;
            leben += heilung;
            System.out.println("Du findest ein Camp und heilst " + heilung + " Leben durch Bandagen.");
        }
        else if (zahl >= 63 && zahl <= 80) {
            int schaden = random.nextInt(10) + 1;
            leben -= schaden;
            System.out.println("Du läufst durch einen Dornenbusch! -" + schaden + " Leben");
        }
        else if (zahl >= 81 && zahl <= 99) {
            System.out.println("Nichts Besonderes passiert.");
        }
        else if (zahl == 100) {
            System.out.println("Glückwunsch! Du hast gewonnen!");
            leben = 100;
        }

        System.out.println("Verbleibende Leben: " + leben);
        System.out.println("Deine aktuelle Macht: " + aktuellerKrieger.getMacht());
    }

    public boolean isSpielAktiv() {
        return leben > 0;
    }

    public int getLeben() {
        return leben;
    }
}
