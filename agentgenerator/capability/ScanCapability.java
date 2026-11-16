package agentgenerator.capability;

public class ScanCapability implements Capability {
    public ScanCapability() {}
    
    @Override
    public String getName() {
        return "scan";
    }
    
    @Override
    public String execute(String input) {
        return "[SCAN] " + input;
    }
    
    @Override
    public String describe() {
        return "Capability[scan]";
    }
}