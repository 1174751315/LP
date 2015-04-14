/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.dataAccess;

import  db.eDbType;
import  loadPrediction.domain.RawWeatherData;
import  loadPrediction.exception.DAE;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015/3/11 21:00。电邮 1174751315@qq.com。
 */
public class DAORawWeatherData extends AbstractDAO {
    public DAORawWeatherData(SuperDAO superDAO) {
        this.superDAO = superDAO;
    }

    public List<RawWeatherData> query() {
        List<RawWeatherData> list = new LinkedList<RawWeatherData>();
        Session defaultSession = SuperDAO.getInstanceOf(eDbType.ACCESS).getSessionFactory().openSession();
        Transaction t = defaultSession.beginTransaction();
        String ql = "from RawWeatherData";
        Query sq = defaultSession.createQuery(ql);
          List l = sq.list();
        t.commit();
        for (int i = 0; i < l.size(); i++) {
            list.add((RawWeatherData) l.get(i));
        }
        return list;
    }

    public RawWeatherData query(String date, Integer cityId) throws DAE {
        Session defaultSession = SuperDAO.getInstanceOf(eDbType.ACCESS).getSessionFactory().openSession();
        Transaction t = defaultSession.beginTransaction();

        String ql = "from RawWeatherData where dateString=? and cityId=?";
        Query sq = defaultSession.createQuery(ql);
        sq.setParameter(0, unnamed(date));
        sq.setParameter(1, cityId);
        t.commit();
        RawWeatherData o = (RawWeatherData) sq.uniqueResult();
        if (o == null)
            throw new DAE(RawWeatherData.class.getSimpleName());
        return o;
    }

    public RawWeatherData queryThenDelete(String date, Integer cityId) throws DAE {

        try {
            RawWeatherData r = query(date, cityId);
            Session defaultSession = SuperDAO.getInstanceOf(eDbType.ACCESS).getSessionFactory().openSession();
            Transaction t = defaultSession.beginTransaction();
            String ql = "delete RawWeatherData where dateString=? and cityId=?";
            Query sq = defaultSession.createQuery(ql);
            sq.setParameter(0, unnamed(date));
            sq.setParameter(1, cityId);
            t.commit();
            return r;
        } catch (DAE e) {
            throw e;
        }

    }

    public void delete(String date) {
        Session defaultSession = SuperDAO.getInstanceOf(eDbType.ACCESS).getSessionFactory().openSession();
        Transaction t = defaultSession.beginTransaction();
        String ql = "delete RawWeatherData where dateString=?";
        Query sq = defaultSession.createQuery(ql);
        sq.setParameter(0, unnamed(date));
        sq.executeUpdate();
        t.commit();
    }

    public void insert(RawWeatherData weatherData) {
        superDAO.insert(weatherData);
    }

    private String unnamed(String in) {
//        String s=in.substring(4);
//        s=s.replaceAll("0","");
//        in=in.substring(0,4);
//        in=in.concat(s);
//        in=in.replaceAll("-","/");
        return in;
    }
}


