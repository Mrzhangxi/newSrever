package DataAnalysis;

import java.util.ArrayList;

import net.sf.json.JSONArray;

public class QureyProcess {
	/**
	 * 将传入的Json对象解析，获得若干台设备的详细数据，然后根据加权公式进行加权计算
	 * @return 经过加权计算以后的数据
	 */
	public JSONArray getWeightedData(JSONArray ja){
		ArrayList<JSONArray> al = (ArrayList<JSONArray>) JsonArrayToArrayList(ja);
//		for (JSONArray jsonArray : al) {
//			System.out.println(jsonArray);
//		}
		Double sumairtemp = 0.0;
		for (JSONArray j : al) {
			sumairtemp += Double.parseDouble(j.getString(0));
		}
		String Qureyairtemp = String.valueOf(sumairtemp/ja.size());//求出加权以后的空气温度
		
		Double sumairhum = 0.0;
		for (JSONArray j : al) {
			sumairhum += Double.parseDouble(j.getString(1));
		}
		String Qureyairhum = String.valueOf(sumairhum/ja.size());//求出加权以后的空气湿度
		
		Double sumsoilhum = 0.0;
		for (JSONArray j : al) {
			sumsoilhum += Double.parseDouble(j.getString(2));
		}
		String Qureysoilhum = String.valueOf(sumsoilhum/ja.size());//求出加权以后的土壤湿度
		
		ArrayList<String> qal = new ArrayList<String>();
		qal.add(Qureyairtemp);
		qal.add(Qureyairhum);
		qal.add(Qureysoilhum);
		JSONArray qja = JSONArray.fromObject(qal);
		System.gc();
//		System.out.println(qja);
		return qja;
	}
	
	public ArrayList<?> JsonArrayToArrayList(JSONArray ja) {
		ArrayList<Object> al = new ArrayList<>();
		for(int i=0; i<ja.size(); i++) {
			al.add(ja.get(i));
		}
		return al;
	}
}
