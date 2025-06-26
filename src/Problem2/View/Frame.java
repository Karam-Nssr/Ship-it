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

        });
    }
}
