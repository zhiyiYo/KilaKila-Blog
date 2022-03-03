package com.zhiyiyo.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkVo {
    private Long id;
    private String name;
    private String logo;
    private String description;
    private String address;
}
