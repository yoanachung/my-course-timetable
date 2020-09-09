package com.example.enrollment.domain.course;

import com.example.enrollment.domain.course.enums.Period;
import com.example.enrollment.domain.course.enums.Weekday;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SectionTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Section section;

    @Column(nullable = false, length = 10)
    @Enumerated(value = EnumType.STRING)
    private Weekday weekday;

    @Column(nullable = false, length = 5)
    @Enumerated(value = EnumType.STRING)
    private Period period;

    @Column(nullable = false)
    private Integer periodCount;

    public boolean isTimeOverlapped(SectionTime anotherSectionTime) {
        if (anotherSectionTime.getWeekday() != weekday) {
            return false;
        }

        return Period.getPeriodsByFirstPeriod(period, periodCount).stream()
                .anyMatch(p -> p == anotherSectionTime.getPeriod());
    }
}
