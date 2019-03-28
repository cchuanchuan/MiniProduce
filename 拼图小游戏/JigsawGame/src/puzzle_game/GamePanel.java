/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle_game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public final class GamePanel extends JPanel implements MouseListener {
    private Cell BlankCell = null;
    private final int row = 4;
    private final int col = 4;//设置这个拼图的行列
    private final Cell cells[] = new Cell[row*col];//创建一个按钮对象数组
    int ImageWidth;
    int ImageHeight;
    
    private JLabel countlabel = new JLabel("已走0步");//表示已走步数
    private int count = 0;
    
    //构造函数
    public GamePanel() {
        this.setLayout(null);
        init();
    }

    //初始化完成以下功能--完成图像的切割，完成图像到图标的转换，完成按钮图标的添加，将按钮添加到面板上，并且给每一个按钮添加监听机制
    public void init() {
        int num = 0;
        BufferedImage buf = null;
        BufferedImage bufnew = null;
        ImageIcon icon = null;
        int width = 0;
        int height = 0;
        try {
            buf = ImageIO.read(new File("2017123235.jpg"));//读取文件图像
            ImageWidth = buf.getWidth();
            ImageHeight = buf.getHeight();
            System.out.println("ImageWidth->"+ImageWidth+"ImageHeight->"+ImageHeight);
            width = ImageWidth/col;
            height = ImageHeight/row;
        }catch(IOException e) {
            System.out.println(e);
        }

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                num = i*col+j;//表示当前这个图像的坐标id，在数组中的下标
                if(num < row*col-1) {
                    bufnew = buf.getSubimage(width*j, height*i, width, height);
                    icon = new ImageIcon(bufnew);//将图像转化成图标
                }else {
                	//使最后一张图像为空白图像
                    icon = new ImageIcon("back.png");//一张空白图像
                }
                cells[num] = new Cell(icon, num, width, height);//添加图标到每一个BUTTON按钮上面
                cells[num].setLocation(width*j, height*i);
            }
        }
        BlankCell = cells[cells.length-1];//初始化空白格
        for(int i = 0; i < cells.length; i++) {
            this.add(cells[i]);//将每一个按钮添加到当前这个面板上面
            if(i < cells.length-1)
            	cells[i].addMouseListener(this);//空白格不添加监听机制
        }
    }
    
    //乱序----打乱图片的排布顺序
    public void OutOfOrder() {
        Random random = new Random();
        for (Cell cell : cells) {
            int index1 = random.nextInt(cells.length);//cells的长度是9，但是他的上限是9，取不到9，所取值范围是0-8
            int index2 = random.nextInt(cells.length);
            int x = cells[index1].getX();
            int y = cells[index1].getY();//获取下标是index1的数组元素按钮的坐标
            cells[index1].setLocation(cells[index2].getX(), cells[index2].getY());
            cells[index2].setLocation(x, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Cell t = (Cell) e.getSource();
        int x = BlankCell.getX();
        int y = BlankCell.getY();
        	//图像向右走
        if(t.getY() == y && t.getX() + ImageWidth/col == x) {
            t.move(Direction.RIGHT);
            BlankCell.move(Direction.LEFT);
        } else if(t.getY() == y && t.getX() - ImageWidth/col == x) {
        	//图像向左走
            t.move(Direction.LEFT);
            BlankCell.move(Direction.RIGHT);
        } else if(t.getX() == x && t.getY() + ImageHeight/row == y) {
        	//图像向上走
            t.move(Direction.UP);
            BlankCell.move(Direction.DOWN);
        } else if(t.getX() == x && t.getY() - ImageHeight/row == y) {
        	//图像向下走
            t.move(Direction.DOWN);
            BlankCell.move(Direction.UP);
        }
        this.count++;
        this.countlabel.setText("已走"+count+"步");
    }



    public JLabel getCountlabel() {
		return countlabel;
	}

	@Override
    public void mousePressed(MouseEvent e) {}



    @Override
    public void mouseReleased(MouseEvent e) {}



    @Override
    public void mouseEntered(MouseEvent e) {}



    @Override
    public void mouseExited(MouseEvent e) {}

}


