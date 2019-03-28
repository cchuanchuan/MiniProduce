package view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import dao.AdminUtils;
import dao.ReaderUtils;
import model.Book;
import model.Reader;

public class ReaderIndex extends JFrame implements ActionListener {
	private String readerno;
	private DefaultTableModel bookmodel;
	
	private JPanel mainpanel;
	private JButton button_borrow,button_return,button_check,button_all,button_back;
	
	public ReaderIndex(String readerno) {
		super("读者界面");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.readerno = readerno;
		
		JToolBar toolbar = new JToolBar();
		this.add(toolbar,"North");
		toolbar.add(button_borrow = new JButton("图书借阅"));
		button_borrow.addActionListener(this);
		toolbar.add(button_return = new JButton("图书归还"));
		button_return.addActionListener(this);
		toolbar.add(button_check = new JButton("我的借阅"));
		button_check.addActionListener(this);
		toolbar.add(button_all = new JButton("所有图书"));
		button_all.addActionListener(this);
		toolbar.add(button_back = new JButton("返回主页"));
		button_back.addActionListener(this);
		
		this.mainpanel = new JPanel(new GridLayout(1,1));
		this.add(mainpanel);
		
		File backfile = new File("file//back.jpg");
		Image imag = new ImageIcon(backfile.getAbsolutePath()).getImage();
		BackgroundPanel backpanel = new BackgroundPanel(imag);
		this.mainpanel.add(backpanel);
		
		this.setToolBar();
		this.setVisible(true);
	}

	private void setToolBar() {
		
	}
	
	private void setBookModel() {
		String bookstr[] = {"书本编号","书本名字","书本作者","出版社","库存数量","类别","简介"};
		this.bookmodel = new DefaultTableModel();
		for(int i=0;i<bookstr.length;i++) {
			this.bookmodel.addColumn(bookstr[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button_borrow) {
			String bookno = JOptionPane.showInputDialog("请输入书本编号");
			ReaderUtils readerutils = new ReaderUtils();
			
			if(readerutils.borrowBook(bookno, readerno)) {
				JOptionPane.showMessageDialog(this, "借阅成功");
			}else {
				JOptionPane.showMessageDialog(this, "借阅失败");
			}
		}
		
		if(e.getSource() == button_return) {
			String bookno = JOptionPane.showInputDialog("请输入归还书本编号");
			ReaderUtils readerutils = new ReaderUtils();
			
			if(readerutils.returnBook(bookno, readerno)) {
				JOptionPane.showMessageDialog(this, "归还成功");
			}else {
				JOptionPane.showMessageDialog(this, "归还失败");
			}
		}
		
		if(e.getSource() == button_check) {
			this.setBookModel();
			ReaderUtils reader = new ReaderUtils();
			ArrayList<Book> booklist = reader.getBorrowBook(readerno);
			
			for(Book book:booklist) {
				Vector v = new Vector();
				v.add(book.getBookno());
				v.add(book.getBookname());
				v.add(book.getBookauthor());
				v.add(book.getBookpress());
				v.add(book.getBooknumber());
				v.add(book.getBookcategory());
				v.add(book.getBookintro());
				this.bookmodel.addRow(v);
			}
			this.mainpanel.remove(0);
			JTable table = new JTable(bookmodel);
			this.mainpanel.add(new JScrollPane(table));
			this.setVisible(true);
		}
		
		if(e.getSource() == button_all) {
			this.setBookModel();
			AdminUtils admin = new AdminUtils();
			ArrayList<Book> booklist = admin.getBookList();
			
			for(Book book:booklist) {
				Vector v = new Vector();
				v.add(book.getBookno());
				v.add(book.getBookname());
				v.add(book.getBookauthor());
				v.add(book.getBookpress());
				v.add(book.getBooknumber());
				v.add(book.getBookcategory());
				v.add(book.getBookintro());
				this.bookmodel.addRow(v);
			}
			this.mainpanel.remove(0);
			JTable table = new JTable(bookmodel);
			this.mainpanel.add(new JScrollPane(table));
			this.setVisible(true);
		}
		
		if(e.getSource() == button_back) {
			this.mainpanel.remove(0);
			File backfile = new File("file//back.jpg");
			Image imag = new ImageIcon(backfile.getAbsolutePath()).getImage();
			BackgroundPanel backpanel = new BackgroundPanel(imag);
			this.mainpanel.add(backpanel);
			this.setVisible(true);
		}
		
	}
	
	public static void main(String args[]) {
		new ReaderIndex("000001");
	}
}
