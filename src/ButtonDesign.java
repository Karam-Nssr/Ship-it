import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
public class ButtonDesign extends JButton {
    final private Color normalColor=new Color(205, 193, 181);
    final private Color hoverColor=new Color(225, 213, 201);
    public ButtonDesign(String text){
        super(text);
        setFocusable(false);
        setBackground(normalColor);
        setForeground(Color.DARK_GRAY);
        setFont(new Font("SansSerif",Font.BOLD,16));
        setPreferredSize(new Dimension(180,60));
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                setBackground(hoverColor);
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e){
                setBackground(normalColor);
                repaint();
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2=(Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape clip=new RoundRectangle2D.Float(0, 0,getWidth(),getHeight(),40,40);
        g2.setClip(clip);
        g2.setColor(getBackground());
        g2.fill(clip);
        super.paintComponent(g2);
        g2.dispose();
    }
    @Override
    protected void paintBorder(Graphics g){
        Graphics2D g2=(Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(180, 168, 156));
        g2.drawRoundRect(0, 0,getWidth()-1,getHeight()-1,40,40);
        g2.dispose();
    }
}
