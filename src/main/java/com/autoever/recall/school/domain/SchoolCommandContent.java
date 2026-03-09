package com.autoever.recall.school.domain;

public sealed interface SchoolCommandContent
        permits SchoolConnectCommand, SchoolCreateCommand {
}
