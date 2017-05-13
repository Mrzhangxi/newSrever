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
		MaintainList ml = new MaintainList();//����һ��ά��list����
		//0.��ȡ��ǰ�̵߳�id����ȡ��ǰ�����������
		Long ThreadID = Thread.currentThread().getId();
		System.out.println("��ǰ�߳�ID�ǣ�" + ThreadID.toString());//��ȡ����ʾ��ǰ���߳�ID
		logger.info("��ǰ�߳�ID�ǣ�" + ThreadID.toString());
		InputStream is = null;
		OutputStream os = null;
		boolean flag = true;//����һ��flag��������addThreadIDAndDeviceIDֻ�ڵ�һ�ν�������ʱѭ��
		try {
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//1.����Э�飬�ж���Ӳ������Android
		Agreementslpml agms = new Agreementslpml();
		while(true) {
		String agreementstr = agms.getAgreement(is);//��ȡЭ���ַ���
		logger.info("��ʼ�ж�");
		if(agms.ifAndroid(agreementstr)) {
			logger.info("��ȷ����Android�豸");
			
			//2.ά���豸&�̱߳�     Android����ά���ñ�
			//3.����ָ�������������в���ѡ��
			logger.info("�������������ѡ��׶�");
			String choicestate = Controller.androidChoiceOption(agreementstr);//����Э����в���ʶ��ʵ�ֲ��������ز�������
			if(choicestate.equals("riguangwenshi")) {
				logger.warn("��ѡ��׶η����˴���");
			}
			//4.��Android��д������
			try {
				String rdata = TransforStationOfInformation.readServerToAndroidOfInformation();
				if(rdata.equals("") || rdata==null){
					os.write("data is null,repeat please".getBytes());
				} else {
					os.write(rdata.getBytes());
				}
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		} else if (agms.ifHardware(agreementstr)) {
			logger.info("��ȷ����Ӳ��");
			//2.ά���豸&�̱߳�
			logger.info("����ά���豸�̱߳�׶�");
			if(flag){
				String state = ml.addThreadIDAndDeviceID(ThreadID, agreementstr);//����ǰ���߳�ID��Э���е��豸id�󶨷���list��
				if("OK".equals(state)){
					logger.info("�߳�ID���豸ID�󶨳ɹ�");
				}
				flag = false;//ֻ�ڵ�һ��ִ��
			}
			//3.����ָ�������������в���ѡ��
			logger.info("�������������ѡ��׶�");
			
			String choicestate = Controller.hardwardChoiceOption(agreementstr);//����Э����в���ʶ��ʵ�ֲ��������ز�������
			if(choicestate.equals("rigangwenshi")){//����У�������riguangwenshiΪ����ʧ��
				logger.warn("��ѡ��׶η����˴���");
			}
			//4.���豸&Androidд��
			try {
				sleep(3000);
				os.write(ml.checkSelf(ThreadID).getBytes());//����Ӧ����������ͻ���
				logger.info("�Ѿ���Ӧ�ͻ���");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
		
		
	}
}
