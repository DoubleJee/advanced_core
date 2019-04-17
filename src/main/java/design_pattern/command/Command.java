package design_pattern.command;
public interface Command {
    /**
     * 执行命令
     */
    void execute();

    /**
     * 撤回命令
     */
    void undo();
}
