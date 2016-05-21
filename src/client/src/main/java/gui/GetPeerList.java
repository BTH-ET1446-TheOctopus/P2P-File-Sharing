package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import backend.Backend;
import backend.api.datatypes.SwarmMetadataShort;
import backend.api.datatypes.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GetPeerList extends JFrame
{
	Backend						backend	= Backend.getInstance();

    public GetPeerList()
    {
    	createtable();
    }
    
    private void createtable()
    {
        String[] columns = new String[] {
            "Id", "Peers"
        };
         
        Object[][] data = new Object[][] {
            {1, "192.168.1.6"},
            {2, "175.95.86.56"},
            {3, "192.168.56.100"},
        };
 
                
      //  List<String> peers = backend.getPeers();
	
		
        JTable table = new JTable(data, columns);
         
        this.add(new JScrollPane(table));
         
        this.setTitle("Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        this.pack();
        this.setVisible(true);
    }


}