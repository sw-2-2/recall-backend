package com.autoever.recall.school.dto;

public record SchoolFilterParams(
        String type
) {
    private static final String ALL_TYPE = "all";

    public SchoolFilterParams {
        if (type == null) {
            type = ALL_TYPE;
        }
    }

    public boolean isAllType() {
        return ALL_TYPE.equals(type);
    }
}
