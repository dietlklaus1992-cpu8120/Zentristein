import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Strahlungsdruckkalkulation {
    BigDecimal Zefo;
    BigDecimal GeKra;
    BigDecimal BaGe;
    BigDecimal KreBa;
    BigDecimal Temp;
    BigDecimal Speed;
    BigDecimal Dichte;
    BigDecimal Volumen;
    BigDecimal Strahlungsdruck;
    BigDecimal Strahlungsdruck3;
    BigDecimal Zefo2;
    BigDecimal GeKra2;
    BigDecimal BaGe2;
    BigDecimal KreBa2;
    BigDecimal Temp2;
    BigDecimal Speed2;
    BigDecimal Dichte2;
    BigDecimal Volumen2;
    BigDecimal Strahlungsdruck2;
    BigDecimal MidTemp;
    BigDecimal MidKreBa;
    BigDecimal MidBaGe;
    BigDecimal MaxTemp;
    BigDecimal MaxKreBa;
    BigDecimal MaxBaGe;
    BigDecimal MinTemp;
    BigDecimal MinKreBa;
    BigDecimal MinBaGe;
    BigDecimal MidTemp2;
    BigDecimal MidKreBa2;
    BigDecimal MidBaGe2;
    BigDecimal MaxTemp2;
    BigDecimal MaxKreBa2;
    BigDecimal MaxBaGe2;
    BigDecimal MinTemp2;
    BigDecimal MinKreBa2;
    BigDecimal MinBaGe2;
    BigDecimal MidTemp3;
    BigDecimal MidKreBa3;
    BigDecimal MidBaGe3;
    public JTable tableRef;
    MathContext mc = new MathContext(40);
    Fenster windowRef;


    public Strahlungsdruckkalkulation(JTable data, Fenster fenster){
        this.tableRef = data;
        this.windowRef = fenster;
        this.Zefo = parse(data.getValueAt(2,0));
        this.GeKra = parse(data.getValueAt(2,1));
        this.BaGe = parse(data.getValueAt(2,2));
        this.KreBa = parse(data.getValueAt(2,3));
        this.Temp = parse(data.getValueAt(2 ,4));
        this.Speed = parse(data.getValueAt(2,5));
        this.Dichte = parse(data.getValueAt(2,6));
        this.Volumen = parse(data.getValueAt(2, 7));
        this.Strahlungsdruck = parse(data.getValueAt(2,8));
        this.MidTemp = parse(data.getValueAt(2,21));
        this.MidKreBa = parse(data.getValueAt(2,22));
        this.MidBaGe = parse(data.getValueAt(2,23));
        this.Strahlungsdruck3 = parse(data.getValueAt(2,10));
        this.Zefo2 = parse(data.getValueAt(2,12));
        this.GeKra2 = parse(data.getValueAt(2,13));
        this.BaGe2 = parse(data.getValueAt(2,14));
        this.KreBa2 = parse(data.getValueAt(2,15));
        this.Temp2 = parse(data.getValueAt(2 ,16));
        this.Speed2 = parse(data.getValueAt(2,17));
        this.Dichte2 = parse(data.getValueAt(2,18));
        this.Volumen2 = parse(data.getValueAt(2, 19));
        this.Strahlungsdruck2 = parse(data.getValueAt(2,20));
        this.MidTemp2 = parse(data.getValueAt(2,24));
        this.MidKreBa2 = parse(data.getValueAt(2,25));
        this.MidBaGe2 = parse(data.getValueAt(2,26));
        this.MidTemp3 = parse(data.getValueAt(2,27));
        this.MaxKreBa = parse(data.getValueAt(2,28));
        this.MaxKreBa2 = parse(data.getValueAt(2,29));
        this.MaxBaGe2 = parse(data.getValueAt(2,30)); // last change

        System.out.println("Gelesen:\nKraft = " + Zefo + " Newton\nMasse = " + GeKra + " Kilogramm\nGeschwindigkeit = " + BaGe + "Meter/Sec\nKreisbahn = " + KreBa + " Meter\nGelesen:\nKraft = "  + Zefo2 + " Newton\nMasse = " + GeKra2 + " Kilogramm\nGeschwindigkeit = " + BaGe2 + "Meter/Sec\nKreisbahn = " + KreBa2 + " Meter");
    }
    private static final BigDecimal FUTIONS_SCHWELLE = new BigDecimal("1.0E+7");         // 10 Mio. Kelvin
    private static final BigDecimal SUPERNOVA_SCHWELLE = new BigDecimal("1.0E+30");      // Extrem kritische Temperatur
    private static final BigDecimal SINGULARITAETS_SCHWELLE = new BigDecimal("1.0E+60"); // Dein E+65 Fall!

    // Methode zur Bestimmung des Zustands als Text
    private String bestimmeObjektZustand(BigDecimal aktuelleTemp) {
        if (aktuelleTemp == null) return "STABIL";
        if (aktuelleTemp.compareTo(SINGULARITAETS_SCHWELLE) >= 0) return "SINGULARITÄT";
        if (aktuelleTemp.compareTo(SUPERNOVA_SCHWELLE) >= 0) return "SUPERNOVA";
        if (aktuelleTemp.compareTo(FUTIONS_SCHWELLE) >= 0) return "KERNFUSION";
        return "STABIL";
    }
    private static final BigDecimal PI = new BigDecimal("3.1415926535897932384626433832795028841971");
    private final BigDecimal KreisForm = BigDecimal.valueOf(3).divide(PI.multiply(BigDecimal.valueOf(4)), mc);

    private BigDecimal parse(Object val) {
        // 1. Prüfung auf null oder leer
        if (val == null || val.toString().trim().isEmpty()) {
            return BigDecimal.ZERO; // BigDecimal.ZERO statt 0
        }
        try {
            // 2. Den String-Konstruktor nutzen (Es gibt kein parseBigDecimal)
            return new BigDecimal(val.toString().trim());
        } catch (NumberFormatException e) {
            // 3. Fehlerfall: Rückgabe von Null-Objekt
            return BigDecimal.ZERO;
        }
    }
    public BigDecimal cbrt(BigDecimal n, MathContext mc) {
        if (n.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        BigDecimal three = new BigDecimal("3");
        // Initialer Schätzwert (hier ist ein double-Cast als STARTwert okay)
        BigDecimal x = BigDecimal.valueOf(Math.pow(n.doubleValue(), 1.0/3.0));
        // Newton-Iteration für 40 Stellen Präzision
        for (int i = 0; i < 8; i++) {
            // Formel: x_neu = (1/3) * (2*x + n / x^2)
            BigDecimal xSquared = x.multiply(x, mc);
            BigDecimal term2 = n.divide(xSquared, mc);
            BigDecimal summe = x.multiply(new BigDecimal("2"), mc).add(term2, mc);
            x = summe.divide(three, mc);
        }
        return x;
    }
    public void calcRad1Obj1Act(DefaultTableModel model){
        MathContext mc = new MathContext(40, RoundingMode.HALF_UP);
        BigDecimal sun = new BigDecimal("15E16", mc);
        BigDecimal KreBaSquared = sun.multiply(sun, mc); // Wirkungsradius Sonne Strahlungsdruck
        BigDecimal pressure = Strahlungsdruck3.divide(KreBaSquared, mc); // Der Strahlungsdruck ist im equivalent zur entfernung zu berechnen
        BigDecimal r32 = (Volumen.multiply(KreisForm, mc)); // m² des Objectes ermitteln
        BigDecimal radObj12 = cbrt(r32, mc); // quadratmeter der Fläche... Kugelkrümmung?. hmmm ..
        BigDecimal actArea12 = radObj12.multiply(radObj12, mc).multiply(PI, mc); // m²
        this.Strahlungsdruck = actArea12.multiply(pressure, mc).multiply(BaGe,mc).multiply(GeKra);// Strahlungsdruck nach entfernung zur Sonne E = mc² = (Newton)
        updateTable(tableRef);
        updateTable(model);// Noch Ohne eigene Bahngeschwindigkeit im Bezug...
        System.out.println("Strahlungsdruck berechnet Objekt1: \n" +this.Strahlungsdruck.toPlainString());
    }
    public void calcRad2Obj2Act(DefaultTableModel model){
        MathContext mc = new MathContext(40, RoundingMode.HALF_UP);
        BigDecimal sun = new BigDecimal(15E16, mc);
        BigDecimal KreBAsquared2 = sun.multiply(sun, mc);
        BigDecimal pressure2 =  Strahlungsdruck3.divide(KreBAsquared2, mc);
        BigDecimal r43 = (Volumen2.multiply(KreisForm, mc));
        BigDecimal radObj23 = cbrt(r43, mc);
        BigDecimal actArea23 = radObj23.multiply(radObj23, mc).multiply(PI, mc);
        this.Strahlungsdruck2 = actArea23.multiply(pressure2, mc).multiply(BaGe2,mc).multiply(GeKra2); // Mit der Bahngeschwindigkeit
        updateTable(tableRef);
        updateTable(model);
        System.out.println("Strahlungsdruck berechnet Objekt2: \n" +this.Strahlungsdruck2.toPlainString());

    }
    public void calcExchangeEnergys (){
        MathContext mc = new MathContext(40, RoundingMode.HALF_UP);
    }

    public BigDecimal getZefo() {
        return Zefo;
    }
    public BigDecimal getKreBa(){
        return KreBa;
    }
    public BigDecimal getBaGe() {
        return BaGe;
    }
    public void setTemp(BigDecimal temp){ this.Temp = temp; }
    public BigDecimal getTemp() { return Temp;}
    public void setTemp2(BigDecimal temp2){ this.Temp2 = temp2; }
    public BigDecimal getTemp2() { return Temp2;}
    public void setZefo(BigDecimal zefo){
        Zefo = zefo;
    }
    public void setKreBa(BigDecimal kreba){
        KreBa = kreba;
    }
    public void setBaGe(BigDecimal bage) {
        BaGe = bage;
    }
    public BigDecimal getStrahlungsdruck() {
        return Strahlungsdruck;
    }
    public void setStrahlungsdruck(BigDecimal strahlungsdruck) {
        Strahlungsdruck = strahlungsdruck;
    }
    public BigDecimal getZefo2(){
        return Zefo2;
    }
    public void setZefo2(BigDecimal zefo2){
        Zefo = zefo2;
    }
    public BigDecimal getKreBa2() {
        return KreBa2;
    }
    public void setKreBa2(BigDecimal kreba2){
        KreBa2 = kreba2;
    }
    public BigDecimal getBaGe2() {
        return BaGe2;
    }
    public void setBaGe2(BigDecimal bage2){
        BaGe2 = bage2;
    }
    public BigDecimal getStrahlungsdruck2() {
        return Strahlungsdruck2;
    }
    public void setStrahlungsdruck2(BigDecimal strahlungsdruck) {
        Strahlungsdruck2 = strahlungsdruck;
    }
    public void setMidTemp(BigDecimal midtemp){this.MidTemp = midtemp;}
    public void setMidKreBa(BigDecimal midkreba){this.MidKreBa = midkreba;}
    public void setMidBaGe(BigDecimal midbage){this.MidBaGe = midbage;}
    public void setMidKreBa2(BigDecimal midkreba2){this.MidKreBa2 = midkreba2;}
    public void setMidBaGe2(BigDecimal midbage2){this.MidBaGe2 = midbage2;}
    public BigDecimal getMidKreBa2(BigDecimal midkreba2){return MidKreBa2;}
    public BigDecimal getMidBaGe2(BigDecimal midbage2){return MidBaGe2;}
    public void setMaxTemp(BigDecimal maxtemp){this.MaxTemp = maxtemp;}
    public void setMaxKreBa(BigDecimal maxkreba){this.MaxKreBa = maxkreba;}
    public void setMaxBaGe(BigDecimal maxbage){this.MaxBaGe = maxbage;}
    public BigDecimal getMaxTemp() {return MaxTemp;}
    public BigDecimal getMaxKreBa() {return MaxKreBa;}
    public BigDecimal getMaxBaGe() {return MaxBaGe;}
    public void setMinTemp(BigDecimal mintemp){this.MinTemp = mintemp;}
    public void setMinKreBa(BigDecimal minkreba){this.MinKreBa = minkreba;}
    public void setMinBaGe(BigDecimal minbage){this.MinBaGe = minbage;}
    public BigDecimal getMinTemp() {return MinTemp;}
    public BigDecimal getMinKreBa() {return MinKreBa;}
    public BigDecimal getMinBaGe() {return MinBaGe;}
    public BigDecimal getMaxTemp2() {return MaxTemp2;}
    public BigDecimal getMaxKreBa2() {return MaxKreBa2;}
    public BigDecimal getMaxBaGe2() {return MaxBaGe2;}
    public void setMaxTemp2(BigDecimal maxtemp2){this.MaxTemp2 = maxtemp2;}
    public void setMaxKreBa2(BigDecimal maxkreba2){this.MaxKreBa2 = maxkreba2;}
    public void setMaxBaGe2(BigDecimal maxbage2){this.MaxBaGe2 = maxbage2;}
    public BigDecimal getDichte() {return Dichte;}
    public void setDichte(BigDecimal dichte){this.Dichte = dichte; }
    public BigDecimal getDichte2() {return Dichte2;}
    public void setDichte2(BigDecimal dichte2){this.Dichte2 = dichte2; }
    public BigDecimal getVolumen(){return Volumen;}
    public void setVolumen(BigDecimal volumen){this.Volumen = volumen;}
    public BigDecimal getVolumen2(){return Volumen2;}
    public void setVolumen2(BigDecimal volumen2){this.Volumen2 = volumen2;}
    public BigDecimal getGeKra() {return GeKra;}
    public void setGeKra(BigDecimal gekra) {this.GeKra = gekra;}
    public void setMidTemp2(BigDecimal midTemp2) { this.MidTemp2 = midTemp2;}
    public BigDecimal getMidTemp2(){return MidTemp2;}
    public BigDecimal getGeKra2() {return GeKra2;}
    public void setGeKra2(BigDecimal gekra2) {this.GeKra2 = gekra2;}
    public void updateTable(JTable data) {
        data.setValueAt(Zefo,2,0);
        data.setValueAt(GeKra,2,1);
        data.setValueAt(BaGe,2,2);
        data.setValueAt(KreBa,2,3);
        data.setValueAt(Temp, 2, 4);
        data.setValueAt(Dichte,2,6);
        data.setValueAt(Volumen,2,7);
        data.setValueAt(Strahlungsdruck, 2,8);
        data.setValueAt(MidTemp,2,21);
        data.setValueAt(Zefo2,2,12);
        data.setValueAt(GeKra2,2,13);
        data.setValueAt(BaGe2,2,14);
        data.setValueAt(KreBa2,2,15);
        data.setValueAt(Temp2 ,2,16);
        data.setValueAt(Dichte2,2,18);
        data.setValueAt(Volumen2,2,19);
        data.setValueAt(Strahlungsdruck2,2,20);
        data.setValueAt(MaxTemp,2,22);
        data.setValueAt(MaxTemp2,2,23);
        data.setValueAt(MidTemp2,2,24);
        data.setValueAt(MaxKreBa,2,28);
        data.setValueAt(MaxKreBa2, 2,29);
        if (this.windowRef != null && this.windowRef.kollisionsAnzeige != null) {
            // 1. Daten für Objekt 1 auslesen (KreBa und Temp)
            double abstand1 = (this.KreBa != null) ? this.KreBa.doubleValue() : 0.0;
            double temp1    = (this.Temp != null) ? this.Temp.doubleValue() : 0.0;

            // 2. Daten für Objekt 2 auslesen (KreBa2 und Temp2)
            double abstand2 = (this.KreBa2 != null) ? this.KreBa2.doubleValue() : 0.0;
            double temp2    = (this.Temp2 != null) ? this.Temp2.doubleValue() : 0.0;

            // 3. Alle 4 Live-Werte zeitgleich an den dualen Graphen senden
            this.windowRef.kollisionsAnzeige.updateKollision(abstand1, temp1, abstand2, temp2);
        }


    }
    public void updateTable(DefaultTableModel model) {

        model.setValueAt(Zefo, 2, 0);
        model.setValueAt(GeKra, 2, 1);
        model.setValueAt(BaGe, 2, 2);
        model.setValueAt(KreBa, 2, 3);
        model.setValueAt(Temp, 2, 4);
        model.setValueAt(Speed,2,5);
        model.setValueAt(Dichte,2,6);
        model.setValueAt(Volumen,2,7);
        model.setValueAt(Strahlungsdruck, 2,8);

        model.setValueAt(Strahlungsdruck3,2 ,10);

        model.setValueAt(Zefo2,2,12);
        model.setValueAt(GeKra2, 2, 13);
        model.setValueAt(BaGe2, 2, 14);
        model.setValueAt(KreBa2, 2, 15);
        model.setValueAt(Temp2,2, 16);
        model.setValueAt(Speed2,2,17);
        model.setValueAt(Dichte2,2 ,18);
        model.setValueAt(Volumen2,2,19);
        model.setValueAt(Strahlungsdruck2,2,20);
        model.setValueAt(MidTemp,2,21);
        model.setValueAt(MaxTemp,2,22);
        model.setValueAt(MaxTemp2,2,23);
        model.setValueAt(MidTemp2,2,24);
        model.setValueAt(MidKreBa2,2,25);
        model.setValueAt(MidBaGe2,2,26);
        model.setValueAt(MidTemp3,2,27);
        model.setValueAt(MaxKreBa,2,28);
        model.setValueAt(MaxKreBa2,2,29);

        // --- NEU: Zustände live vor dem Tabellen-Eintrag bestimmen ---
        String zustandObjekt1 = bestimmeObjektZustand(Temp);
        String zustandObject2 = bestimmeObjektZustand(Temp2);

        // Vorher stand hier "" für TeilA und TeilB, jetzt setzen wir die Zustände ein!
        Object[] neueZeile = {
                Zefo, GeKra, BaGe, KreBa, Temp, Speed, Dichte, Volumen, Strahlungsdruck,
                zustandObjekt1, // Ersetzt die vorherige leere Spalte für Objekt 1
                Strahlungsdruck3,
                zustandObject2, // Ersetzt die vorherige leere Spalte für Objekt 2
                Zefo2, GeKra2, BaGe2, KreBa2, Temp2, Speed2, Dichte2, Volumen2, Strahlungsdruck2,
                MidTemp, MidKreBa, MidBaGe, MidTemp2, MidKreBa2, MidBaGe2, MidTemp3, MaxKreBa, MaxKreBa2
        };

        // Wir fügen dieses Array als neue Zeile am Ende der Tabelle hinzu
        model.addRow(neueZeile);

        // Sicherstellen, dass das Fenster und die Anzeige existieren
        if (this.windowRef != null && this.windowRef.kollisionsAnzeige != null) {
            double abstand1 = (this.KreBa != null) ? this.KreBa.doubleValue() : 0.0;
            double temp1    = (this.Temp != null) ? this.Temp.doubleValue() : 0.0;
            double abstand2 = (this.KreBa2 != null) ? this.KreBa2.doubleValue() : 0.0;
            double temp2    = (this.Temp2 != null) ? this.Temp2.doubleValue() : 0.0;

            this.windowRef.kollisionsAnzeige.updateKollision(abstand1, temp1, abstand2, temp2);
        }
    }



}


