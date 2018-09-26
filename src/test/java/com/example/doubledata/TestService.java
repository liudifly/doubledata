package com.example.doubledata;

import com.alibaba.fastjson.JSONObject;
import com.example.doubledata.mapper.DBOneMapper;
import com.example.doubledata.mapper1.DBTwoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author liudi
 * @version V
 * @Package com.example.doubledata.service
 * @ClassName: TestService
 * @Description: 类作用描述
 * @time 2018/8/27 11:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestService {

    @Autowired
    private DBOneMapper dbOneMapper;

    @Autowired
    private DBTwoMapper dbTwoMapper;

    @Test
    public void updateTravel() {

        List<JSONObject> list = dbTwoMapper.queryTravelInfo();
            int i = 0;
        for (JSONObject object: list) {
            i+= dbOneMapper.updateTravelInfo(object);
        }
        System.out.println("修改了"+i+"条旅行社数据");

    }

    @Test
    public void updateStore() {

        List<JSONObject> list = dbTwoMapper.queryStoreInfo();
        int i = 0;
        for (JSONObject object: list) {
            i+= dbOneMapper.updateStoreInfo(object);
            System.out.println("处理了一条");
        }
        System.out.println("修改了"+i+"条门店数据");

    }



}
