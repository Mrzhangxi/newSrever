package utils;

public interface Options {
	/**
	 * 根据协议解析出来的状态，获取硬件发来的协议字符串的类型获知为心跳包，进行关于心跳包的操作
	 * @param agreement 完整的协议字符串
	 * @return
	 */
	public String hkeepLivePacket(String agreement);
	
	/**
	 * 根据协议解析出来的状态，获取硬件发来的协议字符串的类型获知为故障包，进行关于故障包的操作
	 * @param agreement 完整的协议字符串
	 * @return
	 */
	public String hFaultPacket(String agreement);
	
	/**
	 * 根据协议解析出来的状态，获取硬件发来的协议字符串的类型获知为数据包，进行关于数据包的操作
	 * @param agreement 完整的协议字符串
	 * @return
	 */
	public String hDatePacket(String agreement);
	
	/**
	 * 根据协议解析出来的状态，获取硬件发来的协议字符串的类型获知为状态包，进行关于状态包的操作
	 * @param agreement 完整的协议字符串
	 * @return 
	 */
	public String hStatePacket(String agreement);
	
	/**
	 * 从协议字符串中解析出来要注册的用户名和密码
	 * @param agreement 协议字符串
	 * @return 如果成功返回"OK"
	 */
	public String registeNewUser(String agreement);
	
	/**
	 * 从协议字符串中解析出来要登录的用户名和密码
	 * @param agreement 协议字符串
	 * @return 如果成功返回"OK"
	 */
	public String loginUser(String agreement);
	
	/**
	 * 显示当前用户下温室的实时环境参数
	 * @param username 要查询的用户名
	 * @return 成功返回“OK”
	 */
	public String showCurrentData(String username);
	
	/**
	 * 显示当前用户下温室的详细实时环境参数
	 * @return 成功返回“OK”
	 */
	public String showNewDetailData(String username);
	
	/**
	 * 显示当前用户下温室的24小时详细环境参数
	 * @return 成功返回“OK”
	 */
	public String showDayDetailData(String username);
	
	/**
	 * 显示当前用户下温室的三天详细环境参数
	 * @return 成功返回“OK”
	 */
	public String showThreeDayDetailData(String username);
	
	/**
	 * 显示当前用户下温室的七天详细环境参数
	 * @return 成功返回“OK”
	 */
	public String showSevenDayDetailDate(String username);
}
