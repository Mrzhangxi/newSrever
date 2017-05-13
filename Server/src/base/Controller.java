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
			System.out.println("操作指令" + optionCode);
			switch (optionCode) {
			case "0010":
//				System.out.println("注册");
				String stater = ol.registeNewUser(agreement);
				if(stater.equals("OK")){
					logger.info("新用户注册成功");
					//需要返回给Android用户分配的ID编号，使用Json来处理
					//要么不返ID了， 没用，反OK吧
				}
				break;
			case "0011":
//				System.out.println("登录");
				String statel = ol.loginUser(agreement);
				if(statel.equals("OK")) {
					logger.info("用户登录成功");
					//需要返回给Android设备允许登录的信息，使用Json来处理
					//登录成功，返回OK
				}
				break;
			default:
				break;
			}
			break;
		case "0A20":
			System.out.println("操作指令"+optionCode);
			switch (optionCode) {
			case "0010":
				//显示当前数据
				String statesn = ol.showCurrentData(sl.getUsername(agreement));
				if(statesn.equals("OK")){
					logger.info("已显示当前数据");
				}
				break;
			case "0020":
				//显示当前的详细数据
				String statend = ol.showNewDetailData(sl.getUsername(agreement));
				if(statend.equals("OK")){
					logger.info("已显示当前详细数据");
				}
				break;
			case "0021":
				//显示近一天的详细数据
				//取出全部数据，然后交由本程序来处理，节约数据库性能
				String statedd = ol.showDayDetailData(sl.getUsername(agreement));
				if(statedd.equals("OK")) {
					logger.info("已显示24th的数据");
				}
				break;
			case "0022":
				//显示近三天的详细数据
				String statetd = ol.showThreeDayDetailData(sl.getUsername(agreement));
				if(statetd.equals("OK")){
					logger.info("已显示3天的数据");
				}
				break;
			case "0023":
				//显示近一周的详细数据
				String statesd = ol.showSevenDayDetailDate(sl.getUsername(agreement));
				if(statesd.equals("OK")){
					logger.info("已显示7天的数据");
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
//		logger.info("开始获得状态码");
		String stateCode = sl.getStateCode(agreement);//获得状态码
//		logger.info("拿到状态码，开始操作");
		switch (stateCode) {
		case "0X80":
			String statek = ol.hkeepLivePacket(agreement);//心跳包
			if("OK".equals(statek)){
				logger.info("根据心跳包更新在线列表成功");
			}
			break;
		case "0X40":
			ol.hFaultPacket(agreement);//故障包
			break;
		case "0X20":
//			ol.hDatePacket(agreement);//数据包
			String stated = ol.hDatePacket(agreement);
			if("OK".equals(stated)){
				logger.info("成功解析数据包");
			}
			break;
		case "0X10":
			ol.hStatePacket(agreement);//状态包
			break;
		default:
			return "riguangwenshi";
		}
		return "";		
	}
}
