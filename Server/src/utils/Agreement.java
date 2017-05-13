package utils;

import java.io.InputStream;

public interface Agreement {
	/**
	 * 获得输入流，从输入流中拿到协议后返回
	 * @param is 需要从该输入流中获得协议数据
	 * @return 返回解析处来的协议数据
	 */
	public String getAgreement(InputStream is);
	/**
	 * 根据协议解析判断接入设备是否为Android设备
	 * @param agreement 传入的协议
	 * @return 如果为真，为Android设备
	 */
	public boolean ifAndroid(String agreement);
	
	/**
	 * 根据协议解析判断接入设备是否为硬件设备
	 * @param agreement 传入的协议
	 * @return 如果为真，为硬件设备
	 */
	public boolean ifHardware(String agreement);
	
}
