package mybatis;

import java.util.ArrayList;

public class test {

	
	public static void main(String[] args) {
		
		ArrayList<String> lists = new ArrayList<String>(); 
		String searchTxt = "h e l l o javascript";
		
		String[] sTxtArray = searchTxt.split(" ");
		
		for(String str : sTxtArray) {
			lists.add(str);
		}
		
		System.out.println(lists);
	}
}
