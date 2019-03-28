package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import dao.AdminUtils;
import model.Book;

public class AddBookJFrame extends JFrame implements ActionListener {
	private JButton button_add,button_cancel;
	private JTextField text_bookno,text_bookname,text_bookauthor,text_bookpress,
						text_booknumber,text_category,text_bookintro;
	
	public AddBookJFrame() {
		super("添加书本界面");
		this.setSize(300, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new GridLayout(8,2));
		this.add(new JLabel("书本编号"));
		this.add(text_bookno = new JTextField());
		
		this.add(new JLabel("书本名字"));
		this.add(text_bookname = new JTextField());
		
		this.add(new JLabel("书本作者"));
		this.add(text_bookauthor = new JTextField());
		
		this.add(new JLabel("书本出版社"));
		this.add(text_bookpress = new JTextField());
		
		this.add(new JLabel("库存数量"));
		this.add(text_booknumber = new JTextField());
		
		this.add(new JLabel("书本分类"));
		this.add(text_category = new JTextField());
		
		this.add(new JLabel("书本介绍"));
		this.add(text_bookintro = new JTextField());
		
		this.add(button_add = new JButton("添加"));
		button_add.addActionListener(this);
		
		this.add(button_cancel = new JButton("取消"));
		button_cancel.addActionListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button_add) {
			if(!text_bookno.getText().equals("")
					&&!text_bookname.getText().equals("")) {
				String bookno = text_bookno.getText();
				String bookname = text_bookname.getText();
				String bookauthor = text_bookauthor.getText();
				String bookpress = text_bookpress.getText();
				int booknumber = Integer.parseInt(text_booknumber.getText());
				String bookcategory = text_category.getText();
				String bookintro = text_bookintro.getText();
				Book book = new Book(bookno,bookname,bookauthor,bookpress,
						booknumber,bookcategory,bookintro);
				AdminUtils admin = new AdminUtils();
				if(admin.addBook(book)) {
					JOptionPane.showMessageDialog(this, "增加成功");
					this.dispose();
				}else {
					JOptionPane.showMessageDialog(this, "增加失败");
				}
			}else {
				JOptionPane.showMessageDialog(this, "请输入书本名书本编号");
			}
		}
		
		if(e.getSource() == button_cancel) {
			this.dispose();
		}
		
	}
	
}
