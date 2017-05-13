package datelist;

public class ThreadIDAndData {
	public ThreadIDAndData(Long threadid, String data) {
		super();
		this.threadid = threadid;
		this.data = data;
	}
	private Long threadid;
	private String data;
	public Long getThreadid() {
		return threadid;
	}
	public void setThreadid(Long threadid) {
		this.threadid = threadid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
