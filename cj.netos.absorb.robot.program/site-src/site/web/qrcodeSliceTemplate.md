# 码片模板配置

* 一个模板信息
```java
    String id;
    String name;
    String background;
    String note;
    String copyright;
    long maxAbsorbers;
    long ownerWeight;
    long participWeight;
    long ingeoWeight;
    List<TemplatePropResult> properties;
```
```java
    String id;
    String name;
    String value;
    String note;
```
* json示例:

```json
[
  {
            "id":"official",
            "name": "官方",
            "background": "http://www.nodespower.com:7100/app/qrcodeslice/official.jpg",
            "note": "一招封神",
            "copyright": "郑州节点动力信息科技有限公司",
            "maxAbsorbers": "100",
            "ownerWeight": "50",
            "participWeight": "40",
            "ingeoWeight": "80",
            "properties":[
            ]
  },{
        "id":"normal",
        "name": "简易",
        "background": "http://www.nodespower.com:7100/app/qrcodeslice/normal.jpg",
        "note": "简简单单吧",
        "copyright": "郑州节点动力信息科技有限公司",
        "maxAbsorbers": "100",
        "ownerWeight": "50",
        "participWeight": "40",
        "ingeoWeight": "80",
        "properties":[
          {
            "id":"welcome",
            "name": "欢迎辞",
            "type": "text",
            "value": "欢迎使用地微app",
            "note": ""
          },{
                    "id":"avatar",
                    "name": "头像",
                    "type": "image",
                    "value": "",
                    "note": "上传自已的头像，将放在二维码中展示"
                  }  
        ]
  },{
        "id":"happiness",
        "name": "小确幸",
        "background": "http://www.nodespower.com:7100/app/qrcodeslice/happiness.jpg",
        "note": "有些人就是这么幸运",
        "copyright": "郑州节点动力信息科技有限公司",
        "maxAbsorbers": "100",
        "ownerWeight": "50",
        "participWeight": "40",
        "ingeoWeight": "80",
        "properties":[
        ]
  },{
         "id":"xibao",
         "name": "喜报",
         "background": "http://www.nodespower.com:7100/app/qrcodeslice/xibao.jpeg",
         "note": "给大家送喜报啦",
         "copyright": "郑州节点动力信息科技有限公司",
         "maxAbsorbers": "100",
         "ownerWeight": "50",
         "participWeight": "40",
         "ingeoWeight": "80",
         "properties":[
           {
             "id":"greetings",
             "name": "祝福语",
             "type": "text",
             "value": "喜报到，鸿运照，烦恼的事往边靠，爱情滋润没烦恼，钞票一个劲儿往家跑，出门遇贵人，在家听喜报。",
             "note": ""
           }
         ]
  },{
        "id":"caisheng",
        "name": "财神到",
        "background": "http://www.nodespower.com:7100/app/qrcodeslice/caisheng.jpg",
        "note": "财神驾到，接驾啦",
        "copyright": "郑州节点动力信息科技有限公司",
        "maxAbsorbers": "100",
        "ownerWeight": "50",
        "participWeight": "40",
        "ingeoWeight": "80",
        "properties":[
          {
            "id":"advices",
            "name": "公告",
            "type": "text",
            "value": "财神通知要光临，寻找当今聪明勤奋第一人，我把你的资料报上去了，财神委员会已经研究盖章批准，你要赶快行动做好准备，高高兴兴迎财神。",
            "note": ""
          }
        ]
  },{
          "id":"chiji",
          "name": "吃鸡",
          "background": "http://www.nodespower.com:7100/app/qrcodeslice/ciji.jpeg",
          "note": "大吉大利一起吃鸡",
          "copyright": "郑州节点动力信息科技有限公司",
          "maxAbsorbers": "100",
          "ownerWeight": "50",
          "participWeight": "40",
          "ingeoWeight": "80",
          "properties":[
            {
              "id":"note",
              "name": "说明",
              "type": "text",
              "value": "大吉大利一起吃鸡",
              "note": ""
            }
          ]
  },{
         "id":"wangzherongyao",
         "name": "王者荣耀",
         "background": "http://www.nodespower.com:7100/app/qrcodeslice/wangzheruyao.jpeg",
         "note": "兄弟姐妹今晚开黑",
         "copyright": "郑州节点动力信息科技有限公司",
         "maxAbsorbers": "100",
         "ownerWeight": "50",
         "participWeight": "40",
         "ingeoWeight": "80",
         "properties":[
           {
             "id":"note",
             "name": "说明",
             "type": "text",
             "value": "弄啥哩？一起开黑吧",
             "note": ""
           }
         ]
  },{
          "id":"minxinpian",
          "name": "明信片",
          "background": "http://www.nodespower.com:7100/app/qrcodeslice/minxinpian.jpeg",
          "note": "万里传佳音，期待哪啥",
          "copyright": "郑州节点动力信息科技有限公司",
          "maxAbsorbers": "100",
          "ownerWeight": "50",
          "participWeight": "40",
          "ingeoWeight": "80",
          "properties":[
            {
              "id":"content",
              "name": "内容",
              "type": "text",
              "value": "初见倾心，再见痴心。终日费心，欲得芳心。煞费苦心，想得催心。难道你心，不懂我心，如此狠心，让我伤心。",
              "note": ""
            }
          ]
  },{
        "id":"love",
        "name": "爱情",
        "background": "http://www.nodespower.com:7100/app/qrcodeslice/love.jpeg",
        "note": "致青春",
        "copyright": "郑州节点动力信息科技有限公司",
        "maxAbsorbers": "100",
        "ownerWeight": "50",
        "participWeight": "40",
        "ingeoWeight": "80",
        "properties":[
          {
            "id":"poetry",
            "name": "诗词",
            "type": "text",
            "value": "我说你是人间的四月天；笑响点亮了四面风；轻灵在春的光艳中交舞着变。",
            "note": ""
          }
        ]
  }
]
```