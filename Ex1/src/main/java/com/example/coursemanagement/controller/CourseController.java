package com.example.coursemanagement.controller;

import com.example.coursemanagement.dto.ApiResponse;
import com.example.coursemanagement.dto.CourseResponse;
import com.example.coursemanagement.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * GET /courses?page=0&size=10&sortBy=title&direction=ASC
     *
     * @param page      số trang (mặc định: 0)
     * @param size      số phần tử mỗi trang (mặc định: 10)
     * @param sortBy    tên trường dùng để sắp xếp (có thể không truyền)
     * @param direction chiều sắp xếp ASC/DESC (mặc định: DESC)
     * @return ApiResponse<Page<CourseResponse>>
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction
    ) {
        Page<CourseResponse> courseResponsePage = courseService.getPagedCourses(page, size, sortBy, direction);

        ApiResponse<Page<CourseResponse>> response = ApiResponse.<Page<CourseResponse>>builder()
                .success(true)
                .message("Ok")
                .data(courseResponsePage)
                .build();

        return ResponseEntity.ok(response);
    }
}
