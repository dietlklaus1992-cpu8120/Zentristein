import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.Color;
import java.awt.Component;

public class StatusColorRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Wir färben nur in Zeile 1 (deiner Live-Zeile)
        if (row > 2) {
            if (column == Fenster.lastColumn || column == Fenster.lastColumn2) {
                c.setBackground(new Color(50, 205, 50)); // Sattes Grün für den neusten Wert
                c.setForeground(Color.WHITE);
            } else if (column == Fenster.secondLastColumn  || column == Fenster.secondLastColumn2) {
                c.setBackground(Color.CYAN); // Gold/Gelb für den zweitneusten Wert
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(Color.ORANGE);
                c.setForeground(Color.BLACK);
            }
        } else if (row == 2) {
            c.setBackground(Color.LIGHT_GRAY); // Beschriftungszeile
        } else {
            c.setBackground(new Color(240, 240, 240)); // Archiv-Zeilen
        }

        return c;
    }
}