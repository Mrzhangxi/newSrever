package utils;

public interface Options {
	/**
	 * ����Э�����������״̬����ȡӲ��������Э���ַ��������ͻ�֪Ϊ�����������й����������Ĳ���
	 * @param agreement ������Э���ַ���
	 * @return
	 */
	public String hkeepLivePacket(String agreement);
	
	/**
	 * ����Э�����������״̬����ȡӲ��������Э���ַ��������ͻ�֪Ϊ���ϰ������й��ڹ��ϰ��Ĳ���
	 * @param agreement ������Э���ַ���
	 * @return
	 */
	public String hFaultPacket(String agreement);
	
	/**
	 * ����Э�����������״̬����ȡӲ��������Э���ַ��������ͻ�֪Ϊ���ݰ������й������ݰ��Ĳ���
	 * @param agreement ������Э���ַ���
	 * @return
	 */
	public String hDatePacket(String agreement);
	
	/**
	 * ����Э�����������״̬����ȡӲ��������Э���ַ��������ͻ�֪Ϊ״̬�������й���״̬���Ĳ���
	 * @param agreement ������Э���ַ���
	 * @return 
	 */
	public String hStatePacket(String agreement);
	
	/**
	 * ��Э���ַ����н�������Ҫע����û���������
	 * @param agreement Э���ַ���
	 * @return ����ɹ�����"OK"
	 */
	public String registeNewUser(String agreement);
	
	/**
	 * ��Э���ַ����н�������Ҫ��¼���û���������
	 * @param agreement Э���ַ���
	 * @return ����ɹ�����"OK"
	 */
	public String loginUser(String agreement);
	
	/**
	 * ��ʾ��ǰ�û������ҵ�ʵʱ��������
	 * @param username Ҫ��ѯ���û���
	 * @return �ɹ����ء�OK��
	 */
	public String showCurrentData(String username);
	
	/**
	 * ��ʾ��ǰ�û������ҵ���ϸʵʱ��������
	 * @return �ɹ����ء�OK��
	 */
	public String showNewDetailData(String username);
	
	/**
	 * ��ʾ��ǰ�û������ҵ�24Сʱ��ϸ��������
	 * @return �ɹ����ء�OK��
	 */
	public String showDayDetailData(String username);
	
	/**
	 * ��ʾ��ǰ�û������ҵ�������ϸ��������
	 * @return �ɹ����ء�OK��
	 */
	public String showThreeDayDetailData(String username);
	
	/**
	 * ��ʾ��ǰ�û������ҵ�������ϸ��������
	 * @return �ɹ����ء�OK��
	 */
	public String showSevenDayDetailDate(String username);
}
