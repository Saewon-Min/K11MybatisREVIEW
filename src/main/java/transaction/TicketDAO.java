package transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TicketDAO {

	/*
	멤버변수 선언
		: Spring-JDBC를 사용하기 위한 멤버변수와 setter()정의
		트랜잭션 처리를 위한 매니저클래스의 멤버변수와 setter() 정의
	 */
	JdbcTemplate template;
	PlatformTransactionManager transactionManager;
	
	/*
	servlet-context.xml에서 빈을 생성할때 setter()를 사용해서 초기화함
	 */
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	
	// 기본 생성자
	public TicketDAO() {
		System.out.println("TicketDAO 생성자 호출 : "+template);
	}
	
	// 티켓 구매와 결제를 위한 메소드
	public void buyTicket(final TicketDTO dto) {
		
		System.out.println("buyTicket() 메소드 호출");
		System.out.println(dto.getCustomerId()+"님이 "+
				"티켓 "+dto.getAmount()+"장을 구매합니다.");
	
		// DAO에서 트랜잭션 처리를 위한 객체 생성
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		/*
		2개의 업무를 하나의 프로세스로 처리하기 위해 try~catch문으로 묶어준다.
		만약 업무 중 하나라도 문제가 생긴다면 모든 업무는 rollback 처리된다.
		이를 트랜잭션 처리라 한다.
		 */
		try {
			// 티켓 구매 금액에 대한 DB처리
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

					/*
					티켓 한장에 10000원이라 가정하고, 구매 장수에 가격을 곱해서
					입력한다.
					 */
					String query = " insert into "
							+ " transaction_pay (customerId, amount)"
							+ " values(?,?) ";
					
					PreparedStatement psmt =
							con.prepareStatement(query);
					
					psmt.setString(1, dto.getCustomerId());
					
					psmt.setInt(2, dto.getAmount()*10000);
					
					return psmt;
				}
			});
			
			/*
			티켓 구매 장수에 대한 DB처리
			check 제약 조건에 의해 구매 장수가 5장을 초과하는 경우
			제약 조건 위배로 예외가 발생하게 된다.
			 */
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

					String query = " insert into "
							+ " transaction_ticket (customerId, countNum)"
							+ " values(?,?) ";
					
					PreparedStatement psmt =
							con.prepareStatement(query);
					
					psmt.setString(1, dto.getCustomerId());
					
					psmt.setInt(2, dto.getAmount());
					
					return psmt;
				}
			});
		
			/*
			티켓 구매 장수가 5장 이하인 경우 정상처리 되어 모든 작업이 commit된다.
			 */
			System.out.println("카드결제와 티켓구매 모두 정상처리 되었습니다.");
			transactionManager.commit(status);
			
			
		} catch (Exception e) {
			/*
			티켓 구매 장수가 5장을 초과하는 경우에는 예외가 발생하여
			모든 작업이 취소되고 rollback 된다.
			 */
			System.out.println("제약 조건을 위배하여 카드결제와 티켓구매 모두 취소 되었습니다.");
			transactionManager.rollback(status);
		
		
		}
		
		
	
	
	
	}
	
	
}
