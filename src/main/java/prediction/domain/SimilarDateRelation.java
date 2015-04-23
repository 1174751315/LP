/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package prediction.domain;

import prediction.domain.visitors.IDomainVisitor;
import org.hibernate.type.DateType;

import java.io.Serializable;

/**
 * Created by LBC on 2015/1/25.
 */
public class SimilarDateRelation implements IDomain, Serializable {

    public DateType getSimilarDate() {
        return similarDate;
    }

    public void setSimilarDate(DateType similarDate) {
        this.similarDate = similarDate;
    }

    public DateType getMe() {
        return me;
    }

    public void setMe(DateType me) {
        this.me = me;
    }

    private DateType me;
    private DateType similarDate;

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }
}
