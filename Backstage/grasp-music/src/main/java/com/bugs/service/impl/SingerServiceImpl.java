package com.bugs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bugs.bean.SimilarSinger;
import com.bugs.bean.Singer;
import com.bugs.service.SingerService;
import com.bugs.utils.HanyuPinyinUtil;
import com.bugs.utils.URIBuilderUtil;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    HanyuPinyinUtil pinyinUtil;
    @Autowired
    URIBuilderUtil uriBuilderUtil;
    @Autowired
    PoolingHttpClientConnectionManager manager;

    @Override
    public ArrayList<SimilarSinger> getSingerList() {
        ArrayList<SimilarSinger> res = new ArrayList<>(26);
        Singer[] allSinger = getAllSinger();
        if (allSinger != null) {
            //前10为热门歌手
            SimilarSinger hot = new SimilarSinger("热门");
            for (int i = 0; i < 10; i++) {
                Singer sin = allSinger[i];
                hot.getList().add(new Singer(sin.getId(), sin.getName(), sin.getAvatar(), sin.getFirstCharName()));
            }
            res.add(hot);
            // A B C ... Z

            HashMap<Character, SimilarSinger> temHash = classification(allSinger);
            for (int i = 0; i < 26; i++) {
                SimilarSinger similarSinger = temHash.get((char) ('A' + i));
                if (similarSinger != null) {
                    res.add(similarSinger);
                }
            }
        }

        return trimArrayList(res);
    }

    //去除null字段
    private ArrayList<SimilarSinger> trimArrayList(ArrayList<SimilarSinger> list) {
        ArrayList<SimilarSinger> res = new ArrayList<>(list.size());
        for (SimilarSinger simSin : list) {
            if (simSin != null) {
                res.add(simSin);
            }
        }
        return res;
    }


    /**
     * 获取每个字母所对应的歌手
     */
    private HashMap<Character, SimilarSinger> classification(Singer[] singers) {
        HashMap<Character, SimilarSinger> res = new HashMap<>();
        for (Singer singer : singers) {
            char key = singer.getFirstCharName();
            if (!res.containsKey(key)) { // 包含这个歌手
                SimilarSinger sin = new SimilarSinger(key + "");
                sin.getList().add(singer);
                res.put(key, sin);
            } else {
                SimilarSinger sin = res.get(key);
                sin.getList().add(singer);
                res.put(key, sin);
            }
        }
        return res;
    }

    /**
     * 获取歌手
     * https://u.y.qq.com/cgi-bin/musicu.fcg?-=getUCGI6116488249138035&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data=%7B%22comm%22%3A%7B%22ct%22%3A24%2C%22cv%22%3A0%7D%2C%22singerList%22%3A%7B%22module%22%3A%22Music.SingerListServer%22%2C%22method%22%3A%22get_singer_list%22%2C%22param%22%3A%7B%22area%22%3A-100%2C%22sex%22%3A-100%2C%22genre%22%3A-100%2C%22index%22%3A-100%2C%22sin%22%3A0%2C%22cur_page%22%3A1%7D%7D%7D
     */
    private Singer[] getAllSinger() {
        String url = "https://u.y.qq.com/cgi-bin/musicu.fcg";
        String data = "{\"comm\":{\"ct\":24,\"cv\":0},\"singerList\":{\"module\":\"Music.SingerListServer\",\"method\":\"get_singer_list\",\"param\":{\"area\":-100,\"sex\":-100,\"genre\":-100,\"index\":-100,\"sin\":0,\"cur_page\":1}}}";
        JSONObject jsonObj = uriBuilderUtil.getResponseByBasicParameter(url, data);
        if (jsonObj != null) {
            JSONArray jsonArr = jsonObj.getJSONObject("singerList").getJSONObject("data").getJSONArray("singerlist");
            Singer[] singers = new Singer[jsonArr.size()];
            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                String id = obj.getString("singer_mid");
                String name = obj.getString("singer_name");
                String pic = obj.getString("singer_pic");
                char index = pinyinUtil.getFirstCharByName(name);
                singers[i] = new Singer(id, name, pic, index);
            }
            return singers;
        }
        return null;
    }


}
