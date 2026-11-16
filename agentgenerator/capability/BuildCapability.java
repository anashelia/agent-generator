package agentgenerator.capability;

import module java.base;

public class BuildCapability implements Capability {
    public BuildCapability() {}

    @Override
    public String getName() {
        return "build";
    }

    @Override
    public String execute(String input) {
        return "[BUILD] " + input;
    }

    @Override
    public String describe() {
        return "Capability[build]";
    }
}