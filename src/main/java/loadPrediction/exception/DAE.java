/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.exception;

/**
 * 李倍存 创建于 2015-03-25 14:19。电邮 1174751315@qq.com。
 */
public class DAE extends LPE {

    public DAE(String message, eType type) {
        super(message);
        this.type = type;
    }

    public DAE(String message) {
        this(message, eType.UNDEFINED);
    }


    public DAE(eType type) {
        this(MSG_DEFAULT, type);
    }

    public DAE() {
        this(MSG_DEFAULT, eType.UNDEFINED);
    }

    private eType type;

    public enum eType {
        NOT_FOUND,
        ALREADY_EXIST,
        UNDEFINED
    }

    public static final String MSG_NOT_FOUND = "数据项未找到";
    public static final String MSG_DEFAULT = "无提示信息";
}
