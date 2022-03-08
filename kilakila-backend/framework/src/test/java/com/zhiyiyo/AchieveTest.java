package com.zhiyiyo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhiyiyo.domain.vo.ArchiveCountVo;
import com.zhiyiyo.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AchieveTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void testGetAchieveCount() {
        LambdaQueryWrapper<ArchiveCountVo> wrapper=new LambdaQueryWrapper<>();

    }
}
