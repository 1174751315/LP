/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.timerTask;

import common.IEnhancedCommand;

/**
 * 李倍存 创建于 2015-03-06 15:02。电邮 1174751315@qq.com。
 */
public class DataMoveCommand implements IEnhancedCommand {
    private IDataCopy receiver;

    public DataMoveCommand(IDataCopy receiver) {
        this.receiver = receiver;
    }

    @Override
    public Object execute() throws Exception {
        Object r = receiver.copy(null);
        this.log();
        return r;
    }

    private void log() {
    }
}
