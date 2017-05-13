package utils;

import java.io.InputStream;

public interface Agreement {
	/**
	 * ����������������������õ�Э��󷵻�
	 * @param is ��Ҫ�Ӹ��������л��Э������
	 * @return ���ؽ���������Э������
	 */
	public String getAgreement(InputStream is);
	/**
	 * ����Э������жϽ����豸�Ƿ�ΪAndroid�豸
	 * @param agreement �����Э��
	 * @return ���Ϊ�棬ΪAndroid�豸
	 */
	public boolean ifAndroid(String agreement);
	
	/**
	 * ����Э������жϽ����豸�Ƿ�ΪӲ���豸
	 * @param agreement �����Э��
	 * @return ���Ϊ�棬ΪӲ���豸
	 */
	public boolean ifHardware(String agreement);
	
}
