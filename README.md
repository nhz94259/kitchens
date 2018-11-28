####  kitchens
### 小厨房


# 接口调用说明api

```
var NewApiKitcUrl = '101.132.103.204:8080/kit/';
```

```
module.exports = {

	GoodsList: NewApiKitcUrl + '  buyer/product/list',//所有在售的商品其中按商品类划分

	UserLogin: NewApiKitcUrl + 'weixin/login',//返回sessionkey
	UserGetInfo:NewApiKitcUrl + 'weixin/info',//获取用户信息
	UserGetPhone: NewApiKitcUrl + 'weixin/phone',//获取用户电话

	PayCreate: NewApiKitcUrl + 'weixin/buyer/order/cancel',//支付创建
	PayNotify: NewApiKitcUrl + 'weixin/buyer/order/notify',//支付结果异步通知

	OrderCreate: NewApiKitcUrl + 'buyer/order/create',//订单创建
	OrderList: NewApiKitcUrl + 'buyer/order/list',//全部用户订单列表
	OrderDetail: NewApiKitcUrl + 'buyer/order/detail',//订单详情
	OrderCancel: NewApiKitcUrl + 'buyer/order/cancel',//取消订单
	
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
};
```
