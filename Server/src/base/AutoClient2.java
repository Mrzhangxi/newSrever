package base;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class AutoClient2 {

	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
		byte outbuff[] = "0X20012120170800012850341063700011".getBytes();
		try {
			//1.����һ��socket��ָ����������ַ�Ͷ˿ں�
			Socket socket = new Socket("localhost", 8888);
			//2.��ȡ���������������˷�����Ϣ
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			while(true){
				System.out.println("buff��" + outbuff.length);
				os.write(outbuff);
				System.out.println("buff��" + outbuff.length);
				os.flush();
				System.out.println("buff��" + outbuff.length + "��ʼsleep");
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
//				break;
			}
//			//����Ϊ��ȡ���Է���������Ϣ
//			int readlength = 0;//������������
//			byte[] inbuff = new byte[1024];
//			while((readlength=is.read(inbuff)) > 0)	{
//				String str = new String(inbuff,0,readlength);
//				System.out.println("I'm Client, Server said : "+str);
//				break;
//			}
//			socket.shutdownOutput();
//			is.close();
//			os.close();
//			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
