package com.sms.core.common;

public enum Designation {

    VP(""), TEAM_LEADER("VP"), CONSULTANT("TEAM_LEADER");

    private final String superior;

    Designation(final String superior) {
        this.superior = superior;
    }

    public String getSuperior() {
        return superior;
    }

}
