package com.bruno.bookstore.form;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.bruno.bookstore.entity.Book;

import net.miginfocom.swing.MigLayout;

public class BookForm extends JFrame {
	
	private JLabel publishingCompany;
	private JLabel title;
	private JLabel isbn;
	private JTextField textPublishingCompany;
	private JTextField textTitle;
	private JTextField textIsbn;
	private JPanel panelAdd;
	private JPanel panelTable;
	private JPanel panelButtons;	
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnRemove;
	private JButton btnCancel;	
	private JTable table;	
	private JScrollPane scrollPane;
	private List<Book> bookList;
	private Long bookId;
	private static final String PATH_IMG = "img/";

	public BookForm() throws HeadlessException {
		super("Books' register");
		setContentPane(new JPanel());
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelAdd = new JPanel(new MigLayout());
		panelAdd.setBorder(BorderFactory.createTitledBorder("Add books"));
		panelAdd.setBounds(5, 0, 480, 100);
		
		publishingCompany = new JLabel("Publishing company");
		title = new JLabel("Title");
		isbn = new JLabel("ISBN");
		
		textPublishingCompany = new JTextField(50);
		textTitle = new JTextField(50);
		textIsbn = new JTextField(15);
		
		panelAdd.add(publishingCompany);
		panelAdd.add(textPublishingCompany, "span, growx");
		
		panelAdd.add(title);
		panelAdd.add(textTitle, "span, growx");
		
		panelAdd.add(isbn);
		panelAdd.add(textIsbn, "wrap para");
		
		panelButtons = new JPanel(new MigLayout());
		panelButtons.setBorder(BorderFactory.createEtchedBorder());
		panelButtons.setBounds(5, 105, 480, 40);
				
		ClassLoader loader = getClass().getClassLoader();
		btnNew = new JButton(new ImageIcon(loader.getResource(PATH_IMG + "new.png")));
		btnSave = new JButton(new ImageIcon(loader.getResource(PATH_IMG + "save.png")));
		btnCancel = new JButton(new ImageIcon(loader.getResource(PATH_IMG + "cancel.png")));
		btnRemove = new JButton(new ImageIcon(loader.getResource(PATH_IMG + "trash.png")));
		btnUpdate = new JButton(new ImageIcon(loader.getResource(PATH_IMG + "refresh.png")));
		
		panelButtons.add(btnNew, "gapleft 90");
		panelButtons.add(btnCancel);
		panelButtons.add(btnSave, "gap unrelated");
		panelButtons.add(btnUpdate, "gap unrelated");
		panelButtons.add(btnRemove);
		
		panelTable = new JPanel(new MigLayout());
		panelTable.setBorder(BorderFactory.createTitledBorder("Books' list"));
		panelTable.setBounds(5, 150, 480, 240);
		
		table = new JTable();
		scrollPane = new JScrollPane(table);
		panelTable.add(scrollPane);
		
		add(panelAdd);
		add(panelButtons);
		add(panelTable);
		setMinimumSize(new Dimension(500, 420));
		setVisible(true);			
	}
}
