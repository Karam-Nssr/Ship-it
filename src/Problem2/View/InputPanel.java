package Problem2.View;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    JButton submitButton = new JButton("Submit");
    int n,m;
    JTextField MTextField=new JTextField(20);
    JTextField NTextField=new JTextField(20);
    public InputPanel() {
        setLayout(new GridLayout(10, 1));

        JLabel nLabel = new JLabel("Colomn:");
        JLabel mLabel = new JLabel("row:");
        submitButton.setBackground(new Color(167, 169, 255));

        nLabel.setFont(new Font("Colonna MT", Font.BOLD, 20));
        mLabel.setFont(new Font("Colonna MT", Font.BOLD, 20));
        submitButton.setFont(new Font("Colonna MT", Font.BOLD, 20));


        add(new JLabel());
        add(nLabel);
        add(NTextField);
        add(new JLabel());
        add(mLabel);
        add(MTextField);
        add(new JLabel());
        add(submitButton);

        submitButton.addActionListener(e->{
            try {
                m = Integer.parseInt(MTextField.getText());
                n = Integer.parseInt(NTextField.getText());
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
            }
        });
    }
}
