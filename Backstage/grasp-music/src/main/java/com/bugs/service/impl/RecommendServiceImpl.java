package com.bugs.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bugs.bean.Recommend;
import com.bugs.bean.SongList;
import com.bugs.service.RecommendService;
import com.bugs.utils.URIBuilderUtil;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * https://u.y.qq.com/cgi-bin/musicu.fcg?-=recom9681184374767442&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data=%7B%22comm%22%3A%7B%22ct%22%3A24%7D%2C%22category%22%3A%7B%22method%22%3A%22get_hot_category%22%2C%22param%22%3A%7B%22qq%22%3A%22%22%7D%2C%22module%22%3A%22music.web_category_svr%22%7D%2C%22recomPlaylist%22%3A%7B%22method%22%3A%22get_hot_recommend%22%2C%22param%22%3A%7B%22async%22%3A1%2C%22cmd%22%3A2%7D%2C%22module%22%3A%22playlist.HotRecommendServer%22%7D%2C%22playlist%22%3A%7B%22method%22%3A%22get_playlist_by_category%22%2C%22param%22%3A%7B%22id%22%3A8%2C%22curPage%22%3A1%2C%22size%22%3A40%2C%22order%22%3A5%2C%22titleid%22%3A8%7D%2C%22module%22%3A%22playlist.PlayListPlazaServer%22%7D%2C%22new_song%22%3A%7B%22module%22%3A%22newsong.NewSongServer%22%2C%22method%22%3A%22get_new_song_info%22%2C%22param%22%3A%7B%22type%22%3A5%7D%7D%2C%22new_album%22%3A%7B%22module%22%3A%22newalbum.NewAlbumServer%22%2C%22method%22%3A%22get_new_album_info%22%2C%22param%22%3A%7B%22area%22%3A1%2C%22sin%22%3A0%2C%22num%22%3A10%7D%7D%2C%22new_album_tag%22%3A%7B%22module%22%3A%22newalbum.NewAlbumServer%22%2C%22method%22%3A%22get_new_album_area%22%2C%22param%22%3A%7B%7D%7D%2C%22toplist%22%3A%7B%22module%22%3A%22musicToplist.ToplistInfoServer%22%2C%22method%22%3A%22GetAll%22%2C%22param%22%3A%7B%7D%7D%2C%22focus%22%3A%7B%22module%22%3A%22QQMusic.MusichallServer%22%2C%22method%22%3A%22GetFocus%22%2C%22param%22%3A%7B%7D%7D%7D
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    URIBuilderUtil uriBuilderUtil;

    /**
     * 轮播图
     */
    @Override
    public Map<String, Object> getRecommend() {
        String url = "https://u.y.qq.com/cgi-bin/musicu.fcg";
        String data = "{\"focus\":{\"module\":\"QQMusic.MusichallServer\",\"method\":\"GetFocus\",\"param\":{}}}";
        JSONObject jsonObj = uriBuilderUtil.getResponseByBasicParameter(url, data);
        if (jsonObj != null) {
            JSONArray jsonArray = jsonObj.getJSONObject("focus").getJSONObject("data").getJSONArray("content");
            Recommend[] reArr = new Recommend[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String imgurl = obj.getJSONObject("pic_info").getString("url");
                String jumpUrlKey = obj.getJSONObject("jump_info").getString("url");
                if (jumpUrlKey.contains("https")) {
                    reArr[i] = new Recommend(jumpUrlKey, imgurl);
                } else {
                    String linkUrl = "https://y.qq.com/n/yqq/album/" + jumpUrlKey + ".html";
                    reArr[i] = new Recommend(linkUrl, imgurl);
                }
            }
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("data", reArr);
            return resMap;
        }
        return null;
    }


    /**
     * 歌单
     */
    @Override
    public Map<String, Object> getDiscList() {
        String url = "https://u.y.qq.com/cgi-bin/musicu.fcg";
        String data = "{\"comm\":{\"ct\":24},\"recomPlaylist\":{\"method\":\"get_hot_recommend\",\"param\":{\"async\":1,\"cmd\":2},\"module\":\"playlist.HotRecommendServer\"}}";
        Pair<String, String> pair = new Pair<>("-", "recom45390136807495907");
        JSONObject jsonObj = uriBuilderUtil.getResponseByBasicParameter(url, data, pair);
        if (jsonObj != null) {
            JSONArray jsonArr = jsonObj.getJSONObject("recomPlaylist").getJSONObject("data").getJSONArray("v_hot");
            SongList[] discLists = new SongList[jsonArr.size()];
            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject obj = jsonArr.getJSONObject(i);
                discLists[i] = new SongList(obj.getString("cover"),
                        obj.getString("username"),
                        obj.getString("title"),
                        obj.getString("content_id"));

            }
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("data", discLists);
            return resMap;
        }
        return null;
    }


}
