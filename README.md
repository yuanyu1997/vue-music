# 1.推荐

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

## 1.2 歌单

<http://localhost:8848/discList/musicList>

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

# 2.歌手

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

