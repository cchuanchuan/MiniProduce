/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle_game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class GameFrame extends JFrame {
    public JPanel pane1 = new JPanel();
    public JButton button1 = new JButton("ÓÎÏ·¿ªÊ¼");
    public JButton button2 = new JButton("ÓÎÏ·½áÊø");
    private JButton buttommusic = new JButton("²¥·ÅÒôÀÖ");//°´Å¥£¬µã»÷²¥·ÅÒôÀÖ
    private TimeJLabel label = new TimeJLabel();//±êÇ©£¬±íÊ¾ÓÎÏ·Ê±¼ä
    private JLabel countlabel = new JLabel("");
    public GameFrame() {
        super("Æ´Í¼ÓÎÏ·");
        pane1.setLayout(new FlowLayout());
        
        pane1.add(label);
        pane1.add(buttommusic);
        pane1.add(button1);
        pane1.add(button2);
        
        Container con = this.getContentPane();
        con.add(pane1,BorderLayout.NORTH);
        GamePanel gamepane = new GamePanel();
        con.add(gamepane,BorderLayout.CENTER);
        this.setBounds(500, 0, 515, 580);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addActionListener((final ActionEvent e) -> {
        	gamepane.OutOfOrder();
        	label.reSet();
        	countlabel = gamepane.getCountlabel();
        	pane1.add(countlabel);
            });
        button2.addActionListener((final ActionEvent e) -> {
            System.exit(0);
            });
        //µã»÷²¥·ÅÒôÀÖ°´Å¥
        buttommusic.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int choose = JOptionPane.showConfirmDialog(null, "ÊÇ·ñ²¥·ÅÒôÀÖ");
        		if (choose == JOptionPane.YES_OPTION) {
        			File f = new File("music.mp3"); 
                	AudioPlayer musicthread = new AudioPlayer(f);
        		    musicthread.start();
       			}
        	}
        });
    }

    public static void main(String[] args) {
        GameFrame GameFrame; /*gameFrame*/
        GameFrame = new GameFrame();

    }

}
