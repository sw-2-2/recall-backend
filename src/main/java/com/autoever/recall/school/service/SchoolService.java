package com.autoever.recall.school.service;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.dto.SchoolFilterParams;
import com.autoever.recall.school.dto.SchoolMembersSearchParams;

import java.util.List;

public interface SchoolService {
    // type에 null이 들어오면 전체 검색, 값이 있으면 해당 타입으로 필터링
    List<School> searchSchools(SchoolMembersSearchParams params);

    // 키워드 없이 타입으로만 필터링 (기본값 all)
    List<School> getSchools(SchoolFilterParams params);
}
