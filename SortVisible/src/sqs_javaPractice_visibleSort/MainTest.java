package sqs_javaPractice_visibleSort;
/**
 * ������
 * the entrance of this program
 * �ڲ�����
 * ->����������
 * -->ֱ�Ӳ�������(insert sort)
 * -->�۰��������(binary sort)
 * -->ϣ������(shell sort)
 * ->����������
 * -->ð������(bubble sort)
 * -->��������(quick sort)
 * ->ѡ��������
 * -->��ѡ������
 * -->����ѡ������
 * -->������
 * ->�鲢����
 * ->����������
 * -->��ؼ�������
 * -->��ʽ��������
 * */
public class MainTest {
	public static void main(String[] args) {
		//ֱ�Ӳ�������(insert sort)
		InsertSort insertSort = new InsertSort();
		insertSort.startSort();
		//ð������(bubble sort)
		BubbleSort bubbleSort = new BubbleSort();
		bubbleSort.startSort();
		//ϣ������(shell sort)
		ShellSort shellSort = new ShellSort();
		shellSort.startSort();
		//��������(quick sort)
		QuickSort quickSort = new QuickSort();
		quickSort.startSort();
		
		//�۰��������(binary sort)
		BinarySort binarySort = new BinarySort();
		binarySort.startSort();
	}
}
