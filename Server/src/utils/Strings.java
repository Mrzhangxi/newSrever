package utils;

public interface Strings {
	/**
	 * 从协议字符串中获取状态码，来判断属于什么设备，进行什么操作
	 * @param agreement 协议字符串
	 * @return 返回四位数字的状态码
	 */
	public String getStateCode(String agreement);
	
	/**
	 * 从协议字符串中获取硬件设备的唯一ID编号
	 * @param agreement 协议字符串
	 * @return 返回ID编号
	 */
	public String getHardwardId(String agreement);
	
	/**
	 * 从协议字符串中获取由服务器分配给Android设备的唯一编号
	 * @param agreement 协议字符串
	 * @return 返回ID编号
	 */
	public String getAndroidID(String agreement);
	
	/**
	 * 从协议字符串中获得操作码，来确定Android设备要进行的操作请求
	 * @param agreement 协议字符串 18-22
	 * @return 返回获得的操作码
	 */
	public String getAndroidOptionCode(String agreement);
	
	/**
	 * 获取当前的系统时间
	 * @return 以字符串的形式返回当前的系统时间
	 */
	public String getCurrentTime(/*String currentTime*/);
	
	/**
	 * 获取当前时间距离1970年的毫秒数
	 * @return 以字符串的形式返回当前时间距离1970年到现在时间的毫秒数
	 */
	public String getTimeWatermark(/*String timeWatermark*/);
	
	/**
	 * 根据协议解析数据包，从中获得相应的环境数据
	 * @param agreement 协议字符串
	 * @return 返回环境参数组成的Double数组，包括空气温度、空气湿度、土壤湿度
	 */
	public Double[] getEnvironmentData(String agreement);
	
	/**
	 * 根据协议字符串，获取空气温度
	 * @param agreement 协议字符串，数据包
	 * @return 返回一个Double型的空气温度数值
	 */
	public Double getAirTemp(String agreement);
	
	/**
	 * 根据协议字符串，获取空气湿度
	 * @param agreement 协议字符串，数据包
	 * @return 返回一个Double型的空气湿度数值
	 */
	public Double getAirHumidity(String agreement);
	
	/**
	 * 根据协议字符串，获取土壤湿度
	 * @param agreement 协议字符串，数据包
	 * @return 返回一个Double型的土壤湿度数值
	 */
	public Double getSoilHumidity(String agreement);
	
	/**
	 * 将从协议中截取出来的4位数字中添加小数点，使其变为自然语言描述的温湿度
	 * @param data 从协议中解析出来的温湿度数值
	 * @return 添加小数点以后，转为自然语言描述的数值
	 */
	public String insertSpotToEnvironmentData(String data);
	
	/**
	 * 从协议中解析出登录的用户名（4-18）
	 * @param agreeement 协议字符串
	 * @return 解析出来的用户名
	 */
	public String getUsername(String agreeement);
}
