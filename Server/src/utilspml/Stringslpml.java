package utilspml;

import java.text.SimpleDateFormat;
import java.util.Date;

import utils.Strings;

public class Stringslpml implements Strings{
	StringBuffer sb = new StringBuffer();

	@Override
	public String getStateCode(String agreement) {
		return agreement.substring(0, 4);//��ȡ��Э���ַ���ǰ��λ�������ж�
	}

	@Override
	public String getHardwardId(String agreement) {
		return agreement.substring(4,18);
	}

	@Override
	public String getCurrentTime() {
		Date cur_time = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(cur_time);
	}

	@Override
	public String getTimeWatermark() {
		Date date = new Date();
		return String.valueOf(date.getTime());
	}
	
	@Override
	public Double[] getEnvironmentData(String agreement) {
		Double[] environmentdata = new Double[3];
		environmentdata[0] = getAirTemp(agreement);
		environmentdata[1] = getAirHumidity(agreement);
		environmentdata[2] = getSoilHumidity(agreement);
		return environmentdata;
	}

	@Override
	public Double getAirTemp(String agreement) {
//		18~22
//		String temp = agreement.substring(18, 22);
//		temp = insertSpotToEnvironmentData(temp);
////		System.out.println("��ȡ�������ַ���ʪ��" + temp);
//		Double temps  = Double.valueOf(temp);
////		System.out.println("ת��ΪDouble��Ϊ��" + temps);
		return Double.valueOf(insertSpotToEnvironmentData(agreement.substring(18,22)));
	}

	@Override
	public Double getAirHumidity(String agreement) {
		// 22~26
//		String temp = agreement.substring(22, 26);
//		System.out.println("��ȡ�������ַ���ʪ��" + temp);
//		return Double.valueOf(temp);
		return Double.valueOf(insertSpotToEnvironmentData(agreement.substring(22,26)));
	}

	@Override
	public Double getSoilHumidity(String agreement) {
		// 26~30
		return Double.valueOf(insertSpotToEnvironmentData(agreement.substring(26,30)));
	}

	@Override
	public String insertSpotToEnvironmentData(String data) {
		sb.append(data);
		sb.insert(2, ".");
		data = sb.toString();
		sb.delete(0, sb.length());//�����5��data�ĳ��ȣ�����д��Ϊ�����Ч�ʣ�Ϊ�˽�׳�Ļ�Ӧ���ǣ�0��sb.length��
		return data;
	}

	@Override
	public String getAndroidID(String agreement) {
		return agreement.substring(4,18);
	}

	@Override
	public String getAndroidOptionCode(String agreement) {
		return agreement.substring(18,22);
	}

	@Override
	public String getUsername(String agreeement) {
		String tusername = agreeement.substring(4,18);
		if(tusername.indexOf("#")==-1){
			return tusername;
		} else {
			return tusername.split("#")[0];
		}
	}

}
