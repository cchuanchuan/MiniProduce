import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteJPanel extends JPanel implements ActionListener {
	public JTextField texts[];
	private String str[]= {"商品编号","商品名称","商品价格","商品种类"};
	private JButton buttonadd;
	public DeleteJPanel() {
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
		texts[0].setEditable(false);
		JPanel panel = new JPanel();
		this.add(panel);
		panel.add(this.buttonadd = new JButton("确认修改"));
		buttonadd.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.buttonadd) {
			String id = this.texts[0].getText();
			String name = this.texts[1].getText();
			String price = this.texts[2].getText();
			String category = this.texts[3].getText();
			List<Product> list = FileUtils.readFromFile();
			boolean change = false;
			for(Product pro:list) {
				if(pro.getID().equals(id)) {
					pro.setName(name);
					pro.setPrice(price);
					pro.setCategory(category);
					change = true;
					break;
				}
			}
			if(change) {
				FileUtils.saveToFile(list);
				JOptionPane.showConfirmDialog(this, "修改成功");
			}else {
				JOptionPane.showConfirmDialog(this, "未查询到，修改修改失败");			}
		}
	}

}
