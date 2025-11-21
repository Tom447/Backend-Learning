# CRM接口文档-V1.0

## 1. 客户管理

### 1.1 客户列表查询

#### 1.1.1 基本信息

> 请求路径：/customers
>
> 请求方式：GET
>
> 接口描述：该接口用于客户列表数据的条件分页查询



#### 1.1.2 请求参数

参数格式：queryString

参数说明：

| 参数名称 | 是否必须 | 示例        | 备注                                       |
| -------- | -------- | ----------- | ------------------------------------------ |
| name     | 否       | 张          | 姓名                                       |
| gender   | 否       | 1           | 性别 , 1 男 , 2 女                         |
| phone    | 否       | 13309091111 | 手机号                                     |
| qq       | 否       | 3526263622  | QQ                                         |
| page     | 是       | 1           | 分页查询的页码，如果未指定，默认为1        |
| pageSize | 是       | 10          | 分页查询的每页记录数，如果未指定，默认为10 |

请求数据样例：

```shell
/customers?name=张&gender=1&phone=13309091111&qq=3526263622&page=1&pageSize=5
```





#### 1.1.3 响应数据

参数格式：application/json

参数说明：

| 名称            | 类型      | 是否必须 | 备注                    |
| --------------- | --------- | -------- | ----------------------- |
| code            | number    | 必须     | 响应码, 1 成功 , 0 失败 |
| msg             | string    | 非必须   | 提示信息                |
| data            | object    | 必须     | 返回的数据              |
| \|- total       | number    | 必须     | 总记录数                |
| \|- rows        | object [] | 必须     | 数据列表                |
| \|- id          | number    | 必须     | id                      |
| \|- name        | string    | 必须     | 客户姓名                |
| \|- phone       | string    | 必须     | 手机号                  |
| \|- gender      | number    | 必须     | 性别 , 1 男 ; 2 女      |
| \|- age         | number    | 非必须   | 年龄                    |
| \|- qq          | string    | 非必须   | QQ                      |
| \|- description | number    | 非必须   | 描述                    |
| \|- createTime  | string    | 必须     | 创建时间                |
| \|- updateTime  | string    | 必须     | 更新时间                |

响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "total": 11,
    "rows": [
      {
        "id": 11,
        "name": "如来佛",
        "phone": "18909091212",
        "gender": 1,
        "age": 98,
        "qq": "23232312",
        "description": "如来佛",
        "createTime": "2023-06-03T21:52:06",
        "updateTime": "2023-06-05T23:12:03"
      },
      {
        "id": 1,
        "name": "唐玄奘",
        "phone": "18800000001",
        "gender": 1,
        "age": 34,
        "qq": "82938298323",
        "description": "备注说明信息",
        "createTime": "2023-06-03T20:47:38",
        "updateTime": "2023-06-03T21:48:44"
      }
    ]
  }
}
```







### 1.2 删除客户

#### 1.2.1 基本信息

> 请求路径：/customers/{ids}
>
> 请求方式：DELETE
>
> 接口描述：该接口用于批量删除客户的数据信息



#### 1.2.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型       | 示例  | 是否必须 | 备注         |
| ------ | ---------- | ----- | -------- | ------------ |
| ids    | 数组 array | 1,2,3 | 必须     | 客户的id数组 |

请求参数样例：

```
/customers/1,2,3
```



#### 1.2.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```







### 1.3 添加客户

#### 1.3.1 基本信息

> 请求路径：/customers
>
> 请求方式：POST
>
> 接口描述：该接口用于添加客户的信息



#### 1.3.2 请求参数

参数格式：application/json

参数说明：

| 名称        | 类型   | 是否必须 | 备注             |
| ----------- | ------ | -------- | ---------------- |
| name        | string | 必须     | 客户姓名         |
| phone       | string | 必须     | 手机号           |
| gender      | number | 非必须   | 性别, 1:男, 2:女 |
| age         | number | 非必须   | 年龄             |
| qq          | string | 非必须   | QQ               |
| description | string | 非必须   | 备注说明         |

请求数据样例：

```json
{
  "name": "张翠山",
  "phone": "13209123456",
  "gender": 1,
  "age": 34,
  "qq": "2784398432",
  "description": "铁画银钩"
}
```





#### 1.3.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```





### 1.4 根据ID查询

#### 1.4.1 基本信息

> 请求路径：/customers/{id}
>
> 请求方式：GET
>
> 接口描述：该接口用于根据主键ID查询客户的信息



#### 1.4.2 请求参数

参数格式：路径参数

参数说明：

| 参数名 | 类型   | 是否必须 | 备注   |
| ------ | ------ | -------- | ------ |
| id     | number | 必须     | 客户ID |

请求参数样例：

```
/customers/1
```



#### 1.4.3 响应数据

参数格式：application/json

参数说明：

| 名称            | 类型   | 是否必须 | 备注                    |
| --------------- | ------ | -------- | ----------------------- |
| code            | number | 必须     | 响应码, 1 成功 , 0 失败 |
| msg             | string | 非必须   | 提示信息                |
| data            | object | 必须     | 返回的数据              |
| \|- id          | number | 必须     | id                      |
| \|- name        | string | 必须     | 客户姓名                |
| \|- phone       | string | 必须     | 手机号                  |
| \|- gender      | number | 必须     | 性别 , 1 男 ; 2 女      |
| \|- age         | number | 非必须   | 年龄                    |
| \|- qq          | string | 非必须   | QQ                      |
| \|- description | number | 非必须   | 描述                    |
| \|- createTime  | string | 必须     | 创建时间                |
| \|- updateTime  | string | 必须     | 更新时间                |

