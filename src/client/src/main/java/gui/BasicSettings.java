
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


public class BasicSettings extends JFrame{
    public JCheckBox dp;

    public BasicSettings() {
    	
    	setTitle("Checkbox for going Drak");
        JCheckBox dp = new JCheckBox("Dark Peer");
        setSize(300,300);
        getContentPane().add(dp);
        setVisible(true);
    }
}