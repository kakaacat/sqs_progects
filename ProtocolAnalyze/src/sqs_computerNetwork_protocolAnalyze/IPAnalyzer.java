package sqs_computerNetwork_protocolAnalyze;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
/**
 * ��ɹ��ܡ���ץ������IPЭ��
 * �����ˡ���@�鳤ʯ��˧41709050303
 * ����ںϲ��޸ġ���@�鳤ʯ��˧
 * */
public class IPAnalyzer extends WinForm{
	private JpcapCaptor jpcap;

	
	private JFrame jf;
	private JTextArea textArea;
	JMenuBar jMenuBar = new JMenuBar();
	JMenu backToHome = new JMenu(" �� �� �� �� �� ");
	JMenu again = new JMenu(" �� �� ץ ȡ �� ��  ");
	JMenu save = new JMenu(" �� ��  ");
	JMenu openFile = new JMenu(" �� �� �� �� ");
	int fFlag = 1;
	
	IPAnalyzer(){
		super.bind.printDevices();
	}
		
	//����
	public void analyze(JFrame supJf) {
		this.winForm(supJf);
	
		textArea.append("�豸�б�" + "\n");
		for (int i = 0; i < bind.deviceDescribe.length; i++) {
			textArea.append(bind.deviceDescribe[i] + "\n");;
		}
		textArea.append("��ǰѡ��������豸��\n" + bind.outpuDev());
		textArea.append("\n---------------------------------\n");
		textArea.append("\n����10�γ���ץ��......\n");
		jpcap = bind.selectDevice();
		for (int i = 0,k = 0; i < 10 && k < 100; i++,k++) {

			Packet packet = jpcap.getPacket(); //ץ��
			textArea.append("\n�� " + (i+1) +" �γ���ץ��......\n" );
			if(packet instanceof IPPacket && ((IPPacket)packet).version == 4) {
				//k = 100;
				IPPacket ip = (IPPacket) packet;
				textArea.append("IP����:\n"+ ip.toString());	
				textArea.append("\nIP���ݱ�����: " + Short.toString(ip.length));
				//��ӡIP�ײ�
				textArea.append("\nIP�ײ���\n");
				byte[] ipHead = ip.header;
				for (int j = 0; j < ipHead.length; j++) {
					textArea.append(Integer.toHexString(ipHead[j] & 0xFF) + " ");
					if ((j+1)%16 == 0) {
						textArea.append("\n");
					}	
				}
				//��ӡIP���ݲ���
				textArea.append("\nIP���ݲ��֣�\n");
				byte[] ipData = ip.data;
				for (int j = 0; j < ipData.length; j++) {
					textArea.append(Integer.toHexString(ipData[j] & 0xFF) + " ");
					if ((j+1)%16 == 0) {
						textArea.append("\n");
					}
				}
				//��ӡIP�ײ���Ϣ
				textArea.append("\nIP�ײ���Ϣ");
				textArea.append("\n�汾: IPv4 ");
				textArea.append("\n����Ȩ: " + ip.priority);
				textArea.append("\n���ַ���(����������): " + ip.t_flag );
				textArea.append("\n���ַ���(��ߵĿɿ���): " + ip.r_flag );
				textArea.append("\n�ܳ���: " + ip.length );
				textArea.append("\n��ʶ: " + ip.ident );
				textArea.append("\nMF(���з�Ƭ): " + ip.more_frag);
				textArea.append("\nDF(û�з�Ƭ): " + ip.dont_frag);
				textArea.append("\nƬƫ��: " + ip.offset);
				textArea.append("\n����ʱ��: " + ip.hop_limit);
				
				String protocol = "";
				switch (new Integer(ip.protocol)) {
				case 1: protocol = "ICMP";break;
				case 2: protocol = "IGMP";break;
				case 6: protocol = "TCP";break;
				case 8: protocol = "EGP";break;
				case 9: protocol = "IGP";break;
				case 17:protocol = "UDP";break;
				case 41:protocol = "IPv6";break;
				case 89:protocol = "OSPF";break;
				default:break;
				}
				
				textArea.append("\nЭ��: " + protocol);
				textArea.append("\nԴIP: " + ip.src_ip.getHostAddress());
				textArea.append("\nĿ��IP: " + ip.dst_ip.getHostAddress());
				textArea.append("\n-----------------------------------------------------------------\n");
			}else {
				textArea.append("\n���ź����� " + (i+1) + " �γ��ԣ�û��ץ�����ϵİ���\n");
			}
				
//			}else if(k < 100){
//				--i;
//			}if (k == 99) {
//				textArea.append("\nץ��ʧ��!\n���������豸ѡ��\n");
//			}
			
		}
	}
	
	@Override
	//�������
	public void winForm(JFrame supJf) {
		jf = new JFrame("IPץ�������");
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
				IPAnalyzer ip = new IPAnalyzer();
				ip.analyze(supJf);
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
