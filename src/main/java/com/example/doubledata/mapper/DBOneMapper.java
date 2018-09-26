package com.example.doubledata.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liudi
 * @version V
 * @Package com.example.doubledata.mapper
 * @ClassName: DBOneMapper
 * @Description: 类作用描述
 * @time 2018/8/27 10:59
 */
@Repository
public interface DBOneMapper {

    @Select("select * from s_area_t ")
    List<JSONObject> getdata1();




    @Update("update s_travelinfo_t set TRAVEL_REGTIME = #{CORP_REGTIME} where PID = #{CORP_ID}")
    int updateTravelInfo( JSONObject travelInfo);



    @Update("update s_travel_store_t set STORE_REGTIME = #{STORE_REGTIME} where PID = #{STORE_ID}")
    int updateStoreInfo(JSONObject storeInfo);


}
