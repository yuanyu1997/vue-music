package com.bugs.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bugs.bean.SimilarSinger;
import com.bugs.bean.Singer;
import com.bugs.utils.HanyuPinyinUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class SingerService {
    @Autowired
    HanyuPinyinUtil pinyinUtil;

    @Autowired
    PoolingHttpClientConnectionManager manager;

    public ArrayList<SimilarSinger> getSingerList() throws IOException, URISyntaxException {
        ArrayList<SimilarSinger> res = new ArrayList<>(26);
        Singer[] allSinger = getAllSinger();
        //System.out.println(Arrays.toString(allSinger));
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
        System.out.println(res.get('D'));
        return res;
    }

    /**
     * 获取歌手
     */
    private Singer[] getAllSinger() throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(manager).build();
        URIBuilder uriBuilder = new URIBuilder("https://u.y.qq.com/cgi-bin/musicu.fcg");
        uriBuilder.setParameter("g_tk", "5381");
        uriBuilder.setParameter("loginUin", "0");
        uriBuilder.setParameter("hostUin", "0");
        uriBuilder.setParameter("format", "json");
        uriBuilder.setParameter("inCharset", "utf8");
        uriBuilder.setParameter("outCharset", "utf-8");
        uriBuilder.setParameter("notice", "0");
        uriBuilder.setParameter("platform", "yqq.json");
        uriBuilder.setParameter("needNewCode", "0");
        //{
        //    "comm": {
        //        "ct": 24,
        //        "cv": 0
        //    },
        //    "singerList": {
        //        "module": "Music.SingerListServer",
        //        "method": "get_singer_list",
        //        "param": {
        //            "area": -100,
        //            "sex": -100,
        //            "genre": -100,
        //            "index": -100,
        //            "sin": 0,
        //            "cur_page": 1
        //        }
        //    }
        //}
        String data = "{\"comm\":{\"ct\":24,\"cv\":0},\"singerList\":{\"module\":\"Music.SingerListServer\",\"method\":\"get_singer_list\",\"param\":{\"area\":-100,\"sex\":-100,\"genre\":-100,\"index\":-100,\"sin\":0,\"cur_page\":1}}}";

        JSONObject jsonData = JSON.parseObject(data);
        uriBuilder.setParameter("data", JSON.toJSONString(jsonData));
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity());

            JSONObject temp = JSON.parseObject(content);
            JSONArray jsonArr = temp.getJSONObject("singerList").getJSONObject("data").getJSONArray("singerlist");
            Singer[] singers = new Singer[jsonArr.size()];
            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                String id = obj.getString("singer_id");
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
