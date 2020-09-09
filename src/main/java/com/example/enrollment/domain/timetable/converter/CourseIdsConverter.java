package com.example.enrollment.domain.timetable.converter;

import com.example.enrollment.domain.ProjectConstant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class CourseIdsConverter implements AttributeConverter<List<Long>, String> {
    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        return attribute.stream()
                .map(Object::toString)
                .collect(Collectors.joining(ProjectConstant.COURSE_ID_DELIMITER));
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split(ProjectConstant.COURSE_ID_DELIMITER))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
