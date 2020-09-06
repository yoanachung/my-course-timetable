package com.example.enrollment.domain.course;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<SectionTime> sectionTimes;

    public List<SectionTime> getSectionTimes() {
        return Collections.unmodifiableList(sectionTimes);
    }
}
