package datelist;

import net.sf.json.JSONArray;

public class TransforStationOfInformation {
	
	static private StringBuffer tempdata = new StringBuffer();
	
	static public String saveServerToAndroidOfInformation(String data){
//		System.out.println("ID:" + Thread.currentThread().getId());�������ԣ���ͬ���߳�֮�䲻��������ݻ���Ӱ��������ÿ���������߳̿���Ϊһ�������ĳ���
		tempdata.append(data);
//		System.out.println("data:" + data);
		return "OK";
	}
	
	static public String saveServerToAndroidOfInformation(JSONArray ja){
		tempdata.append(ja);//JsonArray
		return "OK";
	}
	
	static public String readServerToAndroidOfInformation() {
		
		if(tempdata.length()==0){
			return "";
		} else {
			String t = tempdata.substring(0);
			tempdata.delete(0, tempdata.length());
			System.gc();
			return t;
		}
	}
}
