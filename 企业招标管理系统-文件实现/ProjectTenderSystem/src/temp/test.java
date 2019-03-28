package temp;

import java.awt.Font;

import javax.swing.JOptionPane;

public class test {
	public static void main(String args[]) {
		String options[]= {"Comfirm","Cancel"};
		/*JOptionPane.showOptionDialog(null, "123132123" 
				, "Message", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);*/
		
		JOptionPane.showOptionDialog(null, "123132123" 
				, "Input", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	
	
	}

	 

}
