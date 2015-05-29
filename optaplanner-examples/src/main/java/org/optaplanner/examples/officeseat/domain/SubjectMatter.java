package org.optaplanner.examples.officeseat.domain;

public enum SubjectMatter {

	Anaconda("Anaconda"),
	Services("Services"),
	Shells("Shells"),
	Low_volume("Low_volume"),
	ClusterHA("ClusterHA"),
	Containers("Containers"),
	Desktop("Desktop"),
	Filesystem("Filesystem"),
	Identity_Management("Identity_Management"),
	Kernel("Kernel"),
	Networking("Networking"),
	MRG("MRG"),
	SAP("SAP"),
	Security("Security"),
	SysMgmt("SysMgmt"),
	Storage("Storage"),
	Tools("Tools"),
	Virtualization("Virtualization"),
	
	
	Business_Rule_Frameworks("Business_Rule_Frameworks"),
	ESB("ESB"),
	FuseSource("FuseSource"),
	JBDS("JBDS"),
	JBoss_Base_AS("JBoss_Base_AS"),
	JBoss_Clustering("JBoss_Clustering"),
	JBoss_Portal("JBoss_Portal"),
	JBoss_Security("JBoss_Security"),
	JON("JON"),
	JVM_Diagnostics("JVM_Diagnostics"),
	Messaging("Messaging"),
	Teiid_MMX("Teiid_MMX"),
	Transactions_JCA_SQL("Transactions_JCA_SQL"),
	Webservers("Webservers"),
	Web_Services("Web_Services"),
	Web_Frameworks("Web_Frameworks"),
	
	
	Ceph("Ceph"),
	CFME("CFME"),
	Gluster("Gluster"),
	Shift("Shift"),
	Stack("Stack"),
	Spinalstack("Spinalstack");
	
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
