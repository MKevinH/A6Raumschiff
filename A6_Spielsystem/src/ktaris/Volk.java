package ktaris;
import java.util.ArrayList; //ArrayList Import
// Attribute
public class Volk {
    // Attribute
    private String name;
    private int gruendung;
    private Krieger chef;
    //4.2.2
    private ArrayList<Krieger> mitglieder = new ArrayList<>();



    //Methoden
    public Krieger getChef() {
        return chef;
    }
    public void setChef(Krieger chef){
        this.chef = chef;
    }

    public Volk() {
    }
    public Volk(String name, int gruendung){
        this.name = name;
        this.gruendung = gruendung;
        this.mitglieder = new ArrayList<>(); //4.2.2.2
    }

    public int getGruendung() {
        return gruendung;
    }

    public void setGruendung(int gruendung) {
        this.gruendung = gruendung;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Krieger> getKrieger() {
        return mitglieder;
    }

    //4.2.3
    public int getMacht(){
        int summeMacht = 0; // Deklarieren summeMacht Int Variable
        for(Krieger k : mitglieder){ // Für Jede Krieger in der mitglied Array
            if (k.equals(chef)) { // wenn chef macht mal 2
                summeMacht += k.getMacht() * 2;
            }
            else{ // wenn nicht chef dann summeMacht = Macht
                summeMacht += k.getMacht();
            }
        }
        return summeMacht; // Rückgabe summeMacht

    }

    public void addKrieger(Krieger k){
        //4.2.2.5
        if (chef == null) {
            chef = k;
        }

        mitglieder.add(k);
    }

    public void removeKrieger(Krieger k){
        //4.2.2.4
        mitglieder.remove(k);
        if (chef == k) {
            chef = null;
            if(this.mitglieder.size() != 0){
                this.chef = this.mitglieder.get(0);
            }
        }

    }

    @Override
    public String toString() {
        //4.2.2.7
        String allekrieger = "";
        for (int i = 0; i < mitglieder.size(); i++){
            allekrieger += mitglieder.get(i).getName() ;
        if (i < mitglieder.size() - 1 ){
                allekrieger += ", ";
            }
        }
        return "Volk{" +
                "name = '" + name + '\'' +
                ", gruendung = " + gruendung +
                ", krieger = " + allekrieger +
                '}';
    }
}