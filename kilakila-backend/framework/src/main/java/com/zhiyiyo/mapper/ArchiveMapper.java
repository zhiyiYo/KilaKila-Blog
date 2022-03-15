package com.zhiyiyo.mapper;

import com.zhiyiyo.domain.vo.ArchiveCountVo;
import com.zhiyiyo.domain.vo.ArchiveVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArchiveMapper {
    /**
     * 获取每一种归档的文章数量
     *
     * @param start    归档偏移量
     * @param pageSize 每一页的归档数量
     * @return 每一种归档的文章数量
     */
    List<ArchiveCountVo> selectArchiveCount(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    /**
     * 获取归档的总数
     *
     * @return 归档的总数
     */
    Long selectArchiveTotalCount();

    /**
     * 获取每一种归档的
     *
     * @param start    归档偏移量
     * @param pageSize 每一页的归档数量
     * @return 每一种归档的文章数量
     */
    List<ArchiveVo> selectArchiveList(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
}
