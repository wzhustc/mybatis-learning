# mybatis-learning
总结：
1、手写mybatis sql语句 :
	精确查找：SELECT * FROM USER WHERE id=#{value}
	模糊查询：SELECT * FROM USER WHERE username LIKE '%${value}%'
	插入操作：INSERT INTO USER (username,birthday,sex,address) VALUE(#{username},#{birthday},#{sex},#{address})
	
	
2、需要配置的文件
	SqlMapConfig.xml文件：全局配置文件，配置jdbc连接，配置mapper映射，在本例中，mapper映射就是sqlmap/User.xml。
	

3、数据库连接中：
第一步：加载SqlMapConfig.xml--->Resources.getResourceAsStream("SqlMapConfig.xml")，得到一个InputStream流。
第二步：通过new SqlSessionFactoryBuilder().build(inputStream) 创建一个SqlSessionFactory
第三步：通过SqlSessionFactory构建一个SqlSession会话。
第四步：通过SqlSession会话对数据库进行CRUD操作
第五步：关闭SqlSession会话。

4、insert的时候需要提交，但是和spring整合后就不需要了。会自动提交

5、凡是对数据库有写的操作，比如删除，插入，更新这些，都是需要提交commit的。否则数据库中的数据无法变更
