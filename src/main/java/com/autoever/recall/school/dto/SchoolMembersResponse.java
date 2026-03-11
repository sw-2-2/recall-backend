package com.autoever.recall.school.dto;

import java.util.List;

public record SchoolMembersResponse(
        List<SchoolMemberResponse> members
) {
}
