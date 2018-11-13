#API

 
##微信用户相关
###获取用户session_key
```
GET /kit/weixin/login
```

参数

```
 code 

```

返回

```
{
  "session_key": "eA8Y519hWdSxvLbKnhU4Cw\u003d\u003d",
  "openid": "obtjM4joQw7kOnUbY8ZhhRnwy4f4"
}
```
###获取用户信息
```
GET /kit/weixin/info
```

参数

```
 sessionKey 
 signature   
 rawData 
 encryptedData 
 iv 
```

返回

```
{
  "openId": "obtjM4joQw7kOnUbY8ZhhRnwy4f4",
  "nickName": "WIZ",
  "gender": "1",
  "language": "zh_CN",
  "city": "Wuhan",
  "province": "Hubei",
  "country": "China",
  "avatarUrl": "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK7jXQaIib1W5NL3YtktSttv9fA2ibPKvriafFiaLj9VFt9MIdgxR4FKl5bYJclzoztiaKbN7XSnMdTkBw/132",
  "watermark": {
    "timestamp": "1540780210",
    "appid": "wx5b245d22b7f87928"
  }
}
```




##卖家端
###商品列表 

```
GET /kit/buyer/product/list
```

参数

```
无
```

返回

```
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "productVOList": [
            {
                "name": "水果",
                "type": 1,
                "foods": [
                    {
                        "id": "1539001930282896301",
                        "name": "香蕉",
                        "price": 2,
                        "description": "好吃的香蕉",
                        "icon": "http://xxxxx.jpg"
                    },
                    {
                        "id": "1539592965796161398",
                        "name": "苹果",
                        "price": 1,
                        "description": "好吃的苹果",
                        "icon": "http://xxxxx.jpg"
                    }
                ]
            },
            {
                "name": "文具",
                "type": 2,
                "foods": [
                    {
                        "id": "123456",
                        "name": "皮蛋粥",
                        "price": 3.2,
                        "description": "很好喝的粥",
                        "icon": "http://xxxxx.jpg"
                    },
                    {
                        "id": "1539593160043707548",
                        "name": "铅笔",
                        "price": 1,
                        "description": "好吃的铅笔",
                        "icon": "http://xxxxx.jpg"
                    }
                ]
            }
        ]
    }
}
```
 
###创建订单

```
POST /kit/buyer/orde/create
```

参数

```
name: "测试猪"
phone: "13947311111"
address: "火车站"
openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
items: [{
    productId: "1539001930282896301",
    productQuantity: 2 //购买数量
}]

```

返回

```
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "orderId": "1540722601433957959"
    }
}
```

###订单列表

```
GET /kit/buyer/order/list
```

参数

```
openid: 123
page: 0  
size: 10
```

返回

```
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "orderDTOPage": [
            {
                "orderId": "1540722601433957959",
                "buyerName": "测试猪",
                "buyerPhone": "13947311111",
                "buyerAddress": "火车站",
                "buyerOpenid": "123",
                "orderAmount": 4.4,
                "orderStatus": 0,
                "payStatus": 0,
                "createTime": 1540693803,
                "updateTime": 1540694043,
                "orderDetailList": null
            },
            {
                "orderId": "1540723017657487744",
                "buyerName": "测试猪",
                "buyerPhone": "13947311111",
                "buyerAddress": "火车站",
                "buyerOpenid": "123",
                "orderAmount": 44,
                "orderStatus": 0,
                "payStatus": 0,
                "createTime": 1540694217,
                "updateTime": 1540694217,
                "orderDetailList": null
            }
        ]
    }
}
```

###查询订单详情

```
GET /sell/buyer/order/detail
```

参数

```
openid: 123
orderId: 1540722601433957959
```

返回

```
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "orderDTO": {
            "orderId": "1540722601433957959",
            "buyerName": "测试猪",
            "buyerPhone": "13947311111",
            "buyerAddress": "火车站",
            "buyerOpenid": "123",
            "orderAmount": 4.4,
            "orderStatus": 0,
            "payStatus": 0,
            "createTime": 1540693803,
            "updateTime": 1540694043,
            "orderDetailList": [
                {
                    "detailId": "1540722601482726307",
                    "orderId": "1540722601433957959",
                    "productId": "1539001930282896301",
                    "productName": "香蕉",
                    "productPrice": 2.2,
                    "productQuantity": 2,
                    "productIcon": ""
                }
            ]
        }
    }
}
```

###取消订单

```
POST /kit/buyer/order/cancel
```

参数

```
openid: 123
orderId: 1540722601433957959
```

返回

```
{
    "status": {
        "code": 0,
        "msg": "成功"
    }
}
```

 

##买家管理端

