package base;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class Server {

	public static void main(String[] args) {
		Logger logger = Logger.getRootLogger();
		ServerSocket ss = null;
		boolean flag = true;
		try {
			while(flag){
				ss = new ServerSocket(8888);
				logger.info("Waiting Client......");
				Socket socket = ss.accept();
//				flag = true;
				Thread t = new ThreadSocket(socket);//开启多线程socket
				t.start();
//				flag = false;
				ss.close();
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
