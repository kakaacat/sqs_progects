package sqs_computerNetwork_protocolAnalyze;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
/**
 * �������豸
 * */
public class BindNetDevice {
	
	private NetworkInterface[] devices = JpcapCaptor.getDeviceList();
	protected int selDevice = 2;
	private JpcapCaptor jpcap;
	String[] deviceDescribe = new String[devices.length+1];
	
	//���������豸�б�
	protected void printDevices() {
		for (int i = 0; i < devices.length; i++) {
			deviceDescribe[i] = devices[i].name + "\t" +"|   " + devices[i].description;
		}
		deviceDescribe[devices.length] = "-------------------------------------------------------------";
	}
	//ѡ�������豸
	public void setSelDevice(int selDevice) {
		this.selDevice = selDevice;
	}
	public String outpuDev() {
		return devices[selDevice].name + "\t" +"|   " + devices[selDevice].description;
	}
	//���������豸
	public JpcapCaptor selectDevice() {
		try {
			jpcap = JpcapCaptor.openDevice(devices[selDevice],65535, false, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jpcap;
	}
	
}
