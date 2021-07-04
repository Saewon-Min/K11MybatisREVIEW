package mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
/*
해당 인터페이스는 컨트롤러와 DAO사이에서 매개역할을
하는 서비스 객체로 사용된다.
 */

/*
@Service 어노테이션이 있으면 스프링이 시작될때 자동으로
빈이 생성된다. 
따라서 해당 프로그램에서는 @Autowired하는 부분이 없으므로
어노테이션은 생략 가능하다.
 */
@Service
public interface MybatisDAOImpl {

	/*
	1차 버전
	검색 기능 추가 전
	 */
	// 게시물 카운트
	//public int getTotalCount();
	
	// 목록에 출력할 게시물 가져오기
	//public ArrayList<MyBoardDTO> listPage(int s, int e);
	
	
	/*
	2차버전
	검색 기능 추가 후: 파라미터를 저장한 DTO를 매개변수로 받음
	 */
	public int getTotalCount(ParameterDTO parameterDTO);
	public ArrayList<MyBoardDTO> listPage(ParameterDTO parameterDTO);
	
	
	
	// 로그인 처리
	public MemberVO login(String id, String pass);
	
	/*
	Mapper에서 파라미터를 처리할 수 있는 세번째 방법으로,
	@Param 어노테이션을 사용한다.
	이때는 변수명을 그대로 Mapper에서 사용할 수 있다.
	
	@Param("_name") String name
	=> name을 받아와서 _name으로 변수명을 변경한다.
	mapper에서는 _name을 사용한다.
	
	 */
	public void write(@Param("_name") String name,
			@Param("_contents") String contents,
			@Param("_id") String id);
	
	// 기존 게시물 조회
	public MyBoardDTO view(ParameterDTO parameterDTO);
	
	// 수정 처리
	public int modify(MyBoardDTO myBoardDTO);
	
	// 삭제처리
	public int delete(String idx, String id);
	
	// Map 컬렉션 사용을 위한 메소드
	public ArrayList<MyBoardDTO> hashMapUse(Map<String, String> hMap);

	// List컬렉션 사용을 위한 메소드
	public ArrayList<MyBoardDTO> arrayListUse(List<String> aList);
	
	
	
	
	
	
	
	
	
	
	
}

