package utilspml;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import DataAnalysis.QureyProcess;
import datelist.MaintainList;
import datelist.TransforStationOfInformation;
import utils.Options;

public class Optionslpml implements Options {
	static Logger logger = Logger.getRootLogger();
	QureyProcess qp = new QureyProcess();
	public Optionslpml() {
		super();
		logger.info("创建Options对象");
	}

	Databaselpml ds = new Databaselpml();

	Stringslpml sl = new Stringslpml();
	MaintainList ml = new MaintainList();
	public String hkeepLivePacket(String agreement) {
		logger.info("开始解析心跳包数据");
		String currenttime = sl.getTimeWatermark();//获取当前的时间水印，为当前时间距离1970年的毫秒数
		String DeviceID = sl.getHardwardId(agreement);//从协议中解析出设备的ID
		return ml.addkeeplive(DeviceID, currenttime);//将时间水印和ID作为参数创建对象并入列
	}

	@Override
	public String hFaultPacket(String agreement) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String hDatePacket(String agreement) {
		logger.info("开始解析数据包数据");
		String currenttime = sl.getCurrentTime();
		Double[] data = sl.getEnvironmentData(agreement);
		String deviceID = sl.getHardwardId(agreement);
		//数据库操作
		return ds.InsertDataToDB(deviceID, data, currenttime);
	}

	@Override
	public String hStatePacket(String agreement) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String registeNewUser(String agreement) {
		String temp = agreement.substring(22);
		String userandpass[] = temp.split("#");
		String currentTime = sl.getCurrentTime();
//		logger.info("开始写入用户");
		TransforStationOfInformation.saveServerToAndroidOfInformation(ds.registeNewUser(userandpass[0], userandpass[1], currentTime));
		return "OK";
	}

	public String loginUser(String agreement) {
		System.out.println("开始处理登录请求");
		String temp = agreement.substring(22);
		String userandpass[] = temp.split("#");
//		logger.info("用户开始登录");
		TransforStationOfInformation.saveServerToAndroidOfInformation(ds.loginUser(userandpass[0], userandpass[1]));
		return "OK";
	}
	
	public String showCurrentData(String username) {
		logger.info("开始显示实时数据");
		//1.通过username向database层请求，获取用户对应的设备mac
		//2.通过多个mac，请求多个设备的数据
		//3.将请求到的数据通过DataAnalysis进行加权平均
		String mac[] = ds.showDeviceOfUser(username);//获取用户账号下所有的设备，
		JSONArray ja = ds.getNewDetailData(mac);//获得当前各设备的详细数据
		JSONArray curja = qp.getWeightedData(ja);//获得加权处理后的设备数据
		TransforStationOfInformation.saveServerToAndroidOfInformation(curja);//将数据放入信息中转类，向Android端输出
		return "OK";
	}

	@Override
	public String showNewDetailData(String username) {
		logger.info("开始显示详细实时数据");
		String mac[] = ds.showDeviceOfUser(username);
		JSONArray ja = ds.getNewDetailData(mac);//获得当前各设备的详细数据
		TransforStationOfInformation.saveServerToAndroidOfInformation(ja);
		return "OK";
	}

	@Override
	public String showDayDetailData(String username) {
		logger.info("开始显示24小时详细数据");
		String mac[] = ds.showDeviceOfUser(username);
		JSONArray ja = ds.getHistoricalData(mac, 1*24*60*2, 10);
		TransforStationOfInformation.saveServerToAndroidOfInformation(ja);
		return "OK";
	}

	@Override
	public String showThreeDayDetailData(String username) {
		logger.info("开始显示实3天详细数据");
		String mac[] = ds.showDeviceOfUser(username);
		JSONArray ja = ds.getHistoricalData(mac, 3*24*60*2, 10*3);//
		TransforStationOfInformation.saveServerToAndroidOfInformation(ja);
		return "OK";
	}

	@Override
	public String showSevenDayDetailDate(String username) {
		logger.info("开始显示7天详细数据");
		String mac[] = ds.showDeviceOfUser(username);
		JSONArray ja = ds.getHistoricalData(mac, 7*24*60*2, 10*7);
		TransforStationOfInformation.saveServerToAndroidOfInformation(ja);
		return "OK";
	}
}
