package Problem2.View;

import javax.swing.*;
import java.awt.*;

public class InputGridPanel extends JPanel {
JTextField[][] fields;
int grid[][];
int n;
int m;

    public InputGridPanel( int n, int m) {
        this.n = n;
        this.m = m;
        this.setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel() ;
        gridPanel.setLayout(new GridLayout(n, m, 1, 1));
        fields = new JTextField[n][m];
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                fields[i][j] = new JTextField();
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                gridPanel.add(fields[i][j]);
            }
        }
        add(gridPanel,BorderLayout.CENTER);
    }

    public String getCellValue(int i, int j) {
        return fields[i][j].getText();
    }

    public void setCellValue(int i, int j, String value) {
        fields[i][j].setText(value);
    }

    // a function to validate that all the inputs are valid
    public boolean validGrid (){
        try {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = Integer.parseInt(fields[i][j].getText()) ;
                }
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    // a function to return the inputs as an array
    public int[][] getGrid() {
        return grid;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }
}

