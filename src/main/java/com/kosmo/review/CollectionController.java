package com.kosmo.review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mybatis.MyBoardDTO;
import mybatis.MybatisDAOImpl;

@Controller
public class CollectionController {

	@Autowired
	private SqlSession sqlSession;
	
	// Mapper에서 HashMap컬렉션 이용하기
	@RequestMapping("/Collection/hashMap.do")
	public String hashMap(Model model) {
		
		// 검색어를 저장하기 위해 Map컬렉션을 생성한다.
		Map<String, String> hashMap = new HashMap<String, String>();
		
		// 각 키값에 원하는 검색어(파라미터)를 추가한다.
		hashMap.put("key_id", "kosmo");
		hashMap.put("key_name", "코스모");
		
		// 파라미터를 저장한 MAP객체를 Mapper로 전달한다.
		ArrayList<MyBoardDTO> lists=
					sqlSession.getMapper(MybatisDAOImpl.class).hashMapUse(hashMap);
		
		// Mapper에서 동적으로 생성되는 쿼리문을 로그로 출력
		String sql = sqlSession.getConfiguration().getMappedStatement("hashMapUse")
					.getBoundSql(hashMap).getSql();
		System.out.println("sql="+sql);
		
		model.addAttribute("lists",lists);
		
		return "08CollectionUse/hashMapUse";
		
	}
	
	// Mapper에서 ArrayList 컬렉션 사용하기
	@RequestMapping("/Collection/arrayList.do")
	public String arrayList(Model model) {
		
		List<String> arrayList = new ArrayList<String>();
		arrayList.add("옹성우");
		arrayList.add("김선우");
		arrayList.add("김선호");
		
		ArrayList<MyBoardDTO> lists =
				sqlSession.getMapper(MybatisDAOImpl.class).arrayListUse(arrayList);
		
		model.addAttribute("lists",lists);
		
		return "08CollectionUse/ArrayListUse";
		
		
	}
	
	
}
