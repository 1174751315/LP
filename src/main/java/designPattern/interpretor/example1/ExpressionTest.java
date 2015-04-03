/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package designPattern.interpretor.example1;

import org.junit.Test;

public class ExpressionTest {

    @Test
    public void testInterpret() throws Exception {
        Expression num1 = new SingleNumberExpression(1.2);
        Expression num2 = new SingleNumberExpression(1.3);
        Expression num1pulsnum2 = new TwoOpsMathExpression(num1, num2, "+");
        Expression num1pulsnum2plusnum2 = new TwoOpsMathExpression(num1pulsnum2, num2, "+");


        Expression[] params = new Expression[2];
        params[0] = num1pulsnum2plusnum2;
        params[1] = new SingleNumberExpression(2.);
        Expression num1pulsnum2plusnum2sq = new FunctionInvokingExpression("pow", params);
        System.out.println(num1pulsnum2plusnum2sq.interpret());

    }
}