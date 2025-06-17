package ktaris;

public class KtarisMain {

    public static void main(String[] args) {
        // Erzeugen der Objekte
        Gegenstand g1 = new Gegenstand("Quantenkanone", 17);
        Gegenstand g2 = new Gegenstand("Graviton-Blaster", 15);

        /* TODO: 1. Erzeugen Sie ein zusaetzliches Objekt g3, g4
         * die Attributwerte für alle Instanzvariablen finden Sie im Objektdiagramm
         * aus (Hinweis: verwenden Sie den vollparametrisierten Konstruktor)
         */
        Gegenstand g3 = new Gegenstand("Quantenkanone", 17);
        Gegenstand g4 = new Gegenstand("Dunkel-Emitter", 45);
        /* TODO: 2. Erzeugen Sie das zusaetzliche Objekt g5 mit dem Konstruktor,
         * der nichts initialisiert (parameterloser Konstruktor),
         */
        Gegenstand g5 = new Gegenstand();
        // Setzen der Attribute
        g5.setTyp("Schatten-Flame");
        g5.setFaktor(15);
        //A4.1.2
        Volk katarus = new Volk("Katarus",1247);
        Volk arpasian = new Volk("Arpasian",1023);
        //4.1.3
        Krieger allana = new Krieger("Allana",140);
        Krieger tystae = new Krieger("Tystae",70);
        Krieger inaraSerra = new Krieger("Inara Serra",163);

        //4.2.1
        katarus.setChef(allana);
        arpasian.setChef(inaraSerra);

        //4.2.2.6
        katarus.addKrieger(allana);
        katarus.addKrieger(tystae);
        arpasian.addKrieger(inaraSerra);
        /* TODO: 3. Fuegen Sie g5 die Attributwerte über die Setter hinzu, siehe Objektdiagramm
         */

        // Bildschirmausgabe
        System.out.println("Typ: " + g1.getTyp());
        System.out.println("Faktor: " + g1.getFaktor());
        System.out.println(g1.toString()); // Die toString() Methode wird aufgerufen
        System.out.println();
        System.out.println("Typ: " + g2.getTyp());
        System.out.println("Faktor: " + g2.getFaktor());
        System.out.println(g2); // Die toString() Methode wird selbstständig aufgerufen
        System.out.println();
        // TODO: 4. Ausgabe von g3, ergänzen Sie die fehlenden Methodenaufrufen
        System.out.println("Typ: " + g3.getTyp());
        System.out.println("Faktor: " + g3.getFaktor());
        System.out.println(g3); // TODO: 5. Die toString() Methode wird aufgerufen
        System.out.println();
        /* TODO: 6. Geben Sie g4, g5 auch auf dem Bildschirm aus, in dem Sie die
         * fehlenden Methodenaufrufen ergänzen.
         */
        System.out.println("Typ: " + g4.getTyp());
        System.out.println("Faktor: " + g4.getFaktor());
        System.out.println(g4);
        System.out.println();
        System.out.println("Typ: " + g5.getTyp());
        System.out.println("Faktor: " + g5.getFaktor());
        System.out.println(g5);
        System.out.println();

        //4.2.3
        allana.addGegenstand(g1);
        allana.addGegenstand(g2);

        tystae.addGegenstand(g3);

        inaraSerra.addGegenstand(g4);
        inaraSerra.addGegenstand(g5);

        //4.2.2.7
        System.out.println(katarus);
        System.out.println(arpasian);
        System.out.println("Allana hat Macht: " + allana.getMacht());
        System.out.println("Tystae hat Macht: " + tystae.getMacht());
        System.out.println("Inara Serra hat Macht: " + inaraSerra.getMacht());




        //allana.treffen(tystae);

        //allana findet eine neue Quantenkanone
        Gegenstand g6 = new Gegenstand("Neue Quantenkanone", 17);
        allana.addGegenstand(g6);
        System.out.println("Allana hat Macht: " + allana.getMacht());
        //Katarus zeigt seine macht an
        System.out.println("katarus hat Macht: " + katarus.getMacht());
        //tystae verliert seine Quantenkannone
        tystae.removeGegenstand(g3);
        //Allana gibt tystae seine neue Quantkannone
        allana.treffen(tystae);

        System.out.println("katarus hat Macht: " + katarus.getMacht());
        //Zufügen Bria zu Arsprin und als chef setzen
        Krieger bria = new Krieger("Bria ",148);
        arpasian.addKrieger(bria);
        arpasian.setChef(bria);
        System.out.println("arpasian hat Macht: " + arpasian.getMacht());

        // Bria erhält den Dunkel-Emitter von Inara
        inaraSerra.removeGegenstand(g4);
        bria.addGegenstand(g4);

        // Korrektur des Faktors der Quantenkanone von Allana
        g1.setFaktor(12);

        // Katarus zeigt seine Macht
        System.out.println("katarus hat Macht: " + katarus.getMacht()); // sollte 71 sein

        /**
         * 5.2 Zufügen von Tiere und Spielzeug zu den Kriegern und aufrufen von to strings
         */
        Tiere t1 = new Tiere("I-Chaya", 18,"Vulkan",300);
        Tiere t2 = new Tiere("Tribble", 1,"Qo'noS",1);
        Spielzeug s1 = new Spielzeug("Vulkanischer Meditations-Sandkasten", 51, "10 x 14 x 14 cm",3,2261);
        Spielzeug s2 = new Spielzeug("Klingonisches Karaoke-Set", 5, "25 x 30 x 45 cm",7,2382);
        allana.addGegenstand(t1);
        tystae.addGegenstand(t2);
        inaraSerra.addGegenstand(s1);
        bria.addGegenstand(s2);

        System.out.println("arpasian hat Macht: " + arpasian.getMacht());
        System.out.println("katarus hat Macht: " + katarus.getMacht());

        System.out.println(allana);
        System.out.println(bria);


        Spielsystem spiel = new Spielsystem(allana, katarus, arpasian);
        spiel.spielStart();

    }

}
