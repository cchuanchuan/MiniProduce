package puzzle_game;

import javax.swing.JLabel;

public class TimeJLabel extends JLabel implements Runnable{
	private int minute = 0;
	private int second = 0;
	public TimeJLabel() {
		super("");
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		while(true) {
			this.setText("游戏时间:"+minute+"分"+second+"秒");
			if(second<60) {
				second++;
			}else {
				second=0;
				minute++;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public void reSet() {
		minute = 0;
		second = 0;
	}
}
