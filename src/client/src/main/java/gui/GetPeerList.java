package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import backend.Backend;
import backend.api.datatypes.*;
import sql.DatabaseCalls;


public class GetPeerList extends JFrame
{
	Backend	backend	= Backend.getInstance();

    public GetPeerList()
    {
    	createtable();
    }
    
    private void createtable()
    {
    	JTable Peerstable = new JTable();
        DatabaseCalls databaseCalls;
        databaseCalls = new DatabaseCalls();
        List<String> peers = new ArrayList<String>();	
		peers = databaseCalls.getconnPeers();
		DefaultTableModel model = (DefaultTableModel) Peerstable.getModel();
		String[] columnNames = {"Connecting peers"};
		model.setColumnIdentifiers(columnNames);
		if(peers.size()>0)
		{
		model.setColumnCount(1);		
		for (int i=0; i<peers.size(); i++)
		{
			model.addRow(new Object[]{ 
					peers.get(i) });
			
		}		
		Peerstable.setModel(model);  
		}
        this.add(new JScrollPane(Peerstable));
        this.setTitle("PeerList");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);       
        this.pack();
        this.setVisible(true);  
		
    }    
}

