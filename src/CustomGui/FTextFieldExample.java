/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomGui;
import javax.swing.*;
import java.awt.*;

class FTextFieldExample extends JTextField {

    public FTextFieldExample() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int arc = 20; // Adjust the corner radius as needed
            int width = getWidth();
            int height = getHeight();

            // Draw rounded background
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, width - 1, height - 1, arc, arc);

            g2d.setColor(getForeground()); // Set text color
            g2d.drawString(getText(), 6, g2d.getFontMetrics().getAscent() + 3);

            g2d.dispose();
        } else {
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Rounded JTextField Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FTextFieldExample roundedTextField = new FTextFieldExample();
        frame.getContentPane().add(roundedTextField);

        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
