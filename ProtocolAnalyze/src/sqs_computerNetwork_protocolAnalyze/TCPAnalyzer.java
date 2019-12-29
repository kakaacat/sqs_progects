package sqs_computerNetwork_protocolAnalyze;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import jpcap.JpcapCaptor;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
/**
 * ��ɹ��ܡ���ץ������TCPЭ��
 * �����ˡ���@��Ա������41709050301
 * ����ںϲ��޸ġ���@��Ա�����С�@�鳤ʯ��˧
 * */
public class TCPAnalyzer extends WinForm{
//	private BindNetDevice bind = new BindNetDevice();
	private JpcapCaptor jpcap;
	
	private JFrame jf;
	private JTextArea textArea;
	JMenuBar jMenuBar = new JMenuBar();
	JMenu backToHome = new JMenu(" �� �� �� �� �� ");
	JMenu again = new JMenu(" �� �� ץ ȡ �� ��  ");
	JMenu save = new JMenu(" �� ��  ");
	JMenu openFile = new JMenu(" �� �� �� �� ");
	int fFlag = 1;
	
	TCPAnalyzer(){
		bind.printDevices();

	}
	
	public void analyze(JFrame supJf) {
		this.winForm(supJf);
	
		textArea.append("�豸�б�" + "\n");
		for (int i = 0; i < bind.deviceDescribe.length; i++) {
			textArea.append(bind.deviceDescribe[i] + "\n");
		}
		textArea.append("��ǰѡ��������豸��\n" + bind.outpuDev());
		textArea.append("\n---------------------------------\n");
		textArea.append("\n���ڳ���10��ץ��......\n");
		jpcap = bind.selectDevice();	
		for (int i = 0; i < 10; i++) {

			Packet packet = jpcap.getPacket(); //ץ��
			textArea.append("\n�� " + (i+1) +" �γ���ץ��......\n" );
			if(packet instanceof TCPPacket) {
				TCPPacket tcp = (TCPPacket) packet; //ǿת
				textArea.append("TCP����:\n" + tcp.toString());	
				
				//��ӡTCP�ײ���Ϣ
				textArea.append("\nԴ�˿�:  " + tcp.src_port);
				textArea.append("\nĿ�Ķ˿�: " + tcp.dst_port);
				textArea.append("\n���: " + tcp.sequence);
				textArea.append("\nȷ�Ϻ�: " + tcp.ack_num);
				textArea.append("\nURG: " + tcp.urg);
				textArea.append("\nACK: " + tcp.ack);
				textArea.append("\nPSH: " + tcp.psh);
				textArea.append("\nRST: " + tcp.rst);
				textArea.append("\nSYN: " + tcp.syn);
				textArea.append("\nFIN: " + tcp.fin);
				textArea.append("\n����: " + tcp.window);
				textArea.append("\n����ָ��: " + tcp.urgent_pointer);
				//TCPѡ��
				byte[] option = tcp.option;
				if (option != null) {
					for (int j = 0; j < option.length; j++) {
						textArea.append(Integer.toHexString(option[j] & 0xFF) + " ");
						if ((j+1)%16 == 0) {
							textArea.append("\n");
						}
					}
				}
				
				textArea.append("\n-----------------------------------------------------------------\n");
			}else {
				textArea.append("\n���ź����� " + (i+1) + " �γ��ԣ�û��ץ�����ϵİ���\n");
			}
		}
	}
	@Override
	//�������
	public void winForm(JFrame supJf) {
		jf = new JFrame("TCPץ�������");
		jf.setSize(1000, 1000);
		jf.setLocationRelativeTo(null);
		textArea = new JTextArea(900,1000);
		textArea.setEditable(false);//���ɱ༭
		textArea.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		//�������
		JScrollPane scrollPane = new JScrollPane(
				textArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, //��ֱ������һֱ��ʾ
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED //ˮƽ������
				);
		
		
		jMenuBar.add(again);
		again.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jf.setVisible(false);
				new TCPAnalyzer().analyze(supJf);
			}
		});
		
		jMenuBar.add(save);
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String finfo = textArea.getText(); //�ı�����
				String fName = jf.getTitle();	//�ı���
				

				File file = new File(fName + ".txt");
				if (file.exists()) {
					fName = fName + (fFlag++);
					file = new File(fName + ".txt");
				}
				PrintWriter output;//�����ļ�
				try {
					output = new PrintWriter(file);
					output.print(finfo);
					output.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(
						jf,
						"����ɹ�",
						"�ļ�����", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		jMenuBar.add(openFile);
		openFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().open(new File("D:\\Eclipse\\eclipse\\projects\\ProtocolAnalyze"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		jMenuBar.add(backToHome);
		backToHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jf.setVisible(false);
				supJf.setVisible(true);
			}
		});
		jf.setJMenuBar(jMenuBar);
		jf.setContentPane(scrollPane);
		jf.setVisible(true);
		
	}
}
