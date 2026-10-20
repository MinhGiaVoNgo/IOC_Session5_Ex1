package com.example.coursemanagement.service;

import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.entity.Course;
import com.example.coursemanagement.repository.CourseRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Lấy danh sách khóa học có phân trang và sắp xếp.
     *
     * @param page      số trang (bắt đầu từ 0)
     * @param size      số phần tử mỗi trang
     * @param sortBy    tên trường dùng để sắp xếp (nếu null hoặc rỗng → mặc định "id")
     * @param direction chiều sắp xếp (ASC / DESC)
     * @return Page<CourseResponse>
     */
    public Page<CourseResponse> getPagedCourses(int page, int size, String sortBy, Sort.Direction direction) {

        // Safety check: nếu page < 0 → tự động điều chỉnh về 0
        if (page < 0) {
            page = 0;
        }

        // Nếu sortBy không được truyền → mặc định sắp xếp theo "id"
        if (sortBy == null || sortBy.trim().isEmpty()) {
            sortBy = "id";
        }

        // Tạo đối tượng Sort từ direction và sortBy
        Sort sort = Sort.by(direction, sortBy);

        // Tạo đối tượng Pageable từ page, size, sort
        Pageable pageable = PageRequest.of(page, size, sort);

        // Sử dụng findAll(Pageable) để lấy dữ liệu từ database
        Page<Course> coursePage = courseRepository.findAll(pageable);

        // Map từ Page<Course> sang Page<CourseResponse> sử dụng .map()
        Page<CourseResponse> courseResponsePage = coursePage.map(course -> CourseResponse.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructor(course.getInstructor())
                .price(course.getPrice())
                .build()
        );

        return courseResponsePage;
    }
}
