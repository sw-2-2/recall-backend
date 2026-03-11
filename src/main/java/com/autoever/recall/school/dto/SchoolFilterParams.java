package com.autoever.recall.school.dto;

public record SchoolFilterParams(
        String type
) {
    private static final String ALL_TYPE = "all";

    public SchoolFilterParams {
        if (type == null) {
            type = ALL_TYPE;
        }

        // "all"와 정확히 일치(equals)하지 않는 경우에만 SchoolTypeDto 검증 수행
        if(!ALL_TYPE.equals(type)) {
            SchoolTypeDto.fromKey(type);
        }
    }

    public boolean isAllType() {
        return ALL_TYPE.equals(type);
    }
}
