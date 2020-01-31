> 视频地址：
> 笔记地址：

压测并发


mac

```bash
MAC系统上安装Apache ab测试工具: https://www.cnblogs.com/cjsblog/p/10506647.html

brew install apr
brew install pcre


-n 数量

-c 线程数

ab -n 10000 -c 1 localhost:8080/add

curl localhost:8080/stat



使用synchronize

ab -n 100 -c 100 localhost:8080/add

curl localhost:8080/stat

使用ThreadLocal

ab -n 10000 -c 100 localhost:8080/add

curl localhost:8080/stat


```
