package com.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.mybatis.po.User;

public class MybatisFirst {
	
	@Test
	public void findUserByIdTest() throws IOException {
		
		String resource = "SqlMapConfig.xml";
		
		InputStream inputStream = Resources.getResourceAsStream(resource );
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		User user = sqlSession.selectOne("test.findUserById", 1);
		
		System.out.println(user.toString());
		
		sqlSession.close();
	}
	
	@Test
	public void findUserByNameTest() throws IOException {
		
		String resource = "SqlMapConfig.xml";
		
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//创建会话工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		//通过工厂得到sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//通过SqlSession操作数据库
		//第一个参数：namespace + "." + User.xml中的select标签的id
		//第二个参数：指定和映射文件匹配的paramType的类型的参数
		//返回的是一个和User.xml中的select标签的resultType中的user对象
		List<User> list = sqlSession.selectList("test.findUserByName", "张");
		
		System.out.println(list);
		
		sqlSession.close();
	}
	
	@Test
	public void insertUserTest() throws IOException {
		String resource = "SqlMapConfig.xml";
		
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		User user = new User();
		user.setUsername("张小三");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setAddress("合肥");
		
		sqlSession.insert("test.insertUser", user);
		
		sqlSession.commit();
		
		System.out.println(user);
		
		sqlSession.close();
	}
	
	
}
