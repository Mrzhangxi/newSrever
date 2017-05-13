package base;

import org.apache.log4j.Logger;

import utilspml.Optionslpml;
import utilspml.Stringslpml;
public class Controller {
	static Optionslpml ol = new Optionslpml();
	static Stringslpml sl = new Stringslpml();
	static Logger logger = Logger.getRootLogger();
	public static String androidChoiceOption(String agreement) {
		String stateCode = sl.getStateCode(agreement);
		String optionCode = sl.getAndroidOptionCode(agreement);
		switch (stateCode) {
		case "0A00":
			System.out.println("����ָ��" + optionCode);
			switch (optionCode) {
			case "0010":
//				System.out.println("ע��");
				String stater = ol.registeNewUser(agreement);
				if(stater.equals("OK")){
					logger.info("���û�ע��ɹ�");
					//��Ҫ���ظ�Android�û������ID��ţ�ʹ��Json������
					//Ҫô����ID�ˣ� û�ã���OK��
				}
				break;
			case "0011":
//				System.out.println("��¼");
				String statel = ol.loginUser(agreement);
				if(statel.equals("OK")) {
					logger.info("�û���¼�ɹ�");
					//��Ҫ���ظ�Android�豸�����¼����Ϣ��ʹ��Json������
					//��¼�ɹ�������OK
				}
				break;
			default:
				break;
			}
			break;
		case "0A20":
			System.out.println("����ָ��"+optionCode);
			switch (optionCode) {
			case "0010":
				//��ʾ��ǰ����
				String statesn = ol.showCurrentData(sl.getUsername(agreement));
				if(statesn.equals("OK")){
					logger.info("����ʾ��ǰ����");
				}
				break;
			case "0020":
				//��ʾ��ǰ����ϸ����
				String statend = ol.showNewDetailData(sl.getUsername(agreement));
				if(statend.equals("OK")){
					logger.info("����ʾ��ǰ��ϸ����");
				}
				break;
			case "0021":
				//��ʾ��һ�����ϸ����
				//ȡ��ȫ�����ݣ�Ȼ���ɱ�������������Լ���ݿ�����
				String statedd = ol.showDayDetailData(sl.getUsername(agreement));
				if(statedd.equals("OK")) {
					logger.info("����ʾ24th������");
				}
				break;
			case "0022":
				//��ʾ���������ϸ����
				String statetd = ol.showThreeDayDetailData(sl.getUsername(agreement));
				if(statetd.equals("OK")){
					logger.info("����ʾ3�������");
				}
				break;
			case "0023":
				//��ʾ��һ�ܵ���ϸ����
				String statesd = ol.showSevenDayDetailDate(sl.getUsername(agreement));
				if(statesd.equals("OK")){
					logger.info("����ʾ7�������");
				}
				break;
			default:
				break;
			}
			break;
		case "0A40":
			break;
		case "0A60":
			break;
		case "0A80":
			break;

		default:
			return "riguangwenshi";
		}
		return "";
	}

	public static String hardwardChoiceOption(String agreement) {
//		logger.info("��ʼ���״̬��");
		String stateCode = sl.getStateCode(agreement);//���״̬��
//		logger.info("�õ�״̬�룬��ʼ����");
		switch (stateCode) {
		case "0X80":
			String statek = ol.hkeepLivePacket(agreement);//������
			if("OK".equals(statek)){
				logger.info("�������������������б�ɹ�");
			}
			break;
		case "0X40":
			ol.hFaultPacket(agreement);//���ϰ�
			break;
		case "0X20":
//			ol.hDatePacket(agreement);//���ݰ�
			String stated = ol.hDatePacket(agreement);
			if("OK".equals(stated)){
				logger.info("�ɹ��������ݰ�");
			}
			break;
		case "0X10":
			ol.hStatePacket(agreement);//״̬��
			break;
		default:
			return "riguangwenshi";
		}
		return "";		
	}
}
