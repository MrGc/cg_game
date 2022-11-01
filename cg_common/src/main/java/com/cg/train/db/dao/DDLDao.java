package com.cg.train.db.dao;

import com.cg.train.annotation.Dao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Description:
 * @Author: Craig
 * @Date: 2022/11/1 15:45
 * @Version: 1.0.0
 */
@Dao(mapperPath = "com.cg.train.db.dao.DdlMapper")
public class DDLDao {
    private static final Logger log = LoggerFactory.getLogger(DDLDao.class);
    private SqlSession getSqlSession() {
        return MyBatisServer.getInstance().getSqlSession();
    }

    public List<String> showTables(){
        try (SqlSession sqlSession = getSqlSession()) {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            String statement = getClass().getAnnotation(Dao.class).mapperPath() + methodName;
            return sqlSession.selectList(statement);
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

    public void createTable(String sql){
        try (SqlSession sqlSession = getSqlSession()) {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            String mapperPath = getClass().getAnnotation(Dao.class).mapperPath();
            String statement = mapperPath + "." + methodName;
            sqlSession.update(statement, sql);
            sqlSession.commit();
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
