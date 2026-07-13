import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ZentrifugationBigDecimal {
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
    BigDecimal MidTemp2;
    BigDecimal MidKreBa2;
    BigDecimal MidBaGe2;
    BigDecimal MidTemp3;
    BigDecimal MidKreBa3;
    BigDecimal MidBaGe3;
    BigDecimal MaxBaGe;
    BigDecimal MaxTemp;
    BigDecimal MaxKreBa;
    BigDecimal MaxKreBa2;
    BigDecimal Zefo3;
    BigDecimal GeKra3;
    BigDecimal KreBa3;
    BigDecimal Temp3;
    BigDecimal Speed3;
    static long letzterDruck = System.currentTimeMillis();
    BigDecimal LICHT_GESCHWINDIGKEIT = new BigDecimal("299792458");
    boolean Berechne;
    private static final BigDecimal NANO_TO_SEC = new BigDecimal("1000000000");
    static long lastTime = System.nanoTime();
    static long now = System.nanoTime();
    static long diff = now - lastTime;
    private static Thread workerThread = null;
    private JButton ZurArbeit;

    static BigDecimal DELTA_TIME = new BigDecimal(diff)
            .divide(NANO_TO_SEC, MathContext.DECIMAL128);

    BigDecimal BaGe3 = Stern.Sonne.BaGe3;
    MathContext mc = new MathContext(40);
    public JTable tableRef;
    BigDecimal MaxBaGe2;
    Fenster windowRef;
    // NUR DEKLARIEREN, NOCH NICHT REINSTANZIIEREN!
    Strahlungsdruckkalkulation strahlungsrechner;


    public ZentrifugationBigDecimal(JTable data, Fenster fenster){

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

        System.out.println("Gelesen:\nKraft = " + Zefo + " Newton\nMasse = " + GeKra + " Kilogramm\nGeschwindigkeit = " + BaGe + "Meter/Sec\nKreisbahn = " + KreBa + " Meter\nGelesen:\nKraft = "  + Zefo + " Newton\nMasse = " + GeKra + " Kilogramm\nGeschwindigkeit = " + BaGe + "Meter/Sec\nKreisbahn = " + KreBa + " Meter");
        this.strahlungsrechner = new Strahlungsdruckkalkulation(data, fenster) {

            @Override
            public void calcRad1Obj1Act(DefaultTableModel model) {
                super.calcRad1Obj1Act(model);
            }
            @Override
            public void calcRad2Obj2Act(DefaultTableModel model) {
                super.calcRad2Obj2Act(model);
            }
            @Override
            public BigDecimal getZefo(){ return super.getZefo();}
            @Override
            public void setZefo(BigDecimal Zefo){ super.setZefo(Zefo);}
            @Override
            public BigDecimal getTemp(){return super.getTemp();}
            @Override
            public BigDecimal getZefo2(){ return super.getZefo2();}
            @Override
            public void setZefo2(BigDecimal Zefo2){ super.setZefo2(Zefo2);}
            @Override
            public BigDecimal getBaGe(){ return super.getBaGe();}
            @Override
            public void setBaGe(BigDecimal BaGe){ super.setBaGe(BaGe);}
            @Override
            public BigDecimal getBaGe2(){ return super.getBaGe2();}
            @Override
            public void setBaGe2(BigDecimal BaGe2){ super.setBaGe2(BaGe2);}
            @Override
            public BigDecimal getKreBa(){ return super.getKreBa();}
            @Override
            public void setKreBa(BigDecimal KreBa){ super.setKreBa(KreBa);}
            @Override
            public BigDecimal getKreBa2(){ return super.getKreBa2();}
            @Override
            public void setKreBa2(BigDecimal KreBa2){ super.setKreBa2(KreBa2);}
            @Override
            public BigDecimal getStrahlungsdruck() {
                return super.getStrahlungsdruck();
            }
            @Override
            public void setStrahlungsdruck(BigDecimal strahlungsdruck) {
                super.setStrahlungsdruck(strahlungsdruck);
            }
            @Override
            public BigDecimal getStrahlungsdruck2() {
                return super.getStrahlungsdruck2();
            }
            @Override
            public void setStrahlungsdruck2(BigDecimal strahlungsdruck) {super.setStrahlungsdruck2(strahlungsdruck);}
            @Override
            public void setTemp(BigDecimal temp) {super.setTemp(temp);}
            @Override
            public BigDecimal getTemp2(){return super.getTemp2();}
            @Override
            public void setTemp2(BigDecimal temp2) {super.setTemp2(temp2);}
            @Override
            public BigDecimal getDichte2(){return super.getDichte2();}
            @Override
            public void setDichte2(BigDecimal dichte2){super.setDichte2(dichte2);}
            @Override
            public BigDecimal getDichte(){return super.getDichte();}
            @Override
            public void setDichte(BigDecimal dichte){super.setDichte(dichte);}
            @Override
            public BigDecimal getVolumen(){return super.getVolumen();}
            @Override
            public void setVolumen(BigDecimal volumen){ super.setVolumen(volumen);}
            @Override
            public BigDecimal getVolumen2(){return super.getVolumen2();}
            @Override
            public void setVolumen2(BigDecimal volumen2){ super.setVolumen2(volumen2);}
        };
    }
    // Ändere 'void' zu 'BigDecimal', um den Takt für die anderen Objekte nutzbar zu machen
    public BigDecimal berrechneFortschritt(){

        // 1. Berechne die aktuelle Echtzeit-Strecke der Sonne in diesem UI-Frame
        BigDecimal streckeZentralObjekt = BaGe3.multiply(DELTA_TIME, mc);

        // 2. Korrigierte Bedingung (Prüfung gegen 0)
        if(streckeZentralObjekt.compareTo(LICHT_GESCHWINDIGKEIT) >= 0) {
            Berechne = true;
        }

        // 3. Korrigierter physikalischer Taktgrad:
        // Zeit = Universelle Takt-Distanz / Aktuelle Geschwindigkeit der Sonne
        BigDecimal vergangeneSekunden = LICHT_GESCHWINDIGKEIT.divide(BaGe3, mc);

        return vergangeneSekunden; // Wert an die Simulationsschleife übergeben!
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
    public int counter = 0;
    public int counter2 = 0;
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
    public void calcZefo() {this.Zefo = GeKra.multiply(BaGe.multiply(BaGe)).divide(KreBa,40, RoundingMode.HALF_UP);}
    public void calcGeKra(){this.GeKra = Zefo.multiply(KreBa).divide(BaGe.multiply(BaGe), 40, RoundingMode.HALF_UP);}
    public void calcBaGe(){this.BaGe = Zefo.multiply(KreBa).divide(GeKra, 40, RoundingMode.HALF_UP).sqrt(mc);}
    public void calcKreBa(){this.KreBa = GeKra.multiply(BaGe.multiply(BaGe)).divide(Zefo, 40, RoundingMode.HALF_UP);}
    public void calcZefo2() {this.Zefo2 = GeKra2.multiply(BaGe2.multiply(BaGe2)).divide(KreBa2,40, RoundingMode.HALF_UP);}
    public void calcGeKra2(){this.GeKra2 = Zefo2.multiply(KreBa2).divide(BaGe2.multiply(BaGe2), 40, RoundingMode.HALF_UP);}
    public void calcBaGe2(){this.BaGe2 = Zefo2.multiply(KreBa2).divide(GeKra2, 40, RoundingMode.HALF_UP).sqrt(mc);}
    public void calcKreBa2(){this.KreBa2 = GeKra2.multiply(BaGe2.multiply(BaGe2)).divide(Zefo2, 40, RoundingMode.HALF_UP);}

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

    public void updateData(){
        strahlungsrechner.setZefo(this.Zefo);
        strahlungsrechner.setStrahlungsdruck(this.Strahlungsdruck);
        strahlungsrechner.setBaGe(this.BaGe);
        strahlungsrechner.setKreBa(this.KreBa);
        strahlungsrechner.setMaxBaGe(this.MaxBaGe);
        strahlungsrechner.setTemp(this.Temp);
        strahlungsrechner.setDichte(this.Dichte);
        strahlungsrechner.setVolumen(this.Volumen);
        strahlungsrechner.setGeKra(this.GeKra);

        // Vergiss nicht Objekt 2, falls der Strahlungsrechner das auch berechnet:
        strahlungsrechner.setZefo2(this.Zefo2);
        strahlungsrechner.setBaGe2(this.BaGe2);
        strahlungsrechner.setKreBa2(this.KreBa2);
        strahlungsrechner.setTemp2(this.Temp2);
        strahlungsrechner.setDichte2(this.Dichte2);
        strahlungsrechner.setVolumen2(this.Volumen2);
        strahlungsrechner.setGeKra2(this.GeKra2);

    }
    public void calcExpansionPossible(){
        // wann ist ein planet stabil wann explodiert oder zerbröselt dieser --->>>
        // Berrechnungswege-->
        // Stern komplette zentrifugalkraft
        BigDecimal GeKra3 = Stern.Sonne.GeKra3;
        BigDecimal BaGe3 = Stern.Sonne.BaGe3;
        BigDecimal KreBa3 = Stern.Sonne.KreBa3;
        BigDecimal Temp3 = Stern.Sonne.Temp3;
        BigDecimal Speed3 = Stern.Sonne.Speed3;
        BigDecimal Zefo3 = GeKra3.multiply(BaGe3.multiply(BaGe3)).divide(KreBa3,40, RoundingMode.HALF_UP);
        this.KreBa = strahlungsrechner.getKreBa();
        this.GeKra = strahlungsrechner.getGeKra();
        this.BaGe = strahlungsrechner.getBaGe();
        BigDecimal ZeFo = GeKra.multiply(BaGe.multiply(BaGe)).divide(KreBa,40, RoundingMode.HALF_UP);
        BigDecimal RelationZ = Zefo3.divide(ZeFo, mc);
        // --- RECHTES TERMINAL (Nur noch für wichtige Meilensteine) ---
        if(DELTA_TIME.multiply(BaGe, mc).compareTo(LICHT_GESCHWINDIGKEIT) >= 0 ){
            // Das hier wird an den bisherigen Text angehängt!
            javax.swing.SwingUtilities.invokeLater(() -> {
                Fenster.konsoleTextArea.append("[EREIGNIS]: Lichtsekunde vergangen " + DELTA_TIME + "\n");
            });
        }
        //Zeit in der Zentrifugalkraft wirkt [factorisierung!!!!]


        // 1. Berechne die aktuelle Echtzeit-Strecke der Sonne in diesem UI-Frame
        BigDecimal streckeZentralObjekt = BaGe3.multiply(DELTA_TIME, mc);

        // 2. Korrigierte Bedingung (Prüfung gegen 0)
        if(streckeZentralObjekt.compareTo(LICHT_GESCHWINDIGKEIT) >= 0) {
            Berechne = true;
        }

        // 3. Korrigierter physikalischer Taktgrad:
        // Zeit = Universelle Takt-Distanz / Aktuelle Geschwindigkeit der Sonne
        BigDecimal framesSonne = LICHT_GESCHWINDIGKEIT.divide(BaGe3, mc);
        BigDecimal framesObjekt1 = LICHT_GESCHWINDIGKEIT.divide(BaGe, mc);
        BigDecimal Objekt2frames = LICHT_GESCHWINDIGKEIT.divide(BaGe2, mc);
        Fenster.konsoleTextArea.append("\n ====================================== \n");
        Fenster.konsoleTextArea.append("\n Zu berrechnende Framesekunden \n" + framesSonne );
        Fenster.konsoleTextArea.append("\n Zu berrechnende Framesekunden Objekt1: \n" + framesObjekt1 );
        Fenster.konsoleTextArea.append("\n Zu berrechnende Framesekunden Objekt2: \n" + Objekt2frames );
        Fenster.konsoleTextArea.append("\n ====================================== \n");

        // --- LINKES TERMINAL (Berechnungen laufen im Fullspeed weiter) ---
        System.out.println("Check data \n" + Zefo3 + "\n" + RelationZ);

        // Allgemeine Infos oder Check-Daten schickst du ins LINKE Fenster (cmdLog)
        // Um alles in den richtigen berrechnungsschritten zeitlich in relation zu setzen wird abgegelichen und geprüft wenn die sonne
        // einmal die lichtgeschwindigkeit in metern zurückgelegt hat. um bei einem universum script welches später mal m,
        // mehrere sonnensysteme erfassen soll für entlastung der cpu sorgen dürfte, leider gibt es dann auch nur alle 22,2 minuten
        // eine kollisionsüberprüfung sowie prüfung auf mögliche swing bys kollisionen etc... und wenn diese prüfung möglichkeiten ergiebt erst zu berrechenn anstatt stetig dürfte ebenfalls die cpu nicht so grillen...



        System.out.println("Check data \n" + Zefo3 + "\n" + RelationZ + "\n" + Stern.Sonne.GeKra3 + "\n" + Stern.Sonne.BaGe3 + "\n" + Stern.Sonne.KreBa3 + "\n" + Stern.Sonne.Temp3 + "\n" + Stern.Sonne.Speed3);



        // in relation zu seinem bezugs stern. uff klingt nach ner neuen formel....
        // wenn Masse zu nahe an der sonne zuviel strahlungsdruck ausgesetzt ist,
        // muss diese beschleunigen und weiter weg um ihren energetischen ausgleich zu finden..
        // da man aber nur immerwieder auf seinen stern der einen extrudiert hat zurückfällt...
        // und jeweils gegenteiltag für die energien angesagt ist...
        // das heißt aufgrund von trägheitskräften sucht warme materie immer ihren erhalt und jagt der sonne nach...
        // heiße flächen zeigen also immer zur sonne bzw haben auch während ihres entstehens das bestreben dorthin zu wandern...
        // Während kalte wenn diese enstehen eigentlich immer von der sonne wegzeigen.. beim fallen zur sonne ensteht mehr thermische
        // energie als bei davon rasen von der Sonne da der Strahlungsdruck steigt.....
        // wenn ein objekt von der sonne davon rast kann der strahlungsdruck kurzeitig trotzdem so hochschnellen das er
        // Objektflächen erhitzt die von der Sonne wegzeigen, sowie es dann auch einen fläche gibt die zur sonne zeigt und trotzdem unterkühlt...
        // ... Was die berrechnung später noch komplexer macht
        // unser sonnensystem ist eine spieluhr voller strahlungsdrucklöcher da dort schon planeten gewirkt haben und für sich wie ein Räumschild
        // alles aufgesaugt haben was dort an energie ankam...
        // solche strahlungsdruck löcher haben menshcen auch mit atombomben geschaffen.. auch n witziges Thema




    }
    public void calcSwitching1(JTable data, DefaultTableModel model) {

        BigDecimal MidTemp = Puffer.Datasheet1.getMidTemp(); // Mittlere Temperatur beider Objekte laden aus vorheriger Berechnung
        BigDecimal MidTemp2 = Puffer.Datasheet1.getMidTemp();
        strahlungsrechner.setMidTemp(MidTemp);
        strahlungsrechner.setMidTemp2(MidTemp2);
        System.out.println("MidTemp geladen\n" + MidTemp + "Standard Temperatur:\n" + Temp + "MidTemp2 geladen\n" + MidTemp2 + "Standard Temperatur2:\n" + Temp2);
        BigDecimal Summe = Zefo.add(Zefo2); // Gesamtkräfte Relation herstellen
        BigDecimal TeilA = Zefo.divide(Summe, 40, RoundingMode.HALF_UP);
        BigDecimal TeilB = Zefo2.divide(Summe, 40, RoundingMode.HALF_UP);
        BigDecimal rhoRelation = Dichte.add(Dichte2); // Dichte Relation herstellen
        BigDecimal relation1 = Dichte.divide(rhoRelation, 40, RoundingMode.HALF_UP);
        BigDecimal relation2 = Dichte2.divide(rhoRelation, 40, RoundingMode.HALF_UP);
        BigDecimal newtonkreba = GeKra.divide(relation1, RoundingMode.HALF_UP);
        BigDecimal newtonkreba2 = GeKra2.divide(relation2, RoundingMode.HALF_UP);

        // Gegenseitig wirkende Fliehkräfte nach Relation bestimmen
        BigDecimal changeZefo = Zefo.multiply(TeilA, mc);
        BigDecimal newZefo2 = Zefo2.add(changeZefo, mc);
        BigDecimal New1ZeFo = Zefo.subtract(Zefo2, mc);

        BigDecimal newBaGe = New1ZeFo.multiply(KreBa).divide(GeKra, 40, RoundingMode.HALF_UP).sqrt(mc);
        BigDecimal newBaGe2 = newZefo2.multiply(KreBa).divide(GeKra, 40, RoundingMode.HALF_UP).sqrt(mc);

        strahlungsrechner.setMaxBaGe(newBaGe);

        strahlungsrechner.setBaGe2(newBaGe2);
        BigDecimal TempFactor1 = newBaGe.divide(BaGe, mc);
        BigDecimal TempFactor2 = newBaGe2.divide(BaGe2, mc);
        BigDecimal finalTemp = Temp.multiply(TempFactor1);
        BigDecimal finalTemp2 = Temp2.multiply(TempFactor2);
        strahlungsrechner.setMidTemp(finalTemp);
        strahlungsrechner.setMidTemp2(finalTemp2);
        this.Zefo = New1ZeFo;
        this.Zefo2 = newZefo2;
        strahlungsrechner.setZefo(this.Zefo);
        strahlungsrechner.setZefo2(this.Zefo2);

        BigDecimal de2 = new BigDecimal(2, mc);
        // Maximal Energien nach Kreisbahnradius lösen
        BigDecimal newKreBa1 = GeKra.multiply(BaGe.multiply(BaGe)).divide(New1ZeFo, 40, RoundingMode.HALF_UP);
        strahlungsrechner.setMaxKreBa(newKreBa1);
        BigDecimal newKreBa2 = GeKra2.multiply(BaGe2.multiply(BaGe2)).divide(newZefo2, 40, RoundingMode.HALF_UP);
        strahlungsrechner.setMaxKreBa2(newKreBa2);
        // MaximalGeschwindigkeiten nach Bahngeschwindigkeit lösen


        // Arithmetische Mittelwerte definieren
        BigDecimal midBaGe1 = newBaGe.add(BaGe).divide(de2, mc);
        strahlungsrechner.setMidBaGe(midBaGe1);
        BigDecimal midBaGe2 = newBaGe2.add(BaGe2).divide(de2, mc);
        strahlungsrechner.setMaxBaGe2(midBaGe2);
        BigDecimal midKreBa1 = newKreBa1.add(KreBa).divide(de2, mc);
        BigDecimal midKreBa2 = newKreBa2.add(KreBa2).divide(de2, mc);
        strahlungsrechner.setMidKreBa(midKreBa1);
        strahlungsrechner.setMidKreBa2(midKreBa2);
        BigDecimal llun = new BigDecimal(0, mc);

        if (MidKreBa.compareTo(llun) == 0) {
            this.BaGe = midBaGe1;
            strahlungsrechner.setMidBaGe(this.MidBaGe);
            this.MidKreBa = midKreBa1;
            strahlungsrechner.setMidKreBa(this.MidKreBa);
            strahlungsrechner.updateTable(tableRef);
        }

        if (newtonkreba.compareTo(newtonkreba2) > 0) {
            System.out.println("Der Wirkungsgrad des Strahlungsfaktors fuer Objekt 1 betraegt: \n" + Strahlungsdruck);
            System.out.println("Der Wirkungsgrad des Strahlungsfaktors fuer Objekt 2 betraegt: \n" + Strahlungsdruck2);
            System.out.println("Objekt 1: " + TeilA + "zu Objekt 2" + TeilB);
        } else if (newtonkreba.compareTo(newtonkreba2) < 0) {
            System.out.println("Der Wirkungsgrad des Strahlungsdruckfaktors fuer Objekt 1 betraegt: \n" + Strahlungsdruck);
            System.out.println("Der Wirkungsgrad des Strahlungsdruckfaktors fuer Objekt 2 betraegt: \n" + Strahlungsdruck2);
            System.out.println("Objekt 1: \n" + TeilA + "\nzu Objekt 2: \n" + TeilB);
        }

        strahlungsrechner.calcRad1Obj1Act(model);
        strahlungsrechner.calcRad2Obj2Act(model);

        while (Temp.compareTo(MidTemp) != 0 || Temp2.compareTo(MidTemp2) != 0) {
            for (int i = 0; i < 1000000; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Berechnung wurde vom User abgebrochen.");
                    return;
                }
            }

            this.Strahlungsdruck = strahlungsrechner.getStrahlungsdruck();
            this.Zefo = strahlungsrechner.getZefo();
            this.GeKra = strahlungsrechner.getGeKra();
            this.KreBa = strahlungsrechner.getKreBa();
            this.BaGe = strahlungsrechner.getBaGe();
            this.Temp = strahlungsrechner.getTemp();
            this.Dichte = strahlungsrechner.getDichte();
            this.Volumen = strahlungsrechner.getVolumen();
            this.Strahlungsdruck2 = strahlungsrechner.getStrahlungsdruck2();
            this.Zefo2 = strahlungsrechner.getZefo2();
            this.GeKra2 = strahlungsrechner.getGeKra2();
            this.KreBa2 = strahlungsrechner.getKreBa2();
            this.BaGe2 = strahlungsrechner.getBaGe2();
            this.Temp2 = strahlungsrechner.getTemp2();
            this.Dichte2 = strahlungsrechner.getDichte2();
            this.Volumen2 = strahlungsrechner.getVolumen2();
            this.MaxBaGe = strahlungsrechner.getMaxBaGe();
            this.MaxBaGe2 = strahlungsrechner.getMaxBaGe2();
            this.MaxKreBa = strahlungsrechner.getMaxKreBa();
            this.MaxKreBa2 = strahlungsrechner.getMaxKreBa2();

            // ==========================================
            // BLOCK 1: Objekt 1 - BaGe > MidBaGe
            // ==========================================
            if (BaGe.compareTo(MaxBaGe) > 0) { // Min Kreisbahn
                for (int i = 0; i < 1000000; i++) {
                    if (Thread.currentThread().isInterrupted()) { return; }
                }
                System.out.println("Richtung Orthsche Wolke");

                BigDecimal calc4 = this.Zefo.subtract(this.Strahlungsdruck, mc);
                BigDecimal way = this.GeKra.multiply(this.BaGe.multiply(this.BaGe)).divide(calc4, 40, RoundingMode.HALF_UP);
                BigDecimal r4speed22 = calc4.multiply(this.KreBa).divide(this.GeKra, 40, RoundingMode.HALF_UP).sqrt(mc);
                BigDecimal weight = calc4.multiply(this.KreBa).divide(this.BaGe.multiply(this.BaGe), 40, RoundingMode.HALF_UP);
                BigDecimal newRho = weight.divide(this.Volumen, mc);
                BigDecimal newVol = this.GeKra.divide(newRho, mc);
                BigDecimal neueMaxTemp = this.Temp.multiply(this.BaGe.divide(r4speed22, mc), mc);

                this.Dichte = newRho;
                this.Volumen = newVol;
                this.BaGe = r4speed22;
                this.KreBa = way;
                this.Temp = neueMaxTemp;
                strahlungsrechner.setTemp(neueMaxTemp);
                this.Zefo = calc4;

                updateData();
                strahlungsrechner.calcRad1Obj1Act(model);
                this.Strahlungsdruck = strahlungsrechner.getStrahlungsdruck();
                strahlungsrechner.updateTable(model);
                strahlungsrechner.updateTable(data);
            }

            // ==========================================
            // BLOCK 2: Objekt 1 - KreBa > MidKreBa
            // ==========================================
            if (KreBa.compareTo(MaxKreBa) > 0) { // Min Speed
                for (int i = 0; i < 1000000; i++) {
                    if (Thread.currentThread().isInterrupted()) { return; }
                }
                System.out.println("Max Speed Leveling errechnen");

                BigDecimal calc1 = this.Zefo.add(this.Strahlungsdruck);
                BigDecimal t2speed = calc1.multiply(this.KreBa).divide(this.GeKra, 40, RoundingMode.HALF_UP).sqrt(mc);
                BigDecimal way = this.GeKra.multiply(this.BaGe.multiply(this.BaGe), mc).divide(calc1, 40, RoundingMode.HALF_UP);
                BigDecimal weight = calc1.multiply(this.KreBa).divide(this.BaGe.multiply(this.BaGe), 40, RoundingMode.HALF_UP);
                BigDecimal newRho = weight.divide(this.Volumen, mc);
                BigDecimal newVol = this.GeKra.divide(newRho, mc);
                BigDecimal neueMaxTemp = this.Temp.multiply(t2speed.divide(this.BaGe, mc), mc);

                this.Dichte = newRho;
                this.Volumen = newVol;
                this.Temp = neueMaxTemp;
                strahlungsrechner.setTemp(neueMaxTemp);
                this.BaGe = t2speed;
                this.KreBa = way;
                this.Zefo = calc1;

                updateData();
                strahlungsrechner.calcRad1Obj1Act(model);
                this.Strahlungsdruck = strahlungsrechner.getStrahlungsdruck();
                strahlungsrechner.updateTable(model);
                strahlungsrechner.updateTable(data);
            }

            // ==========================================
            // BLOCK 3: Objekt 1 - KreBa < MidKreBa
            // ==========================================
            if (KreBa.compareTo(MaxKreBa) < 0) { // Min Speed
                for (int i = 0; i < 1000000; i++) {
                    if (Thread.currentThread().isInterrupted()) { return; }
                }
                System.out.println("Min Speed Leveling errechnen");

                BigDecimal calc2 = this.Zefo.subtract(this.Strahlungsdruck, mc);
                BigDecimal way = this.GeKra.multiply(this.BaGe.multiply(this.BaGe), mc).divide(calc2, 40, RoundingMode.HALF_UP);
                BigDecimal d3speed = calc2.multiply(this.KreBa).divide(this.GeKra, 40, RoundingMode.HALF_UP).sqrt(mc);
                BigDecimal weight = calc2.multiply(this.KreBa).divide(this.BaGe.multiply(this.BaGe), 40, RoundingMode.HALF_UP);
                BigDecimal newRho = weight.divide(this.Volumen, mc);
                BigDecimal newVol = this.GeKra.divide(newRho, mc);
                BigDecimal neueMaxTemp = this.Temp.multiply(this.BaGe.divide(d3speed, mc), mc);

                this.Dichte = newRho;
                this.Volumen = newVol;
                this.Temp = neueMaxTemp;
                strahlungsrechner.setTemp(neueMaxTemp);
                this.BaGe = d3speed;
                this.KreBa = way;
                this.Zefo = calc2;

                updateData();
                strahlungsrechner.calcRad1Obj1Act(model);
                this.Strahlungsdruck = strahlungsrechner.getStrahlungsdruck();
                strahlungsrechner.updateTable(model);
                strahlungsrechner.updateTable(data);
            }

            if (KreBa.compareTo(MaxKreBa) == 0) {
                System.out.println("Fertsch uh naja fast");
            }

            // ==========================================
            // BLOCK 4: Objekt 1 - BaGe < MidBaGe
            // ==========================================
            if (BaGe.compareTo(MaxBaGe) < 0) { // Max Kreisbahn
                for (int i = 0; i < 1000000; i++) {
                    if (Thread.currentThread().isInterrupted()) { return; }
                }
                System.out.println("Richtung Zentrum");

                BigDecimal calc3 = this.Zefo.add(this.Strahlungsdruck, mc);
                BigDecimal way = this.GeKra.multiply(this.BaGe.multiply(this.BaGe)).divide(calc3, 40, RoundingMode.HALF_UP);
                BigDecimal weight = calc3.multiply(this.KreBa).divide(this.BaGe.multiply(this.BaGe), 40, RoundingMode.HALF_UP);
                BigDecimal s3speed = calc3.multiply(this.KreBa, mc).divide(this.GeKra, 40, RoundingMode.HALF_UP).sqrt(mc);
                BigDecimal newRho = weight.divide(this.Volumen, mc);
                BigDecimal newVol = this.GeKra.divide(newRho, mc);
                BigDecimal neueMaxTemp = this.Temp.multiply(s3speed.divide(this.BaGe, mc), mc);

                this.Dichte = newRho;
                this.Volumen = newVol;
                this.Temp = neueMaxTemp;
                strahlungsrechner.setTemp(neueMaxTemp);
                this.KreBa = way;
                this.BaGe = s3speed;
                this.Zefo = calc3;

                updateData();
                strahlungsrechner.calcRad1Obj1Act(model);
                this.Strahlungsdruck = strahlungsrechner.getStrahlungsdruck();
                strahlungsrechner.updateTable(model);
                strahlungsrechner.updateTable(data);
            }

            if (KreBa2.compareTo(MaxKreBa2) == 0) {
                System.out.println("Fertsch uh naja fast");
            }

            // ==========================================
            // BLOCK 5: Objekt 2 - KreBa2 < MidKreBa2
            // ==========================================
            if (KreBa2.compareTo(MaxKreBa2) < 0) { // Max Speed
                for (int i = 0; i < 1000000; i++) {
                    if (Thread.currentThread().isInterrupted()) { return; }
                }
                System.out.println("Min Speed Leveling errechnen");

                BigDecimal calc5 = this.Zefo2.subtract(this.Strahlungsdruck2, mc);
                BigDecimal way22 = this.GeKra2.multiply(this.BaGe2.multiply(this.BaGe2), mc).divide(calc5, 40, RoundingMode.HALF_UP);
                BigDecimal t1speed22 = calc5.multiply(this.KreBa2).divide(this.GeKra2, 40, RoundingMode.HALF_UP).sqrt(mc);
                BigDecimal weight22 = calc5.multiply(this.KreBa2).divide(this.BaGe2.multiply(this.BaGe2), 40, RoundingMode.HALF_UP);
                BigDecimal newRho22 = weight22.divide(this.Volumen2, mc);
                BigDecimal newVol22 = this.GeKra2.divide(newRho22, mc);
                BigDecimal neueMaxTemp2 = this.Temp2.multiply(BaGe.divide(t1speed22, mc), mc);

                this.Dichte2 = newRho22;
                this.Volumen2 = newVol22;
                this.Temp2 = neueMaxTemp2;
                strahlungsrechner.setTemp2(neueMaxTemp2);
                this.BaGe2 = t1speed22;
                this.KreBa2 = way22;
                this.Zefo2 = calc5;

                updateData();
                strahlungsrechner.calcRad2Obj2Act(model);
                this.Strahlungsdruck2 = strahlungsrechner.getStrahlungsdruck2();
                strahlungsrechner.updateTable(model);
                strahlungsrechner.updateTable(data);
            }

            // ==========================================
            // BLOCK 6: Objekt 2 - KreBa2 > MidKreBa2
            // ==========================================
            if (KreBa2.compareTo(MaxKreBa2) > 0) { // Min Speed
                for (int i = 0; i < 1000000; i++) {
                    if (Thread.currentThread().isInterrupted()) { return; }
                }
                System.out.println("Max Speed Leveling errechnen");

                BigDecimal calc6 = this.Zefo2.add(this.Strahlungsdruck2);
                BigDecimal speed22 = calc6.multiply(this.KreBa2).divide(this.GeKra2, 40, RoundingMode.HALF_UP).sqrt(mc);
                BigDecimal way22 = this.GeKra2.multiply(this.BaGe2.multiply(this.BaGe2), mc).divide(calc6, 40, RoundingMode.HALF_UP);
                BigDecimal t1weight22 = calc6.multiply(this.KreBa2).divide(this.BaGe2.multiply(this.BaGe2), 40, RoundingMode.HALF_UP);
                BigDecimal newRho22 = t1weight22.divide(this.Volumen2, mc);
                BigDecimal newVol22 = this.GeKra2.divide(newRho22, mc);
                BigDecimal neueMaxTemp2 = this.Temp2.multiply(speed22.divide(BaGe, mc), mc);

                this.Dichte2 = newRho22;
                this.Volumen2 = newVol22;
                this.Temp2 = neueMaxTemp2;
                strahlungsrechner.setTemp2(neueMaxTemp2);
                this.BaGe2 = speed22;
                this.KreBa2 = way22;
                this.Zefo2 = calc6;

                updateData();
                strahlungsrechner.calcRad2Obj2Act(model);
                this.Strahlungsdruck2 = strahlungsrechner.getStrahlungsdruck2();
                strahlungsrechner.updateTable(model);
                strahlungsrechner.updateTable(data);
            }

            if (KreBa2.compareTo(MaxKreBa2) == 0) {
                System.out.println("Fertsch uh naja fast");
            }

            // ==========================================
            // BLOCK 7: Objekt 2 - BaGe2 < MidBaGe2
            // ==========================================
            if (BaGe2.compareTo(MaxBaGe2) < 0) { // Max Kreisbahn
                for (int i = 0; i < 1000000; i++) {
                    if (Thread.currentThread().isInterrupted()) { return; }
                }
                System.out.println("Richtung Zentrum");

                BigDecimal calc7 = this.Zefo2.add(this.Strahlungsdruck2, mc);
                BigDecimal way22 = this.GeKra2.multiply(this.BaGe2.multiply(this.BaGe2)).divide(calc7, 40, RoundingMode.HALF_UP);
                BigDecimal speed22 = calc7.multiply(this.KreBa2, mc).divide(this.GeKra2, 40, RoundingMode.HALF_UP).sqrt(mc);
                BigDecimal weight22 = calc7.multiply(this.KreBa2).divide(this.BaGe2.multiply(this.BaGe2), 40, RoundingMode.HALF_UP);
                BigDecimal newRho22 = weight22.divide(this.Volumen2, mc);
                BigDecimal newVol22 = this.GeKra2.divide(newRho22, mc);
                BigDecimal neueMaxTemp2 = this.Temp2.multiply(speed22.divide(BaGe2, mc), mc);

                this.Dichte2 = newRho22;
                this.Volumen2 = newVol22;
                this.Temp2 = neueMaxTemp2;
                strahlungsrechner.setTemp2(neueMaxTemp2);
                this.KreBa2 = way22;
                this.BaGe2 = speed22;
                this.Zefo2 = calc7;

                updateData();
                strahlungsrechner.calcRad2Obj2Act(model);
                this.Strahlungsdruck2 = strahlungsrechner.getStrahlungsdruck2();
                strahlungsrechner.updateTable(model);
                strahlungsrechner.updateTable(data);
            }

            // ==========================================
            // BLOCK 8: Objekt 2 - BaGe2 > MidBaGe2
            // ==========================================
            if (BaGe2.compareTo(MaxBaGe2) > 0) { // Min Kreisbahn
                for (int i = 0; i < 1000000; i++) {
                    if (Thread.currentThread().isInterrupted()) { return; }
                }
                System.out.println("Richtung Orthsche Wolke");

                BigDecimal calc8 = this.Zefo2.subtract(this.Strahlungsdruck2, mc);
                BigDecimal way22 = this.GeKra2.multiply(this.BaGe2.multiply(this.BaGe2)).divide(calc8, 40, RoundingMode.HALF_UP);
                BigDecimal f5speed22 = calc8.multiply(this.KreBa2).divide(this.GeKra2, 40, RoundingMode.HALF_UP).sqrt(mc);
                BigDecimal weight22 = calc8.multiply(this.KreBa2).divide(this.BaGe2.multiply(this.BaGe2), 40, RoundingMode.HALF_UP);
                BigDecimal newRho22 = weight22.divide(this.Volumen2, mc);
                BigDecimal newVol22 = this.GeKra2.divide(newRho22, mc);
                BigDecimal neueMaxTemp2 = this.Temp2.multiply(BaGe2.divide(f5speed22, mc), mc);

                this.Dichte2 = newRho22;
                this.Volumen2 = newVol22;
                this.Temp2 = neueMaxTemp2;
                strahlungsrechner.setTemp2(neueMaxTemp2);
                this.BaGe2 = f5speed22;
                this.KreBa2 = way22;
                this.Zefo2 = calc8;

                updateData();
                strahlungsrechner.calcRad2Obj2Act(model);
                this.Strahlungsdruck2 = strahlungsrechner.getStrahlungsdruck2();
                strahlungsrechner.updateTable(model);
                strahlungsrechner.updateTable(data);
            }

            if (KreBa2.compareTo(MaxKreBa2) == 0) {
                System.out.println("Fertsch uh naja fast");
            }
        }
    }

    public void calcRhoVergleich(DefaultTableModel model){
        System.out.println("\n******Rho Vergleiche******\n");
        //Dichte in Relattion zueinander Bestimmen
        BigDecimal Rhorelation = Dichte.add(Dichte2,mc);
        BigDecimal relRho1 = Dichte.divide(Rhorelation,40, RoundingMode.HALF_UP);
        BigDecimal relRho2 = Dichte2.divide(Rhorelation, 40, RoundingMode.HALF_UP);
        System.out.println("Die Relationen der Dichten zueinander betraegt: \n" +relRho1+ "\n" + relRho2+"\n");
        BigDecimal TempRel = Temp.add(Temp2,mc);
        BigDecimal relTemp1 = Temp.divide(TempRel,40, RoundingMode.HALF_UP);
        BigDecimal relTemp2 = Temp2.divide(TempRel,40, RoundingMode.HALF_UP);
        System.out.println("\nDie Relationen der Temperaturen zueinander betraegt: \n" +relTemp1+ "\n" + relTemp2+"\n");
        System.out.println("\nIm vergleich muss der Strahlungsdruck herausgerechnet werden da dieser sich auf beide Objekte unterschieldich auswirkt");
        System.out.println("\nObjekt1:\n" + "Dichte Relation:\n"+relRho1+ "\nTemperatur Relation:\n" +relTemp1+"\nObjekt2:\n" + "Dichte Relation:\n"+relRho2+ "\nTemperatur Relation:\n" +relTemp2);
        System.out.println("\nEs bedarf einer konstante welche den wert des Strahlungsdruckes bestimmt und in die relation mit einfliesen laesst");
        //Strahlungsdruck berechnen im quadrat zu entfernung des Kreiszentrums
        Strahlungsdruckkalkulation strahlungsrechner = new Strahlungsdruckkalkulation(this.tableRef, this.windowRef) {
            @Override
            public void calcRad1Obj1Act(DefaultTableModel model) {
                super.calcRad1Obj1Act(model);
            }

            @Override
            public void calcRad2Obj2Act(DefaultTableModel model) {
                super.calcRad2Obj2Act(model);
            }

            @Override
            public BigDecimal getStrahlungsdruck() {
                return super.getStrahlungsdruck();
            }

            @Override
            public void setStrahlungsdruck(BigDecimal strahlungsdruck) {
                super.setStrahlungsdruck(strahlungsdruck);
            }

            @Override
            public BigDecimal getStrahlungsdruck2() {
                return super.getStrahlungsdruck2();
            }

            @Override
            public void setStrahlungsdruck2(BigDecimal strahlungsdruck) {
                super.setStrahlungsdruck2(strahlungsdruck);
            }

        };
        strahlungsrechner.calcRad1Obj1Act(model);
        strahlungsrechner.calcRad2Obj2Act(model);
        this.Strahlungsdruck = strahlungsrechner.getStrahlungsdruck();
        this.Strahlungsdruck2 = strahlungsrechner.getStrahlungsdruck2();
        //Volumen durch Rho = Masse
        //Volumen durch Masse = rho
        //Masse mal rho = Volumen
    }
    // Später wird calcChangeValues durch eine Kollisionslogik ausgelöst.
    // if (Distance == 0){rechner.calcChanginValues}
    public void calcChanginValues(){
       calcZefo();
       calcZefo2();
       if(Zefo.compareTo(Zefo2) > 0){
           BigDecimal Summe;
           Summe = Zefo.add(Zefo2);
           BigDecimal TeilA = Zefo.divide(Summe, 40, RoundingMode.HALF_UP);
           BigDecimal TeilB = Zefo2.divide(Summe,40 , RoundingMode.HALF_UP);
           System.out.println("Relationen Der wirkenden Gesammtenergie(Zentrifugalkraefte) zueinander:  \n"+"TeilA: " + TeilA +"\nTeilB: "+ TeilB);
           if ((TeilA.compareTo(TeilB) >= 0) || (TeilA.compareTo(TeilB) <= 0)){
               BigDecimal rhoRelation = Dichte.add(Dichte2); //Relation herstellen
               BigDecimal relation1 = Dichte.divide(rhoRelation, 40, RoundingMode.HALF_UP); // Zentrifugalkraftsanteil der Überwunden werden muss um einfluss auf Objektradius zu erzielen
               BigDecimal relation2 = Dichte2.divide(rhoRelation, 40, RoundingMode.HALF_UP); // Zentrifugalkraftsanteil der überwunden werden muss um abzuprallen oder auf Objektradius zu erzielen
               System.out.println("\nRelationen der Dichte beider Objekte: \n" +relation1+ " rho\nObjekt2:\n" +relation2+ " rho");
               if(Dichte.compareTo(Dichte2) > 0){
                   System.out.println("\nDie Dichte von Objekt 1 ist Massehaltiger\nRelationen: \n" +relation1+ " rho\nObjekt2:\n" +relation2+ " rho");
               }else if(Dichte.compareTo(Dichte2) < 0){
                   System.out.println("\nDie Dichte von Objekt 2 ist Massehaltiger\nRelationen: \n" +relation1+ " rho\nObjekt2:\n" +relation2+ " rho");
               }else{
                   System.out.println("\nDie Dichte ist gleichstark");
               }
               BigDecimal SummeBaGe = BaGe.add(BaGe2);
               BigDecimal BaGerelation1 = BaGe.divide(SummeBaGe, 40, RoundingMode.HALF_UP);
               BigDecimal BaGerelation2 = BaGe2.divide(SummeBaGe, 40, RoundingMode.HALF_UP);
               System.out.println("\nRelationen der Geschwindigkeit beider Objekte: \n" +BaGerelation1+" m/s\nObjekt2:\n" +BaGerelation2+ " m/s");
               if(BaGe.compareTo(BaGe2) > 0){
                   System.out.println("Objekt1 ist Schneller");
               }else if(BaGe.compareTo(BaGe2) <0){
                   System.out.println("Objekt2 ist schneller");
               }else{
                   System.out.println("Beide Objekte gleich schnell");
               }
               BigDecimal Newtonkreba = Zefo.multiply(relation1); // Dichte wird zum Faktor für die gegenseitig einflusnahme der Zentrifugalkräfte im gesammten
               BigDecimal Newtonkreba2 = Zefo2.multiply(relation2);  //Soll im späteren Verlauf den Temperatur vergleich ersetzen Masse soll nur im Verhältnis zur jeweiligen Dichte wirken.
               //Differenz dieser Werte sollte automatisch einen werteunterschied ergeben..
               // allerdings denke ich werde ich dichte und masse in relationen setzen und miteinander factorisieren
               // und diese relation dann anwenden
               // ob die differenzen dem Verformungswert gleichgesetzt werden können ist fraglich....
               BigDecimal calcZefo =  Zefo.multiply(TeilA, mc); // Eigene wirkende Kraft
               System.out.println("\nObjekt1 uebertrifft Objekt 2 im Energetischen gehalt(Newton) um\n" +calcZefo+ " Newton");
               // Energie vom Objekt 2 von Objekt1 subtrahieren. wechselwirkung
               BigDecimal changeZefo =  Zefo.subtract(Zefo2, mc); // Kraft von Objekt 2 wird von Kraft Objekt 1 abgezogen
               //Neue Kreisbahn berechnen für Objekt 1
               BigDecimal newKreBa=  GeKra.multiply(BaGe.multiply(BaGe)).divide(changeZefo, 40, RoundingMode.HALF_UP);
               strahlungsrechner.setMaxKreBa(newKreBa);
               //wirkende Energie von Objekt 1 auf Objekt 2 addieren
               BigDecimal changeZefo2 = Zefo2.add(Zefo,mc); // Kraft vo Objekt 1 wird auf Objekt 2 übrtragen
               BigDecimal newKreBa2=  GeKra2.multiply(BaGe2.multiply(BaGe2)).divide(changeZefo2, 40, RoundingMode.HALF_UP);
               strahlungsrechner.setMaxKreBa2(newKreBa2);
               //MaximalGeschwindigkeiten nach Bahngeschwindigkeit lösen
               BigDecimal newBaGe=  changeZefo.multiply(KreBa).divide(GeKra, 40, RoundingMode.HALF_UP).sqrt(mc);
               BigDecimal newBaGe2=  changeZefo2.multiply(KreBa2).divide(GeKra2, 40, RoundingMode.HALF_UP).sqrt(mc);
               BigDecimal BaGeDiff = newBaGe.subtract(BaGe);
               BigDecimal BaGeDiff2 = newBaGe2.subtract(BaGe2);
               BigDecimal newMinBaGe = BaGe.subtract(BaGeDiff);
               BigDecimal newMinBaGe2 = BaGe2.subtract(BaGeDiff2);
               BigDecimal KreBaDiff = newKreBa.subtract(KreBa);
               BigDecimal KreBaDiff2 = newKreBa2.subtract(KreBa2);
               BigDecimal newMinKreBa = KreBa.subtract(KreBaDiff);
               BigDecimal newMinKreBa2 = KreBa2.subtract(KreBaDiff2);
               //letztlich zu erreichende arrhitmetische mittelwerte bestimmen Zeitpunkt abdem Max Temp erreicht.
               BigDecimal red2 = new BigDecimal(2,mc);
               BigDecimal midBaGe = newBaGe.add(BaGe).divide(red2,40,RoundingMode.HALF_UP); //noch falsch
               BigDecimal midBaGe2 = newBaGe2.add(BaGe2).divide(red2,40,RoundingMode.HALF_UP);
               BigDecimal midKreBa1 = newKreBa.add(KreBa).divide(red2,40,RoundingMode.HALF_UP);
               BigDecimal midKreBa2 = newKreBa2.add(KreBa2).divide(red2,40,RoundingMode.HALF_UP);
               //MinimalWerte KREISBAHN 1e Schwankung gegen Strahlungsdruck rechnen (Strahlungsdruck/m²)
               //MinimalWerte BAHNGESCHWINDIGKEIT
               BigDecimal r3 = (Volumen.multiply(KreisForm, mc));
               BigDecimal r4 = (Volumen2.multiply(KreisForm,mc));
               BigDecimal radObj1 = cbrt(r3, mc);
               BigDecimal radObj2 = cbrt(r4,mc);
               System.out.println("\nRadius des Objektes1: \n" +radObj1+ " m");
               System.out.println("Radius des Objektes2: \n" +radObj2+ " m");
               BigDecimal actArea1 = radObj1.multiply(radObj1,mc).multiply(PI,mc);
               BigDecimal actArea2 = radObj2.multiply(radObj2,mc).multiply(PI,mc);
               System.out.println("\nacting Area Object1: \n" +actArea1+ " m²");
               System.out.println("acting Area Object2: \n" +actArea2+ " m²");
               BigDecimal radForce1 = actArea1.multiply(Strahlungsdruck3,mc);
               BigDecimal radForce2 = actArea2.multiply(Strahlungsdruck3,mc);
               System.out.println("\nNeues Bahnminimum Objekt1: \n" +newMinKreBa+ "\nNeue minimale Bahngeschwindigkeit Obj.1: \n" +newMinBaGe);
               System.out.println("Neues Bahnminimum Objekt2: \n" +newMinKreBa2+ "\nNeue minimale Bahngeschwindigkeit Obj.2: \n" +newMinBaGe2);
               //Temperaturfactoren vorerst nach wenn geschwindigkeit plus 1 und kreisbahnradius plus 1 dann temp = BaGe * Kreba
               // Bzw Wenn Bage alt zu Bage neu * factor = Temp * factor
               BigDecimal TempFactor1 = newBaGe.divide(BaGe, mc);
               BigDecimal TempFactor2 = newBaGe2.divide(BaGe2, mc);
               BigDecimal finalTemp = Temp.multiply(TempFactor1);
               BigDecimal finalTemp2 = Temp2.multiply(TempFactor2);
               Puffer meinPuffer = Puffer.Datasheet1;
               BigDecimal MidTemp = finalTemp.divide(red2,40,RoundingMode.HALF_UP);
               meinPuffer.setMidTemp(MidTemp);
               BigDecimal MidTemp2 = finalTemp2.divide(red2,40,RoundingMode.HALF_UP);
               meinPuffer.setMidTemp(MidTemp2);
               // Wir laden die aktuelle Temperatur (this.Temp) in den Puffer
               System.out.println("***********************************************************\n");
               System.out.println("Fuer Objekt 1:\n" + "******************************************************\n" +
                       "Die Dichte " +Dichte+ "\nwird in dieser relation beeinflusst: " +TeilB+
                       "\ndas ergibt in Relation " +changeZefo+
                       " Zentrifugalen Wirkungsgrad (Newton) für Planet \nDie neu wirkende Zentrifugalkraft ist nun \n" +changeZefo+
                       "\n" +Zefo+ "\nWar der alte Wert fuer die Zentrifugalkraft");
               System.out.println("Neue Bahngeschwindigkeit \n" + newBaGe + " Meter/Sekunde"); //=maxTemp / 2; if max BaGe max Temp;
               System.out.println("\nNeue Kreisbahn \n" + newKreBa+ " Meter"); //= maxTemp/ 2; if max Kreba max Temp;
               System.out.println("\nDer Mittelwert fuer beides waere\n Bahngeschwindigkeit: " +midBaGe+ " Meter/Sekunde\nKreisbahnradius: "+midKreBa1);
               System.out.println("\nDie Temperatur des Objectes wuerde sich ver" +TempFactor1+ "-fachen");
               System.out.println("\nWas bei \n" + midKreBa1+ " Meter\nund einer Bahngeschwindigkeit von \n" +midBaGe+ " Meter/Sekunde\neine Temperatur von " +finalTemp+ " Kelvin ergeben sollte" );
               System.out.println("\ndie wirkende gegenkraft die den planeten1 zurueck in die umlaufbahn bremst betraegt: \n" +radForce1+ " Gramm \nbei einem Strahlungsdruck von: \n"+Strahlungsdruck3+ " g/m²");
               System.out.println("\n***********************************************************\n");
               System.out.println("\nFuer Objekt 2:\n"+ "******************************************************\n" +
                       "Die Dichte " +Dichte2+ "\nwird in dieser relation beeinflusst: " +TeilA+
                       "\ndas ergibt in Relation " +changeZefo2+
                       " Zentrifugalen Wirkungsgrad (Newton) für Mond \nDie neu wirkende Zentrifugalkraft ist nun \n" +changeZefo2+
                       " Newton\n" +Zefo2+ " Newton\nWar der alte Wert fuer die Zentrifugalkraft");
               System.out.println("Alte Kreisbahn: \n" +KreBa2);
               System.out.println("\nNeue Bahngeschwindigkeit: \n" + newBaGe2+ " Meter/Sekunde"); //=maxTemp / 2; if max BaGe max Temp;
               System.out.println("\nNeue Kreisbahn: \n" + newKreBa2+ " Meter"); //= maxTemp/ 2; if max Kreba max Temp;
               System.out.println("\nDer Mittelwert fuer beides waere\n Bahngeschwindigkeit: " +midBaGe2+ " Meter/Sekunde\nKreisbahnradius: "+midKreBa2 + " Meter");
               System.out.println("\nDie Temperatur des Objectes wuerde sich ver" +TempFactor2+ "-fachen\n");
               System.out.println("\nWas bei \n" + midKreBa2+ " Meter\nund einer Bahngeschwindigkeit von \n" +midBaGe2+ " Meter/Sekunde\neine Temperatur von " +finalTemp2+ " Kelvin ergeben sollte" );
               System.out.println("\ndie wirkende gegenkraft die den planeten2 zurueck in die umlaufbahn bremst betraegt: \n" +radForce2+ " Gramm \nbei einem Strahlungsdruck von: \n"+Strahlungsdruck3+ " g/m² ");
               if(Temp.compareTo(finalTemp) < 0){
                   System.out.println("\nEnergien werden berechnet und ausgeglichen von: \n" +Temp+ "\n bis \n" +finalTemp+ " fuer Objekt1!");
                   System.out.println("\nEnergien werden berechnet und ausgeglichen von: \n" +Temp2+ "\n bis \n" +finalTemp2+ " fuer Objekt2!");
               }// TempMax
           }
           else if(Zefo.compareTo(Zefo2) == 0){
               Summe = Zefo.add(Zefo2);
               TeilA = Zefo.divide(Summe, 40, RoundingMode.HALF_UP);
               TeilB= Zefo2.divide(Summe,40 , RoundingMode.HALF_UP);
               System.out.println("TeilA: " + TeilA +"\nTeilB: "+ TeilB);
               System.out.println("Das System ist gleichstark");
           }

       }
    }
    public void updateTable(DefaultTableModel model) {

        BigDecimal Summe = Zefo.add(Zefo2);
        BigDecimal TeilA = Zefo.divide(Summe, 40, RoundingMode.HALF_UP);
        BigDecimal TeilB = Zefo2.divide(Summe,40 , RoundingMode.HALF_UP);
        model.setValueAt(Zefo, 2, 0);
        model.setValueAt(GeKra, 2, 1);
        model.setValueAt(BaGe, 2, 2);
        model.setValueAt(KreBa, 2, 3);
        model.setValueAt(Temp, 2, 4);
        model.setValueAt(Speed,2,5);
        model.setValueAt(Dichte,2,6);
        model.setValueAt(Volumen,2,7);
        model.setValueAt(Strahlungsdruck, 2,8);
        model.setValueAt(TeilA,2,9);
        model.setValueAt(Strahlungsdruck3,2 ,10);
        model.setValueAt(TeilB,2,11);
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
        model.setValueAt(MidKreBa,2,22);
        model.setValueAt(MidBaGe,2,23);
        model.setValueAt(MidTemp2,2,24);
        model.setValueAt(MidKreBa2,2,25);
        model.setValueAt(MidBaGe2,2,26);
        model.setValueAt(MidTemp3,2,27);
        model.setValueAt(MaxKreBa,2,28);
        model.setValueAt(MaxKreBa2,2,29);

        // --- NEU: Zustände live vor dem Tabellen-Eintrag bestimmen ---
        String zustandObjekt1 = bestimmeObjektZustand(Temp);
        String zustand2Object2 = bestimmeObjektZustand(Temp2);

        // Vorher stand hier "" für TeilA und TeilB, jetzt setzen wir die Zustände ein!
        Object[] neueZeile = {
                Zefo, GeKra, BaGe, KreBa, Temp, Speed, Dichte, Volumen, Strahlungsdruck,
                zustandObjekt1, // Ersetzt die vorherige leere Spalte für Objekt 1
                Strahlungsdruck3,
                zustand2Object2, // Ersetzt die vorherige leere Spalte für Objekt 2
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