###商品【类目】列表
```
GET /kit/seller/category/list
```
参数
```
categoryId=1
```
返回
````
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "categoryList": [
            {
                "categoryId": 1,
                "categoryName": "水果",
                "categoryType": 1,
                "createTime": "2018-10-08T04:31:12.000+0000",
                "updateTime": "2018-10-08T04:31:12.000+0000"
            },
            {
                "categoryId": 2,
                "categoryName": "文具",
                "categoryType": 2,
                "createTime": "2018-10-08T04:31:30.000+0000",
                "updateTime": "2018-10-08T04:31:30.000+0000"
            }
        ]
    }
}
````
###商品【类目】详情
```
GET /kit/seller/category/index
```

参数

```
categoryId=1
```

返回

```
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "category": {
            "categoryId": 1,
            "categoryName": "水果",
            "categoryType": 1,
            "createTime": "2018-10-08T04:31:12.000+0000",
            "updateTime": "2018-10-08T04:31:12.000+0000"
        }
    }
}
```


###商品类目更新与保存
````
POST /kit/seller/category/save
```` 

参数
````

categoryName=化妆品
categoryType=3

````

返回

````

{
    "status": {
        "code": 0,
        "msg": "成功"
    }
}

````

###商品信息列表
````
GET /kit/seller/product/list
```` 

参数
````
无
````

返回

````
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "currentPage": 1,
        "size": 10,
        "productInfoPage": {
            "content": [
                {
                    "productId": "123456",
                    "productName": "皮蛋粥",
                    "productPrice": 3.2,
                    "productStock": 100,
                    "productDescription": "很好喝的粥",
                    "productIcon": "http://xxxxx.jpg",
                    "productStatus": 0,
                    "categoryType": 2,
                    "createTime": "2018-10-22T02:40:24.000+0000",
                    "updateTime": "2018-10-22T02:40:24.000+0000"
                },
                {
                    "productId": "1539001930282896301",
                    "productName": "香蕉",
                    "productPrice": 2.2,
                    "productStock": 18,
                    "productDescription": "好吃的香蕉",
                    "productIcon": "",
                    "productStatus": 0,
                    "categoryType": 1,
                    "createTime": "2018-10-08T04:32:10.000+0000",
                    "updateTime": "2018-10-28T09:45:17.000+0000"
                },
                {
                    "productId": "1539592965796161398",
                    "productName": "苹果",
                    "productPrice": 6.6,
                    "productStock": 100,
                    "productDescription": "好吃的苹果",
                    "productIcon": "",
                    "productStatus": 0,
                    "categoryType": 1,
                    "createTime": "2018-10-15T00:42:45.000+0000",
                    "updateTime": "2018-10-28T02:26:29.000+0000"
                },
                {
                    "productId": "1539593160043707548",
                    "productName": "铅笔",
                    "productPrice": 1,
                    "productStock": 100,
                    "productDescription": "好用的铅笔",
                    "productIcon": "",
                    "productStatus": 0,
                    "categoryType": 2,
                    "createTime": "2018-10-15T00:45:59.000+0000",
                    "updateTime": "2018-10-28T02:26:35.000+0000"
                }
            ],
            "pageable": {
                "sort": {
                    "sorted": false,
                    "unsorted": true
                },
                "offset": 0,
                "pageSize": 10,
                "pageNumber": 0,
                "unpaged": false,
                "paged": true
            },
            "totalElements": 4,
            "totalPages": 1,
            "last": true,
            "number": 0,
            "size": 10,
            "sort": {
                "sorted": false,
                "unsorted": true
            },
            "first": true,
            "numberOfElements": 4
        }
    }
}

````
###商品上架
````
GET /kit/seller/product/on_sale
```` 

参数
````
productId=1539001930282896301
````

返回

````
{
    "status": {
        "code": 0,
        "msg": "成功"
    }
}
````

###商品下架
````
GET /kit/seller/product/off_sale
```` 

参数
````
productId=1539001930282896301
````

返回

````
{
    "status": {
        "code": 0,
        "msg": "成功"
    }
}
````

###商品下架
````
GET /kit/seller/product/index
```` 

参数
````
productId=1539001930282896301
````

返回  ps返回商品类别为类别下拉框信息

````
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "categoryList": [
            {
                "categoryId": 1,
                "categoryName": "水果",
                "categoryType": 1,
                "createTime": "2018-10-08T04:31:12.000+0000",
                "updateTime": "2018-10-08T04:31:12.000+0000"
            },
            {
                "categoryId": 2,
                "categoryName": "文具",
                "categoryType": 2,
                "createTime": "2018-10-08T04:31:30.000+0000",
                "updateTime": "2018-10-08T04:31:30.000+0000"
            },
            {
                "categoryId": 3,
                "categoryName": "化妆品",
                "categoryType": 3,
                "createTime": "2018-10-28T09:50:20.000+0000",
                "updateTime": "2018-10-28T09:50:20.000+0000"
            }
        ],
        "productInfo": {
            "productId": "1539001930282896301",
            "productName": "香蕉",
            "productPrice": 2.2,
            "productStock": 18,
            "productDescription": "好吃的香蕉",
            "productIcon": "",
            "productStatus": 1,
            "categoryType": 1,
            "createTime": "2018-10-08T04:32:10.000+0000",
            "updateTime": "2018-10-28T09:56:35.000+0000"
        }
    }
}
````


