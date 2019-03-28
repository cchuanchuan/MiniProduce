import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddJPanel extends JPanel implements ActionListener {
	private JTextField texts[];
	private String str[]= {"商品编号","商品名称","商品价格","商品种类"};
	private JButton buttonadd;
	public AddJPanel() {
		super();
		this.setBorder(BorderFactory.createTitledBorder("增加商品"));
		this.setLayout(new GridLayout(str.length+1,1));
		texts = new JTextField[str.length];
		for(int i=0;i<str.length;i++) {
			JPanel panel = new JPanel();
			this.add(panel);
			panel.add(new JLabel(str[i]));
			panel.add(texts[i] = new JTextField(10));
		}
		JPanel panel = new JPanel();
		this.add(panel);
		panel.add(this.buttonadd = new JButton("添加商品"));
		buttonadd.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonadd) {
			String id = this.texts[0].getText();
			String name = this.texts[1].getText();
			String price = this.texts[2].getText();
			String category = this.texts[3].getText();
			Product product = new Product(id,name,price,category);
			boolean isadd = FileUtils.addToFile(product);
			if(isadd) {
				JOptionPane.showMessageDialog(this, "增加成功");
			}else {
				JOptionPane.showMessageDialog(this, "增加失败");
			}
		}
	}

}
