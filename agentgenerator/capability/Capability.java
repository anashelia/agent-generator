package agentgenerator.capability;

public interface Capability {
    public abstract String getName();
    public abstract String execute(String input);
    public abstract String describe();

}