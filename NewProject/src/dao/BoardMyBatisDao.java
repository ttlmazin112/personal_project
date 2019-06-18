package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.Board;

public class BoardMyBatisDao {
	
	private SqlSessionFactory factory;
	private final String NAMESPACE = "dao.mapper.BoardMapper.";

	private SqlSessionFactory getSqlSessionFactory() {
		if (factory != null) {
			return factory;
		}

		InputStream is = null;
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		factory = builder.build(is);
		return factory;
	} // getSqlSessionFactory()
	
	
	// 주글 insert
	public int insertBoard(Board board) {
		SqlSession sqlSession = getSqlSessionFactory().openSession(true); // 자동커밋 true
		String statement = NAMESPACE + "getMaxBoardNum";
		// selectOne() 호출로 가져올 레코드가 없으면 null 리턴함
		Integer num = sqlSession.selectOne(statement);
		if (num != null) {
			num = num + 1;
		} else {
			num = 1;
		}
		// 주글 관련 정보 설정
		board.setNum(num);
		board.setRe_ref(num);
		board.setRe_lev(0);
		board.setRe_seq(0);
		board.setReadcount(0);
		
		// 주글 insert
		statement = NAMESPACE + "insertBoard";
		int rowCount = sqlSession.insert(statement, board);
		sqlSession.close();
		return rowCount;
	} // insertBoard()
	
	
	public List<Board> getBoards(int startRow, int pageSize, String search) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("pageSize", pageSize);
		map.put("search", "%" + search + "%");
		
		List<Board> list = sqlSession.selectList(NAMESPACE + "getBoards", map);
		sqlSession.close();
		return list;
	} // getBoards()
	
	
	// 답글쓰기
	public void replyInsert(Board board) {
		SqlSession sqlSession = getSqlSessionFactory().openSession(false); // 기본값이 수동커밋(false)
		String statement = NAMESPACE + "updateGroupSequence";
		sqlSession.update(statement, board);
		
		statement = NAMESPACE + "getMaxBoardNum";
		// selectOne() 호출로 가져올 레코드가 없으면 null 리턴함
		Integer num = sqlSession.selectOne(statement);
		if (num != null) {
			num = num + 1;
		}
		
		// 답글정보 설정
		board.setNum(num);
		board.setRe_lev(board.getRe_lev() + 1);
		board.setRe_seq(board.getRe_seq() + 1);
		board.setReadcount(0);
		
		statement = NAMESPACE + "insertBoard";
		
		int result = sqlSession.insert(statement, board);
		if (result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
	} // replyInsert()
	
	

	public static void main(String[] args) {
		// Dao 테스트
		BoardMyBatisDao dao = new BoardMyBatisDao();
		
		// 샘플 데이터 준비
		Board board1 = new Board();
		board1.setName("글쓴이1");
		board1.setPass("1234");
		board1.setSubject("글제목 테스트");
		board1.setContent("글내용 테스트");
		
		// 주글 insert 수행
		dao.insertBoard(board1);
		
		// 글 리스트 가져오기
		List<Board> list = dao.getBoards(0, 5, "test");
		for (Board board : list) {
			System.out.println(board);
		}
		
	} // main

}
