package com.wuzt.myblog.scheduling;

import com.wuzt.myblog.dao.AccessRecordDao;
import com.wuzt.myblog.model.AccessRecord;
import com.wuzt.myblog.redis.RedisUtil;
import com.wuzt.myblog.utils.ObjectUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Auther: wuzt
 * @Date: 2019/1/30 11:09
 * @Description:
 */
@Component
public class SchedulingJob {

    @Autowired
    private AccessRecordDao accessRecordDao;
    @Autowired
    private RedisUtil redisUtil;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SchedulingJob.class);

    @Scheduled(cron = "0 1 0 * * ?")
    public void fixedDelayJob(){
        logger.info("----- 访问记录持久化定时任务执行 -----");
        List<AccessRecord> accessRecordList = null;
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        String currentDate = df.format(cal.getTime());
        Object o = redisUtil.get("accessRecord_" + currentDate);
        if(ObjectUtils.isNotNull(o)){
            accessRecordList = (List<AccessRecord>) o;
            if (ObjectUtils.isNotNull(accessRecordList)){
                for(int i=0;i<accessRecordList.size();i+=1000){
                    List<AccessRecord> accessRecordListDto = new ArrayList<AccessRecord>();
                    if((i+1000) < accessRecordList.size()){
                        accessRecordListDto = accessRecordList.subList(i, i+1000);
                    }else{
                        accessRecordListDto = accessRecordList.subList(i, accessRecordList.size()-1);
                    }
                    accessRecordDao.addAccessRecords(accessRecordListDto);
                }
            }
        }
        redisUtil.del("accessRecord_" + currentDate);
        logger.info("----- 访问记录持久化定时任务结束 -----");
    }

}
