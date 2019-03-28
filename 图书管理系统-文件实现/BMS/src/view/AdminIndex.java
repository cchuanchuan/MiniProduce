package view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.AdminUtils;
import model.Book;
import model.Reader;

public class AdminIndex extends JFrame implements ActionListener {
	private String menubarstr[] = {"读者管理","图书管理","图书检索","返回"};
	private String menuitemstr[][] = {{"增加读者","查询读者","删除读者","全部读者"},
									{"采编入库","清除库存","全部图书"},
									{"通过关键字","通过书名","通过书号","通过作者","通过出版社"},
									{"主界面"}};
	private DefaultTableModel readermodel,bookmodel;
	
	private JPanel mainpanel;
	
	public AdminIndex() {
		super("管理员界面");
		this.setSize(800,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.mainpanel = new JPanel(new GridLayout(1,1));
		this.add(mainpanel);
		
		File backfile = new File("file//back.jpg");
		Image imag = new ImageIcon(backfile.getAbsolutePath()).getImage();
		BackgroundPanel backpanel = new BackgroundPanel(imag);
		this.mainpanel.add(backpanel);
		
		this.setMenu();
		this.setVisible(true);
	}

	private void setMenu() {
		JMenuBar menubar = new JMenuBar();
		JMenu menus[] = new JMenu[menubarstr.length];
		JMenuItem menuitems[][] = new JMenuItem[menuitemstr.length][];
		for(int i=0;i<menus.length;i++) {
			menubar.add(menus[i]=new JMenu(menubarstr[i]));
			menuitems[i] = new JMenuItem[menuitemstr[i].length];
			for(int j=0;j<menuitems[i].length;j++) {
				menus[i].add(menuitems[i][j]=new JMenuItem(menuitemstr[i][j]));
				menuitems[i][j].addActionListener(this);
			}
		}
		this.setJMenuBar(menubar);
	}
	
	private void setReaderModel() {
		String readerstr[] = {"用户编号","读者姓名","读者密码"};
		this.readermodel = new DefaultTableModel();
		for(int i=0;i<readerstr.length;i++) {
			this.readermodel.addColumn(readerstr[i]);
		}
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
		if(e.getActionCommand().equals("增加读者")) {
			new AddReaderJFrame();
		}
		
		if(e.getActionCommand().equals("查询读者")) {
			String readerno = JOptionPane.showInputDialog("请输入读者编号");
			AdminUtils admin = new AdminUtils();
			Reader reader = admin.checkReader(readerno);
			if(reader != null) {
				JOptionPane.showMessageDialog(this, reader.toString());
			}else {
				JOptionPane.showMessageDialog(this, "没有找到此读者");
			}
		}
		
		if(e.getActionCommand().equals("删除读者")) {
			String readerno = JOptionPane.showInputDialog("请输入读者编号");
			AdminUtils admin = new AdminUtils();
			if(admin.deleteReader(readerno)) {
				JOptionPane.showMessageDialog(this, "删除成功");
			}else {
				JOptionPane.showMessageDialog(this, "没有此读者");
			}
		}
		
		if(e.getActionCommand().equals("采编入库")) {
			new AddBookJFrame();
		}
		
		if(e.getActionCommand().equals("清除库存")) {
			String bookno = JOptionPane.showInputDialog("请输入书本编号");
			AdminUtils admin = new AdminUtils();
			if(admin.deleteBook(bookno)) {
				JOptionPane.showMessageDialog(this, "删除成功");
			}else {
				JOptionPane.showMessageDialog(this, "没有此书本");
			}
		}
		
		if(e.getActionCommand().equals("通过关键字")) {
			String bookno = JOptionPane.showInputDialog("请输入书本编号");
			this.setBookModel();
			AdminUtils admin = new AdminUtils();
			ArrayList<Book> booklist = admin.getBookByKeyWord(bookno);
			
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
		
		if(e.getActionCommand().equals("通过书号")) {
			String bookno = JOptionPane.showInputDialog("请输入书本编号");
			AdminUtils admin = new AdminUtils();
			Book book = admin.getBookByNo(bookno);
			if(book!=null) {
				JOptionPane.showMessageDialog(this, book.toString());
			}else {
				JOptionPane.showMessageDialog(this, "没有此书本");
			}
		}
		
		if(e.getActionCommand().equals("通过书名")) {
			String bookname = JOptionPane.showInputDialog("请输入书本名字");
			AdminUtils admin = new AdminUtils();
			Book book = admin.getBookByName(bookname);
			if(book!=null) {
				JOptionPane.showMessageDialog(this, book.toString());
			}else {
				JOptionPane.showMessageDialog(this, "没有此书本");
			}
		}
		
		if(e.getActionCommand().equals("通过作者")) {
			String author = JOptionPane.showInputDialog("请输入作者姓名");
			this.setBookModel();
			AdminUtils admin = new AdminUtils();
			ArrayList<Book> booklist = admin.getBookByAuthor(author);
			
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
		
		if(e.getActionCommand().equals("通过出版社")) {
			String press = JOptionPane.showInputDialog("请输入出版社名字");
			this.setBookModel();
			AdminUtils admin = new AdminUtils();
			ArrayList<Book> booklist = admin.getBookByPress(press);
			
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
		
		if(e.getActionCommand().equals("全部图书")) {
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
		
		if(e.getActionCommand().equals("全部读者")) {
			this.setReaderModel();
			AdminUtils admin = new AdminUtils();
			ArrayList<Reader> readerlist = admin.getReaderList();
			
			for(Reader reader:readerlist) {
				Vector v = new Vector();
				v.add(reader.getReaderno());
				v.add(reader.getReadername());
				v.add(reader.getReaderpassword());
				this.readermodel.addRow(v);
			}
			this.mainpanel.remove(0);
			JTable table = new JTable(readermodel);
			this.mainpanel.add(new JScrollPane(table));
			this.setVisible(true);
		}
		
		if(e.getActionCommand().equals("主界面")) {
			this.mainpanel.remove(0);
			File backfile = new File("file//back.jpg");
			Image imag = new ImageIcon(backfile.getAbsolutePath()).getImage();
			BackgroundPanel backpanel = new BackgroundPanel(imag);
			this.mainpanel.add(backpanel);
			this.setVisible(true);
		}
		
	}
	
	public static void main(String args[]) {
		new AdminIndex();
	}

}
