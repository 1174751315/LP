/*
 * 版权所有 (c) 2015 。 李倍存 （iPso）。
 * 所有者对该文件所包含的代码的正确性、执行效率等任何方面不作任何保证。
 * 所有个人和组织均可不受约束地将该文件所包含的代码用于非商业用途。若需要将其用于商业软件的开发，请首先联系所有者以取得许可。
 */

package loadPrediction.core.test;

import common.ICommandMacro;
import common.IEnhancedCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * 李倍存 创建于 2015-02-22 21:26。电邮 1174751315@qq.com。
 */
public class TestCommands implements ICommandMacro {
    private List<IEnhancedCommand> commandsToExecute = new LinkedList<IEnhancedCommand>();
    private List<IEnhancedCommand> historyCommands = new LinkedList<IEnhancedCommand>();

    private TestCommands() {
    }

    private static TestCommands instance = new TestCommands();

    public static TestCommands getInstance() {
        return instance;
    }

    private Boolean enableLog;

    public Boolean getEnableLog() {
        return enableLog;
    }

    public void setEnableLog(Boolean enableLog) {
        this.enableLog = enableLog;
    }


    @Override
    public void add(IEnhancedCommand command) {
        commandsToExecute.add(0, command);
        if (commandsToExecute.size() > 100)
            execute();
    }

    @Override
    public void remove(IEnhancedCommand command) {
        commandsToExecute.remove(command);
    }

    @Override
    public void execute() {
        Integer size = commandsToExecute.size();
        String err = null;
        for (int i = 0; i < size; i++) {
            try {
                err = (String) commandsToExecute.get(0).execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (err != null)
                log(err);

            historyCommands.add(commandsToExecute.remove(0));
        }
    }

    private void log(String errMsg) {
        System.err.println(errMsg);
    }

}
