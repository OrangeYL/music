package com.orange.music.service.impl;

import com.orange.music.dao.RankMapper;
import com.orange.music.domain.Rank;
import com.orange.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评价service接口实现类
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    /**
     * 新增
     *
     * @param rank
     * @return
     */
    @Override
    public boolean insert(Rank rank) {
        return rankMapper.insert(rank)>0;
    }

    /**
     * 查询评价总分
     *
     * @param songListId
     * @return
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    /**
     * 查询评价总人数
     *
     * @param songListId
     * @return
     */
    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    /**
     * 平均分
     *
     * @param songListId
     * @return
     */
    @Override
    public int AverageScore(Integer songListId) {
        int rankNum = rankMapper.selectRankNum(songListId);
        int scoreSum = rankMapper.selectScoreSum(songListId);
        if(rankNum==0){
            return 0;
        }
        return scoreSum/rankNum;
    }
}
