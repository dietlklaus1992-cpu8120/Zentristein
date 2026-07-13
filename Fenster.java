import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.MathContext;
import javax.swing.text.DefaultCaret;
import javax.swing.SwingUtilities;

public class Fenster {
    private static Thread workerThread = null;
    private JButton ZurArbeit;

    private static final BigDecimal NANO_TO_SEC = new BigDecimal("1000000000");
    static long lastTime = System.nanoTime();
    static long now = System.nanoTime();
    static long diff = now - lastTime;

    // Das hier wird GENAU EINMAL beim Programmstart berechnet und bleibt dann immer gleich!
    static BigDecimal deltaTime = new BigDecimal(diff)
            .divide(NANO_TO_SEC, MathContext.DECIMAL128);

    // Ändere die getDeltaTime() Methode in Fenster.java genau so um:
    public static BigDecimal getDeltaTime() {
        long aktuellerAblauf = System.nanoTime();
        long vergangeneDiff = aktuellerAblauf - lastTime; // Berechnet die echte Differenz seit Start

        return new BigDecimal(vergangeneDiff)
                .divide(NANO_TO_SEC, MathContext.DECIMAL128);
    }

    public void setDeltaTime(double deltaTime) {
        this.deltaTime = BigDecimal.valueOf(deltaTime);
    }
    public static int lastColumn = -1;
    public static int lastColumn2 = -1;
    public static int secondLastColumn = -1;
    public static int secondLastColumn2= -1;
    public KollisionsGraph kollisionsAnzeige;
    public static PrintStream cmdLog;      // Für das LINKE Fenster
    public static PrintStream dtLog;
    public static JTextArea konsoleTextArea;


