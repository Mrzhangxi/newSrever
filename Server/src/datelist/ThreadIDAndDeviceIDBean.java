package datelist;

public class ThreadIDAndDeviceIDBean {
	public ThreadIDAndDeviceIDBean(Long threadID, String deviceID) {
		super();
		ThreadID = threadID;
		DeviceID = deviceID;
	}
	private Long ThreadID;
	private String DeviceID;
	public Long getThreadID() {
		return ThreadID;
	}
	public void setThreadID(Long threadID) {
		ThreadID = threadID;
	}
	public String getDeviceID() {
		return DeviceID;
	}
	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
	}
}
