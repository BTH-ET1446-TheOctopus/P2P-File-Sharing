
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.*;
import javax.swing.JSplitPane;


public class BasicSettings extends JFrame{

	
    public JCheckBox dp;
    public JButton b;
    

    public BasicSettings() {

    	settingWizard();
    }
    
    private void settingWizard()
    	{    
        
      	setTitle("Checkbox for going Dark");    	
        getContentPane().setLayout(new FlowLayout());
        setSize(300,300);
        
        JCheckBox dp= new JCheckBox("Dark Peer");
        getContentPane().add(dp);

        JButton ap=new JButton("Apply");
        ap.addActionListener(new ActionListener() 
        {
    	   public void actionPerformed(ActionEvent e)
    	   {
       		
       	   }
        });

        getContentPane().add(ap);     
        setSize(400,400);
        setVisible(true);
        
        
    }
}
