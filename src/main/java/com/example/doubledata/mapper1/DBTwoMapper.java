package com.example.doubledata.mapper1;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liudi
 * @version V
 * @Package com.example.doubledata.mapper
 * @ClassName: DBTwoMapper
 * @Description: 类作用描述
 * @time 2018/8/27 11:00
 */
@Repository
public interface DBTwoMapper {

    @Select("select * from tb_bank_return ")
    List<JSONObject> getdata2();


    @Select("select * from tb_cloud_corp ")
    List<JSONObject> queryTravelInfo();



    @Select("select * from tb_cloud_travelstore ")
    List<JSONObject> queryStoreInfo();
}
