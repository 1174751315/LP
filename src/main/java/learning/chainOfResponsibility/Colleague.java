/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package learning.chainOfResponsibility;

/**
 * 李倍存 创建于 2015-03-01 16:39。电邮 1174751315@qq.com。
 */
public class Colleague extends Handler {
    public Colleague(String name, Handler next) {
        super(name, next);
    }

    public void handleRequest(Request request) {
        if (request.getType() == eRequestType.jieqian) {
            System.err.println("同事 " + name + "说：无问题！借多少说吧。");
        } else {
            System.out.println("同事 " + name + "说：你有毛病啊，向我" + request.getType().toString());
            super.handleRequest(request);
        }

    }
}
