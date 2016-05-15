package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import backend.Backend;

public class Mode extends JDialog
{

	private JDialog			frame;
	private JLabel			lblName;
	private JLabel			Name;
	private JLabel			Size;
	private JLabel			lblSize;
	private JRadioButton	rdbtnPrivate;
	private JRadioButton	rdbtnPublic;
	private JButton			btnCreateSwarm;
	private JButton			btnCancel;
	private JPanel			pnlMode;
	Backend					backend	= Backend.getInstance();

	public Mode(JFrame parent, String file, String size, String address)
	{

		initialize(parent, file, size, address);
	}

	private void initialize(JFrame parent, String file, String size, String address)
	{
		createModeJDialog(parent, file, size, address);

	}

	private void createModeJDialog(JFrame parent, String file, String size, String address)
	{

		frame = new JDialog(parent, "Select Swarm's Mode");
		frame.setBounds(0, 0, 350, 235);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setMinimumSize(null);
		frame.setLocationRelativeTo(parent);
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setVisible(true);

		pnlMode = new JPanel();
		pnlMode.setBorder(
				new TitledBorder(null, "Choose File Mode", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlMode.setBounds(6, 6, 338, 201);
		pnlMode.setLayout(null);
		frame.getContentPane().add(pnlMode);

		lblName = new JLabel(file);
		lblName.setBounds(73, 31, 259, 33);
		pnlMode.add(lblName);

		Name = new JLabel("Name:");
		Name.setBounds(16, 31, 55, 33);
		pnlMode.add(Name);

		Size = new JLabel("Size:");
		Size.setBounds(16, 76, 55, 33);
		pnlMode.add(Size);

		lblSize = new JLabel(size + " Bytes");
		lblSize.setBounds(73, 76, 259, 33);
		pnlMode.add(lblSize);

		rdbtnPrivate = new JRadioButton("Private");
		rdbtnPrivate.setBounds(43, 121, 80, 23);
		pnlMode.add(rdbtnPrivate);

		rdbtnPublic = new JRadioButton("Public");
		rdbtnPublic.setBounds(227, 121, 80, 23);
		rdbtnPublic.setSelected(true);
		pnlMode.add(rdbtnPublic);

		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(rdbtnPrivate);
		btnGroup.add(rdbtnPublic);

		btnCreateSwarm = new JButton("Create Swarm");
		btnCreateSwarm.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				backend.createSwarm(address);
				frame.setVisible(false);
			}
		});
		btnCreateSwarm.setBounds(215, 166, 117, 29);
		pnlMode.add(btnCreateSwarm);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
			}
		});
		btnCancel.setBounds(6, 166, 117, 29);
		pnlMode.add(btnCancel);

	}
}
