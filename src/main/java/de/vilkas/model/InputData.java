package de.vilkas.model;

public class InputData {
    private String name;
    private String policyJson;

    public InputData(String name, String policy) {
        this.name = name;
        this.policyJson = policy;
    }

    public String getName() {
        return name;
    }

    public String getPolicy() {
        return policyJson;
    }
}
