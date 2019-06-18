package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import vo.Member;

public class MemberMyBatisDao {

	private SqlSessionFactory factory;
	private final String NAMESPACE = "dao.mapper.MemberMapper.";

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

	public Member getMemberById(String id) {
		// SqlSession이 기존 Connection 역할과 동일함
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Member member = sqlSession.selectOne(NAMESPACE + "getMemberById", id);
		sqlSession.close();
		return member;
	} // getMemberById()

	public List<Member> getAllMembers() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<Member> list = sqlSession.selectList(NAMESPACE + "getAllMembers");
		sqlSession.close();
		return list;
	} // getAllMembers()

	public int insertMember(Member member) {
		// openSession(true); // 자동커밋여부 true로 설정.
		SqlSession sqlSession = getSqlSessionFactory().openSession(true);
		int result = sqlSession.insert(NAMESPACE + "insertMember", member);
		sqlSession.close();
		return result;
	} // insertMember()

	public int deleteAllMembers() {
		// 수동커밋
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = sqlSession.delete(NAMESPACE + "deleteAllMembers");
		if (result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	} // deleteAllMembers()

	public int deleteMember(String id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = sqlSession.delete(NAMESPACE + "deleteMember", id);
		if (result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	} // deleteMember()

	public int updateMember(Member member) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int result = sqlSession.update(NAMESPACE + "updateMember", member);
		if (result > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return result;
	} // updateMember()

	public int countById(String id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int count = sqlSession.selectOne(NAMESPACE + "countById", id);
		sqlSession.close();
		return count;
	} // countById()

	public int loginCheck(String id, String pass) {
		int check = -1;
		String password = "";
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		// selectOne메소드는 가져오는 레코드개수가 0개이거나 2개 이상이면 null이 리턴됨!
		password = sqlSession.selectOne(NAMESPACE + "loginCheck", id);
		System.out.println("password : " + password);
		if (password == null) {
			check = -1; // 아이디 없음
		} else if (pass.equals(password)) {
			check = 1; // 비밀번호 일치
		} else {
			check = 0; // 비밀번호 불일치
		}
		sqlSession.close();
		return check;
	} // loginCheck()

	public static void main(String[] args) {
		// 테스트
		MemberMyBatisDao dao = new MemberMyBatisDao();

		// 전체 회원 삭제
		dao.deleteAllMembers();

		// 신규 샘플데이터 준비
		Member member1 = new Member();
		member1.setId("aaa");
		member1.setPassword("1234");
		member1.setName("홍길동");

		Member member2 = new Member();
		member2.setId("bbb");
		member2.setPassword("1234");
		member2.setName("성춘향");

		// insert 수행
		dao.insertMember(member1);
		dao.insertMember(member2);

		// select 수행
		Member memberGet1 = dao.getMemberById("aaa");
		Member memberGet2 = dao.getMemberById("bbb");

		// 출력
		System.out.println(memberGet1);
		System.out.println(memberGet2);

		// 회원 한명 삭제
		dao.deleteMember("aaa");

		List<Member> list = dao.getAllMembers();
		for (Member member : list) {
			System.out.println(member);
		}

		// 회원 한명 수정
		Member member3 = new Member();
		member3.setId("bbb");
		member3.setName("송혜교");
		member3.setPassword("1111");

		// update 수행
		dao.updateMember(member3);

		Member memberGet3 = dao.getMemberById(member3.getId());
		System.out.println(member3);

		int check = dao.loginCheck("bbb", "1111");
		System.out.println("로그인체크값: " + check);
	} // main()

} // MemberMyBatisDao 클래스
