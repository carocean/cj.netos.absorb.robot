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
    "id":"normal",
    "name": "常规",
    "background": "http://www.nodespower.com:7100/app/qrcodeslice/normal.jpg",
    "note": "极为简洁的模板",
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
  }
]
```