package sqs.javaPractice.Tree;

public interface Tree<E> extends Iterable<E> {

	//���Ԫ�������ﷵ��true
	public boolean search(E e);
	//����һ��Ԫ��,�ɹ��򷵻�true
	public boolean insert(E e);
	//ɾ���ض�Ԫ��,�ɹ��򷵻�true
	public boolean delete(E e);
	//�и�������
	public void inorder();
	//�������
	public void postorder();
	//�ȸ�����
	public void preorder();
	//���Ľ����
	public int getSize();
	//�ж����շ�
	public boolean isEmpty();	
}
