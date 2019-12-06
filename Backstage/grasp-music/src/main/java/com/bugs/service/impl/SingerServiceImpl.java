package com.bugs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bugs.bean.SimilarSinger;
import com.bugs.bean.Singer;
import com.bugs.bean.Song;
import com.bugs.service.SingerService;
import com.bugs.utils.HanyuPinyinUtil;
import com.bugs.utils.URIBuilderUtil;
import javafx.util.Pair;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    HanyuPinyinUtil pinyinUtil;
    @Autowired
    URIBuilderUtil uriBuilderUtil;
    @Autowired
    PoolingHttpClientConnectionManager manager;

    Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

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


    /**
     * 获取具体某一个歌手的歌曲列表
     * https://u.y.qq.com/cgi-bin/musicu.fcg?-=getSingerSong9032856280407204&g_tk=5381&loginUin=0&hostUin=0&format=json&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq.json&needNewCode=0&data=%7B%22comm%22%3A%7B%22ct%22%3A24%2C%22cv%22%3A0%7D%2C%22singerSongList%22%3A%7B%22method%22%3A%22GetSingerSongList%22%2C%22param%22%3A%7B%22order%22%3A1%2C%22singerMid%22%3A%22002J4UUk29y8BY%22%2C%22begin%22%3A0%2C%22num%22%3A10%7D%2C%22module%22%3A%22musichall.song_list_server%22%7D%7D
     */
    @Override
    public List<Song> getSingerDetail(String singerId) {
        String url = "https://u.y.qq.com/cgi-bin/musicu.fcg";
        //歌曲条数
        int songLen = 666;
        String data = "{\"comm\":{\"ct\":24,\"cv\":0},\"singerSongList\":{\"method\":\"GetSingerSongList\",\"param\":{\"order\":1,\"singerMid\":\"" + singerId + "\",\"begin\":0,\"num\":" + songLen + "},\"module\":\"musichall.song_list_server\"}}";
        Pair<String, String> pair = new Pair<>("-", "getSingerSong9032856280407204");
        JSONObject jsonObj = uriBuilderUtil.getResponseByBasicParameter(url, data, pair);
        if (jsonObj != null) {
            ArrayList<Song> songArr = new ArrayList<>();
            JSONArray jsonArr = jsonObj.getJSONObject("singerSongList").getJSONObject("data").getJSONArray("songList");
            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject songInfo = jsonArr.getJSONObject(i).getJSONObject("songInfo");
                String id = songInfo.getString("id");
                //歌曲id
                String mid = songInfo.getString("mid");
                // 歌曲名称
                String name = songInfo.getString("name");
                //专辑名称
                String album = songInfo.getJSONObject("album").getString("name");
                //歌手
                String singer = songInfo.getJSONArray("singer").getJSONObject(0).getString("name");
                //歌曲长度
                String duration = songInfo.getString("interval");
                //歌曲图片
                String image = "https://y.gtimg.cn/music/photo_new/T001R300x300M000" + singerId + ".jpg?max_age=2592000";
                //歌曲的播放地址
                String mediaMid = songInfo.getJSONObject("file").getString("media_mid");
                String songUrl = getSongUrl(name, singer, mid, mediaMid);
                if (songUrl != null) {
                    Song song = new Song(id, mid, singer, name, album, duration, image, songUrl);
                    songArr.add(song);
                }
            }
            return songArr;
        }
        return null;
    }

    /**
     * 获取歌曲的url
     *
     * @param name     歌名
     * @param mid      歌曲id
     * @param mediaMid 拼接需要media_mid
     */
    private String getSongUrl(String name, String singer, String mid, String mediaMid) {
        String url = "https://u.y.qq.com/cgi-bin/musicu.fcg";
        String data = "{\"req\":{\"module\":\"CDN.SrfCdnDispatchServer\",\"method\":\"GetCdnDispatch\",\"param\":{\"guid\":\"2822634809\",\"calltype\":0,\"userip\":\"\"}},\"req_0\":{\"module\":\"vkey.GetVkeyServer\",\"method\":\"CgiGetVkey\",\"param\":{\"guid\":\"2822634809\",\"songmid\":[\"" + mid + "\"],\"songtype\":[0],\"uin\":\"0\",\"loginflag\":1,\"platform\":\"20\"}},\"comm\":{\"uin\":0,\"format\":\"json\",\"ct\":24,\"cv\":0}}";
        Pair<String, String> pair = new Pair<>("-", "getplaysongvkey13038739389900833");
        JSONObject jsonObj = uriBuilderUtil.getResponseByBasicParameter(url, data, pair);
        if (jsonObj != null) {
            String vKey = jsonObj.getJSONObject("req_0").getJSONObject("data").getJSONArray("midurlinfo").getJSONObject(0).getString("vkey");
            if (vKey == null || "".equals(vKey.trim())) {
                logger.info("歌曲获取失败: " + name + "(" + singer + ")");
                return null;
            }
            JSONArray ipArr = jsonObj.getJSONObject("req").getJSONObject("data").getJSONArray("sip");
            ArrayList<String> usableIp = new ArrayList<>(6);
            for (int i = 0; i < ipArr.size(); i++) {
                usableIp.add(ipArr.getString(i));
            }
            //System.out.println(usableIp);
            String songUrl = "http://isure.stream.qqmusic.qq.com/C400" + mediaMid + ".m4a?guid=2822634809&vkey=" + vKey + "&fromtag=66";
            return songUrl;
        }
        return null;
    }


}