    public static void ladeDatenInTabelle(Himmelskoerper koerper, DefaultTableModel model) {
        // Wir setzen die Werte in Zeile 1 (die editierbare Zeile unter den Headern)
        model.setValueAt(koerper.getZefo().toString(), 2, 0);
        model.setValueAt(koerper.getGeKra().toString(), 2, 1);
        model.setValueAt(koerper.getBaGe().toString(), 2, 2);
        model.setValueAt(koerper.getKreBa().toString(), 2, 3);
        model.setValueAt(koerper.getTemp().toString(), 2,4);
        model.setValueAt(koerper.getSpeed().toString(), 2,5);
        model.setValueAt(koerper.getDichte().toString(), 2,6);
        model.setValueAt(koerper.getVolumen().toString(),2,7);
        model.setValueAt(koerper.getStrahlungsdruck().toString(),2,8);
        model.setValueAt(koerper.getMidTemp().toString(),2,21);
        model.setValueAt(koerper.getMidKreBa().toString(),2,22);
        model.setValueAt(koerper.getMidBaGe().toString(),2,23);

        // Optional: Falls du die restlichen Daten (Temp, Speed, Dichte)
        // auch anzeigen willst, müsstest du die Tabelle um Spalten erweitern.
        System.out.println("Daten geladen fuer: " + koerper.toString());
    }
    public static void ladeMondInTabelle(Himmelskoerper mond, DefaultTableModel model) {
        // Wir setzen die Werte in Zeile 1 (die editierbare Zeile unter den Headern)
        model.setValueAt(mond.getZefo().toString(), 2,12);
        model.setValueAt(mond.getGeKra().toString(), 2,13);
        model.setValueAt(mond.getBaGe().toString(), 2,14);
        model.setValueAt(mond.getKreBa().toString(), 2,15);
        model.setValueAt(mond.getTemp().toString(), 2,16);
        model.setValueAt(mond.getSpeed().toString(), 2,17);
        model.setValueAt(mond.getDichte().toString(), 2,18);
        model.setValueAt(mond.getVolumen().toString(),2,19);
        model.setValueAt(mond.getStrahlungsdruck().toString(),2,20);
        model.setValueAt(mond.getMidTemp().toString(),2,24);
        model.setValueAt(mond.getMidKreBa().toString(),2,25);
        model.setValueAt(mond.getMidBaGe().toString(),2,26);

        // Optional: Falls du die restlichen Daten (Temp, Speed, Dichte)
        // auch anzeigen willst, müsstest du die Tabelle um Spalten erweitern.
        System.out.println("Daten geladen fuer: " + mond.toString());
    }
    public static void ladeSonneInTabelle(Himmelskoerper Sonne, DefaultTableModel model) {
        // Wir setzen die Werte in Zeile 1 (die editierbare Zeile unter den Headern)
        //model.setValueAt(Sonne.getZefo().toString(), 8,12);
        //model.setValueAt(Sonne.getGeKra().toString(), 8,13);
        //model.setValueAt(Sonne.getBaGe().toString(), 8,14);
        //model.setValueAt(Sonne.getKreBa().toString(), 8,15);
        //model.setValueAt(Sonne.getTemp().toString(), 8,16);
        //model.setValueAt(Sonne.getSpeed().toString(), 8,17);
        //model.setValueAt(Sonne.getDichte().toString(), 8,18);
        //model.setValueAt(Sonne.getVolumen().toString(),8,19);
        model.setValueAt(Sonne.getStrahlungsdruck().toString(),2,10);
        model.setValueAt(Sonne.getMidTemp().toString(),2,27);
        //model.setValueAt(Sonne.getMidKreBa().toString(),2,28);
        //model.setValueAt(Sonne.getMidBaGe().toString(),2,29);


        // Optional: Falls du die restlichen Daten (Temp, Speed, Dichte)
        // auch anzeigen willst, müsstest du die Tabelle um Spalten erweitern.
        System.out.println("Daten geladen fuer: " + Sonne.toString());
    }
    public Fenster() {

        java.util.List<ZentrifugationBigDecimal> berechnungsListe = new java.util.ArrayList<>();
        JFrame frame = new JFrame();
        JPanel tabelle = new JPanel();
        JButton function = new JButton("Zentrifugalkraft");
        JButton function1 = new JButton("Gewicht");
        JButton function2 = new JButton("Bewegungsgeschwindigkeit");
        JButton function3 = new JButton("Radius");
        JPanel buttonset = new JPanel();


        DefaultTableModel model = new DefaultTableModel(0, 31); // 0 bedeutet: wir starten ohne Datenzeilen

        model.addRow(new Object[]{"Zentrifugalkraft", "Gewichtskraftm", "Geschwindigkeit", "Radius", "Temperatu", "Rotat.Speed", "Dichte", "Volumen", "Strahlungsdruck", "Relation1", "Strahlungsdruck", "Relation2", "Zentrifugalkraft2", "Gewichtskraft2", "Geschwindigkeit2", "Radius2", "Temperatur2", "Rotat.Speed2", "Dichte2", "Volumen2", "Strahlungsdruck", "", "", "", "", "", "", "", "", "", "", ""});
        model.addRow(new Object[]{"     Newton", " Kilogramm", "    m/s   ", " Meter", "  Kelvin", "   m/s   ", "  Kg/V ", "   m³  ", "     g/m    ", "Relation1", " OrbitalZentrum gramm   ", "Relation2", "  Newton    ", " Kilogramm    ", "       m/s      ", " Meter ", "  Kelvin   ", "    m/s     ", "  Kg/V ", "   m³   ", "    g/m    ", "MidTemp", "ActTemp1", "ActTemp2", "MidTemp2", "MidKreBa2", "MidBaGe2", "MidTemp3", "MaxRadObj1", "MaxRadObj2", "MaxBaGe2"});
        model.addRow(new Object[]{"", "", "", ""});
        JTable data = new JTable(model);
        JComboBox<Planet> planetenBox = new JComboBox<>(Planet.values());
        buttonset.add(planetenBox);
        JButton resetBtn = new JButton("Simulation zurücksetzen");

// 2. Den Button farblich etwas abheben (optional)
        resetBtn.setBackground(new Color(180, 50, 50));
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFont(new Font("SansSerif", Font.BOLD, 12));

// Button zum Laden der Daten
        JButton loadButton = new JButton("Daten laden");
        buttonset.add(loadButton);

        loadButton.addActionListener(e -> {
            Planet gewaehlterPlanet = (Planet) planetenBox.getSelectedItem();
            if (gewaehlterPlanet != null) {
                ladeDatenInTabelle(gewaehlterPlanet, model);
                data.repaint();
            }
        });
        JComboBox<Mond> mondenBox = new JComboBox<>(Mond.values());
        JComboBox<Stern> sternenBox = new JComboBox<>(Stern.values());
        buttonset.add(mondenBox, BorderLayout.LINE_END);
        buttonset.add(sternenBox, BorderLayout.LINE_END);

        JButton loadStern = new JButton("Sterndaten Laden");
        buttonset.add(loadStern, BorderLayout.AFTER_LAST_LINE);
        loadStern.addActionListener(e -> {
            // 1. Hole den ausgewählten Stern aus der Box
            Stern gewaehlteSonne = (Stern) sternenBox.getSelectedItem();

            if (gewaehlteSonne != null) {
                // 2. Weise ihn deiner Klassenvariable zu, falls du sie später brauchst
                // Lädt in Spalten 12+
                ladeSonneInTabelle(gewaehlteSonne, model); // Lädt in Zeile 8 (Sonne)

                data.repaint();
            }
        });
        JButton loadMond = new JButton("Monddaten Laden");
        buttonset.add(loadMond, BorderLayout.AFTER_LAST_LINE);
        loadMond.addActionListener(e -> {
            Mond gewaehlterMond = (Mond) mondenBox.getSelectedItem();

            if (gewaehlterMond != null) {
                ladeMondInTabelle(gewaehlterMond, model);
                data.repaint();
            }
        });
        JButton wackeln = new JButton("AnnäherungbisMax");
        buttonset.add(wackeln, BorderLayout.AFTER_LAST_LINE);
        wackeln.addActionListener(e -> {
            //BigDecimal energie = (BigDecimal) data.getValueAt( 1,0);
            //BigDecimal plus = new BigDecimal("1000000000000"); // später schnittstelle für kollisonseingabe und logik
            //BigDecimal ergebnis = energie.add(plus);
            //data.setValueAt(ergebnis, 1,0);
            data.repaint();
            ZentrifugationBigDecimal rechner = new ZentrifugationBigDecimal(data, this);
            rechner.calcChanginValues();
            data.repaint();


        });
        ZurArbeit = new JButton("Akkumulationsgleichung");
        buttonset.add(ZurArbeit, BorderLayout.AFTER_LAST_LINE);

        ZurArbeit.addActionListener(e -> {
            // Falls bereits ein Thread läuft -> Stopp-Logik
            if (workerThread != null && workerThread.isAlive()) {
                workerThread.interrupt(); // Signal zum Abbrechen senden
                ZurArbeit.setEnabled(false); // Kurz deaktivieren, um Spam zu verhindern
                return;
            }

            // Start-Logik
            ZurArbeit.setText("Stoppen");

            workerThread = new Thread(() -> {
                try {
                    ZentrifugationBigDecimal rechner = new ZentrifugationBigDecimal(data, this);

                    // WICHTIG: Deine Rechenmethode muss auf Interrupts prüfen!
                    rechner.calcSwitching1(data, model);


                    SwingUtilities.invokeLater(() -> {
                        if (data.getModel() instanceof DefaultTableModel) {
                            rechner.updateTable((DefaultTableModel) data.getModel());
                        }
                        finishWork();
                    });

                } catch (Exception ex) {
                    ex.printStackTrace();
                    SwingUtilities.invokeLater(() -> finishWork());
                }
            });

            workerThread.start();
            data.revalidate();
            data.repaint();
        });


// Hilfsmethode zum Zurücksetzen des UI-Zustands


        JButton rhovergleich = new JButton("Dichte zu Temperatur \n" + "Vergleich");
        buttonset.add(rhovergleich, BorderLayout.AFTER_LAST_LINE);
        rhovergleich.addActionListener(e -> {
            ZentrifugationBigDecimal rechner = new ZentrifugationBigDecimal(data, this);
            Strahlungsdruckkalkulation pressSun = new Strahlungsdruckkalkulation(data, this);
            rechner.calcRhoVergleich(model);
            pressSun.calcRad1Obj1Act(model);
            pressSun.calcRad2Obj2Act(model);

            if (data.getModel() instanceof DefaultTableModel) {
                rechner.updateTable((DefaultTableModel) data.getModel());
            }


            data.revalidate();
            data.repaint();


        });
        function.addActionListener(e -> {
            ZentrifugationBigDecimal rechner = new ZentrifugationBigDecimal(data, this);

            // 2. Die spezifische Logik ausführen
            rechner.calcZefo();
            rechner.calcZefo2();
            rechner.calcExpansionPossible();
            Fenster.secondLastColumn = Fenster.lastColumn;
            Fenster.lastColumn = 0;
            Fenster.secondLastColumn2 = Fenster.lastColumn2;
            // Der neue "letzte" ist der Radius (Spalte 3)

            Fenster.lastColumn2 = 12;
            // 3. Werte zurückgeben (und an die Liste schicken)
            rechner.updateTable((DefaultTableModel) data.getModel());
            data.repaint();
            // HIER kannst du es jetzt an deine Liste weiterreichen:
            berechnungsListe.add(rechner);

            System.out.println("Zentrifugalkraft fuer Objekt1 wurde berechnet: " + rechner.Zefo + " Newton");
            System.out.println("Zentrifugalkraft fuer Object2 wurde berechnet: " + rechner.Zefo2 + " Newton");
        });
        function1.addActionListener(e -> {
            ZentrifugationBigDecimal rechner = new ZentrifugationBigDecimal(data, this);

            // 2. Die spezifische Logik ausführen
            rechner.calcGeKra();
            rechner.calcGeKra2();
            Fenster.secondLastColumn = Fenster.lastColumn;
            // Der neue "letzte" ist der Radius (Spalte 3)
            Fenster.lastColumn = 1;
            Fenster.secondLastColumn2 = Fenster.lastColumn2;
            Fenster.lastColumn2 = 13;
            // 3. Werte zurückgeben (und an die Liste schicken)
            rechner.updateTable((DefaultTableModel) data.getModel());
            data.repaint();
            // HIER kannst du es jetzt an deine Liste weiterreichen:
            berechnungsListe.add(rechner);

            System.out.println("Masse wurde berechnet " + rechner.GeKra);
        });
        function2.addActionListener(e -> {
            ZentrifugationBigDecimal rechner = new ZentrifugationBigDecimal(data, this);

            // 2. Die spezifische Logik ausführen
            rechner.calcBaGe();
            rechner.calcBaGe2();
            Fenster.secondLastColumn = Fenster.lastColumn;
            Fenster.lastColumn = 2;
            Fenster.secondLastColumn2 = Fenster.lastColumn2;
            // Der neue "letzte" ist der Radius (Spalte 3)

            Fenster.lastColumn2 = 14;

            // 3. Werte zurückgeben (und an die Liste schicken)
            rechner.updateTable((DefaultTableModel) data.getModel());

            // HIER kannst du es jetzt an deine Liste weiterreichen:
            berechnungsListe.add(rechner);
            data.repaint();
            System.out.println("Bahngeschwindigkeit wurde ermittelt: " + rechner.BaGe);
        });
        function3.addActionListener(e -> {
            if (data.isEditing()) data.getCellEditor().stopCellEditing();

            // 1. Einmal instanziieren (holt sich alle aktuellen Werte)
            ZentrifugationBigDecimal rechner = new ZentrifugationBigDecimal(data, this);

            // 2. Die spezifische Logik ausführen
            rechner.calcKreBa();
            rechner.calcKreBa2();
            // ZUSTANDS-UPDATE:
            // Der alte "letzte" wird der "vorletzte"
            Fenster.secondLastColumn = Fenster.lastColumn;
            Fenster.lastColumn = 3;
            Fenster.secondLastColumn2 = Fenster.lastColumn2;
            // Der neue "letzte" ist der Radius (Spalte 3)
            Fenster.lastColumn2 = 15;
            // 3. Werte zurückgeben (und an die Liste schicken)
            rechner.updateTable((DefaultTableModel) data.getModel());

            // HIER kannst du es jetzt an deine Liste weiterreichen:
            berechnungsListe.add(rechner);
            data.repaint();
            System.out.println("Radius wurde generiert: " + rechner.KreBa);
        });
        resetBtn.addActionListener(e -> {
            // 1. Simulation stoppen, falls sie läuft
            if (workerThread != null && workerThread.isAlive()) {
                workerThread.interrupt();
                workerThread = null;


            }
            finishWork(); // Setzt deinen "Zur Arbeit"-Button wieder auf den Starttext

            // 2. Tabellendaten komplett zurücksetzen
            // Behält die Header (Spaltennamen), löscht aber alle Zeilen ab Zeile 3
            // VORHER: DefaultTableModel model = (DefaultTableModel) data.getModel();
// NACHHER: Wir nennen die Variable einfach anders, um dem Konflikt zu entgehen!

            DefaultTableModel resetModel = (DefaultTableModel) data.getModel(); // 'data' ist deine JTable
            while (resetModel.getRowCount() > 3) {
                resetModel.removeRow(3);
            }

            // 3. Den KollisionsGraphen leeren
            if (kollisionsAnzeige != null) {
                kollisionsAnzeige.clearGraph(); // Diese Methode bauen wir gleich im Graphen ein
            }

            // 4. PHYSIK-RESET: Werte der Enums auf Startzustand setzen
            // Da sich die Temperaturen im Interface geändert haben, überschreiben wir sie wieder mit den Standardwerten
            for (Planet p : Planet.values()) {
                // Hier die Standard-Kelvin/Temperatur eurer Enums eintragen (Beispiel Erde: 288.15)
                if (p == Planet.ERDE) p.setTemp(new BigDecimal("288.15"));
                if (p == Planet.MERKUR) p.setTemp(new BigDecimal("440.15"));
                if (p == Planet.VENUS) p.setTemp(new BigDecimal("737.15"));
                if (p == Planet.MARS) p.setTemp(new BigDecimal("208.15"));
                // ... falls nötig auch für andere Werte
            }

            for (Mond m : Mond.values()) {
                if (m == Mond.ERDMOND) m.setTemp(new BigDecimal("215.15"));
                // ...
            }

            if (Stern.Sonne != null) {
                Stern.Sonne.setTemp(new BigDecimal("15699726.85"));
            }

            // 5. UI aktualisieren, damit die Startwerte wieder in Zeile 1 stehen
            // Hier einfach die Methode aufrufen, mit der du am Anfang die Körper reingeladen hast, z.B.:
            // ladeDatenInTabelle(aktuellerKoerper1, model);

            System.out.println("--- Simulation erfolgreich zurückgesetzt ---");
        });
        // ==========================================
        // TERMINAL LINKS: CMD-Look (Berechnungen & System.out)
        // ==========================================
        JTextArea consoleOutput = new JTextArea(10, 50);
        consoleOutput.setEditable(false); // Nutzer soll nicht drin rumtippen
        consoleOutput.setBackground(Color.BLACK); // CMD Look
        consoleOutput.setForeground(Color.GREEN);
        JScrollPane consoleScroll = new JScrollPane(consoleOutput);

        // Wir erstellen den linken Stream und setzen ihn als STANDARD für das System
        cmdLog = new PrintStream(new CustomOutputStream(consoleOutput), true);
        System.setOut(cmdLog);
        System.setErr(cmdLog); // Auch Fehlermeldungen landen links im CMD-Look

        // ==========================================
        // TERMINAL RECHTS: Nur DeltaTime & Lichtsekunden
        // ==========================================
        konsoleTextArea = new JTextArea();
        konsoleTextArea.setEditable(false);
        konsoleTextArea.setBackground(new Color(15, 15, 30)); // Dunkler Weltraum-Look
        konsoleTextArea.setForeground(Color.CYAN); // Cyan für Zeitdaten
        JScrollPane console5Scroll4 = new JScrollPane(konsoleTextArea);

        // Dieser Stream wird NICHT auf System.setOut gelegt,
        // sondern bleibt separat über Fenster.dtLog erreichbar!
        dtLog = new PrintStream(new CustomOutputStream(konsoleTextArea), true);

        // ==========================================
        // TABELLEN- & GRAPH-SETUP (Bleibt wie bei dir)
        // ==========================================
        JScrollPane scrollPane = new JScrollPane(data);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        data.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        data.setCellSelectionEnabled(true);
        StatusColorRenderer renderer = new StatusColorRenderer();
        for (int i = 0; i < data.getColumnCount(); i++) {
            data.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        kollisionsAnzeige = new KollisionsGraph();
        kollisionsAnzeige.setPreferredSize(new Dimension(2500, 250));
        JScrollPane graphScrollPane = new JScrollPane(kollisionsAnzeige);
        graphScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        graphScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        graphScrollPane.setPreferredSize(new Dimension(0, 270));

        frame.add(graphScrollPane, BorderLayout.NORTH);

        // ==========================================
        // DIE SPLITPANE-VERSCHACHTELUNG (Korrektur!)
        // ==========================================

        // 1. Wir packen die beiden Terminals NEBENANDER (Horizontal Split)
        JSplitPane terminalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, consoleScroll, console5Scroll4);
        terminalSplit.setDividerLocation(700); // Teilt den Platz mittig bei einer 1400er Breite

        // 2. Wir packen die Tabelle OBEN und das Terminal-Duo UNTEN hin (Vertical Split)
        JSplitPane mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, terminalSplit);
        mainSplit.setDividerLocation(400);

