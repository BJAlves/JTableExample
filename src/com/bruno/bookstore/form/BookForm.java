package com.bruno.bookstore.form;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.bruno.bookstore.controller.BookController;
import com.bruno.bookstore.entity.Book;
import com.bruno.bookstore.table.BookCellRenderer;
import com.bruno.bookstore.table.BookTableModel;

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
		
		refreshTable();
		enableFields(false);
		
		add(panelAdd);
		add(panelButtons);
		add(panelTable);
		setMinimumSize(new Dimension(500, 420));
		setVisible(true);
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onSaveBooks();
			}			
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}			
		});
		
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onNewBook();								
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onRemoveBook();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onUpdateBook();
			}
		});
	}
	
	private void onRemoveBook() {
		int rowIndex = table.getSelectedRow();
		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Select a book to remove it!");
			return;
		}
		Book book = new BookTableModel(bookList).get(rowIndex);
		int confirm = JOptionPane.showConfirmDialog(this, "Do you really want to delete?", "Delete book", JOptionPane.YES_NO_OPTION);
		if(confirm != 0) {
			return;
		}
		int result = new BookController().remove(book.getId());
		if(result == 1) {
			JOptionPane.showMessageDialog(this, "Value removed successfully!");
			onCancel();
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Try again!");
		}
	}
	
	private void onUpdateBook() {
		int rowIndex = table.getSelectedRow();
		if(rowIndex == -1) {
			JOptionPane.showMessageDialog(this, "Select a book to update it!");
			return;
		}
		Book book = new BookTableModel(bookList).get(rowIndex);
		
		bookId = book.getId();
		
		textPublishingCompany.setText(book.getPublishingCompany());
		textTitle.setText(book.getTitle());
		textIsbn.setText(book.getIsbn());
		enableFields(true);
			
	}
	
	private void onNewBook() {
		enableFields(true);
	}
	
	private void onSaveBooks() {
		Book book = new Book();
		if((textPublishingCompany.getText().length() > 0) &&
				(textTitle.getText().length() > 0) &&
				(textIsbn.getText().length() > 0)) {
			book.setPublishingCompany(textPublishingCompany.getText());
			book.setTitle(textTitle.getText());
			book.setIsbn(textIsbn.getText());
		} else {
			JOptionPane.showMessageDialog(this, "All fields are required!");
			return;
		}
		
		int result = 0;
		if(bookId == null) {
			result = new BookController().addBook(book);
		} else {
			book.setId(bookId);
			result = new BookController().updateBook(book);
			bookId = null;
		}
		
		if(result == 1) {
			JOptionPane.showMessageDialog(this, "Value entered successfully!");
			enableFields(false);
			onCancel();
			refreshTable();
		} else {
			JOptionPane.showMessageDialog(this, "Try again!");
		}
	}
	
	private void onCancel() {
		textPublishingCompany.setText("");
		textTitle.setText("");
		textIsbn.setText("");
		enableFields(false);
	}
	
	private void enableFields(boolean fieldEnable) {
		textPublishingCompany.setEnabled(fieldEnable);
		textTitle.setEnabled(fieldEnable);
		textIsbn.setEnabled(fieldEnable);
	}
	
	private void refreshTable() {
		bookList = new BookController().findAll();
		if(bookList != null) {
			table.setModel(new BookTableModel(bookList));
			table.setDefaultRenderer(Object.class, new BookCellRenderer());
		}
	}
}
