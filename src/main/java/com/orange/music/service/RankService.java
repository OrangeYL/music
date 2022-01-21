package com.orange.music.service;

import com.orange.music.domain.Rank;
import org.apache.ibatis.annotations.Param;

/**
 * 评价service接口
 */
public interface RankService {

    /**
     * 新增
     * @param rank
     * @return
     */
    public boolean insert(@Param("rank") Rank rank);

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

    /**
     * 平均分
     * @param songListId
     * @return
     */
    public int AverageScore(Integer songListId);
}
