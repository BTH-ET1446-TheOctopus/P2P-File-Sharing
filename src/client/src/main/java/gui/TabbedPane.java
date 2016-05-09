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
        setTitle("Settings Panel");
        JTabbedPane jmenu = new JTabbedPane();
//		jtp.setBounds(0, 0, 600, 300);
//		jtp.setLocationRelativeTo(parent);
        getContentPane().add(jmenu);
        setSize(400,400);
     
        JPanel tab1 = new JPanel();
        jmenu.addTab("General", tab1);
        
        JCheckBox cb = new JCheckBox("Dark Peer", true);
        tab1.add(cb);
        JButton btn1 = new JButton("Apply Changes");
        tab1.add(btn1);
         
        JPanel tab2 = new JPanel();      
        JLabel lb2 = new JLabel();
        lb2.setText("More Features");
        tab2.add(lb2);
        jmenu.addTab("Advanced", tab2);
    }
    
}