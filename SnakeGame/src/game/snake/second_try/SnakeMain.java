package game.snake.second_try;

import javax.swing.JFrame;

public class SnakeMain {

	public static void main(String[] args) {
		//����һ������
		JFrame frame = new JFrame();
		frame.setBounds(500, 200, 950, 750); //���ڵ�λ�ô�С
		frame.setTitle("̰����1.0");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رշ�ʽ
		frame.setResizable(false);
		
		//��Ϸ����
		SnakeGamePanel snakeGamePanel = new SnakeGamePanel();
		frame.add(snakeGamePanel);
		
		frame.setVisible(true);
	}

}
