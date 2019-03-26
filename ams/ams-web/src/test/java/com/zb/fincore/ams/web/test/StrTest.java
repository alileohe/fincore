package com.zb.fincore.ams.web.test;

import com.zb.fincore.common.utils.DateUtils;
import net.sf.json.JSONObject;

import java.util.Date;
import java.util.Iterator;

/**
 * Created by MABIAO on 2017/7/3 0003.
 */
public class StrTest {

    public static void main(String args[]){
//        String string = "123.doc";
//        String str = string.substring(0,string.lastIndexOf("."));
//        System.out.println(str);

//        JSONObject jsonObject = JSONObject.fromObject(object);
//        Iterator ite = jsonObject.keys();

        String str = DateUtils.format(new Date(),"yyyyMMdd");
        System.out.println(str);
        str = str.substring(2,str.length());
        System.out.println(str);
    }
}
