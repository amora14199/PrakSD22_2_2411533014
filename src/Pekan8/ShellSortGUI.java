package Pekan8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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
import javax.swing.SwingUtilities;

public class ShellSortGUI extends JFrame {
	private boolean isSwapping = false;
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
	private int gap;
	private int temp;

	/**
	 * Create the frame.
	 */
	public ShellSortGUI() {
		setTitle("Shell Sort Langkah per Langkah");
		setSize(750, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		// Panel Input
		JPanel inputPanel = new JPanel(new FlowLayout());
		inputField = new JTextField(30);
		setButton = new JButton("Set Array");
		inputPanel.add(new JLabel("Masukkan angka (pisahkan dengan koma:"));
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
			JOptionPane.showMessageDialog(this, "Masukkan hanya "
					+ " angka yang dipisahkan dengan koma!", "Error", JOptionPane.ERROR_MESSAGE);
			return; 
		}
		gap = array.length / 2;
		i = gap;
		stepCount = 1;
		sorting = true;
		stepButton.setEnabled(true);
		stepArea.setText("");
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
		panelArray.revalidate();
		panelArray.repaint();
	}
	private void performStep() {
		    resetHighlights();

		    if (!sorting || gap == 0) {
		        stepArea.append("Shell Sort selesai.\n");
		        stepButton.setEnabled(false);
		        JOptionPane.showMessageDialog(this, "Shell Sort selesai!");
		        stepArea.append("Hasil akhir: " + java.util.Arrays.toString(array) + "\n\n");
		        return;
		    }

		    if (i < array.length) {
		        if (!isSwapping) {
		            temp = array[i];
		            j = i;
		            isSwapping = true;
		        }

		        if (j >= gap && array[j - gap] > temp) {
		            array[j] = array[j - gap]; // geser ke kanan
		            labelArray[j].setBackground(Color.GREEN);
		            labelArray[j - gap].setBackground(Color.CYAN);
		            updateLabels();
		            logStep("Geser elemen " + array[j] + " ke kanan");
		            j -= gap;
		            return;
		        } else {
		            array[j] = temp; // letakkan nilai temp
		            updateLabels();
		            logStep("Tempatkan " + temp + " di posisi " + j);
		            i++;
		            isSwapping = false;
		        }
		    } else {
		        gap /= 2;
		        i = gap;
		        isSwapping = false;
		        stepArea.append("Langkah " + stepCount++ + ": Kurangi gap menjadi " + gap + "\n\n");
		    }
		}
	
	private void updateLabels() {
		for (int k = 0; k < array.length; k++) {
			labelArray[k].setText(String.valueOf(array[k]));
		}
	}	
	private void logStep(String message) {
		stepArea.append("Langkah " + stepCount++ + ": " + message + "\n");
		stepArea.append("Array " + java.util.Arrays.toString(array) + "\n\n");	
	}

	private void resetHighlights() {
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
		sorting = false;
		i = 1;
		stepCount = 1;
	}	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ShellSortGUI gui = new ShellSortGUI();
			gui.setVisible(true);
		});
	}
}