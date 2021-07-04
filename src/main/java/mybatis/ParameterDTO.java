package mybatis;

import java.util.ArrayList;

import lombok.Data;

// 파라미터 처리를 위한 DTO객체
@Data
public class ParameterDTO {

	
	private String user_id; //사용자 아이디
	private String board_idx; //게시판 일련번호
	
	// 검색어 처리를 위한 멤버변수
	private String searchField; // 검색할 필드명
	
	private ArrayList<String> searchTxt;// 검색어(3차버전)
	
	// select 구간을 위한 멤버변수
	private int start; // select의 시작
	private int end; // 끝
}
