package com.autoever.recall.school.service;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolCreateCommand;
import com.autoever.recall.school.domain.SchoolType;

import java.util.List;

public interface SchoolService {
    // type에 null이 들어오면 전체 검색, 값이 있으면 해당 타입으로 필터링
    List<School> searchSchools(String keyword, boolean isAllType, SchoolType type);

    // 키워드 없이 타입으로만 필터링 (기본값 all)
    List<School> getSchools(boolean isAllType, SchoolType type);

    /*
    * 특정 학교 조회 (Fetch Join 없이)
    * 없으면 내부적으로 exception throw
    * */
    School getSchool(Long schoolId);

    /*
    * 학교 생성
    * */
    School createSchool(SchoolCreateCommand command);
}
