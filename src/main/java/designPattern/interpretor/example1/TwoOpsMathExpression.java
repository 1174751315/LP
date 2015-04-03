/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package designPattern.interpretor.example1;

/**
 * 李倍存 创建于 2015-03-01 17:13。电邮 1174751315@qq.com。
 */
public class TwoOpsMathExpression extends Expression {
    public TwoOpsMathExpression(Expression op1, Expression op2, String op) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = op;
    }

    @Override
    public Double interpret() {
        if (operator.equals("+")) {
            return new Add(op1.interpret(), op2.interpret()).calc();
        } else {
            return 0.0;
        }
    }

    private Expression op1;
    private Expression op2;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Expression getOp1() {
        return op1;
    }

    public void setOp1(SingleNumberExpression op1) {
        this.op1 = op1;
    }

    public Expression getOp2() {
        return op2;
    }

    public void setOp2(SingleNumberExpression op2) {
        this.op2 = op2;
    }

    private String operator;

    public interface TwoOpsOperator {
        Double calc();
    }

    public class Add implements TwoOpsOperator {
        private Double result;

        public Add(Double op1, Double op2) {
            result = op1 + op2;
        }

        @Override
        public Double calc() {
            return result;
        }
    }
}
