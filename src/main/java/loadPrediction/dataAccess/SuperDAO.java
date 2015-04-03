/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.dataAccess;

import  db.eDbType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建：2015/1/24 21:23
 * 作者：李倍存
 * 电邮：1174751315@qq.com
 */
public class SuperDAO {
    private SuperDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.defaultSession = this.sessionFactory.openSession();
    }

    private static Map<String, SuperDAO> instances = new HashMap<String, SuperDAO>();

    public static SuperDAO getInstanceOf(eDbType db) {
        if (db == eDbType.ACTUAL_LOAD) {
            SuperDAO dao = instances.get("actual-load");
            if (dao == null) {
                dao = instances.put("actual-load", new SuperDAO(new Configuration().configure("hibernate.cfg.actual-load.xml").buildSessionFactory()));
            }
            dao = instances.get("actual-load");
            return dao;
        }
        if (db == eDbType.ACCESS) {
            SuperDAO dao = instances.get("access");
            if (dao == null) {
                dao = instances.put("access", new SuperDAO(new Configuration().configure("hibernate.cfg.access.xml").buildSessionFactory()));
            }
            dao = instances.get("access");
            return dao;
        }
        if (db==eDbType.BACKUP){
            SuperDAO dao=instances.get("bkp");
            if (dao==null){
                dao=instances.put("bkp",new SuperDAO(new Configuration().configure("hibernate.cfg.bkp.xml").buildSessionFactory()));
            }
            dao=instances.get("bkp");
            return dao;
        }
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private SessionFactory sessionFactory;
    private Session defaultSession;

    /**
     * 在数据库中插入一个对象。
     *
     * @param o 将要插入的对象。
     * @throws Exception 当发生数据库写入异常时抛出。
     */
    protected void insert(Object o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.save(o);
        t.commit();
    }

    protected void insertOrUpdate(Object o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.saveOrUpdate(o);
        t.commit();
    }

    /**
     * 在数据库中检索一个对象。
     *
     * @param aClass 待检索的对象的类型。
     * @param pk     待检索的主键。
     * @return 主键所对应的数据表行所构成的对象图。
     * @throws Exception 当发生数据库检索异常时抛出。
     */
    protected Object query(Class aClass, Serializable pk) {
        Session session = defaultSession;
        Object o = session.get(aClass, pk);
        return o;
    }

    /**
     * 查询一个数据库表的所有对象。
     *
     * @param aClass 待检索对象的类型。
     * @return 待检索对象类型所对应的数据表的所有行所构成的对象图的链表。
     * @throws Exception 当发生数据库检索异常时抛出。
     */
    protected List query(Class aClass) {
        List list = null;
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("from " + aClass.getName());
        list = q.list();
        t.commit();
        return list;
    }

    /**
     * 更新一个数据库对象。
     *
     * @param o 用于替换数据库原有对象的新对象。
     * @throws Exception 当发生数据库写入异常时抛出。
     */
    protected void update(Object o) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.update(o);
        t.commit();
    }

    /**
     * 更新一个数据库对象。
     *
     * @param o 用于替换数据库原有对象的新对象。
     * @throws Exception 当发生数据库写入异常时抛出。
     */
    protected void delete(Class aClass) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        Query q = session.createQuery("delete " + aClass.getName());
        q.executeUpdate();
        t.commit();
    }

    /**
     * 在数据库中删除一个对象。
     *
     * @param aClass 待删除对象的类型。
     * @param pk     待删除对象的主键。
     * @throws Exception 当发生数据库写入异常或者主键未找到时抛出。
     */
    protected void delete(Class aClass, Serializable pk) {
        Session session = defaultSession;
        Transaction t = session.beginTransaction();
        session.delete(query(aClass, pk));
        t.commit();
    }
}
