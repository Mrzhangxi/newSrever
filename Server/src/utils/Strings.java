package utils;

public interface Strings {
	/**
	 * ��Э���ַ����л�ȡ״̬�룬���ж�����ʲô�豸������ʲô����
	 * @param agreement Э���ַ���
	 * @return ������λ���ֵ�״̬��
	 */
	public String getStateCode(String agreement);
	
	/**
	 * ��Э���ַ����л�ȡӲ���豸��ΨһID���
	 * @param agreement Э���ַ���
	 * @return ����ID���
	 */
	public String getHardwardId(String agreement);
	
	/**
	 * ��Э���ַ����л�ȡ�ɷ����������Android�豸��Ψһ���
	 * @param agreement Э���ַ���
	 * @return ����ID���
	 */
	public String getAndroidID(String agreement);
	
	/**
	 * ��Э���ַ����л�ò����룬��ȷ��Android�豸Ҫ���еĲ�������
	 * @param agreement Э���ַ��� 18-22
	 * @return ���ػ�õĲ�����
	 */
	public String getAndroidOptionCode(String agreement);
	
	/**
	 * ��ȡ��ǰ��ϵͳʱ��
	 * @return ���ַ�������ʽ���ص�ǰ��ϵͳʱ��
	 */
	public String getCurrentTime(/*String currentTime*/);
	
	/**
	 * ��ȡ��ǰʱ�����1970��ĺ�����
	 * @return ���ַ�������ʽ���ص�ǰʱ�����1970�굽����ʱ��ĺ�����
	 */
	public String getTimeWatermark(/*String timeWatermark*/);
	
	/**
	 * ����Э��������ݰ������л����Ӧ�Ļ�������
	 * @param agreement Э���ַ���
	 * @return ���ػ���������ɵ�Double���飬���������¶ȡ�����ʪ�ȡ�����ʪ��
	 */
	public Double[] getEnvironmentData(String agreement);
	
	/**
	 * ����Э���ַ�������ȡ�����¶�
	 * @param agreement Э���ַ��������ݰ�
	 * @return ����һ��Double�͵Ŀ����¶���ֵ
	 */
	public Double getAirTemp(String agreement);
	
	/**
	 * ����Э���ַ�������ȡ����ʪ��
	 * @param agreement Э���ַ��������ݰ�
	 * @return ����һ��Double�͵Ŀ���ʪ����ֵ
	 */
	public Double getAirHumidity(String agreement);
	
	/**
	 * ����Э���ַ�������ȡ����ʪ��
	 * @param agreement Э���ַ��������ݰ�
	 * @return ����һ��Double�͵�����ʪ����ֵ
	 */
	public Double getSoilHumidity(String agreement);
	
	/**
	 * ����Э���н�ȡ������4λ���������С���㣬ʹ���Ϊ��Ȼ������������ʪ��
	 * @param data ��Э���н�����������ʪ����ֵ
	 * @return ���С�����Ժ�תΪ��Ȼ������������ֵ
	 */
	public String insertSpotToEnvironmentData(String data);
	
	/**
	 * ��Э���н�������¼���û�����4-18��
	 * @param agreeement Э���ַ���
	 * @return �����������û���
	 */
	public String getUsername(String agreeement);
}
