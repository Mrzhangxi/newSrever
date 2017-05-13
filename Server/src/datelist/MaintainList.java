package datelist;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import utilspml.Stringslpml;

public class MaintainList {
	static Logger logger = Logger.getRootLogger();
	static ArrayList<KeepLiveBean> keeplivelist = new ArrayList<KeepLiveBean>();
	static ArrayList<ThreadIDAndDeviceIDBean> threadanddevicelist = new ArrayList<ThreadIDAndDeviceIDBean>();//�����б������洢״̬
	static ArrayList<ThreadIDAndData> threadidanddatalist = new ArrayList<ThreadIDAndData>();
	
	Stringslpml sl = new Stringslpml();
	
	//�����������kepplive�б���
	public String addkeeplive(String id, String createtime){
		
		keeplivelist.add(new KeepLiveBean(id, createtime));//���ݴ�������ݴ���javabean������list��
		
		logger.info("keeplivelist����λ��" + keeplivelist.size());
		for(KeepLiveBean k: keeplivelist) {
			logger.debug(k.getId());
			logger.debug(k.getCreateTime());
		}
		return dropOldData();//������ɣ�����OK
	}

	//����ǰ30s������
	public String dropOldData(){
		String currentTime = sl.getTimeWatermark();
		int[] size = new int[keeplivelist.size()];
		int j = 0;
		
		for(int i=0; i<keeplivelist.size(); i++){//�����ҵ���Ҫ��������ݣ��������±걣�浽һ�����鵱��
			if((Long.valueOf(currentTime) - Long.valueOf(keeplivelist.get(i).getCreateTime())) > 30000){
//				keeplivelist.remove(k);
				size[j] = i;
				j++;
			}
		}
		for(int k=0; k<j-1; k++) {//��Ϊ��ǰ��j++����size[j]=i֮��ģ�����j��size�ĳ��ȴ�1
			keeplivelist.remove(0);//ע��list���Զ����룬���Ƴ������ݿ�λ
		}
		
		return "OK";
	};
	
	//�������豸����ʱ�����ȼ���б�Ȼ���µ��߳�ID���豸ID�Ķ�Ӧ��ϵ�����
	public String addThreadIDAndDeviceID(Long threadID, String agreement){
		String deviceid = sl.getHardwardId(agreement);//����Э��õ��豸ID
		int i = checkThreadIDAndDeviceIDList(deviceid);//�õ��ظ����ݵ��±�
		if(i>=0){//�жϸ��豸ID�Ƿ�������б���
			//���Ƴ������ݴ���
			threadanddevicelist.remove(i);
		}
		threadanddevicelist.add(new ThreadIDAndDeviceIDBean(threadID, deviceid));
		for(ThreadIDAndDeviceIDBean k: threadanddevicelist){
			logger.debug("�̣߳�" + k.getThreadID() + "��" + "�豸��" + k.getDeviceID() + "��");
		}
		return "OK";
	}
	
	//�ж���ThreadID&data�б����Ƿ���ڱ��̶߳�Ӧ���߳�ID������У������±꣬���û�У�����-1
	public String checkSelf(Long threadid){
		for(ThreadIDAndData k: threadidanddatalist){
			if(threadid.equals(k.getThreadid())){//�ж��Ƿ���ڣ�������ھͷ���data�������ھͷ�����Ӧ�ַ�M
				return k.getData();
			}
			return "M";
		}
		return "M";
	}
	
	//�����Կͻ��˵��豸ID+��Ϣ������ת��Ϊ�߳�ID+��Ϣ������
	public String transDeviceIDToThreadID (){
		return "";
	}
	
	//���߳�ID+��Ϣ�����ݷ������߳�ID+��Ϣ��ThreadID&data���У��ű��������
	public String addThreadIDAndData(Long threadid, String data) {
		int i = checkThreadIdAndDataList(threadid);//�õ��ظ����ݵ��±�
		if(i>=0){//�жϸ��豸ID�Ƿ�������б���
			//���Ƴ������ݴ���
			threadanddevicelist.remove(i);
		}
		threadidanddatalist.add(new ThreadIDAndData(threadid, data));
		return "OK";
	}
	
	//���list���Ƿ������ͬ���豸ID��������ھͷ��ظ������������±�
	public int checkThreadIDAndDeviceIDList(String device) {
		int i=0;
		for(ThreadIDAndDeviceIDBean k: threadanddevicelist) {
			
			if(device.equals(k.getDeviceID())){
				return i;
			}
			i++;
		}
		return -1;//����û���ظ�����
	}
	
	//���threadidanddatalist�б����Ƿ����ظ����ݣ����ݹ������б���һ���豸ֻ�ܴ���һ������
	public int checkThreadIdAndDataList(Long threadid){
		int i=0;
		for(ThreadIDAndData k: threadidanddatalist) {
			
			if(threadid.equals(k.getThreadid())){
				return i;
			}
			i++;
		}
		return -1;//����û���ظ�����
	}
}
