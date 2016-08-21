运行：mvn jetty:run
在页面中输入 http://localhost:8080/  或者 http://localhost:8080/index
备注：jdbc配置文件在 resouces目录下，登陆账号为customer数据表中的first_name，
由于没有密码字段，所以密码定为last_name字段 （比如 账号：MARY 密码 SMITH）