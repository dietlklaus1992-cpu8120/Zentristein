import java.io.OutputStream;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
    private StringBuilder buffer = new StringBuilder();
    private long lastUpdate = System.currentTimeMillis();

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void write(int b) {
        synchronized (buffer) {
            buffer.append((char) b);
        }

        // Nur alle 100ms die UI aktualisieren, um den Event-Stau zu verhindern
        long now = System.currentTimeMillis();
        if (now - lastUpdate > 100) {
            updateUI();
            lastUpdate = now;
        }
    }

    private void updateUI() {
        final String textOut;
        synchronized (buffer) {
            textOut = buffer.toString();
            buffer.setLength(0); // Buffer leeren
        }

        SwingUtilities.invokeLater(() -> {
            textArea.append(textOut);
            // Nur scrollen, wenn wirklich neuer Text da ist
            if (textOut.length() > 0) {
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
    }

    @Override
    public void flush() {
        updateUI();
    }
}