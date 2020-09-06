package com.example.enrollment.domain.course;

import com.example.enrollment.domain.course.converter.CreditConverter;
import com.example.enrollment.domain.course.enums.Credit;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 15)
    @Convert(converter = CreditConverter.class)
    private Credit credit;

    @Embedded
    private Sections sections;

    public List<Section> getSections() {
        return Collections.unmodifiableList(sections.getContents());
    }
}
