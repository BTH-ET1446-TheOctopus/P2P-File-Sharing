package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JButton;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;

 
public class TabbedPane extends JFrame {
    Border paneEdge = BorderFactory.createEmptyBorder(0, 10, 10, 10);
    public TabbedPane() {
    	
    	setup();
    
    }
       
    	private void setup(){
        setTitle("Settings Panel");
        JTabbedPane jmenu = new JTabbedPane();
        getContentPane().add(jmenu);
        setSize(400,400);
     
        
        /////////////////////////////////////
        
        JPanel tab1 = new JPanel();
        jmenu.addTab("General", tab1);
        
        
        sp = new JPanel();
        sp.setBorder(new TitledBorder(new LineBorder(Color.blue, 2),"Sub Panel"));
        sp.add(new JLabel("TitledBorder using LineBorder"));
        content.add(sp);
        
        
        JCheckBox cb = new JCheckBox("Dark Peer", true);
        cb.setToolTipText("Peer will go dark");
        JButton btn1 = new JButton("Apply Changes");
        tab1.setLayout(new BorderLayout(0, 0));                    
        tab1.add(cb, BorderLayout.NORTH);
        tab1.add(btn1, BorderLayout.SOUTH);
        
        tab1.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(), ""));
        
        //////////////////////////////////////
         
        JPanel tab2 = new JPanel();
        //lb2.setText("More Features");
        tab2.setLayout(new BorderLayout());
        tab2.setBorder(BorderFactory.createTitledBorder(
        BorderFactory.createEtchedBorder(), "More Features"));
        jmenu.addTab("Advanced", tab2);
    }
    
}