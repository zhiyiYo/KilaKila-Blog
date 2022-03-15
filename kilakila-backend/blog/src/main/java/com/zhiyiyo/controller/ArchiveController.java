package com.zhiyiyo.controller;

import com.zhiyiyo.domain.ResponseResult;
import com.zhiyiyo.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/archive")
public class ArchiveController {
    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/archiveCountList")
    public ResponseResult getArchiveCountList(Integer pageNum, Integer pageSize){
        return archiveService.getArchiveCountList(pageNum, pageSize);
    }

    @GetMapping("/archiveList")
    public ResponseResult getArchiveList(Integer pageNum, Integer pageSize){
        return archiveService.getArchiveList(pageNum, pageSize);
    }
}
