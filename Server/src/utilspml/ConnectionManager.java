package utilspml;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class ConnectionManager {
	 //使用单利模式创建数据库连接池 
	private static ConnectionManager instance;
	private static ComboPooledDataSource dataSource;
	
	private ConnectionManager() throws PropertyVetoException {
		dataSource = new ComboPooledDataSource();
		
		dataSource.setUser("root");     //用户名  
        dataSource.setPassword(""); //密码  
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/servers");//数据库地址  
        dataSource.setDriverClass("com.mysql.jdbc.Driver");  
        dataSource.setInitialPoolSize(10); //初始化连接数  
        dataSource.setMinPoolSize(5);//最小连接数  
        dataSource.setMaxPoolSize(20);//最大连接数  
        dataSource.setMaxStatements(50);//最长等待时间  
        dataSource.setMaxIdleTime(60);//最大空闲时间，单位毫秒  
	}
	
	/**
	 * 获得一个数据库连接池的实例对象
	 * @return 返回ConnectionManager一个实例对象
	 */
	public static final ConnectionManager getInstance(){
		if(instance == null) {
			try {
				instance = new ConnectionManager();
			} catch (PropertyVetoException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * 从数据库连接池中获得一个数据库连接
	 * @return 一个Connect对象
	 */
	public synchronized final Connection getConnection(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return conn;
	}
}
