package Problem2.View;

import javax.swing.*;
import java.awt.*;

public class InputGridPanel extends JPanel {
JTextField[][] fields;
int Grid[][];
JButton Solve=new JButton("Solve");

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
        add(Solve);

    }

    public String getCellValue(int i, int j) {
        return fields[i][j].getText();
    }

    public void setCellValue(int i, int j, String value) {
        fields[i][j].setText(value);
    }
}

