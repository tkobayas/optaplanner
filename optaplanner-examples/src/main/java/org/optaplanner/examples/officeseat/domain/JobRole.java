package org.optaplanner.examples.officeseat.domain;


public enum JobRole {

	TSE("TSE"),
	SEG("SEG"),
	TAM("TAM"),
	LE("LE"),
	ENG("ENG"),
	MGR("MGR");
	
    public static JobRole valueOfCode(String code) {
        for (JobRole jobRole : values()) {
            if (code.equalsIgnoreCase(jobRole.getCode())) {
                return jobRole;
            }
        }
        return null;
    }

    private String code;

    private JobRole(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
