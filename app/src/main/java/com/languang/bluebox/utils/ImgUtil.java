package com.languang.bluebox.utils;

import com.languang.bluebox.entity.ImgEntity;
import com.languang.bluebox.entity.ImgListEntity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImgUtil {
    public static List<ImgListEntity> getTimeImg(String res) {
        List<ImgListEntity> imgListEntity = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(res);
            Iterator<String> keys = jsonObject.keys();
            ArrayList<String> list0 = new ArrayList<String>();
            while (keys.hasNext()) {
                list0.add(keys.next());
            }
            String data = jsonObject.getString("data");

            JSONObject jsonObject1 = new JSONObject(data);

            Iterator<String> keys1 = jsonObject1.keys();
            ArrayList<String> list2 = new ArrayList<String>();
            while (keys1.hasNext()) {
                list2.add(keys1.next());
            }
            JSONObject jsonObject2 = new JSONObject(jsonObject1.getString("files"));
            Iterator<String> keys2 = jsonObject2.keys();
            ArrayList<String> list3 = new ArrayList<String>();
            while (keys2.hasNext()) {
                list3.add(keys2.next());
            }

            for (int i = 0; i < list3.size(); i++) {
                ImgListEntity imgListEntity1 = new ImgListEntity();
                List<ImgEntity> list4 = new ArrayList<>();
                JSONObject jsonObject4 = new JSONObject(jsonObject2.getString(list3.get(i)));
                imgListEntity1.setTime(list3.get(i));
                Iterator<String> keys3 = jsonObject4.keys();

                while (keys3.hasNext()) {
                    String key = keys3.next();
                    JSONObject jsonObject5 = new JSONObject(jsonObject4.getString(key));
                    ImgEntity imgEntity = new ImgEntity();
                    imgEntity.setIdimg(jsonObject5.getString("idimg"));
                    imgEntity.setUuid(jsonObject5.getString("uuid"));
                    imgEntity.setSrctype(jsonObject5.getString("srctype"));
                    imgEntity.setSmallname(jsonObject5.getString("smallname"));
                    imgEntity.setSmallpath(jsonObject5.getString("smallpath"));
                    imgEntity.setSrcname(jsonObject5.getString("srcname"));
                    imgEntity.setSrcpath(jsonObject5.getString("srcpath"));
                    list4.add(imgEntity);
                    imgListEntity1.setImgEntityList(list4);
                }
                imgListEntity.add(imgListEntity1);

            }
        } catch (Exception e) {
            return null;
        }
        return imgListEntity;
    }
}
