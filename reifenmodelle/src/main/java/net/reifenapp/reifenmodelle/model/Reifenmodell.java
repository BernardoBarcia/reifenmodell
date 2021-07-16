package net.reifenapp.reifenmodelle.model;

import javax.persistence.*;
import java.io.Serializable;

/*Entity
public class Style {
    Color color1; // default is EnumType.ORDINAL
    @Enumerated(EnumType.ORDINAL) Color color2;
    @Enumerated(EnumType.STRING) Color color3;
}

enum Color { RED, GREEN, BLUE };*/

@Entity
public class Reifenmodell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    // 205/55/R16 91H
    private int breite; //mm
    private int hoeheZuBreiteVerhaeltnis; //%
    private String reifenbauart;
    private int durchmesser; //zoll
    private String imageURL;

    // Extra
    /*private char geschwindigkeitsIndex;
    private int tragfaehigkeitsIndex;*/

    public Reifenmodell() {
    }

    public Reifenmodell(int breite, int hoeheZuBreiteVerhaeltnis, String reifenbauart, int durchmesser, String imageURL) {
        this.breite = breite;
        this.hoeheZuBreiteVerhaeltnis = hoeheZuBreiteVerhaeltnis;
        this.reifenbauart = reifenbauart;
        this.durchmesser = durchmesser;
        this.imageURL = imageURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBreite() {
        return breite;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public int getHoeheZuBreiteVerhaeltnis() {
        return hoeheZuBreiteVerhaeltnis;
    }

    public void setHoeheZuBreiteVerhaeltnis(int hoeheZuBreiteVerhaeltnis) {
        this.hoeheZuBreiteVerhaeltnis = hoeheZuBreiteVerhaeltnis;
    }

    public String getReifenbauart() {
        return reifenbauart;
    }

    public void setReifenbauart(String reifenbauart) {
        this.reifenbauart = reifenbauart;
    }

    public int getDurchmesser() {
        return durchmesser;
    }

    public void setDurchmesser(int durchmesser) {
        this.durchmesser = durchmesser;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Reifenmodell{" +
                "id=" + id +
                ", breite=" + breite +
                ", hoeheZuBreiteVerhaeltnis=" + hoeheZuBreiteVerhaeltnis +
                ", reifenbauart=" + reifenbauart +
                ", durchmesser=" + durchmesser +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
