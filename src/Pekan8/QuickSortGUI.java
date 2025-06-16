package Pekan8;

import java.util.Stack;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class QuickSortGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private int[] array;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;
    private int i = 1, j;
    private boolean sorting = false;
    private int stepCount = 1;
    private boolean partitioning = false;
    private Stack<int[]> stack = new Stack<>();
    private int low, high, pivot;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuickSortGUI frame = new QuickSortGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public QuickSortGUI() {
        setTitle("Quick Sort Langkah per Langkah");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Input
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(30);
        setButton = new JButton("Set Array");
        inputPanel.add(new JLabel("Masukkan angka (pisahkan dengan koma):"));
        inputPanel.add(inputField);
        inputPanel.add(setButton);

        // Panel Array visual
        panelArray = new JPanel();
        panelArray.setLayout(new FlowLayout());

        // Panel Kontrol
        JPanel controlPanel = new JPanel();
        stepButton = new JButton("Langkah Selanjutnya");
        resetButton = new JButton("Reset");
        stepButton.setEnabled(false);
        controlPanel.add(stepButton);
        controlPanel.add(resetButton);

        // Area Teks untuk log langkah-langkah
        stepArea = new JTextArea(8, 60);
        stepArea.setEditable(false);
        stepArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(stepArea);

        // Tambahkan Panel ke Frame
        add(inputPanel, BorderLayout.NORTH);
        add(panelArray, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        // Event Set Array
        setButton.addActionListener(e -> setArrayFromInput());

        // Event Langkah Selanjutnya
        stepButton.addActionListener(e -> performStep());

        // Event Reset
        resetButton.addActionListener(e -> reset());
    }

    private void setArrayFromInput() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) return;

        String[] parts = text.split(",");
        array = new int[parts.length];

        try {
            for (int k = 0; k < parts.length; k++) {
                array[k] = Integer.parseInt(parts[k].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka yang dipisahkan" +
                " dengan koma!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        panelArray.removeAll();
        labelArray = new JLabel[array.length];
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
        stack.clear();
        stack.push(new int[] {
            0,
            array.length - 1
        });
        sorting = true;
        partitioning = false;
        stepCount = 1;
        stepArea.setText("");
        stepButton.setEnabled(true);
        panelArray.revalidate();
        panelArray.repaint();
    }

    private void performStep() {
        if (!sorting || (stack.isEmpty() && !partitioning)) {
            sorting = false;
            stepButton.setEnabled(false);
            resetHighlights();
            stepArea.append("Quick Sort selesai.\n");
            JOptionPane.showMessageDialog(this, "Quick Sort selesai!");
            return;
        }

        resetHighlights();
        if (!partitioning) {
            int[] range = stack.pop();
            low = range[0];
            high = range[1];
            pivot = array[high];
            i = low - 1;
            j = low;
            partitioning = true;
            stepArea.append("Langkah " + stepCount++ + ": Mulai partition dari index " +
                low + " ke " + high + " dengan pivot " + pivot + "\n");
            highlightPivot(high);
            return;
        }

        if (j < high) {
            highlightCompare(j, high);
            if (array[j] <= pivot) {
                i++;
                if (i != j) {
                    swap(i, j);
                    stepArea.append("Langkah " + stepCount++ + ": Tukar " + array[j] + " dan " + array[i] + "\n");
                } else {
                    stepArea.append("Langkah " + stepCount++ + ": " + array[j] + " sudah di posisi yang benar\n");
                }
                updateLabels();
            } else {
                stepArea.append("Langkah " + stepCount++ + ": Lewatkan " + array[j] + " (lebih besar dari pivot)\n");
            }
            j++;
            return;
        }

        // Final swap
        if (i + 1 != high) {
            swap(i + 1, high);
            stepArea.append("Langkah " + stepCount++ + ": Pindahkan pivot ke posisi tengah\n");
        }
        updateLabels();
        int p = i + 1;
        partitioning = false;
        // Push subproblems
        if (p - 1 > low) {stack.push(new int[] { low,p - 1 });
        if (p + 1 < high) {stack.push(new int[] { p + 1, high });
        }
      }
    }  
    private void highlightPivot(int index) {
        labelArray[index].setBackground(Color.YELLOW);
    }

    private void highlightCompare(int jIndex, int pivotIndex) {
        labelArray[jIndex].setBackground(Color.CYAN);
        labelArray[pivotIndex].setBackground(Color.YELLOW);
    }

    private void resetHighlights() {
        for (JLabel label: labelArray) {
            label.setBackground(Color.WHITE);
        }
    }

    private void swap(int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private void updateLabels() {
        for (int k = 0; k < array.length; k++) {
            labelArray[k].setText(String.valueOf(array[k]));
        }
    }

    private void reset() {
        inputField.setText("");
        panelArray.removeAll();
        panelArray.revalidate();
        panelArray.repaint();
        stepArea.setText("");
        stepButton.setEnabled(false);
        stack.clear();
        sorting = false;
        partitioning = false;
        stepCount = 1;
    }
}