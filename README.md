#  kitchens
:trophy: springboot springdata 

## 接口调用说明api
## 全局
```
var NewApiKitcUrl = 'localhost:8080/kit/';
```


### 所有在售的商品其中按商品类划分
```
	GoodsList: NewApiKitcUrl + '  buyer/product/list'
```

### 微信用户信息接口
```
	UserLogin: NewApiKitcUrl + 'weixin/login',//返回sessionkey
	UserGetInfo:NewApiKitcUrl + 'weixin/info',//获取用户信息
	UserGetPhone: NewApiKitcUrl + 'weixin/phone',//获取用户电话
```

### 微信支付接口
```
	PayCreate: NewApiKitcUrl + 'weixin/buyer/order/cancel',//支付创建
	PayNotify: NewApiKitcUrl + 'weixin/buyer/order/notify',//支付结果异步通知
```

### 用户商品订单
```
	OrderCreate: NewApiKitcUrl + 'buyer/order/create',//订单创建
	OrderList: NewApiKitcUrl + 'buyer/order/list',//全部用户订单列表
	OrderDetail: NewApiKitcUrl + 'buyer/order/detail',//订单详情
	OrderCancel: NewApiKitcUrl + 'buyer/order/cancel',//取消订单
```

### 商家管理商品接口
```
	ProductList: NewApiKitcUrl + 'seller/product/list',//所有商品信息
	ProductOnSale: NewApiKitcUrl + 'seller/product/on_sale',//上架商品
	ProductOffSale: NewApiKitcUrl + 'seller/product/off_sale',//下架商品
	ProductIndex: NewApiKitcUrl + 'seller/product/index',//某个商品信息
	ProductSave: NewApiKitcUrl + 'seller/product/save',//更新保存商品信息
 
	CategoryList: NewApiKitcUrl + 'seller/category/list',//更新保存商品信息
	CategoryIndex: NewApiKitcUrl + 'seller/category/index',//更新保存商品信息
	CategorySave: NewApiKitcUrl + 'seller/category/save',//更新保存商品信息
	
	OrderList:NewApiKitcUrl + 'seller/category/list',//所有订单
	OrderCancel:NewApiKitcUrl + 'seller/category/cancel',//取消订单
	OrderDetail:NewApiKitcUrl + 'seller/category/detail',//订单详情
	OrderFinished:NewApiKitcUrl + 'seller/category/finish',//完结订单
 
```
### 具体接口请求与相应 参 /doc/API.md
