package hust.soict.hespi.swing;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWTAccumulator extends Frame {

    private TextField tfInput;
    private TextField tfOutput;
    private int sum = 0;

    // Constructor
    public AWTAccumulator() {

        setLayout(new GridLayout(2, 2));

        // Input label
        add(new Label("Enter an Integer: "));

        // Input text field
        tfInput = new TextField(10);
        add(tfInput);

        // Add action listener
        tfInput.addActionListener(new TFInputListener());

        // Output label
        add(new Label("The Accumulated Sum is: "));

        // Output text field
        tfOutput = new TextField(10);
        tfOutput.setEditable(false);
        add(tfOutput);

        // Frame settings
        setTitle("AWT Accumulator");
        setSize(350, 120);
        setVisible(true);

        // Đóng cửa sổ
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
    }

    // Hàm đóng chương trình
    private void close() {
        dispose();
        System.exit(0);
    }

    // Main method
    public static void main(String[] args) {
        new AWTAccumulator();
        new NumberGrid();
    }

    // Listener class
    private class TFInputListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {

            int numberIn = Integer.parseInt(tfInput.getText());

            sum += numberIn;

            tfInput.setText("");

            tfOutput.setText(sum + "");
        }
    }
}