package com.wuzt.myblog.dao;

import com.wuzt.myblog.model.AccessRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: wuzt
 * @Date: 2019/1/30 11:03
 * @Description:
 */
@Repository
public interface AccessRecordDao {
    /*
     * Description:批量添加访问记录
     * @auther: wuzt
     * @date: 2019/1/30 11:04
     */
    public void addAccessRecords(List<AccessRecord> accessRecord);

}
