package datelist;

import net.sf.json.JSONArray;

public class TransforStationOfInformation {
	
	static private StringBuffer tempdata = new StringBuffer();
	
	static public String saveServerToAndroidOfInformation(String data){
//		System.out.println("ID:" + Thread.currentThread().getId());经过测试，不同的线程之间不会存在数据互相影响的情况，每个单独的线程可视为一个独立的程序
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
