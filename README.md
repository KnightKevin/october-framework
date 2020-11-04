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
- [ ] 加载配置文件，我想现阶段只支持yml文件
- [ ] 添加自定义异常