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
			//1.创建一个socket，指定服务器地址和端口号
			Socket socket = new Socket("localhost", 8888);
			//2.获取输出流，向服务器端发送信息
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			while(true){
				System.out.println("buff长" + outbuff.length);
				os.write(outbuff);
				System.out.println("buff长" + outbuff.length);
				os.flush();
				System.out.println("buff长" + outbuff.length + "开始sleep");
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
//				break;
			}
//			//以下为读取来自服务器的消息
//			int readlength = 0;//输入流计数器
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
