package Problem2.View;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class OutputPanel extends JPanel {

    public OutputPanel(int[][] Grid, int n, int m ,Set<Integer> ans) {
        setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(n, m);
        JTable table = new JTable(model);


        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int rowIndex, int columnIndex) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, rowIndex, columnIndex);

                int flatIndex = rowIndex * m + columnIndex;
                if (ans.contains(flatIndex)) {
                    cell.setBackground(new Color(167, 169, 255));
                } else {
                    cell.setBackground(Color.WHITE);
                }

                setHorizontalAlignment(SwingConstants.CENTER);
                return cell;
            }
        });

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                table.setValueAt(Grid[i][j], i, j);
            }
        }

        table.setRowHeight(358 / n);

        for (int col = 0; col < m; col++) {
            table.getColumnModel().getColumn(col).setPreferredWidth(400 / m);
        }
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
