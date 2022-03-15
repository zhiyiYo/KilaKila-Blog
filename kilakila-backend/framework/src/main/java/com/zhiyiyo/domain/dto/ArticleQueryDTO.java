package com.zhiyiyo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleQueryDTO {
    private Integer pageNum = 1;

    @Range(min = 1, max = 40, message = "每页条目数只能在 1-40 之间")
    private Integer pageSize = 10;

    private Long categoryId;

    private Long tagId;

    @Pattern(regexp = "^((19)|(2\\d))\\d{2}/((0?[1-9])|1[012])$", message = "日期格式错误")
    private String date;
}
