package base;

public class ceshi {

	public static void main(String[] args) {
		String tusername = "123456789abc##";
		if(tusername.indexOf("#")==-1){
			System.out.println(tusername);
//			return tusername;
		} else {
			System.out.println(tusername.split("#")[0]);
//			return tusername.split("#")[0];
		}
	}

}
