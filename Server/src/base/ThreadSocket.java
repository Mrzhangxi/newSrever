package base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import datelist.MaintainList;
import datelist.TransforStationOfInformation;
import utilspml.Agreementslpml;

public class ThreadSocket extends Thread{
	static Logger logger = Logger.getRootLogger();
	private Socket socket;
	public ThreadSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void run(){
		MaintainList ml = new MaintainList();//创建一个维护list对象
		//0.获取当前线程的id，获取当前的输入输出流
		Long ThreadID = Thread.currentThread().getId();
		System.out.println("当前线程ID是：" + ThreadID.toString());//获取并显示当前的线程ID
		logger.info("当前线程ID是：" + ThreadID.toString());
		InputStream is = null;
		OutputStream os = null;
		boolean flag = true;//创建一个flag，用来让addThreadIDAndDeviceID只在第一次接受数据时循环
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//1.解析协议，判断是硬件还是Android
		Agreementslpml agms = new Agreementslpml();
		while(true) {
		String agreementstr = agms.getAgreement(is);//获取协议字符串
		logger.info("开始判断");
		if(agms.ifAndroid(agreementstr)) {
			logger.info("已确认是Android设备");
			
			//2.维护设备&线程表     Android不用维护该表
			//3.解析指令，传入控制器进行操作选择
			logger.info("进入控制器操作选择阶段");
			String choicestate = Controller.androidChoiceOption(agreementstr);//根据协议进行操作识别并实现操作，返回操作反馈
			if(choicestate.equals("riguangwenshi")) {
				logger.warn("在选择阶段发生了错误");
			}
			//4.向Android端写出数据
			try {
				String rdata = TransforStationOfInformation.readServerToAndroidOfInformation();
				if(rdata.equals("") || rdata==null){
					os.write("data is null,repeat please".getBytes());
				} else {
					os.write(rdata.getBytes());
				}
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else if (agms.ifHardware(agreementstr)) {
			logger.info("已确认是硬件");
			//2.维护设备&线程表
			logger.info("进入维护设备线程表阶段");
			if(flag){
				String state = ml.addThreadIDAndDeviceID(ThreadID, agreementstr);//将当前的线程ID和协议中的设备id绑定放入list中
				if("OK".equals(state)){
					logger.info("线程ID与设备ID绑定成功");
				}
				flag = false;//只在第一次执行
			}
			//3.解析指令，传入控制器进行操作选择
			logger.info("进入控制器操作选择阶段");
			
			String choicestate = Controller.hardwardChoiceOption(agreementstr);//根据协议进行操作识别并实现操作，返回操作反馈
			if(choicestate.equals("rigangwenshi")){//进行校验操作，riguangwenshi为操作失败
				logger.warn("在选择阶段发生了错误");
			}
			//4.向设备&Android写出
			try {
				sleep(3000);
				os.write(ml.checkSelf(ThreadID).getBytes());//将响应输入输出给客户端
				logger.info("已经回应客户端");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
		
		
	}
}
