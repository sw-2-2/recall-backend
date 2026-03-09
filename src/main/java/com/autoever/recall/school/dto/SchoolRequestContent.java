package com.autoever.recall.school.dto;

import com.autoever.recall.school.domain.SchoolCommandContent;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "contentType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SchoolCreateRequest.class, name = "new"),
        @JsonSubTypes.Type(value = SchoolConnectRequest.class, name = "exist")
})
public sealed interface SchoolRequestContent
    permits SchoolCreateRequest, SchoolConnectRequest {
    SchoolCommandContent toDomain();
}
