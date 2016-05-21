package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JButton;
 
public class TabbedPane extends JFrame {
     
    public TabbedPane() {
    	
    	setup();
    
    }
       	private void setup(){
        setTitle("Swarm Panel");
        JTabbedPane jmenu = new JTabbedPane();
		jmenu.setBounds(0, 0, 600, 380);
        getContentPane().add(jmenu);
        setSize(400,400);        
     
        JPanel tab1 = new JPanel();
        jmenu.addTab("General", tab1);
        JCheckBox cb = new JCheckBox("Dark Peer", true);
        tab1.add(cb);
        JButton btn1 = new JButton("Add peer");
        tab1.add(btn1);
        JButton btn2 = new JButton("Refresh");
        tab1.add(btn2);
        JButton btn3 = new JButton("Cancel");
        tab1.add(btn3);
        JButton btn4 = new JButton("Apply Changes");
        tab1.add(btn4);
        
        JPanel tab2 = new JPanel();      
        JLabel lb2 = new JLabel();
        lb2.setText("More Features");
        tab2.add(lb2);
        jmenu.addTab("Advanced", tab2);
    }
}