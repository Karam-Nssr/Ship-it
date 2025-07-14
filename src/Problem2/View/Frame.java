package Problem2.View;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class Frame extends JFrame {
    InputPanel panel1;
    InputGridPanel panel2;
    CardLayout cards;
    JButton solve = new JButton("Solve");

    public Frame() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.out.println(e);
        }

        setSize(400, 400);
        setLocationRelativeTo(null);
        cards = new CardLayout();
        setLayout(cards);

        panel1 = new InputPanel();
        add(panel1, "M and N panel");

        panel1.submitButton.addActionListener(e -> {
            try {
                panel1.m = Integer.parseInt(panel1.MTextField.getText());
                panel1.n = Integer.parseInt(panel1.NTextField.getText());

                panel2 = new InputGridPanel(panel1.n, panel1.m);
                JPanel panel3= new JPanel();
                panel3.setLayout(new BorderLayout());
                solve.setBackground(new Color(167, 169, 255));
                solve.setFont(new Font("Colonna MT", Font.BOLD, 20));
                panel3.add(solve,BorderLayout.CENTER);
                panel2.add(panel3,BorderLayout.SOUTH);
                add(panel2, "Input panel");
                cards.show(this.getContentPane(), "Input panel");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
            }
        });
        setVisible(true);

    }

    public JButton getSolve() {
        return solve;
    }

    public InputGridPanel getPanel2() {
        return panel2;
    }

    public void makeOutputPane(Set<Integer> set){
        OutputPanel outputPanel = new OutputPanel(panel2.getGrid(), panel2.n, panel2.m, set);
        add(outputPanel, "Output panel");
        cards.show(this.getContentPane(), "Output panel");
    }
}
