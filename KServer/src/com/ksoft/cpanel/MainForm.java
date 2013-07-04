package com.ksoft.cpanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.ksoft.kserver.Starter;

public class MainForm extends JFrame {

	private static JTextArea txtConsole = null;
	private static MainForm instance	=	null;
	public MainForm() {
		super("PC Control Panel");
		setLayout(new GridBagLayout());
		init();
		setOutputStream();
		addListeners();
		//setUI();
	}

	public static MainForm getInstance(){
		
		if(null == instance){
			instance	=	new MainForm();
		}
		return instance;
	}
	
	private void init() {
		setSize(500, 300);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle frame = getBounds();
		setLocation((screen.width - frame.width),
				(screen.height - frame.height - 50));

		// Title box
		Box titleBox = Box.createVerticalBox();
		JLabel lblTitle = new JLabel("PC Control Panel");
		Font f = lblTitle.getFont();
		lblTitle.setFont(new Font(f.getFamily(), Font.BOLD, f.getSize()));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		titleBox.add(lblTitle);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(titleBox, gbc);

		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setLayout(new GridBagLayout());	
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.RED);
		GridBagConstraints gbc1 = new GridBagConstraints();
		
		gbc1.fill = GridBagConstraints.BOTH;
		panel.add(panel1,gbc1);
					
		
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(panel, gbc);
		
		/*btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	System.out.println("Hi, Welcome ! \n Started PC");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });*/

		txtConsole = new JTextArea();
		txtConsole.setEditable(false);
		JScrollPane consolePane = new JScrollPane(txtConsole);

		gbc.gridy = 2;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 5, 5, 5);
		add(consolePane, gbc);
	}

	public static void setUI() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	private void addListeners() {		

	}

	private void setOutputStream() {

		PrintStream txtout = new PrintStream(new TextAreaOutputStream(txtConsole));
		System.setOut(txtout);
		System.setErr(txtout);

	}
}
