package com.cg.train.db.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/1 15:47
 * @Version: 1.0.0
 */
public class MyBatisServer {
    private static final Logger log = LoggerFactory.getLogger(MyBatisServer.class);
    private static MyBatisServer INSTANCE = new MyBatisServer();
    private SqlSessionFactory sqlSesFactory = null;
    private MyBatisServer() {
        try (InputStream resource = Resources.getResourceAsStream("mybatis-config.xml")) {
            Properties properties = new Properties();
            URL dbResource = MyBatisServer.class.getResource("/db.properties");
            String filePath = dbResource.getFile();
            properties.load(new FileInputStream(filePath));
            sqlSesFactory = new SqlSessionFactoryBuilder().build(resource, properties);
        } catch (Exception e) {
            log.error("",e);
        }
    }

    public static MyBatisServer getInstance() {
        return INSTANCE;
    }

    /**
     * 获取sqlSession
     * 一定要记得用完后关闭调用：close方法
     * @return
     */
    public final SqlSession getSqlSession(){
        return sqlSesFactory.openSession();
    }

    /**
     * 获取批量处理的sqlSession
     * 一定要记得用完后关闭调用：close方法
     * @return
     */
    public final SqlSession getBatchSqlSession(){
        return sqlSesFactory.openSession(ExecutorType.BATCH);
    }
}
