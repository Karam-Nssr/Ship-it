package Problem2.View;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class OutputPanel extends JPanel {

    public OutputPanel(int[][] Grid, int n, int m) {
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(n, m);
        JTable table = new JTable(model);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                table.setValueAt(Grid[i][j], i, j);
            }
        }

        table.setRowHeight(358 / n);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int col = 0; col < m; col++) {
            table.getColumnModel().getColumn(col).setPreferredWidth(400 / m);
            table.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
        }

        table.setGridColor(Color.BLACK);
        table.setShowGrid(true);
        table.setCellSelectionEnabled(false);
        JTableHeader header = table.getTableHeader();
        header.setVisible(false);
        header.setPreferredSize(new Dimension(0, 0));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }
}
