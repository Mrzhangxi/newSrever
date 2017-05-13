package utilspml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import utils.Database;

public class Databaselpml implements Database{
	static Logger logger = Logger.getRootLogger();
	static int userMaxDevice = 10;
	ConnectionManager cm = ConnectionManager.getInstance();
	Connection conn = cm.getConnection();

	public String InsertDataToDB(String deviceID, Double[] data,
			String createTime) {
		try {
			PreparedStatement ps = conn.prepareStatement("insert into elementdata(mac, airtemp, airhum, soilhum, time) values (?, ?, ?, ?, ?)");
			ps.setString(1, deviceID);
			ps.setDouble(2, data[0]);
			ps.setDouble(3, data[1]);
			ps.setDouble(4, data[2]);
			ps.setString(5, createTime);
			int f = ps.executeUpdate();
			if(f>0){
				logger.info("数据库操作完成，数据已录入");
				return "OK";
			} else {
				return "";
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			
		}
		return "";
	}

	@Override
	public String registeNewUser(String username, String password, String createTime) {
		try {
			PreparedStatement pstemt = conn.prepareStatement("insert into user(username, password, craatetime) values (?,?,?)");
			pstemt.setString(1, username);
			pstemt.setString(2, password);
			pstemt.setString(3, createTime);
			int f = pstemt.executeUpdate();
			if(f>0){
				logger.info("数据库操作完成，用户成功注册");
				return "OK";
			} else {
				return "";
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String loginUser(String username, String password) {
		try {
			PreparedStatement pstemt = conn.prepareStatement("select * from user where username = ? and password = ?");
			pstemt.setString(1, username);
			pstemt.setString(2, password);
			ResultSet rs = pstemt.executeQuery();
			if(rs.next()) {
				logger.info("用户成功登陆");
				return "OK";
			} else {
				return "";//如果登录不成功，要怎么处理，缺少业务逻辑
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String[] showDeviceOfUser(String username) {
		try {
			String[] mac = new String[userMaxDevice];
			PreparedStatement ps = conn.prepareStatement("select deviid from useranddevice where uid = (select id from user where username = ?)");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				mac[i] = rs.getString(1);
				System.out.println(mac[i]);
				i++;
			}
			mac = Arrays.copyOf(mac, i);
			System.gc();
			return mac;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public JSONArray getNewDetailData(String DeviceID) {
		try {
			PreparedStatement ps = conn.prepareStatement("select * from elementdata where mac = (select Mac from device where deviceid = ?) order by dataid desc limit 1");
			ps.setInt(1, Integer.parseInt(DeviceID));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ArrayList<String> al = new ArrayList<String>();
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				JSONArray ja = JSONArray.fromObject(al);
				System.gc();
				return ja;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JSONArray getNewDetailData(String[] DeviceID) {
		ArrayList<JSONArray> al = new ArrayList<JSONArray>();
		for (String deviceID : DeviceID) {
			al.add(getNewDetailData(deviceID));
		}
		JSONArray ja = JSONArray.fromObject(al);
		System.gc();
		return ja;
	}

	@Override
	public JSONArray getHistoricalData(String DeviceID, int days, int k) {
		try {
			int j = 0;//数据踩点计数器，
			PreparedStatement ps = conn.prepareStatement("select * from elementdata where mac = (select Mac from device where deviceid = ?) order by dataid desc limit ?");
			ps.setInt(1, Integer.parseInt(DeviceID));
			ps.setInt(2, days);
			ResultSet rs = ps.executeQuery();
			ArrayList<JSONArray> alj = new ArrayList<JSONArray>();
			while(rs.next()) {
				if(j<k) {
					j++;
					continue;
				} else if(j==k){
					ArrayList<String> al = new ArrayList<String>();
					al.add(rs.getString(3));
					al.add(rs.getString(4));
					al.add(rs.getString(5));
					JSONArray ja = JSONArray.fromObject(al);
					alj.add(ja);
					j=0;
					System.gc();
				} else {
					j=0;
				}
			}
			JSONArray ja = JSONArray.fromObject(alj);
//			System.out.println(ja);
			System.out.println("从一台设备中获取到" + ja.size() + "条数据");
			return ja;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public JSONArray getHistoricalData(String[] DeviceID, int days, int k) {
		ArrayList<JSONArray> alj = new ArrayList<JSONArray>();
		for(int i=0; i<DeviceID.length; i++) {
			alj.add(getHistoricalData(DeviceID[i], days, k));
		}
		JSONArray ja = JSONArray.fromObject(alj);
		System.gc();
		return ja;
	}
}
