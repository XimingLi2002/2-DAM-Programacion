package UT1.SimulacroExamen;

import java.io.Serializable;

public class Equipos implements Serializable {
    int clubNumber;
    String clubName;
    String president;
    int telephoneNumber;
    String locate;
    public Equipos(int clubNumber, String clubName, String president, int telephoneNumber, String locate) {
        this.clubNumber = clubNumber;
        this.clubName = clubName;
        this.president = president;
        this.telephoneNumber = telephoneNumber;
        this.locate = locate;
    }

    public int getClubNumber() {
        return clubNumber;
    }

    public void setClubNumber(int clubNumber) {
        this.clubNumber = clubNumber;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getPresident() {
        return president;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public int getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(int telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    @Override
    public String toString() {
        return "Equipos{" +
                "clubNumber=" + clubNumber +
                ", clubName='" + clubName + '\'' +
                ", president='" + president + '\'' +
                ", telephoneNumber=" + telephoneNumber +
                ", locate='" + locate + '\'' +
                '}';
    }
}
