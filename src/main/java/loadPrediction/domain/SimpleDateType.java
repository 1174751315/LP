/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */


package loadPrediction.domain;

import loadPrediction.domain.visitors.IDomainVisitor;

import java.io.Serializable;

/**
 * LBC 创建于 2015/1/25 17:15。电邮 1174751315@qq.com。
 */
public class SimpleDateType implements IDomain, Serializable {
    private Integer code;
    private String name;

    public SimpleDateType() {
    }

    public SimpleDateType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object accept(IDomainVisitor visitor) {
        return null;
    }
}
