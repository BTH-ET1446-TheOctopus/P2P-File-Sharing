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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

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
		frame.setResizable(false);
		frame.setMinimumSize(null);
		frame.setLocationRelativeTo(parent);
		frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		frame.setVisible(true);

		pnlMode = new JPanel();
		pnlMode.setBorder(
				new TitledBorder(null, "Choose File Mode", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		lblName = new JLabel(file);

		Name = new JLabel("Name:");

		Size = new JLabel("Size:");

		lblSize = new JLabel(size + " Bytes");

		rdbtnPrivate = new JRadioButton("Private");

		rdbtnPublic = new JRadioButton("Public");
		rdbtnPublic.setSelected(true);

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

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.setVisible(false);
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(pnlMode, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
					.addGap(6))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(pnlMode, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
					.addGap(6))
		);
		GroupLayout gl_pnlMode = new GroupLayout(pnlMode);
		gl_pnlMode.setHorizontalGroup(
			gl_pnlMode.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMode.createSequentialGroup()
					.addGap(10)
					.addComponent(Name, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addGap(2)
					.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
				.addGroup(gl_pnlMode.createSequentialGroup()
					.addGap(10)
					.addComponent(Size, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
					.addGap(2)
					.addComponent(lblSize, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
				.addGroup(gl_pnlMode.createSequentialGroup()
					.addGap(37)
					.addComponent(rdbtnPrivate, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addGap(104)
					.addComponent(rdbtnPublic, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addGap(25))
				.addGroup(gl_pnlMode.createSequentialGroup()
					.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
					.addGap(92)
					.addComponent(btnCreateSwarm, GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE))
		);
		gl_pnlMode.setVerticalGroup(
			gl_pnlMode.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlMode.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_pnlMode.createParallelGroup(Alignment.LEADING)
						.addComponent(Name, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
					.addGap(12)
					.addGroup(gl_pnlMode.createParallelGroup(Alignment.LEADING)
						.addComponent(Size, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSize, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
					.addGap(12)
					.addGroup(gl_pnlMode.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnPrivate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(rdbtnPublic))
					.addGap(22)
					.addGroup(gl_pnlMode.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancel)
						.addComponent(btnCreateSwarm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		pnlMode.setLayout(gl_pnlMode);
		frame.getContentPane().setLayout(groupLayout);

	}
}
