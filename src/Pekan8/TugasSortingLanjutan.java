package Pekan8;

// Nama: Sasya Zamora
// NIM: 2411533014

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Launch the application.
 */
//public static void main(String[] args) {
//	EventQueue.invokeLater(new Runnable() {
//		public void run() {
//			try {
//				TugasSortingLanjutan frame = new TugasSortingLanjutan();
//				frame.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	});
//}

/**
 * Create the frame.
 */

public class TugasSortingLanjutan extends JFrame {
    private static final long serialVersionUID = 1L;

    private int[] array;
    private JLabel[] labelArray;
    private JTextField inputField;
    private JButton setButton, stepButton, resetButton;
    private JPanel panelArray;
    private JTextArea stepArea;

    private int i = 1, j, key, langkah = 1;
    private boolean shifting = false, placing = false;

    public TugasSortingLanjutan() {
        setTitle("Insertion Sort Langkah per Langkah");
        setSize(900, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(30);
        setButton = new JButton("Set Array");
        inputPanel.add(new JLabel("Masukkan angka (pisahkan dengan koma): "));
        inputPanel.add(inputField);
        inputPanel.add(setButton);

        panelArray = new JPanel(new FlowLayout());

        JPanel controlPanel = new JPanel();
        stepButton = new JButton("Langkah Selanjutnya");
        resetButton = new JButton("Reset");
        controlPanel.add(stepButton);
        controlPanel.add(resetButton);

        stepArea = new JTextArea(10, 60);
        stepArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        stepArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(stepArea);

        add(inputPanel, BorderLayout.NORTH);
        add(panelArray, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        setButton.addActionListener(e -> setArray());
        stepButton.setEnabled(false);
        stepButton.addActionListener(e -> performStep());
        resetButton.addActionListener(e -> reset());
    }

    private void setArray() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) return;

        String[] parts = text.split(",");
        array = new int[parts.length];

        try {
            for (int k = 0; k < parts.length; k++) {
                array[k] = Integer.parseInt(parts[k].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka dipisahkan koma!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        labelArray = new JLabel[array.length];
        panelArray.removeAll();

        for (int k = 0; k < array.length; k++) {
            labelArray[k] = new JLabel(String.valueOf(array[k]));
            labelArray[k].setFont(new Font("Arial", Font.BOLD, 24));
            labelArray[k].setOpaque(true);
            labelArray[k].setBackground(Color.WHITE);
            labelArray[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelArray[k].setPreferredSize(new Dimension(50, 50));
            labelArray[k].setHorizontalAlignment(SwingConstants.CENTER);
            panelArray.add(labelArray[k]);
        }

        i = 1;
        langkah = 1;
        shifting = false;
        placing = false;
        stepArea.setText("");
        stepArea.append("// Nama: Sasya Zamora\n");
        stepArea.append("// NIM: 2411533014\n\n");
        stepArea.append("Deret awal: " + Arrays.toString(array) + "\n");
        stepArea.append("Algoritma: Insertion Sort (Descending)\n\n");

        stepButton.setEnabled(true);
        panelArray.revalidate();
        panelArray.repaint();
    }

    private void performStep() {
        resetHighlights();

        if (i >= array.length) {
            stepArea.append("Selesai.\nArray akhir: " + Arrays.toString(array) + "\n");
            for (JLabel label : labelArray) label.setBackground(Color.ORANGE);
            stepButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Sorting selesai!");
            return;
        }

        if (!shifting && !placing) {
            key = array[i];
            j = i - 1;
            shifting = true;
            stepArea.append("Langkah " + langkah++ + ": Ambil " + key + " untuk disisipkan\n");
            labelArray[i].setBackground(Color.CYAN);
            return;
        }

        if (shifting && j >= 0 && array[j] < key) {
            array[j + 1] = array[j];
            labelArray[j + 1].setText(String.valueOf(array[j]));
            labelArray[j].setBackground(Color.CYAN);
            labelArray[j + 1].setBackground(Color.CYAN);
            stepArea.append("Langkah " + langkah++ + ": Geser " + array[j] + " ke posisi " + (j + 1) + "\n");
            j--;
            return;
        }

        if (shifting) {
            array[j + 1] = key;
            labelArray[j + 1].setText(String.valueOf(key));
            labelArray[j + 1].setBackground(Color.GREEN);
            stepArea.append("Langkah " + langkah++ + ": Tempatkan " + key + " di posisi " + (j + 1) + "\n\n");
            i++;
            shifting = false;
            return;
        }
    }

    private void resetHighlights() {
        if (labelArray == null) return;
        for (JLabel label : labelArray) {
            label.setBackground(Color.WHITE);
        }
    }

    private void reset() {
        inputField.setText("");
        panelArray.removeAll();
        panelArray.revalidate();
        panelArray.repaint();
        stepArea.setText("");
        stepButton.setEnabled(false);
        i = 1;
        shifting = false;
        placing = false;
        langkah = 1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TugasSortingLanjutan().setVisible(true);
        });
    }
}