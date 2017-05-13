package utils;

import java.sql.Connection;

import net.sf.json.JSONArray;

public interface Database {	
	
//	/**
//	 * 插入数据到数据库，传入数据为数组
//	 * @param data String数组类型的数据
//	 * @param createTime 数据创建时间
//	 * @return 返回是否成功执行，如果成功，范围“OK”
//	 */
//	public String InsertDataToDB(String[] data, String createTime);
//	
//	/**
//	 * 插入数据到数据库，传入数据为数组
//	 * @param data Double数组类型的数据
//	 * @param createTime 数据创建时间
//	 * @return 返回是否成功执行，如果成功，范围“OK”
//	 */
//	public String InsertDataToDB(Double[] data, String createTime);
//	
//	/**
//	 * 插入数据到数据库，传入数据为数组
//	 * @param data Double数组类型的数据
//	 * @return 返回是否成功执行，如果成功，范围“OK”
//	 */
//	public String InsertDataToDB(Double[] data);
//	
//	/**
//	 * 插入数据到数据库，传入数据为数组
//	 * @param deviceID 设备ID，用来区分数据属于哪台设备
//	 * @param data String数组类型的数据
//	 * @param createTime
//	 * @return
//	 */
//	public String InsertDataToDB(String deviceID, String[] data, String createTime);
//	
	/**
	 * 插入数据到数据库，传入数据为数组
	 * @param deviceID 设备ID，用来区分数据属于哪台设备
	 * @param data Double数组类型的数据
	 * @param createTime
	 * @return
	 */
	public String InsertDataToDB(String deviceID, Double[] data, String createTime);
	
//	/**
//	 * 插入数据到数据库，传入数据为数组
//	 * @param deviceID 设备ID，用来区分数据属于哪台设备
//	 * @param data Double数组类型的数据
//	 * @return
//	 */
//	public String InsertDataToDB(String deviceID, String[] data);
	
	/**
	 * 向用户表创建用户信息
	 * @param username 要创建用户的用户名
	 * @param password 要创建用户的密码
	 * @return 成功返回“OK”
	 */
	public String registeNewUser(String username, String password, String createTime);
	
	/**
	 * 根据username属性向user表中查询，是否有username相匹配的password记录
	 * @param username 登录的用户名
	 * @param password 登录的密码
	 * @return 成功返回"OK"
	 */
	public String loginUser(String username, String password);
	
	/**
	 * 根据用户名，从user表和useranddivece表中获得该用户下对应的N台设备的ID编号
	 * @param username 用户名
	 * @return 一个由设备在Device表中的id组成的数组
	 */
	public String[] showDeviceOfUser(String username);
	
	/**
	 * 根据传入的DeviceID(Mac)从elementdata表中获得最近的一次环境数值
	 * @param DeviceID 一个DeviceID
	 * @return 返回由数据组成的Json对象
	 */
	public JSONArray getNewDetailData(String DeviceID);
	
	/**
	 * 根据传入的DeviceID(Mac)从elementdata表中获得最近的一次环境数值
	 * @param DeviceID 一个DeviceID数组
	 * @return 返回由多台数据组成的Json对象
	 */
	public JSONArray getNewDetailData(String[] DeviceID);
	
	/**
	 * 根据传入的DeviceID(Mac)从elementdata表中获得最近的days天的环境数值
	 * @param DeviceID 一个DeviceID
	 * @param days 回看days天的数据
	 * @param k 数据踩点计数器的阈值
	 * @return 返回由数据组成的Json对象
	 */
	public JSONArray getHistoricalData(String DeviceID, int days, int k);
	/**
	 * 根据传入的DeviceID(Mac)从elementdata表中获得最近的days天的环境数值
	 * @param DeviceID 一个DeviceID数组
	 * @param days 回看days天的数据
	 * @param k 数据踩点计数器的阈值
	 * @return 返回由多台数据组成的Json对象
	 */
	public JSONArray getHistoricalData(String[] DeviceID, int days, int k);
}
