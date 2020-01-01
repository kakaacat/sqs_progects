package game.snake.second_try;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGamePanel extends JPanel implements KeyListener, ActionListener{
	//����ͼƬ
	ImageIcon up = new ImageIcon("up.png");
	ImageIcon down = new ImageIcon("down.png");
	ImageIcon right = new ImageIcon("right.png");
	ImageIcon left = new ImageIcon("left.png");
	ImageIcon body = new ImageIcon("body.png");
	ImageIcon food = new ImageIcon("food.png");
	ImageIcon title = new ImageIcon("title.png");

	int[] snakex = new int[850];
	int[] snakey = new int[850];
	
	int len = 3;
	int score = 0;
	String direction = "R";
	
	Random random = new Random();
	//�������ʳ��
	int foodx = random.nextInt(36) * 25 + 25; //25-925
	int foody = random.nextInt(24) * 25 + 75; //75-675
	
	boolean flag = true;
	boolean isStarted = false;
	boolean isFaild = false;
	
	Timer timer = new Timer(150,this);
	
	public SnakeGamePanel() {
		this.setFocusable(true);
		initSnake();
		this.addKeyListener(this);
		timer.start();
	}
	
	//��ʼ��
	public void initSnake() {
		isFaild = false;
		isStarted = false;
		len = 3;
		score = 0;
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
	
	/**
	 * ����*/
	public void paint(Graphics g) {
		//������
		this.setBackground(Color.BLACK);
		g.fillRect(25, 75, 900, 625);
		//����ͷ
		title.paintIcon(this, g, 25, 10);
		//����ͷ
		if(direction.equals("R")) {
			right.paintIcon(this, g, snakex[0], snakey[0]);	
		}else if(direction.equals("L")) {
			left.paintIcon(this, g, snakex[0], snakey[0]);	
		}else if(direction.equals("U")) {
			up.paintIcon(this, g, snakex[0], snakey[0]);	
		}else if(direction.equals("D")) {
			down.paintIcon(this, g, snakex[0], snakey[0]);	
		}
		//������
		for(int i=1; i<len; i++) {
			body.paintIcon(this, g, snakex[i], snakey[i]);
		}
		//��ʼ��Ϸ��ʾ��Ļ
		if(!isStarted) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("΢���ź�",Font.BOLD,30));
			g.drawString("�밴�ո����ʼ����ͣ", 300, 350);
		}
		//��Ϸ������ʾ
		if(isFaild) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("΢���ź�",Font.BOLD,30));
			g.drawString("��Ϸ���������ո����¿�ʼ", 300, 350);
		}
		//��ʳ��
		flag = true;
		while(flag) {
			if (isFoodFit()) {
				//ʳ��λ�ú���
				food.paintIcon(this, g, foodx, foody);
				flag = false;
			}else {
				//��������������
				foodx = random.nextInt(36) * 25 + 25;
				foody = random.nextInt(24) * 25 + 75;
			}
		}
		
		//������ʾ
		g.setColor(Color.WHITE);
		g.setFont(new Font("����",Font.BOLD,15));
		g.drawString("Score: " + score, 800, 35);
		g.drawString("Length: " + len, 800, 55);
	}

	/**
	 * �����¼�*/
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

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
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
