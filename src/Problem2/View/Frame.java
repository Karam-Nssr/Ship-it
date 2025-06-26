package Problem2.View;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame() throws HeadlessException {
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }catch (Exception e){
            System.out.println(e);
        }
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        InputPanel panel1=new InputPanel();
        add(panel1);
        setVisible(true);

        panel1.submitButton.addActionListener(e->{
            try {
            panel1.m = Integer.parseInt(panel1.MTextField.getText());
            panel1.n = Integer.parseInt(panel1.NTextField.getText());
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
            InputGridPanel panel2=new InputGridPanel(panel1.n,panel1.m);
            remove(panel1);
            add(panel2);
            revalidate();
            repaint();
            panel2.Solve.addActionListener(a->{
                try {
                    for (int i = 0; i < panel1.n; i++) {
                        for (int j = 0; j < panel1.m; j++) {
                            panel2.Grid[i][j]=Integer.parseInt(panel2.fields[i][j].getText());
                        }
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
                }
                OutputPanel panel3=new OutputPanel(panel2.Grid,panel1.n,panel1.m);
                remove(panel2);
                add(panel3);
                revalidate();
                repaint();
            });

        });
    }
}
