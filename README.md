## A Sample Http Framework

### 待做事项

- [x] 了解@ComponentScan的作用
- [x] 实现@ComponentScan
- [x] @Component
- [x] @RequestController
- [x] 扫描相关包下指定的类（被注解注释过的），并将其加入容器当中
- [x] 了解org.reflections.Reflections这个依赖，看源码的理解来的意思是用来作为反射工具，方便操作吧
- [x] @GetMapping @PostMapping
- [x] 解析路由，保存路由与相关执行method的隐射关系，实现的结果就是，某个路由对应某个执行方法
- [x] 将被相关注解修饰的类加载到ioc容器中
- [x] 加载配置文件，我想现阶段只支持yml文件
- [x] 添加自定义异常
- [x] 了解ClassLoader的getResource方法
- [x] java.net.URL
- [x] java.nio.file.Paths
- [x] java.nio.Files
- [ ] 理解ConfigurationManager的实现，目前很不理解为什么这样做，最好画出类图
- [x] 实现BeanFactory的getBean方法
- [ ] 准确来说加载配置文件的方法就有一大半没弄懂，估计是因为平日所用的范围过少吧
- [ ] Class.isAssignableFrom()
- [ ] Class.cast()
- [ ] Collection的stream使用
- [ ] 了解拦截器的实现
- [ ] 提供一个反射工具，执行某个某个对象的方法+参数
- [ ] @Autowired功能实现，有点难
- [ ] @Autowired解决循环依赖的问题
- [ ] @Autowired添加AOP
- [ ] 了解post processor(貌似叫做后置处理器)
- [ ] MethodInvocation
- [ ] 了解拦截器Interceptor
- [ ] Proxy.newProxyInstance()
- [ ] 要复习一下代理模式了，而且还用到了jdk自带的InvocationHandler类实现的
- [ ] clig动态代理

