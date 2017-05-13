package DataAnalysis;

import java.util.ArrayList;

import net.sf.json.JSONArray;

public class QureyProcess {
	/**
	 * �������Json����������������̨�豸����ϸ���ݣ�Ȼ����ݼ�Ȩ��ʽ���м�Ȩ����
	 * @return ������Ȩ�����Ժ������
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
		String Qureyairtemp = String.valueOf(sumairtemp/ja.size());//�����Ȩ�Ժ�Ŀ����¶�
		
		Double sumairhum = 0.0;
		for (JSONArray j : al) {
			sumairhum += Double.parseDouble(j.getString(1));
		}
		String Qureyairhum = String.valueOf(sumairhum/ja.size());//�����Ȩ�Ժ�Ŀ���ʪ��
		
		Double sumsoilhum = 0.0;
		for (JSONArray j : al) {
			sumsoilhum += Double.parseDouble(j.getString(2));
		}
		String Qureysoilhum = String.valueOf(sumsoilhum/ja.size());//�����Ȩ�Ժ������ʪ��
		
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
