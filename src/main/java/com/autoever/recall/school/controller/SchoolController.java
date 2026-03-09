package com.autoever.recall.school.controller;

import com.autoever.recall.school.domain.School;
import com.autoever.recall.school.domain.SchoolType;
import com.autoever.recall.school.dto.SchoolResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
public class SchoolController {
    //    private final SchoolService schoolService;
    private static final List<School> testSchools = Arrays.asList(
            School.builder()
                  .name("서울교동초등학교")
                  .type(SchoolType.ELEMENTARY)
                  .imageUrl("https://img.khan.co.kr/newsmaker/913/20110222_813_42a.jpg")
                  .address("서울특별시 종로구 삼일대로 446")
                  .build(),
            School.builder()
                  .name("경기초등학교")
                  .type(SchoolType.ELEMENTARY)
                  .imageUrl("https://i.namu.wiki/i/Tija70TEQghotvbgxk71hSnZZ5wYmtlAbg7Om6QjFiZgwE0IParLVtTBWWbZkTsIME-BSau8ixsXn7VVN9eBQA.webp")
                  .address("서울특별시 서대문구 경기대로9길 10")
                  .build(),
            School.builder()
                  .name("대왕중학교")
                  .type(SchoolType.MIDDLE)
                  .imageUrl("https://mblogthumb-phinf.pstatic.net/MjAyMTEyMjRfMjkx/MDAxNjQwMzE1Njk5OTgy.XR73D9bZFMsKqwGdaPaMUr5YSEvHIqsmiVjFBFuTplcg.TB90d1g73HyPlEqEgIw0DWdvs1ms2FNSzfLGWzGM5mwg.PNG.greensnow1/대왕중학교_1.png?type=w800")
                  .address("서울특별시 강남구 광평로20길 7")
                  .build(),
            School.builder()
                  .name("압구정중학교")
                  .type(SchoolType.MIDDLE)
                  .imageUrl("https://www.google.com/url?sa=t&source=web&rct=j&url=https%3A%2F%2Fblog.naver.com%2Fgreensnow1%2F222654655995&ved=0CBYQjRxqFwoTCOD62Y3nkZMDFQAAAAAdAAAAABAH&opi=89978449")
                  .address("서울특별시 강남구 압구정로 225")
                  .build(),
            School.builder()
                  .name("대청중학교")
                  .type(SchoolType.MIDDLE)
                  .imageUrl(null)
                  .address("서울특별시 강남구 양재천로 321")
                  .build(),
            School.builder()
                  .name("세종과학고등학교")
                  .type(SchoolType.HIGH)
                  .imageUrl("https://i.namu.wiki/i/rSxWUC1sG_pkpZ8IhXSgKcu8uSXC5Hy5R1sA1r2pocMRRFzLNZCESDtTUbfaDwNcVOWmRheBvbzUTR4_449Lkg.webp")
                  .address("서울특별시 구로구 오리로21길 79")
                  .build(),
            School.builder()
                  .name("울산외국어고등학교")
                  .type(SchoolType.HIGH)
                  .imageUrl("https://i.namu.wiki/i/H4D42RhlIys6A1SS5-l26GRixhY0_5aEi0MdYu5AsRW6E-LY1ulKjeqnlcUiw-hq3cUTU8ujYW2Ro1eMeV1K9A.webp")
                  .address("울산광역시 북구 중산동로 32-46")
                  .build(),
            School.builder()
                  .name("대구국제학교")
                  .type(SchoolType.HIGH)
                  .imageUrl(null)
                  .address("대구광역시 동구 팔공로50길 22")
                  .build()
    );

    // 학교 검색 리스트 조회
    @GetMapping("/search")
    public ResponseEntity<SchoolResponse> findAllSchools(
            @RequestParam(value = "type", required = false, defaultValue = "all") String type,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        List<School> filteredSchools = testSchools.stream()
                                                  .filter(school -> {
                                                      if ("all".equals(type)) {
                                                          return true;
                                                      }
                                                      return school.getType().name().toLowerCase().equals(type);
                                                  })
                                                  .filter(school -> school.getName().contains(keyword))
                                                  .toList();

        SchoolResponse response = SchoolResponse.from(filteredSchools);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
