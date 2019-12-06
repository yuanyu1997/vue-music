# 1.推荐页面

![](./img/1.png)

## 1.1 轮播图

<http://localhost:8848/recommend/picture>

```json
{
    "code": 0,
    "data": [
        {
            "imgUrl": "http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1876408.jpg",
            "linkUrl": "https://y.qq.com/n/yqq/album/001TbRMw3bOWUK.html"
        },
        {
            "imgUrl": "http://y.gtimg.cn/music/common/upload/MUSIC_FOCUS/1873962.jpg",
            "linkUrl": "https://y.qq.com/n/yqq/album/0036Ue1l4K8s3c.html"
        }
    ]
}
```

```
musicu.fcg
```


```
https://u.y.qq.com/cgi-bin/musicu.fcg
```

```
-: recom19477423855264875
g_tk: 5381
loginUin: 0
hostUin: 0
format: json
inCharset: utf8
outCharset: utf-8
notice: 0
platform: yqq.json
needNewCode: 0
```

```
data
```

```json
{
    "comm": {
        "ct": 24
    },
    "category": {
        "method": "get_hot_category",
        "param": {
            "qq": ""
        },
        "module": "music.web_category_svr"
    },
    "recomPlaylist": {
        "method": "get_hot_recommend",
        "param": {
            "async": 1,
            "cmd": 2
        },
        "module": "playlist.HotRecommendServer"
    },
    "playlist": {
        "method": "get_playlist_by_category",
        "param": {
            "id": 8,
            "curPage": 1,
            "size": 40,
            "order": 5,
            "titleid": 8
        },
        "module": "playlist.PlayListPlazaServer"
    },
    "new_song": {
        "module": "newsong.NewSongServer",
        "method": "get_new_song_info",
        "param": {
            "type": 5
        }
    },
    "new_album": {
        "module": "newalbum.NewAlbumServer",
        "method": "get_new_album_info",
        "param": {
            "area": 1,
            "sin": 0,
            "num": 10
        }
    },
    "new_album_tag": {
        "module": "newalbum.NewAlbumServer",
        "method": "get_new_album_area",
        "param": {}
    },
    "toplist": {
        "module": "musicToplist.ToplistInfoServer",
        "method": "GetAll",
        "param": {}
    },
    "focus": {
        "module": "QQMusic.MusichallServer",
        "method": "GetFocus",
        "param": {}
    }
}
```

```json
{
	"focus": {
		"module": "QQMusic.MusichallServer",
		"method": "GetFocus",
		"param": {}
	}
}
```





## 1.2 热门歌单

<http://localhost:8848/recommend/songList>

```json
{
    "code": 0,
    "data": [
        {
            "imgurl": "http://qpic.y.qq.com/music_cover/Uj77DagTFgiccudSicYvppRrbxf0qWnIsJ7icREw8WkrZliahJ8IKw3pLw/300?n=1",
            "name": "Lee BG嗷柏",
            "desc": "Hi-NRG：高能量迪斯科",
            "id": "7300237199"
        },      
        {
            "imgurl": "http://qpic.y.qq.com/music_cover/QZ7tq786GFicI8CAYxeibnamvicpNelTib2TdK7E4s5fTibZE2t2VkYKZ5g/300?n=1",
            "name": "醉枫染墨",
            "desc": "六朝古都 · 十里南京灯火灿",
            "id": "7217581917"
        }
    ]
}
```



```

```


```json
{
	"comm": {
	"ct": 24
	},
		"recomPlaylist": {
		"method": "get_hot_recommend",
		"param": {
		"async": 1,
		"cmd": 2
	},
	"module": "playlist.HotRecommendServer"
	}
}
```



# 2.歌手页面

![](./img/2.png)

## 2.1 热门歌手/歌手分类 

<http://localhost:8848/singer/list>

```json
{
    "code": 0,
    "data": [
        {
            "key": "热门",
            "list": [
                {
                    "id": "4558",
                    "name": "周杰伦",
                    "avatar": "http://y.gtimg.cn/music/photo_new/T001R150x150M0000025NhlN2yWrP4.webp",
                    "firstCharName": "Z"
                },
                {
                    "id": "1473880",
                    "name": "陈雪凝",
                    "avatar": "http://y.gtimg.cn/music/photo_new/T001R150x150M000004V53Ga0bV65j.webp",
                    "firstCharName": "C"
                }
            ]
        },
        {
            "key": "A",
            "list": [
                {
                    "id": "944940",
                    "name": "Alan Walker (艾兰·沃克)",
                    "avatar": "http://y.gtimg.cn/music/photo_new/T001R150x150M0000020PeOh4ZaCw1.webp",
                    "firstCharName": "A"
                },
                {
                    "id": "158556",
                    "name": "阿肆",
                    "avatar": "http://y.gtimg.cn/music/photo_new/T001R150x150M000003VJ4nP0Q19xJ.webp",
                    "firstCharName": "A"
                }
            ]
        },
        {
            "key": "C",
            "list": [
                {
                    "id": "1473880",
                    "name": "陈雪凝",
                    "avatar": "http://y.gtimg.cn/music/photo_new/T001R150x150M000004V53Ga0bV65j.webp",
                    "firstCharName": "C"
                },
                {
                    "id": "39000",
                    "name": "Charlie Puth (查理·普斯)",
                    "avatar": "http://y.gtimg.cn/music/photo_new/T001R150x150M000000jnR7q3pCzYG.webp",
                    "firstCharName": "C"
                }
            ]
        },
        {
            "key": "Z",
            "list": [
                {
                    "id": "4558",
                    "name": "周杰伦",
                    "avatar": "http://y.gtimg.cn/music/photo_new/T001R150x150M0000025NhlN2yWrP4.webp",
                    "firstCharName": "Z"
                },
                {
                    "id": "219",
                    "name": "张敬轩",
                    "avatar": "http://y.gtimg.cn/music/photo_new/T001R150x150M000003AfDK34H82GU.webp",
                    "firstCharName": "Z"
                }
            ]
        }
    ]
}
```

```
musicu.fcg
```

```
https://u.y.qq.com/cgi-bin/musicu.fcg
```

```
-: getUCGI10871303029917678
g_tk: 5381
loginUin: 0
hostUin: 0
format: json
inCharset: utf8
outCharset: utf-8
notice: 0
platform: yqq.json
needNewCode: 0
```

```json
{
    "comm": {
        "ct": 24,
        "cv": 0
    },
    "singerList": {
        "module": "Music.SingerListServer",
        "method": "get_singer_list",
        "param": {
            "area": -100,
            "sex": -100,
            "genre": -100,
            "index": -100,
            "sin": 0,
            "cur_page": 1
        }
    }
}
```

