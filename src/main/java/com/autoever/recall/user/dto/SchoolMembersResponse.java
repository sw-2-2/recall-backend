package com.autoever.recall.user.dto;

import java.util.List;

public record SchoolMembersResponse(
        List<SchoolMemberDto> members
) {
}
