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
    public JButton button1 = new JButton("游戏开始");
    public JButton button2 = new JButton("游戏结束");
    private JButton buttommusic = new JButton("播放音乐");//按钮，点击播放音乐
    private TimeJLabel label = new TimeJLabel();//标签，表示游戏时间
    private JLabel countlabel = new JLabel("");
    public GameFrame() {
        super("拼图游戏");
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
        //点击播放音乐按钮
        buttommusic.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int choose = JOptionPane.showConfirmDialog(null, "是否播放音乐");
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
