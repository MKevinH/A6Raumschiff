package ktaris;
import java.util.ArrayList; //ArrayList Import
import java.util.Scanner;
public class Krieger {
    //Attribute
    private String name;
    private int alter;
    private int macht;
    //4.3
    private ArrayList<Gegenstand> aGegenstand = new ArrayList<>();
    //Methoden

    public Krieger(String name, int alter) {
        this.name = name;
        this.alter = alter;
        this.macht = 1;
        berechneMacht();


    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAlter() {
        return alter;
    }
    public void setAlter(int alter) {
        this.alter = alter;
    }
    public int getMacht() {
        berechneMacht();
        return macht;

    }
    public void setMacht(int macht) {
        this.macht = macht;
    }

    /**
     * Berechnet das Faktor basierend auf die Besitzende Gegenstände
     */
    private void berechneMacht(){
        this.macht = 0;
        for(Gegenstand g : aGegenstand){
            this.macht += g.getFaktor();
        }


    }

    public void addGegenstand(Gegenstand g){
        aGegenstand.add(g); // 4.2.3
        berechneMacht();

    }
    public void removeGegenstand(Gegenstand g){
        aGegenstand.remove(g); //4.2.3
        berechneMacht();
    }

    /**
     * 5.3
     * Methode für das Handeln eines Gegenstandes. geben und erhelaten
     *
     * @param g das gegenstand das entweder zugefügt oder Entfernt werden soll.
     */
    public void handeln(Gegenstand g){
        if (aGegenstand.contains(g)) {
            removeGegenstand(g);
            System.out.println(name + " hat abgegeben: " + g);
        } else {
            addGegenstand(g);
            System.out.println(name + " hat aufgenommen: " + g);
        }
        berechneMacht();
        System.out.println(this);

    }

    /**
     * Führt eine Interaktion zwischen zwei Kriegern durch, bei der der Spieler entscheiden kann,
     * ob er einen Gegenstand verschenken oder erhalten möchte. Die Methode enthält logische
     * Abläufe zur Auswahl und Übergabe von Gegenständen zwischen den Kriegern.
     *
     * @param k der Krieger, mit dem die Interaktion stattfindet
     */
    public void treffen(Krieger k){
        Scanner scanner = new Scanner(System.in);
        int wahl;
        do {
            System.out.println("Du bist auf " + k.getName() + " getroffen. Was willst du tun, " + name + "?");
            System.out.println("[1] Verschenken");
            System.out.println("[2] Erhalten");

            while (!scanner.hasNextInt()) {
                System.out.println("Bitte eine Zahl eingeben (1 oder 2):");
                scanner.next();
            }

            wahl = scanner.nextInt();

        } while (wahl != 1 && wahl != 2);

        switch(wahl) {
            case 1:
                System.out.println("Du hast gewählt zu verschenken.");

                if (aGegenstand.isEmpty()) {
                    System.out.println("Du hast keine Gegenstände zum Verschenken.");
                    break;
                }

                for (int i = 0; i < aGegenstand.size(); i++) {
                    System.out.println((i+1) + ": " + aGegenstand.get(i).toString());
                }
                int auswahl = -1;

                do {
                    System.out.println("Welchen Gegenstand möchtest du verschenken? (1-" + aGegenstand.size() + ")");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Bitte eine gültige Zahl eingeben:");
                        scanner.next();
                    }
                    auswahl = scanner.nextInt();
                } while (auswahl < 1 || auswahl > aGegenstand.size());

                Gegenstand geschenkt = aGegenstand.get(auswahl - 1);

                this.handeln(geschenkt);
                k.handeln(geschenkt);

                System.out.println("Du hast " + geschenkt.getTyp() + " an " + k.getName() + " verschenkt.");
                break;
            case 2:
                System.out.println("Du hast gewählt zu erhalten.");

                if (k.aGegenstand.isEmpty()) {
                    System.out.println(k.getName() + " hat keine Gegenstände zum Übergeben.");
                    break;
                }

                for (int i = 0; i < k.aGegenstand.size(); i++) {
                    System.out.println((i + 1) + ": " + k.aGegenstand.get(i));
                }

                int auswahl2 = -1;

                do {
                    System.out.println("Welchen Gegenstand möchtest du erhalten? (1-" + k.aGegenstand.size() + ")");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Bitte eine gültige Zahl eingeben:");
                        scanner.next();
                    }
                    auswahl2 = scanner.nextInt();
                } while (auswahl2 < 1 || auswahl2 > k.aGegenstand.size());

                Gegenstand erhalten = k.aGegenstand.get(auswahl2 - 1);

                k.handeln(erhalten);
                this.handeln(erhalten);

                System.out.println("Du hast " + erhalten.getTyp() + " von " + k.getName() + " erhalten.");
                break;
            default:
                System.out.println("Ungültige Auswahl!");
                break;
        }
    }





    @Override
    public String toString() {
        return "Krieger{" +
                "name='" + name + '\'' +
                ", alter=" + alter +
                ", macht=" + macht +
                ", aGegenstand=" + aGegenstand +
                '}';
    }
}
