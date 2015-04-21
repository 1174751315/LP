/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.config;

import common.IEnhancedCommandWithUndo;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-02-27 11:18。电邮 1174751315@qq.com。
 */
public class ChangeConfigurationCommand implements IEnhancedCommandWithUndo {

    private List<AllConfiguration.Memento> mementos = new LinkedList<AllConfiguration.Memento>();
    private AllConfiguration configuration;

    public ChangeConfigurationCommand(AllConfiguration receiver) {
        configuration = receiver;
    }

    @Override
    public Object execute() {
        mementos.add(0, configuration.createMemento());
        configuration.changeAll();
        return configuration;
    }

    @Override
    public Object unExecute() {
        configuration.recoverFromMemento(mementos.remove(0));
        return configuration;
    }

}
