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
		String stateCode = sl.getStateCode(agreement);//获得状态码
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
		String stateCode = sl.getStateCode(agreement);//获得状态码
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
			while((readlength=is.read(buff)) > 0)	{//这个地方存在隐患，如果传过来的数据大于2014byte就会出现数据丢失的情况
				str = new String(buff,0,readlength);//在没有断开输入流之前，这个循环会一直存在
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
