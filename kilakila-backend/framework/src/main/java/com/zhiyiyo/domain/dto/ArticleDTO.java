package com.zhiyiyo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long id;

    @NotBlank(message = "博客标题不能为空")
    private String title;

    /**
     * 分类名
     */
    @NotBlank(message = "博客分类不能为空")
    private String category;

    @NotBlank(message = "博客内容不能为空")
    private String content;

    @Length(max = 1024, message = "摘要长度不能超过 1024 个字符哦~")
    private String summary;

    /**
     * 标签名列表
     */
    private List<String> tags;

    private String thumbnail;

    @NotNull(message = "必须指明博客是否为草稿")
    private Boolean isDraft;
}
