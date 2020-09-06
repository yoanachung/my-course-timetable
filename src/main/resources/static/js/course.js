const course = {
    selectedCourseClass: 'selected',

    init: function () {
        const _this = this;

        $('.course').click(function () {
            _this.toggleSelectSection(this);
        });

        $('.scheduleBtn').click(function () {
            _this.schedule();
        });
    },

    toggleSelectSection: function (elem) {
        $(elem).toggleClass(this.selectedCourseClass);
    },

    schedule: function () {
        const selectedCourses = $('.course.' + this.selectedCourseClass);
        const courseIds = $.map(selectedCourses, elem => $(elem).data('course-id'));

        const data = {
          "courseIds": courseIds
        };

        $.ajax({
            type: 'POST',
            url: '/timetable',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function (res) {
                alert('성공!');
                console.log(res);
            },
            error: function (error) {
                alert(JSON.stringify(error));
            }
        });
    }
};

course.init();
