package datelist;

public class KeepLiveBean {
	public KeepLiveBean(String id, String createTime) {
		super();
		this.id = id;
		this.createTime = createTime;
	}
	private String id;
	private String createTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