        // 3. Das Haupt-SplitPane dem Frame hinzufügen
        frame.add(mainSplit, BorderLayout.CENTER);

        // ==========================================
        // BUTTON-PANEL & FINALE ANZEIGE
        // ==========================================
        buttonset.add(resetBtn, BorderLayout.EAST);
        buttonset.add(function, BorderLayout.NORTH);
        buttonset.add(function1, BorderLayout.CENTER);
        buttonset.add(function2, BorderLayout.WEST);
        buttonset.add(function3, BorderLayout.LINE_END);

        scrollPane.setVisible(true);
        scrollPane.setSize(1400, 400);
        buttonset.setVisible(true);
// ==========================================
        // KONTINUIERLICHER LIVE-CLOCK-TIMER (NEU)
        // ==========================================
        javax.swing.Timer uiClockTimer = new javax.swing.Timer(50, e -> {
            BigDecimal aktuelleZeit = Fenster.getDeltaTime();

            if (aktuelleZeit != null && Fenster.konsoleTextArea != null) {
                String ganzerText = Fenster.konsoleTextArea.getText();
                String uhrzeitKopf = "=== SYSTEM LIVE CLOCK ===\n" +
                        "Status: Simulation läuft...\n" +
                        "Aktuelle DeltaTime: " + aktuelleZeit.setScale(6, java.math.RoundingMode.HALF_UP).toPlainString() + " Sek.\n" +
                        "---------------------------------\n";

                // Wenn die TextArea leer ist oder nur die Uhr lief, setzen wir sie neu auf
                if (!ganzerText.contains("---------------------------------")) {
                    Fenster.konsoleTextArea.setText(uhrzeitKopf);
                } else {
                    // Wir trennen die alte Uhr vom restlichen gestreamten Log ab
                    int trennIndex = ganzerText.indexOf("---------------------------------\n");
                    String logHistorie = ganzerText.substring(trennIndex + "---------------------------------\n".length());

                    // Setzt die Uhr frisch oben drauf, behält den gestreamten Log darunter bei!
                    Fenster.konsoleTextArea.setText(uhrzeitKopf + logHistorie);
                }
            }
        });
        uiClockTimer.start();
        // Verhindert, dass der Cursor bei jedem Update wild herumspringt und sorgt für sauberes Scrollen
        if (Fenster.konsoleTextArea != null) {
            ((javax.swing.text.DefaultCaret) Fenster.konsoleTextArea.getCaret()).setUpdatePolicy(javax.swing.text.DefaultCaret.NEVER_UPDATE);
        }
        frame.add(buttonset, BorderLayout.SOUTH);
        frame.setSize(1400, 900);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void finishWork() {
        Fenster.this.ZurArbeit.setText("Zur Arbeit");
        Fenster.this.ZurArbeit.setEnabled(true);
        workerThread = null;
    }



}
