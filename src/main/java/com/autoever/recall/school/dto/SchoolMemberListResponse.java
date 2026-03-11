package com.autoever.recall.school.dto;

import java.util.List;

public record SchoolMemberListResponse(
        List<SchoolMemberResponse> members
) {
}
