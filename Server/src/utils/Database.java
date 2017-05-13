package utils;

import java.sql.Connection;

import net.sf.json.JSONArray;

public interface Database {	
	
//	/**
//	 * �������ݵ����ݿ⣬��������Ϊ����
//	 * @param data String�������͵�����
//	 * @param createTime ���ݴ���ʱ��
//	 * @return �����Ƿ�ɹ�ִ�У�����ɹ�����Χ��OK��
//	 */
//	public String InsertDataToDB(String[] data, String createTime);
//	
//	/**
//	 * �������ݵ����ݿ⣬��������Ϊ����
//	 * @param data Double�������͵�����
//	 * @param createTime ���ݴ���ʱ��
//	 * @return �����Ƿ�ɹ�ִ�У�����ɹ�����Χ��OK��
//	 */
//	public String InsertDataToDB(Double[] data, String createTime);
//	
//	/**
//	 * �������ݵ����ݿ⣬��������Ϊ����
//	 * @param data Double�������͵�����
//	 * @return �����Ƿ�ɹ�ִ�У�����ɹ�����Χ��OK��
//	 */
//	public String InsertDataToDB(Double[] data);
//	
//	/**
//	 * �������ݵ����ݿ⣬��������Ϊ����
//	 * @param deviceID �豸ID��������������������̨�豸
//	 * @param data String�������͵�����
//	 * @param createTime
//	 * @return
//	 */
//	public String InsertDataToDB(String deviceID, String[] data, String createTime);
//	
	/**
	 * �������ݵ����ݿ⣬��������Ϊ����
	 * @param deviceID �豸ID��������������������̨�豸
	 * @param data Double�������͵�����
	 * @param createTime
	 * @return
	 */
	public String InsertDataToDB(String deviceID, Double[] data, String createTime);
	
//	/**
//	 * �������ݵ����ݿ⣬��������Ϊ����
//	 * @param deviceID �豸ID��������������������̨�豸
//	 * @param data Double�������͵�����
//	 * @return
//	 */
//	public String InsertDataToDB(String deviceID, String[] data);
	
	/**
	 * ���û������û���Ϣ
	 * @param username Ҫ�����û����û���
	 * @param password Ҫ�����û�������
	 * @return �ɹ����ء�OK��
	 */
	public String registeNewUser(String username, String password, String createTime);
	
	/**
	 * ����username������user���в�ѯ���Ƿ���username��ƥ���password��¼
	 * @param username ��¼���û���
	 * @param password ��¼������
	 * @return �ɹ�����"OK"
	 */
	public String loginUser(String username, String password);
	
	/**
	 * �����û�������user���useranddivece���л�ø��û��¶�Ӧ��N̨�豸��ID���
	 * @param username �û���
	 * @return һ�����豸��Device���е�id��ɵ�����
	 */
	public String[] showDeviceOfUser(String username);
	
	/**
	 * ���ݴ����DeviceID(Mac)��elementdata���л�������һ�λ�����ֵ
	 * @param DeviceID һ��DeviceID
	 * @return ������������ɵ�Json����
	 */
	public JSONArray getNewDetailData(String DeviceID);
	
	/**
	 * ���ݴ����DeviceID(Mac)��elementdata���л�������һ�λ�����ֵ
	 * @param DeviceID һ��DeviceID����
	 * @return �����ɶ�̨������ɵ�Json����
	 */
	public JSONArray getNewDetailData(String[] DeviceID);
	
	/**
	 * ���ݴ����DeviceID(Mac)��elementdata���л�������days��Ļ�����ֵ
	 * @param DeviceID һ��DeviceID
	 * @param days �ؿ�days�������
	 * @param k ���ݲȵ����������ֵ
	 * @return ������������ɵ�Json����
	 */
	public JSONArray getHistoricalData(String DeviceID, int days, int k);
	/**
	 * ���ݴ����DeviceID(Mac)��elementdata���л�������days��Ļ�����ֵ
	 * @param DeviceID һ��DeviceID����
	 * @param days �ؿ�days�������
	 * @param k ���ݲȵ����������ֵ
	 * @return �����ɶ�̨������ɵ�Json����
	 */
	public JSONArray getHistoricalData(String[] DeviceID, int days, int k);
}
