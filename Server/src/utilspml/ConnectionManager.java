package utilspml;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class ConnectionManager {
	 //ʹ�õ���ģʽ�������ݿ����ӳ� 
	private static ConnectionManager instance;
	private static ComboPooledDataSource dataSource;
	
	private ConnectionManager() throws PropertyVetoException {
		dataSource = new ComboPooledDataSource();
		
		dataSource.setUser("root");     //�û���  
        dataSource.setPassword(""); //����  
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/servers");//���ݿ��ַ  
        dataSource.setDriverClass("com.mysql.jdbc.Driver");  
        dataSource.setInitialPoolSize(10); //��ʼ��������  
        dataSource.setMinPoolSize(5);//��С������  
        dataSource.setMaxPoolSize(20);//���������  
        dataSource.setMaxStatements(50);//��ȴ�ʱ��  
        dataSource.setMaxIdleTime(60);//������ʱ�䣬��λ����  
	}
	
	/**
	 * ���һ�����ݿ����ӳص�ʵ������
	 * @return ����ConnectionManagerһ��ʵ������
	 */
	public static final ConnectionManager getInstance(){
		if(instance == null) {
			try {
				instance = new ConnectionManager();
			} catch (PropertyVetoException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * �����ݿ����ӳ��л��һ�����ݿ�����
	 * @return һ��Connect����
	 */
	public synchronized final Connection getConnection(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return conn;
	}
}
