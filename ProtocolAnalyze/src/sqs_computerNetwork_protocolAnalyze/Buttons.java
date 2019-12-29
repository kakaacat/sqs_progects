package sqs_computerNetwork_protocolAnalyze;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
/**
 * ���水ť���� 
 * ��Ӧ����Ӧ�Ĳ���
 * */
public class Buttons {

		JLabel label2;
		JPanel jp1;
		//Э��ѡ��
		JLabel label;
		JPanel jp2;
		String[] list;
		JComboBox<String> jComboBox;
		//��ʼ��ť
		JButton start;
		JPanel jp3;
		//�����豸ѡ��
		JRadioButton wlanButton;
		JRadioButton internetButton;
		ButtonGroup btnGroup;
		JPanel jp4;		
			
		Buttons(JFrame jf){
			label2 = new JLabel(" ��ӭʹ��Э��������  ");
			label2.setFont(new Font("����", Font.BOLD, 20));
			jp1 = new JPanel();
			jp1.add(label2);
			
			label = new JLabel("Э��ѡ��");
			list = new String[] {" IP "," TCP "," UDP ","ICMP","MAC","ALL"};
			jComboBox = new JComboBox<String>(list);
			jComboBox.setSelectedIndex(0);	//��ʼѡ��
			
			internetButton = new JRadioButton("��̫��");
			wlanButton = new JRadioButton("WLAN");
			jp4 = new JPanel();
			btnGroup = new ButtonGroup();
			btnGroup.add(internetButton);
			btnGroup.add(wlanButton);
			wlanButton.setSelected(true);//��ʼѡ��wlan			
			jp4.add(internetButton);
			jp4.add(wlanButton);
			
			jp2 = new JPanel();
			jp2.add(label);
			jp2.add(jComboBox);
			
			start = new JButton(" ��ʼ����  ");
			jp3 = new JPanel();
			jp3.add(start);
			this.startAnalyze(jf);						
			
			Box box = Box.createVerticalBox();
			box.add(jp1);
			box.add(jp4);
			box.add(jp2);
			box.add(jp3);
			
			jf.add(box);
		}
		
		//��ʼ����
		public void startAnalyze(JFrame jf) {
			start.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					jf.setVisible(false);
					int protocolIndex = jComboBox.getSelectedIndex();
					if (wlanButton.isSelected()) {
						int sel = 2;
						if (protocolIndex == 0) { //����IP
							WinForm ipAnalyzer = new IPAnalyzer();
							ipAnalyzer.bind.setSelDevice(sel);
							ipAnalyzer.analyze(jf);
						}else if (protocolIndex == 1) { //����TCP
							WinForm tcpAnalyzer = new TCPAnalyzer();
							tcpAnalyzer.bind.setSelDevice(sel);
							tcpAnalyzer.analyze(jf);
						}else if (protocolIndex == 2) {	//����UDP
							UDPAnalyzer udpAnalyzer = new UDPAnalyzer();
							udpAnalyzer.bind.setSelDevice(sel);
							udpAnalyzer.analyze(jf);
						}else if (protocolIndex == 3) { //����ICMP
							ICMPAnalyzer icmpAnalyzer = new ICMPAnalyzer();
							icmpAnalyzer.bind.setSelDevice(sel);
							icmpAnalyzer.analyze(jf);
						}else if (protocolIndex == 4) { //����MAC
							MACAnalyzer macAnalyzer = new MACAnalyzer();
							macAnalyzer.bind.setSelDevice(sel);
							macAnalyzer.analyze(jf);
						}else if (protocolIndex == 5) { //ץȡһ�����������
							AllAnalyzer allAnalyzer = new AllAnalyzer();
							allAnalyzer.bind.setSelDevice(sel);
							allAnalyzer.analyze(jf);
						}
					}else if (internetButton.isSelected()) {
						int sel = 0;
						if (protocolIndex == 0) { //����IP
							WinForm ipAnalyzer = new IPAnalyzer();
							ipAnalyzer.bind.setSelDevice(sel);
							ipAnalyzer.analyze(jf);
						}else if (protocolIndex == 1) { //����TCP
							WinForm tcpAnalyzer = new TCPAnalyzer();
							tcpAnalyzer.bind.setSelDevice(sel);
							tcpAnalyzer.analyze(jf);
						}else if (protocolIndex == 2) {	//����UDP
							UDPAnalyzer udpAnalyzer = new UDPAnalyzer();
							udpAnalyzer.bind.setSelDevice(sel);
							udpAnalyzer.analyze(jf);
						}else if (protocolIndex == 3) { //����ICMP
							ICMPAnalyzer icmpAnalyzer = new ICMPAnalyzer();
							icmpAnalyzer.bind.setSelDevice(sel);
							icmpAnalyzer.analyze(jf);
						}else if (protocolIndex == 4) { //����MAC
							MACAnalyzer macAnalyzer = new MACAnalyzer();
							macAnalyzer.bind.setSelDevice(sel);
							macAnalyzer.analyze(jf);
						}else if (protocolIndex == 5) { //ץȡһ�����������
							AllAnalyzer allAnalyzer = new AllAnalyzer();
							allAnalyzer.bind.setSelDevice(sel);
							allAnalyzer.analyze(jf);
						}
					}
				}
			});
		}				
}
