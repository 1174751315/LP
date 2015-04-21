/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.timerTask;

import common.ICommandMacro;
import common.IEnhancedCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-03-06 15:08。电邮 1174751315@qq.com。
 */
public class DataMoveManager implements ICommandMacro {
    private static DataMoveManager instance = new DataMoveManager();

    public static DataMoveManager instance() {
        return instance;
    }

    private List<IEnhancedCommand> commands = new LinkedList<IEnhancedCommand>();

    private DataMoveManager() {
    }

    @Override
    public void add(IEnhancedCommand command) {
        commands.add(command);

    }

    @Override
    public void remove(IEnhancedCommand command) {
        commands.remove(command);
    }

    @Override
    public void execute() {

    }
}
