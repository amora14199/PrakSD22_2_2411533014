package Pekan7;

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

public class SelectionSortGUI extends JFrame {
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectionSortGUI frame = new SelectionSortGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();			}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public SelectionSortGUI() {
		setTitle("Insertion Sort Langkah per Langkah");
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
			JOptionPane.showMessageDialog(this, "Masukkan hanya angka ynag dipisahkan"
					+ " dengan koma!", "Error", JOptionPane.ERROR_MESSAGE);
			return; }	
		i = 1;
		stepCount = 1;
		sorting = true;
		stepButton.setEnabled(true);
		stepArea.setText("");
		panelArray.removeAll();
		labelArray = new JLabel[array.length];
		for (int k = 0; k < array.length; k++) {
			labelArray[k] = new JLabel(String.valueOf(array[k]));
			labelArray[k].setFont(new Font("Arial", Font.BOLD, 24));
			labelArray[k].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			labelArray[k].setPreferredSize(new Dimension(50, 50));
			labelArray[k].setHorizontalAlignment(SwingConstants.CENTER);
			panelArray.add(labelArray[k]);
		}
		panelArray.revalidate();
		panelArray.repaint();
	}
	private void performStep() {
		if (i < array.length && sorting) {
			int key = array[i];
			j = i - 1;			
			StringBuilder stepLog = new StringBuilder();
			stepLog.append("Langkah ").append(stepCount).append(": Memasukkan ").append(key).append("\n");			
			while (j>= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
			updateLabels();
			stepLog.append("Hasil: ").append(arrayToString(array)).append("\n\n");
			stepArea.append(stepLog.toString());			
			i++;
			stepCount++;			
			if (i == array.length) {
				sorting = false;
				stepButton.setEnabled(false);
				JOptionPane.showMessageDialog(this, "Sorting selesai!");
			}
		}
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
		sorting = false;
		i = 1;
		stepCount = 1;
	}	

	private String arrayToString(int [] arr) {
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k < arr.length; k++) {
			sb.append(arr[k]);
		if(k < arr.length - 1) sb.append(", ");
	}
	return sb.toString();
	}
}