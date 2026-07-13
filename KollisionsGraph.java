import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class KollisionsGraph extends JPanel {
    // Datenverlauf für Objekt 1 (z.B. Erde/Objekt 1)
    private final List<Double> abstandVerlauf = new ArrayList<>();
    private final List<Double> tempVerlauf = new ArrayList<>();

    // NEU: Datenverlauf für Objekt 2
    private final List<Double> abstandVerlauf2 = new ArrayList<>();
    private final List<Double> tempVerlauf2 = new ArrayList<>();

    private double maximalerAbstand = 0;
    private double minimalerAbstand = Double.MAX_VALUE;
    public void clearGraph() {
        abstandVerlauf.clear();
        tempVerlauf.clear();
        abstandVerlauf2.clear();
        tempVerlauf2.clear();
        maximalerAbstand = 0;
        minimalerAbstand = Double.MAX_VALUE;
        repaint(); // Zeichnet das Panel sofort leer (schwarzer Weltraum) neu
    }
    // Aktualisierte Methode, die nun die Daten für BEIDE Objekte gleichzeitig empfängt
    public void updateKollision(double abstand1, double temp1, double abstand2, double temp2) {
        boolean gueltigeDaten = false;

        // Objekt 1 filtern und hinzufügen
        if (abstand1 > 0) {
            abstandVerlauf.add(abstand1);
            tempVerlauf.add(temp1);
            if (abstand1 > maximalerAbstand) maximalerAbstand = abstand1;
            if (abstand1 < minimalerAbstand) minimalerAbstand = abstand1;
            gueltigeDaten = true;
        }

        // NEU: Objekt 2 filtern und hinzufügen
        if (abstand2 > 0) {
            abstandVerlauf2.add(abstand2);
            tempVerlauf2.add(temp2);
            if (abstand2 > maximalerAbstand) maximalerAbstand = abstand2;
            if (abstand2 < minimalerAbstand) minimalerAbstand = abstand2;
            gueltigeDaten = true;
        }

        if (!gueltigeDaten) {
            return; // Keine neuen gültigen Daten zum Zeichnen
        }

        // Performance-Schutz für unendlich lange Simulationen (max. 300 Punkte halten)
        if (abstandVerlauf.size() > 10000) {
            abstandVerlauf.remove(0);
            tempVerlauf.remove(0);
        }
        if (abstandVerlauf2.size() > 10000) {
            abstandVerlauf2.remove(0);
            tempVerlauf2.remove(0);
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // 1. Weltraum-Hintergrund
        g2.setColor(new Color(10, 15, 30));
        g2.fillRect(0, 0, w, h);

        // Subtiles Raster
        g2.setColor(new Color(35, 40, 60));
        for (int i = 0; i < w; i += 80) g2.drawLine(i, 0, i, h);
        for (int i = 0; i < h; i += 40) g2.drawLine(0, i, w, i);

        // Nulllinie / Äquator
        g2.setColor(new Color(50, 60, 90, 100));
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
        g2.drawLine(0, h / 2, w, h / 2);

        // 2. Saturn zentriert rechts platzieren
        int saturnX = w - 150;
        int saturnY = h / 2;

        g2.setColor(new Color(240, 220, 170));
        g2.fillOval(saturnX - 35, saturnY - 35, 70, 70);
        g2.setColor(new Color(190, 175, 140));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(saturnX - 60, saturnY - 10, 120, 20);

        // Warten bis mindestens ein Objekt Daten hat
        if (abstandVerlauf.size() < 2 && abstandVerlauf2.size() < 2) {
            g2.setColor(Color.CYAN);
            g2.setFont(new Font("Monospaced", Font.BOLD, 13));
            g2.drawString("TELEMETRIE ONLINE: WARTE AUF LIVE-DATENSTRÖME...", 30, h / 2 + 5);
            return;
        }

        // 3. Geometrie-Einstellungen für die Trajektorien
        int startX = 20;
        int endX = saturnX - 45;
        double gesamtBreite = endX - startX;

        int pufferPixel = 90; // Hält die Graphen unter dem HUD-Text frei
        double maximalerAusschlagY = (h / 2.0) - pufferPixel;
        if (maximalerAusschlagY < 10) maximalerAusschlagY = 10;

        // ==========================================
        // PLOT FÜR OBJEKT 1 (Klassischer Graph)
        // ==========================================
        for (int i = 0; i < abstandVerlauf.size() - 1; i++) {
            double progress1 = (double) i / (abstandVerlauf.size() - 1);
            double progress2 = (double) (i + 1) / (abstandVerlauf.size() - 1);

            int x1 = startX + (int) (progress1 * gesamtBreite);
            int x2 = startX + (int) (progress2 * gesamtBreite);

            double verhaeltnis1 = (maximalerAbstand > 0) ? (abstandVerlauf.get(i) / maximalerAbstand) : 1.0;
            double verhaeltnis2 = (maximalerAbstand > 0) ? (abstandVerlauf.get(i + 1) / maximalerAbstand) : 1.0;

            int y1 = saturnY - (int) (verhaeltnis1 * maximalerAusschlagY);
            int y2 = saturnY - (int) (verhaeltnis2 * maximalerAusschlagY);

            // Thermische Farbe Objekt 1
            double temp = tempVerlauf.get(i);
            if (temp > 7000) g2.setColor(Color.WHITE);
            else if (temp > 2000) g2.setColor(Color.ORANGE);
            else if (temp > 400) g2.setColor(Color.RED);
            else g2.setColor(new Color(0, 255, 255)); // Cyan

            g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(x1, y1, x2, y2 );

            // Raumschiff-Punkt für Objekt 1 (Grün)
            if (i == abstandVerlauf.size() - 2) {
                g2.setColor(new Color(50, 255, 50));
                g2.fillOval(x2 - 6, y2 - 6, 12, 12);
                g2.setColor(Color.WHITE);
                g2.fillOval(x2 - 2, y2 - 2, 4, 4);
            }
        }

        // ==========================================
        // NEU: PLOT FÜR OBJEKT 2 (Magenta/Lila-Töne)
        // ==========================================
        for (int i = 0; i < abstandVerlauf2.size() - 1; i++) {
            double progress1 = (double) i / (abstandVerlauf2.size() - 1);
            double progress2 = (double) (i + 1) / (abstandVerlauf2.size() - 1);

            int x1 = startX + (int) (progress1 * gesamtBreite);
            int x2 = startX + (int) (progress2 * gesamtBreite);

            double verhaeltnis1 = (maximalerAbstand > 0) ? (abstandVerlauf2.get(i) / maximalerAbstand) : 1.0;
            double verhaeltnis2 = (maximalerAbstand > 0) ? (abstandVerlauf2.get(i + 1) / maximalerAbstand) : 1.0;

            // Leicht versetzt (z.B. +15 Pixel nach unten gespiegelt oder skaliert),
            // damit sie sich bei identischen Werten nicht komplett überlagern.
            int y1 = saturnY - (int) (verhaeltnis1 * maximalerAusschlagY);
            int y2 = saturnY - (int) (verhaeltnis2 * maximalerAusschlagY);

            // Thermische Farbe Objekt 2 (Abgestuft in elegantem Lila/Magenta/Pink)
            double temp2 = tempVerlauf2.get(i);
            if (temp2 > 7000) g2.setColor(Color.WHITE);
            else if (temp2 > 2000) g2.setColor(new Color(255, 0, 128)); // Pink
            else if (temp2 > 400) g2.setColor(new Color(180, 50, 255));  // Lila
            else g2.setColor(new Color(200, 150, 255));                // Hell-Lila

            g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(x1, y1+80, x2, y2+80);

            // Raumschiff-Punkt für Objekt 2 (Gelb/Orange)
            if (i == abstandVerlauf2.size() - 2) {
                g2.setColor(Color.YELLOW);
                g2.fillOval(x2 - 5, y2 +85, 10, 10);
                g2.setColor(Color.WHITE);
                g2.fillOval(x2 - 1, y2 +79, 2, 2);
            }
        }

        // 6. Live-HUD (Erweitert um Objekt 2)
        g2.setColor(new Color(0, 255, 150));
        g2.setFont(new Font("Monospaced", Font.BOLD, 12));
        g2.drawString("=== DUAL ORBITAL TELEMETRY ===", 20, 25);

        g2.setFont(new Font("SansSerif", Font.PLAIN, 11));

        // Anzeige Objekt 1
        if (!abstandVerlauf.isEmpty()) {
            double aktAbstand1 = abstandVerlauf.get(abstandVerlauf.size() - 1);
            double aktTemp1 = tempVerlauf.get(tempVerlauf.size() - 1);
            g2.setColor(new Color(0, 255, 255));
            g2.drawString(String.format("Objekt 1 - Abstand: %,.0f m | Temp: %,.1f K", aktAbstand1, aktTemp1), 20, 45);
        }

        // Anzeige Objekt 2
        if (!abstandVerlauf2.isEmpty()) {
            double aktAbstand2 = abstandVerlauf2.get(abstandVerlauf2.size() - 1);
            double aktTemp2 = tempVerlauf2.get(tempVerlauf2.size() - 1);
            g2.setColor(new Color(200, 150, 255));
            g2.drawString(String.format("Objekt 2 - Abstand: %,.0f m | Temp: %,.1f K", aktAbstand2, aktTemp2), 20, 60);
        }
    }
}