###商品新增与保存
````
POST /kit/seller/product/save
```` 

参数
````
productName:夜来香
productPrice:10
productStock:1000
productDescription:很好用的化妆品
productIcon: ****
categoryType:3
````

返回   

````
{
    "status": {
        "code": 0,
        "msg": "成功"
    }
}
 
````


###订单列表
````
GET /kit/seller/order/list
```` 

参数
````
无
````

返回   

````
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "orderDTOPage": {
            "content": [
                {
                    "orderId": "1540722601433957959",
                    "buyerName": "测试猪",
                    "buyerPhone": "13947311111",
                    "buyerAddress": "火车站",
                    "buyerOpenid": "123",
                    "orderAmount": 4.4,
                    "orderStatus": 2,
                    "payStatus": 0,
                    "createTime": 1540693803,
                    "updateTime": 1540694043,
                    "orderDetailList": null
                },
                {
                    "orderId": "1540723005439509960",
                    "buyerName": "测试猪",
                    "buyerPhone": "13947311111",
                    "buyerAddress": "火车站",
                    "buyerOpenid": "111111",
                    "orderAmount": 44,
                    "orderStatus": 0,
                    "payStatus": 0,
                    "createTime": 1540694205,
                    "updateTime": 1540694205,
                    "orderDetailList": null
                },
                {
                    "orderId": "1540723017657487744",
                    "buyerName": "测试猪",
                    "buyerPhone": "13947311111",
                    "buyerAddress": "火车站",
                    "buyerOpenid": "123",
                    "orderAmount": 44,
                    "orderStatus": 0,
                    "payStatus": 0,
                    "createTime": 1540694217,
                    "updateTime": 1540694217,
                    "orderDetailList": null
                },
                {
                    "orderId": "1540732277982241293",
                    "buyerName": "测试猪",
                    "buyerPhone": "13947311111",
                    "buyerAddress": "火车站",
                    "buyerOpenid": "123",
                    "orderAmount": 44,
                    "orderStatus": 0,
                    "payStatus": 0,
                    "createTime": 1540703477,
                    "updateTime": 1540703477,
                    "orderDetailList": null
                },
                {
                    "orderId": "1540748717604879616",
                    "buyerName": "测试猪",
                    "buyerPhone": "13947311111",
                    "buyerAddress": "火车站",
                    "buyerOpenid": "123",
                    "orderAmount": 44,
                    "orderStatus": 0,
                    "payStatus": 0,
                    "createTime": 1540719917,
                    "updateTime": 1540719917,
                    "orderDetailList": null
                }
            ],
            "pageable": {
                "sort": {
                    "sorted": false,
                    "unsorted": true
                },
                "offset": 0,
                "pageSize": 10,
                "pageNumber": 0,
                "unpaged": false,
                "paged": true
            },
            "totalElements": 5,
            "totalPages": 1,
            "last": true,
            "number": 0,
            "size": 10,
            "sort": {
                "sorted": false,
                "unsorted": true
            },
            "first": true,
            "numberOfElements": 5
        },
        "size": 10,
        "currentPage": 1
    }
}
 
````

###订单详情
````
GET /kit/seller/order/detail
```` 

参数
````
orderId=1540722601433957959
````

返回   

````
 {
     "status": {
         "code": 0,
         "msg": "成功"
     },
     "data": {
         "orderDTO": {
             "orderId": "1540722601433957959",
             "buyerName": "测试猪",
             "buyerPhone": "13947311111",
             "buyerAddress": "火车站",
             "buyerOpenid": "123",
             "orderAmount": 4.4,
             "orderStatus": 2,
             "payStatus": 0,
             "createTime": 1540693803,
             "updateTime": 1540694043,
             "orderDetailList": [
                 {
                     "detailId": "1540722601482726307",
                     "orderId": "1540722601433957959",
                     "productId": "1539001930282896301",
                     "productName": "香蕉",
                     "productPrice": 2.2,
                     "productQuantity": 2,
                     "productIcon": ""
                 }
             ]
         }
     }
 }
 
````

###订单取消
````
GET /kit/seller/order/cancel
```` 

参数
````
orderId=1540722601433957959
````

返回   

````
  {
      "status": {
          "code": 0,
          "msg": "成功"
      },
      "data": {
          "msg": "订单取消成功"
      }
  }
 
````

###订单取消
````
GET /kit/seller/order/finish
```` 

参数
````
orderId=1540722601433957959
````

返回   

````
{
    "status": {
        "code": 0,
        "msg": "成功"
    },
    "data": {
        "msg": "订单完结成功"
    }
}
 
````