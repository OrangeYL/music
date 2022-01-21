package com.orange.music.dao;


import com.orange.music.domain.Rank;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评价mapper接口
 */
@Repository
public interface RankMapper {

    /**
     * 新增
     * @param rank
     * @return
     */
   public int insert(@Param("rank")Rank rank);

    /**
     * 查询评价总分
     * @param songListId
     * @return
     */
   public int selectScoreSum(Integer songListId);


    /**
     * 查询评价总人数
     * @param songListId
     * @return
     */
    public int selectRankNum(Integer songListId);
}
