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
		logger.info("����Options����");
	}

	Databaselpml ds = new Databaselpml();

	Stringslpml sl = new Stringslpml();
	MaintainList ml = new MaintainList();
	public String hkeepLivePacket(String agreement) {
		logger.info("��ʼ��������������");
		String currenttime = sl.getTimeWatermark();//��ȡ��ǰ��ʱ��ˮӡ��Ϊ��ǰʱ�����1970��ĺ�����
		String DeviceID = sl.getHardwardId(agreement);//��Э���н������豸��ID
		return ml.addkeeplive(DeviceID, currenttime);//��ʱ��ˮӡ��ID��Ϊ����������������
	}

	@Override
	public String hFaultPacket(String agreement) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public String hDatePacket(String agreement) {
		logger.info("��ʼ�������ݰ�����");
		String currenttime = sl.getCurrentTime();
		Double[] data = sl.getEnvironmentData(agreement);
		String deviceID = sl.getHardwardId(agreement);
		//���ݿ����
		return ds.InsertDataToDB(deviceID, data, currenttime);
	}

	@Override
	public String hStatePacket(String agreement) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public String registeNewUser(String agreement) {
		String temp = agreement.substring(22);
		String userandpass[] = temp.split("#");
		String currentTime = sl.getCurrentTime();
//		logger.info("��ʼд���û�");
		TransforStationOfInformation.saveServerToAndroidOfInformation(ds.registeNewUser(userandpass[0], userandpass[1], currentTime));
		return "OK";
	}

	public String loginUser(String agreement) {
		System.out.println("��ʼ�����¼����");
		String temp = agreement.substring(22);
		String userandpass[] = temp.split("#");
//		logger.info("�û���ʼ��¼");
		TransforStationOfInformation.saveServerToAndroidOfInformation(ds.loginUser(userandpass[0], userandpass[1]));
		return "OK";
	}
	
	public String showCurrentData(String username) {
		logger.info("��ʼ��ʾʵʱ����");
		//1.ͨ��username��database�����󣬻�ȡ�û���Ӧ���豸mac
		//2.ͨ�����mac���������豸������
		//3.�����󵽵�����ͨ��DataAnalysis���м�Ȩƽ��
		String mac[] = ds.showDeviceOfUser(username);//��ȡ�û��˺������е��豸��
		JSONArray ja = ds.getNewDetailData(mac);//��õ�ǰ���豸����ϸ����
		JSONArray curja = qp.getWeightedData(ja);//��ü�Ȩ�������豸����
		TransforStationOfInformation.saveServerToAndroidOfInformation(curja);//�����ݷ�����Ϣ��ת�࣬��Android�����
		return "OK";
	}

	@Override
	public String showNewDetailData(String username) {
		logger.info("��ʼ��ʾ��ϸʵʱ����");
		String mac[] = ds.showDeviceOfUser(username);
		JSONArray ja = ds.getNewDetailData(mac);//��õ�ǰ���豸����ϸ����
		TransforStationOfInformation.saveServerToAndroidOfInformation(ja);
		return "OK";
	}

	@Override
	public String showDayDetailData(String username) {
		logger.info("��ʼ��ʾ24Сʱ��ϸ����");
		String mac[] = ds.showDeviceOfUser(username);
		JSONArray ja = ds.getHistoricalData(mac, 1*24*60*2, 10);
		TransforStationOfInformation.saveServerToAndroidOfInformation(ja);
		return "OK";
	}

	@Override
	public String showThreeDayDetailData(String username) {
		logger.info("��ʼ��ʾʵ3����ϸ����");
		String mac[] = ds.showDeviceOfUser(username);
		JSONArray ja = ds.getHistoricalData(mac, 3*24*60*2, 10*3);//
		TransforStationOfInformation.saveServerToAndroidOfInformation(ja);
		return "OK";
	}

	@Override
	public String showSevenDayDetailDate(String username) {
		logger.info("��ʼ��ʾ7����ϸ����");
		String mac[] = ds.showDeviceOfUser(username);
		JSONArray ja = ds.getHistoricalData(mac, 7*24*60*2, 10*7);
		TransforStationOfInformation.saveServerToAndroidOfInformation(ja);
		return "OK";
	}
}
