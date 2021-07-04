package mybatis;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String pass;
	private String name;
	private java.sql.Date regidate;
	
}
