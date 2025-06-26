package Problem2.View;

import javax.swing.*;
import java.awt.*;

public class InputGridPanel extends JPanel {
private JTextField[][] fields;
int Grid[][];

    public InputGridPanel( int n, int m) {
        this.setLayout(new GridLayout(n+1, m-1, 5, 5));
        fields = new JTextField[n][m];
        Grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                fields[i][j] = new JTextField();
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                this.add(fields[i][j]);
            }
        }
        JButton Solve=new JButton("Solve");
        add(Solve);
        Solve.addActionListener(e->{
            try {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        Grid[i][j]=Integer.parseInt(fields[i][j].getText());
                    }
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
            }
        });
    }

    public String getCellValue(int i, int j) {
        return fields[i][j].getText();
    }

    public void setCellValue(int i, int j, String value) {
        fields[i][j].setText(value);
    }
}

