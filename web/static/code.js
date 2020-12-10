//使用新定义的模块
layui.config({
  base: './src/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
  mymod: 'mymod' //如果 mymod.js 是在根目录，也可以不用设定别名
  //相对于上述 base 目录的子目录
});
//使用拓展模块
layui.use(['mymod'], function(){
  var mymod = layui.mymod
 
  
  mymod.hello('World!'); //弹出 Hello World!
});