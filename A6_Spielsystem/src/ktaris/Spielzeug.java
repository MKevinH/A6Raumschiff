/**
 * 5.3
 */
package ktaris;

public class Spielzeug extends Gegenstand{
    private String abmessung;
    private int gewicht;
    private int baujahr;

    public Spielzeug(){}
    public Spielzeug(String typ, int faktor, String abmessung, int gewicht, int baujahr){
        super(typ, faktor);
        this.abmessung = abmessung;
        this.gewicht = gewicht;
        this.baujahr = baujahr;
    }
    public String getAbmessung(){
        return abmessung;
    }
    public String setAbmessung(String abmessung){
        return this.abmessung = abmessung;
    }
    public int getGewicht(){
        return gewicht;
    }
    public int setGewicht(int gewicht){
        return this.gewicht = gewicht;
    }
    public int getBaujahr(){
        return baujahr;
    }
    public int setBaujahr(int baujahr){
        return this.baujahr = baujahr;
    }


    @Override
    public String toString() {
        return "Spielzeug{" +
                super.toString() +
                "abmessung=" + abmessung +
                ", gewicht=" + gewicht +
                ", baujahr=" + baujahr +
                '}';
    }
}
