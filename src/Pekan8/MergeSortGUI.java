package Pekan8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedList;
import java.util.Queue;
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

public class MergeSortGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private int[] array;
    private JLabel[] labelArray;
    private JButton stepButton, resetButton, setButton;
    private JTextField inputField;
    private JPanel panelArray;
    private JTextArea stepArea;
    private int stepCount = 1;
    private boolean isMerging = false;
    private Queue<int[]> mergeQueue = new LinkedList<>();
    private int left, mid, right, i, j, k;
    private int[] temp;
    private boolean copying = false;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MergeSortGUI frame = new MergeSortGUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MergeSortGUI() {
        setTitle("Merge Sort Langkah per Langkah");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(30);
        setButton = new JButton("Set Array");
        inputPanel.add(new JLabel("Masukkan angka (pisahkan dengan koma):"));
        inputPanel.add(inputField);
        inputPanel.add(setButton);

        panelArray = new JPanel();
        panelArray.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel controlPanel = new JPanel();
        stepButton = new JButton("Langkah Selanjutnya");
        resetButton = new JButton("Reset");
        stepButton.setEnabled(false);
        controlPanel.add(stepButton);
        controlPanel.add(resetButton);

        stepArea = new JTextArea(8, 60);
        stepArea.setEditable(false);
        stepArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(stepArea);

        add(inputPanel, BorderLayout.NORTH);
        add(panelArray, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        setButton.addActionListener(e -> setArrayFromInput());
        stepButton.addActionListener(e -> performStep());
        resetButton.addActionListener(e -> reset());
    }

    private void setArrayFromInput() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) return;
        String[] parts = text.split(",");
        array = new int[parts.length];
        try {
            for (int idx = 0; idx < parts.length; idx++) {
                array[idx] = Integer.parseInt(parts[idx].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan hanya angka yang dipisahkan dengan koma!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        panelArray.removeAll();
        labelArray = new JLabel[array.length];
        for (int idx = 0; idx < array.length; idx++) {
            labelArray[idx] = new JLabel(String.valueOf(array[idx]));
            labelArray[idx].setFont(new Font("Arial", Font.BOLD, 24));
            labelArray[idx].setOpaque(true);
            labelArray[idx].setBackground(Color.WHITE);
            labelArray[idx].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            labelArray[idx].setPreferredSize(new Dimension(50, 50));
            labelArray[idx].setHorizontalAlignment(SwingConstants.CENTER);
            panelArray.add(labelArray[idx]);
        }
        mergeQueue.clear();
        generateMergesteps(0, array.length - 1);
        stepButton.setEnabled(true);
        stepArea.setText("");
        stepCount = 1;
        isMerging = false;
        copying = false;
        panelArray.revalidate();
        panelArray.repaint();
    }

    private void performStep() {
        resetHighlights();

        if (!isMerging && !mergeQueue.isEmpty()) {
            int[] range = mergeQueue.poll();
            left = range[0];
            mid = range[1];
            right = range[2];
            temp = new int[right - left + 1];
            i = left;
            j = mid + 1;
            k = 0;
            copying = false;
            isMerging = true;
            stepArea.append("Langkah " + stepCount++ + 
                            ": Mulai merge dari " + left + " ke " + right + "\n");
            return;
        }

        if (isMerging && !copying) {
            if (i <= mid && j <= right) {
                labelArray[i].setBackground(Color.CYAN);
                labelArray[j].setBackground(Color.CYAN);
                if (array[i] <= array[j]) {
                    temp[k++] = array[i++];
                } else {
                    temp[k++] = array[j++];
                }
                stepArea.append("Langkah " + stepCount++ + ": Bandingkan dan salin elemen\n");
                return;
            } else if (i <= mid) {
                temp[k++] = array[i++];
                stepArea.append("Langkah " + stepCount++ + ": Salin sisa kiri\n");
                return;
            } else if (j <= right) {
                temp[k++] = array[j++];
                stepArea.append("Langkah " + stepCount++ + ": Salin sisa kanan\n");
                return;
            } else {
                copying = true;
                k = 0;
                return;
            }
        }
       if (copying && k < temp.length) {
            array[left + k] = temp[k];
            labelArray[left + k].setText(String.valueOf(temp[k]));
            labelArray[left + k].setBackground(Color.GREEN);
            k++;
            stepArea.append("Langkah " + stepCount++ + ": Tempelkan ke array utama\n");
            return;
        }

        if (copying && k == temp.length) {
            isMerging = false;
            copying = false;
        }

        if (mergeQueue.isEmpty() && !isMerging) {
            stepArea.append("Selesai.\n");
            stepButton.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Merge Sort selesai!");
        }
    }


    private void generateMergesteps(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            generateMergesteps(l, m);
            generateMergesteps(m + 1, r);
            mergeQueue.add(new int[]{l, m, r});
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
        mergeQueue.clear();
        isMerging = false;
        copying = false;
        stepCount = 1;
        labelArray = null;
    }
}