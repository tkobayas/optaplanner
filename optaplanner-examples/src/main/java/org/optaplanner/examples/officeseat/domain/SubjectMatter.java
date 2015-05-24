package org.optaplanner.examples.officeseat.domain;

public enum SubjectMatter {

	BASE_AS("BASE_AS"),
	BUSINESS_RULE_FRAMEWORK("BUSINESS_RULE_FRAMEWORK"),
	JBOSS_PORTAL("JBOSS_PORTAL"),
	OPEN_SHIFT("OPEN_SHIFT"),
	JBOSS_CLUSTER("JBOSS_CLUSTER");
	
    public static SubjectMatter valueOfCode(String code) {
        for (SubjectMatter subjectMatter : values()) {
            if (code.equalsIgnoreCase(subjectMatter.getCode())) {
                return subjectMatter;
            }
        }
        return null;
    }

    private String code;

    private SubjectMatter(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
