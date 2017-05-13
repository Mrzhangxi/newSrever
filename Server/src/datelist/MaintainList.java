package datelist;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import utilspml.Stringslpml;

public class MaintainList {
	static Logger logger = Logger.getRootLogger();
	static ArrayList<KeepLiveBean> keeplivelist = new ArrayList<KeepLiveBean>();
	static ArrayList<ThreadIDAndDeviceIDBean> threadanddevicelist = new ArrayList<ThreadIDAndDeviceIDBean>();//创建列表，用来存储状态
	static ArrayList<ThreadIDAndData> threadidanddatalist = new ArrayList<ThreadIDAndData>();
	
	Stringslpml sl = new Stringslpml();
	
	//添加心跳包到kepplive列表中
	public String addkeeplive(String id, String createtime){
		
		keeplivelist.add(new KeepLiveBean(id, createtime));//根据传入的数据创建javabean并存入list中
		
		logger.info("keeplivelist长度位：" + keeplivelist.size());
		for(KeepLiveBean k: keeplivelist) {
			logger.debug(k.getId());
			logger.debug(k.getCreateTime());
		}
		return dropOldData();//清理完成，返回OK
	}

	//清理前30s的数据
	public String dropOldData(){
		String currentTime = sl.getTimeWatermark();
		int[] size = new int[keeplivelist.size()];
		int j = 0;
		
		for(int i=0; i<keeplivelist.size(); i++){//依次找到需要清除的数据，并将其下标保存到一个数组当中
			if((Long.valueOf(currentTime) - Long.valueOf(keeplivelist.get(i).getCreateTime())) > 30000){
//				keeplivelist.remove(k);
				size[j] = i;
				j++;
			}
		}
		for(int k=0; k<j-1; k++) {//因为在前边j++是在size[j]=i之后的，导致j比size的长度大1
			keeplivelist.remove(0);//注意list会自动补齐，添补移除的数据空位
		}
		
		return "OK";
	};
	
	//当有新设备接入时，首先检查列表，然后将新的线程ID与设备ID的对应关系存进来
	public String addThreadIDAndDeviceID(Long threadID, String agreement){
		String deviceid = sl.getHardwardId(agreement);//根据协议得到设备ID
		int i = checkThreadIDAndDeviceIDList(deviceid);//拿到重复数据的下标
		if(i>=0){//判断该设备ID是否存在与列表中
			//做移除旧数据处理
			threadanddevicelist.remove(i);
		}
		threadanddevicelist.add(new ThreadIDAndDeviceIDBean(threadID, deviceid));
		for(ThreadIDAndDeviceIDBean k: threadanddevicelist){
			logger.debug("线程：" + k.getThreadID() + "与" + "设备：" + k.getDeviceID() + "绑定");
		}
		return "OK";
	}
	
	//判断在ThreadID&data列表中是否存在本线程对应的线程ID，如果有，返回下标，如果没有，返回-1
	public String checkSelf(Long threadid){
		for(ThreadIDAndData k: threadidanddatalist){
			if(threadid.equals(k.getThreadid())){//判断是否存在，如果存在就返回data，不存在就返回响应字符M
				return k.getData();
			}
			return "M";
		}
		return "M";
	}
	
	//将来自客户端的设备ID+信息的数据转换为线程ID+信息的数据
	public String transDeviceIDToThreadID (){
		return "";
	}
	
	//将线程ID+信息的数据放入存放线程ID+信息的ThreadID&data表中，放便遍历检索
	public String addThreadIDAndData(Long threadid, String data) {
		int i = checkThreadIdAndDataList(threadid);//拿到重复数据的下标
		if(i>=0){//判断该设备ID是否存在与列表中
			//做移除旧数据处理
			threadanddevicelist.remove(i);
		}
		threadidanddatalist.add(new ThreadIDAndData(threadid, data));
		return "OK";
	}
	
	//检查list中是否存在相同的设备ID，如果存在就返回该数据所处的下标
	public int checkThreadIDAndDeviceIDList(String device) {
		int i=0;
		for(ThreadIDAndDeviceIDBean k: threadanddevicelist) {
			
			if(device.equals(k.getDeviceID())){
				return i;
			}
			i++;
		}
		return -1;//代表没有重复数据
	}
	
	//检查threadidanddatalist列表中是否有重复数据，根据规则，在列表中一个设备只能存在一条命令
	public int checkThreadIdAndDataList(Long threadid){
		int i=0;
		for(ThreadIDAndData k: threadidanddatalist) {
			
			if(threadid.equals(k.getThreadid())){
				return i;
			}
			i++;
		}
		return -1;//代表没有重复数据
	}
}
