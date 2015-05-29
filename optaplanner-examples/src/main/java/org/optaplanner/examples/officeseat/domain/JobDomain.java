package org.optaplanner.examples.officeseat.domain;


public enum JobDomain {

	PL("PL"),
	MW("MW"),
	EM("EM");
	
    public static JobDomain valueOfCode(String code) {
        for (JobDomain jobDomain : values()) {
            if (code.equalsIgnoreCase(jobDomain.getCode())) {
                return jobDomain;
            }
        }
        return null;
    }

    private String code;

    private JobDomain(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
