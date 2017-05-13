package utilspml;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import utils.Agreement;

public class Agreementslpml implements Agreement {
	static Logger logger = Logger.getRootLogger();
	Stringslpml sl = new Stringslpml();

	@Override
	public boolean ifAndroid(String agreement) {
		String stateCode = sl.getStateCode(agreement);//���״̬��
		switch (stateCode) {
		case "0A00":
			return true;
		case "0A20":
			return true;
		case "0A40":
			return true;
		case "0A60":
			return true;
		case "0A80":
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean ifHardware(String agreement) {
		String stateCode = sl.getStateCode(agreement);//���״̬��
		switch (stateCode) {
		case "0X80":
			return true;
		case "0X40":
			return true;
		case "0X20":
			return true;
		case "0X10":
			return true;
		default:
			return false;
		}
	}

	@Override
	public String getAgreement(InputStream is) {
		byte buff[] = new byte[1024];
		int readlength = 0;
		String str = null;
		try {
			while((readlength=is.read(buff)) > 0)	{//����ط�������������������������ݴ���2014byte�ͻ�������ݶ�ʧ�����
				str = new String(buff,0,readlength);//��û�жϿ�������֮ǰ�����ѭ����һֱ����
				System.out.println(str);
				logger.info(str);
				return str;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
