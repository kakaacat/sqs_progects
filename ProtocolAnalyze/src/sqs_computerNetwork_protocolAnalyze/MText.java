package sqs_computerNetwork_protocolAnalyze;

import javax.swing.JFrame;
/**
 * ������
 * �����ˡ���@�鳤ʯ��˧
 * */
public class MText {

	public static void main(String[] args) {
		JFrame jf = new JFrame("Э��������");
		jf.setSize(360, 200);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false); //��С���ɱ�

		Buttons starts = new Buttons(jf);
		
		jf.setVisible(true);
		
	}

}
