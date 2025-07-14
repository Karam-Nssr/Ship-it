package Problem2.Controler;

import Problem2.Model.Island;
import Problem2.View.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler {
    Island island;
    Frame mainFrame;

    public Controler() {
        island = new Island();
        mainFrame = new Frame();

        mainFrame.getSolve().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mainFrame.getPanel2().validGrid()){
                    island.toGraph(mainFrame.getPanel2().getGrid(), mainFrame.getPanel2().getN(), mainFrame.getPanel2().getM());
                    island.ans();
                    mainFrame.makeOutputPane(island.getAns());
                }else
                    JOptionPane.showMessageDialog(mainFrame, "Please enter valid numbers!");
            }
        });
    }
}