响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "id": 3,
    "name": "猪八戒",
    "phone": "18800000003",
    "gender": 1,
    "age": 32,
    "qq": "34657453453",
    "description": "备注说明信息",
    "createTime": "2023-06-03T20:47:38",
    "updateTime": "2023-06-03T20:47:38"
  }
}
```







### 1.5 修改客户
#### 1.5.1 基本信息

> 请求路径：/customers
>
> 请求方式：PUT
>
> 接口描述：该接口用于修改客户的数据信息



#### 1.5.2 请求参数

参数格式：application/json

参数说明：

| 名称        | 类型   | 是否必须 | 备注                   |
| ----------- | ------ | -------- | ---------------------- |
| id          | number | 必须     | id                     |
| name        | string | 必须     | 客户姓名               |
| gender      | number | 必须     | 性别, 说明: 1 男, 2 女 |
| phone       | string | 必须     | 手机号                 |
| age         | number | 非必须   | 年龄                   |
| qq          | string | 非必须   | QQ                     |
| description | number | 非必须   | 描述                   |

请求数据样例：

```json
{
  "id": 15,
  "name": "张翠山",
  "phone": "13209123456",
  "gender": 1,
  "age": 34,
  "qq": "2784398432",
  "description": "铁画银钩"
}
```



#### 1.5.3 响应数据

参数格式：application/json

参数说明：

| 参数名 | 类型   | 是否必须 | 备注                           |
| ------ | ------ | -------- | ------------------------------ |
| code   | number | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg    | string | 非必须   | 提示信息                       |
| data   | object | 非必须   | 返回的数据                     |

响应数据样例：

```json
{
    "code":1,
    "msg":"success",
    "data":null
}
```







### 1.6 客户年龄段统计

#### 1.6.1 基本信息

> 请求路径：/customers/ageReport
>
> 请求方式：GET
>
> 接口描述：该接口用于统计客户的年龄段数据



#### 1.6.2 请求参数

无



#### 1.6.3 响应数据

参数格式：application/json

参数说明：

| 参数名         | 类型     | 是否必须 | 备注                           |
| -------------- | -------- | -------- | ------------------------------ |
| code           | number   | 必须     | 响应码，1 代表成功，0 代表失败 |
| msg            | string   | 非必须   | 提示信息                       |
| data           | object   | 必须     | 数据列表                       |
| \|- categories | string[] | 必须     | 年龄段列表                     |
| \|- datas      | number[] | 必须     | 该年龄段对应的人数             |

响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "categories": ["0-20", "21-40", "41-60", "61-80", "81以上"],
    "datas": [4, 7, 2, 1, 2]
  }
}
```









### 1.7 日志列表查询

#### 1.7.1 基本信息

> 请求路径：/logs
>
> 请求方式：GET
>
> 接口描述：该接口用于分页查询日志信息



#### 1.7.2 请求参数

参数格式：queryString

参数说明：

| 参数名称 | 是否必须 | 示例 | 备注                                       |
| -------- | -------- | ---- | ------------------------------------------ |
| page     | 是       | 1    | 分页查询的页码，如果未指定，默认为1        |
| pageSize | 是       | 10   | 分页查询的每页记录数，如果未指定，默认为10 |

请求数据样例：

```shell
/logs?page=2&pageSize=5
```





#### 1.7.3 响应数据

参数格式：application/json

参数说明：

| 名称               | 类型      | 是否必须 | 备注                    |
| ------------------ | --------- | -------- | ----------------------- |
| code               | number    | 必须     | 响应码, 1 成功 , 0 失败 |
| msg                | string    | 非必须   | 提示信息                |
| data               | object    | 必须     | 返回的数据              |
| \|- total          | number    | 必须     | 总记录数                |
| \|- rows           | object [] | 必须     | 数据列表                |
| \|- id             | number    | 非必须   | id                      |
| \|- url            | string    | 非必须   | 请求URL                 |
| \|- targetClass    | string    | 非必须   | 目标类的全类名          |
| \|- targetMethod   | string    | 非必须   | 目标方法的方法名        |
| \|- methodParams   | string    | 非必须   | 目标方法运行的参数      |
| \|- methodReturn   | string    | 非必须   | 目标方法运行的返回值    |
| \|- clientBrowser  | string    | 非必须   | 客户端浏览器信息        |
| \|- clientPlatform | string    | 非必须   | 客户端操作系统          |
| \|- updateTime     | string    | 非必须   | 操作时间                |
| \|- costTime       | number    | 非必须   | 操作耗时(ms)            |

响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": {
    "total": 11,
    "rows": [
      {
        "id": 1,
        "url": "http://localhost:8080/customers",
        "targetClass": "com.itheima.controller.CustomerController",
        "targetMethod": "update",
        "methodParams": "[Customer(id=11, name=如来佛, phone=18909091212, gender=1, age=98, qq=23232312)]",
        "methodReturn": "{\"code\":1,\"msg\":\"success\"}",
        "clientBrowser": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/108.0.0.0 Safari/537.36",
        "clientPlatform": "\"Windows\"",
        "operateTime": "2023-06-05T23:12:03",
        "costTime": 8
      }
    ]
  }
}
```





