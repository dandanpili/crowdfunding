1、启动虚拟器，将虚拟机的redis给跑起来:/usr/local/bin/redis-server /myredis/redis.conf
2、之后运行所有的spring boot应用
3、可以通过http://localhost来访问应用

内网穿透
1、运行NATAPP应用，获得到一个外网的连接，通过这个连接就可以访问到本地的应用

沙箱alipay
1、运行alipay的tomcat应用
2、需要在AlipayConfig修改一下返回的url地址

