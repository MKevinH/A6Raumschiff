/**
 * 5.3
 */
package ktaris;

public class Tiere extends Gegenstand{
    private String heimat;
    private int lebenserwartung;

    public Tiere(){}
    public Tiere(String typ, int faktor, String heimat, int lebenserwartung){
        super(typ, faktor);
        this.heimat = heimat;
        this.lebenserwartung = lebenserwartung;
    }

    public String getHeimat(){
        return heimat;
    }
    public String setHeimat(String heimat){
        return this.heimat = heimat;
    }
    public int getLebenserwartung(){
        return lebenserwartung;
    }
    public int setLebenserwartung(int lebenserwartung){
        return this.lebenserwartung = lebenserwartung;
    }

    @Override
    public String toString() {
        return "Tiere{" +
                super.toString() +
                "heimat='" + heimat + '\'' +
                ", lebenserwartung=" + lebenserwartung +
                '}';
    }
}
