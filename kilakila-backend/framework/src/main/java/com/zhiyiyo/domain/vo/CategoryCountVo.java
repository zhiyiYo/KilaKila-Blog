package com.zhiyiyo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCountVo {
    private Long id;
    private String name;
    private Integer count;
    private Long pid;
}
