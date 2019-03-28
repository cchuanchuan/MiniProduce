/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle_game;
import java.awt.Rectangle;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Administrator
 */
public class Cell extends JButton
{
    private static int IMAGEWIDTH;//设置按钮的宽度大小

    private static int IMAGEHEIGHT;

    private int ID = 0;//设置当前按钮的指向坐标

    public Cell(Icon icon, int id, int imagewidth, int height)//构造函数初始化，传入两个参数，一个是图像的图标，一个是该按钮的数组ID

    {

        this.setIcon(icon);

        this.ID = id;

        Cell.IMAGEWIDTH = imagewidth;

        Cell.IMAGEHEIGHT = height;

        this.setSize(IMAGEWIDTH, IMAGEHEIGHT);

    }

    public void move(Direction dir)//移动

    {

        Rectangle rec = this.getBounds();//获取当前对象的这个边框

        switch(dir)

        {

            case UP://向上移动，改变坐标

            this.setLocation(rec.x, rec.y + IMAGEHEIGHT);

            break;

            case DOWN://向下移动

            this.setLocation(rec.x, rec.y - IMAGEHEIGHT);

            break;

            case LEFT://向左移动

            this.setLocation(rec.x - IMAGEWIDTH, rec.y);

            break;

            case RIGHT://向右移动

            this.setLocation(rec.x + IMAGEWIDTH, rec.y);

            break;

        }

    }

    public int getID() {
        return ID;
    }

    @Override
    public int getX() {

        return this.getBounds().x;

    }

    @Override
    public int getY() {

        return this.getBounds().y;

    }

}
