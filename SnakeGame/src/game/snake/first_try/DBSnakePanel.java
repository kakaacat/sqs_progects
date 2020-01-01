package game.snake.first_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DBSnakePanel extends JPanel implements KeyListener,Runnable {
	//����ͼƬ
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon title = new ImageIcon("title.png");
	
	//���������������ڵ������
	int[] snakex = new int[850];
	int[] snakey = new int[850];
	
	int len = 3;  //��ʼ����
	int score = 0;//��ʼ����
	String direction = "R"; //��ʼ����
	int n = 150;
	
	
	Random random = new Random();
	//�������ʳ��
	int foodx = random.nextInt(36) * 25 + 25; //25-925
	int foody = random.nextInt(24) * 25 + 75; //75-675
	
	boolean foodFitFlag = true;
	boolean isStarted = false;
	boolean isFaild = false;
	
	public Image iBuffer;
	public Graphics gBUffer;
	
	public DBSnakePanel() {
		this.setFocusable(true); 
		initSnake();
		this.addKeyListener(this);
		Thread t = new Thread(this);
		t.start();
	}
	
	//��ʼ��
	public void initSnake() {
		isFaild = false;
		isStarted = false;
		len = 3;
		score = 0;
		n = 150;
		direction = "R";
		snakex[0] = 100; 
		snakey[0] = 100; 
		snakex[1] = 75; 
		snakey[1] = 100; 
		snakex[2] = 50; 
		snakey[2] = 100;	
	}
	
	//�ж�ʳ������λ���Ƿ�������
	public boolean isFoodFit() {
		for (int i = 0; i < len; i++) {
			if(foodx == snakex[i] && foody == snakey[i]) {
				return false; //ʳ������������
			}
		}
		return true;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(n);
			} catch (Exception e) {}
			
			if(isStarted && !isFaild) {
				//�����ƶ�----ÿ����������ǰ���������
				for(int i=len; i>.0; i--) {
					snakex[i] = snakex[i-1];
					snakey[i] = snakey[i-1];
				}
			//��ͷ�ƶ�
			if(direction.equals("R")) {
				snakex[0] = snakex[0] + 25;
				if(snakex[0] > 900) snakex[0] = 25;
			}else if(direction.equals("L")) {
				snakex[0] = snakex[0] - 25;	
				if(snakex[0] < 25) snakex[0] = 900;
			}else if(direction.equals("U")) {
				snakey[0] = snakey[0] - 25;
				if(snakey[0] < 75) snakey[0] = 675;
			}else if(direction.equals("D")) {
				snakey[0] = snakey[0] + 25;	
				if(snakey[0] > 675) snakey[0] = 75;
			}
			if(snakex[0] == foodx && snakey[0] == foody) {
				len++;
				score++;
				//if(score % 3 == 0) n = n - 10;  
				foodx = random.nextInt(36) * 25 + 25;
				foody = random.nextInt(22) * 25 + 75;
			}
			//ʧ���ж�
			for(int i = 1; i < len; i++) {
				if(snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
					isFaild = true;
				}
			}
		}
		repaint();
		}
	}
	/** ����*/
	public void paint(Graphics g) {
		if(iBuffer == null) {
			iBuffer = createImage(this.getSize().width, this.getSize().height);
			gBUffer = iBuffer.getGraphics();
		}
		//������
		gBUffer.setColor(Color.BLACK);
		gBUffer.fillRect(25, 75, 900, 625);
		//����ͷ
		title.paintIcon(this, gBUffer, 25, 10);
		//����ͷ
		if(direction.equals("R")) {
			right.paintIcon(this, gBUffer, snakex[0], snakey[0]);	
		}else if(direction.equals("L")) {
			left.paintIcon(this, gBUffer, snakex[0], snakey[0]);	
		}else if(direction.equals("U")) {
			up.paintIcon(this, gBUffer, snakex[0], snakey[0]);	
		}else if(direction.equals("D")) {
			down.paintIcon(this, gBUffer, snakex[0], snakey[0]);	
		}
		//������
		for(int i=1; i<len; i++) {
			body.paintIcon(this, gBUffer, snakex[i], snakey[i]);
		}
		//��ʼ��Ϸ��ʾ��Ļ
		if(!isStarted) {
			gBUffer.setColor(Color.WHITE);
			gBUffer.setFont(new Font("arial",Font.BOLD,30));
			gBUffer.drawString("Press Space to Start or Pause", 230, 350);
		}
		//��Ϸ������ʾ
		if(isFaild) {
			gBUffer.setColor(Color.WHITE);
			gBUffer.setFont(new Font("arial",Font.BOLD,30));
			gBUffer.drawString("Game Over,Press Space to Start", 230, 350);
		}
		//��ʳ��
		foodFitFlag = true;
		while(foodFitFlag) {
			if (isFoodFit()) {
				//ʳ��λ�ú���
				food.paintIcon(this, gBUffer, foodx, foody);
				foodFitFlag = false;
			}else {
				//��������������
				foodx = random.nextInt(36) * 25 + 25;
				foody = random.nextInt(24) * 25 + 75;
			}
		}
		
		//������ʾ
		gBUffer.setColor(Color.WHITE);
		gBUffer.setFont(new Font("arial",Font.PLAIN,15));
		gBUffer.drawString("Score: " + score, 800, 35);
		gBUffer.drawString("Length: " + len, 800, 55);
		
		//��ͼ��һ���ԵĻ��Ƶ���Ļ��
		g.drawImage(iBuffer, 0, 0, this);
	}
	//����Ҫ��дupdate��������������˸
	@Override
	public void update(Graphics g) {
		paint(g);
	}

	/** �����¼�*/
	@Override
	public void keyPressed(KeyEvent arg0) {
		//��ʼ��ͣ����
		int keyCode = arg0.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(isFaild) {
				initSnake();
			}else{
				isStarted = !isStarted;
			}
		//���ƶ�����
		}else if(keyCode == KeyEvent.VK_UP && !direction.equals("D")) {
			direction = "U";
		}else if(keyCode == KeyEvent.VK_DOWN && !direction.equals("U")) {
			direction = "D";
		}else if(keyCode == KeyEvent.VK_LEFT && !direction.equals("R")) {
			direction = "L";
		}else if(keyCode == KeyEvent.VK_RIGHT && !direction.equals("L")) {
			direction = "R";
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
}
