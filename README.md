# ServiceTest
安卓service的demo。
首先启动SimpleServer，然后运行app。
在SimpleServer的控制台输入指令，app会做出相应的响应。

总共三种指令：
-start:MainActivity跳到ColorChangeActivity。
-color xxx:消除颜色xxx。
-end:ColorChangeActivity跳到EndActivity。
然后再-start会跳到ColorChangeActivity。

记住自己改CommandTask里的IP地址。

后面的多设备模式就按照这个demo写。
