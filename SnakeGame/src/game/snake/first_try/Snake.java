package game.snake.first_try;

import javax.swing.JFrame;

public class Snake {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setBounds(550, 200, 950, 750);	//�߽�
		frame.setTitle("̰����1.0");		//����
		frame.setResizable(false);		//�ܷ�ı��С
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�˳���ʽ
		
		
		//SnakePanel panel = new SnakePanel();
		//frame.add(new SnakePanel());
		frame.add(new DBSnakePanel());
		
		frame.setVisible(true);
	}

}
