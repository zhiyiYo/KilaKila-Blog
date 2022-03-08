package com.zhiyiyo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagCountVo {
    private Long id;
    private String name;
    private Integer count;
}
