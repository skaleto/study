# SpringApplication 启动类



我们在创建一个springboot项目的时候，一般会自动生成一个启动类
启动类会带一个main方法，同时启动类会带有注解@SpringBootApplication，如下：

```java
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
```



## 一、SpringApplication.run()

### 1.先来看看SpringApplication的javadoc

SpringApplication这个类就是用来通过main方法初始化和启动一个spring应用程序的，默认情况下初始化会做下面的四件事情：

```Java
(1) Create an appropriate {@link ApplicationContext} instance (depending on your classpath)
    根据环境变量来创建一个合适的ApplicationContext实例
(2) Register a {@link CommandLinePropertySource} to expose command line arguments as Spring properties
	注册一个CommandLinePropertySource来将命令行参数转化为Spring的配置项
(3) Refresh the application context, loading all singleton beans
	刷新application上下文，加载所有单例的bean
(4) Trigger any {@link CommandLineRunner} beans
	触发CommandLineRunner的bean
```

在大多数情况下，可以通过如下的方式直接启动：

```java
SpringApplication.run(MyApplication.class, args);
```

也可以通过如下的方式：

```java
SpringApplication app = new SpringApplication(MyApplication.class);
// ... customize app settings here
app.run(args);
```

上面方式可以对app对一些定制化的配置，我们看SpringApplication提供了什么方法：

//TODO



### 2.application的加载过程

（1）SpringApplication.run方法，首先调用了SpringApplication的构造方法，构造方法中调用了initialize

```java
public static ConfigurableApplicationContext run(Object source, String... args) {
	return run(new Object[] { source }, args);
}

public static ConfigurableApplicationContext run(Object[] sources, String[] args) {
    return new SpringApplication(sources).run(args);
}

public SpringApplication(Object... sources) {
    //（2）
	initialize(sources);
}
```

（2）initialize的实现逻辑

```java
@SuppressWarnings({ "unchecked", "rawtypes" })
private void initialize(Object[] sources) {
	if (sources != null && sources.length > 0) {
        //sources是一个LinkedHashSet，本例中将MyApplication.class放入集合中
		this.sources.addAll(Arrays.asList(sources));
	}
    //（3）推断运行环境是否符合web环境
	this.webEnvironment = deduceWebEnvironment();
    //（4）
	setInitializers((Collection) getSpringFactoriesInstances(
			ApplicationContextInitializer.class));
    //（5）
	setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    //（6）
	this.mainApplicationClass = deduceMainApplicationClass();
}
```



（3）deduceWebEnvironment中检查javax.servlet.Servlet和org.springframework.web.context.ConfigurableWebApplicationContext这两个class是否在环境中

```java
private static final String[] WEB_ENVIRONMENT_CLASSES = { "javax.servlet.Servlet",
			"org.springframework.web.context.ConfigurableWebApplicationContext" };

private boolean deduceWebEnvironment() {
	for (String className : WEB_ENVIRONMENT_CLASSES) {
		if (!ClassUtils.isPresent(className, null)) {
			return false;
		}
	}
	return true;
}
```

调用ClassUtils.isPresent来检查

```java

/**
 * Determine whether the {@link Class} identified by the supplied name is present
 * and can be loaded. Will return {@code false} if either the class or
 * one of its dependencies is not present or cannot be loaded.
 * @param className the name of the class to check
 * @param classLoader the class loader to use
 * (may be {@code null}, which indicates the default class loader)
 * @return whether the specified class is present
 */
public static boolean isPresent(String className, ClassLoader classLoader) {
	try {
		forName(className, classLoader);
		return true;
	}
	catch (Throwable ex) {
		// Class or one of its dependencies is not present...
		return false;
	}
}

public static Class<?> forName(String name, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
	Assert.notNull(name, "Name must not be null");

	Class<?> clazz = resolvePrimitiveClassName(name);
	if (clazz == null) {
		clazz = commonClassCache.get(name);
	}
	if (clazz != null) {
		return clazz;
	}
		
    // "java.lang.String[]" style arrays
    // 假如是普通数组形式的，则去掉后面的[]再获取他的class名称
	if (name.endsWith(ARRAY_SUFFIX)) {
		String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
		Class<?> elementClass = forName(elementClassName, classLoader);
		return Array.newInstance(elementClass, 0).getClass();
	}
    
	// "[Ljava.lang.String;" style arrays
    // 假如是对象数组形式的，则去掉前面的[L再获取他的class名称
	if (name.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name.endsWith(";")) {
		String elementName = name.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name.length() - 1);
		Class<?> elementClass = forName(elementName, classLoader);
		return Array.newInstance(elementClass, 0).getClass();
	}

	// "[[I" or "[[Ljava.lang.String;" style arrays
    // 假如是二维数组，则去掉前缀再获取
	if (name.startsWith(INTERNAL_ARRAY_PREFIX)) {
		String elementName = name.substring(INTERNAL_ARRAY_PREFIX.length());
		Class<?> elementClass = forName(elementName, classLoader);
		return Array.newInstance(elementClass, 0).getClass();
	}
	ClassLoader clToUse = classLoader;
	if (clToUse == null) {
        //classloader默认情况下是当前线程的classloader
		clToUse = getDefaultClassLoader();
	}
	try {
		return (clToUse != null ? clToUse.loadClass(name) : Class.forName(name));
	}
	catch (ClassNotFoundException ex) {
		int lastDotIndex = name.lastIndexOf(PACKAGE_SEPARATOR);
		if (lastDotIndex != -1) {
			String innerClassName =
					name.substring(0, lastDotIndex) + INNER_CLASS_SEPARATOR + name.substring(lastDotIndex + 1);
			try {
				return (clToUse != null ? clToUse.loadClass(innerClassName) : Class.forName(innerClassName));
			}
			catch (ClassNotFoundException ex2) {
				// Swallow - let original exception get through
			}
		}
		throw ex;
	}
    //假如class无法加载，则会抛出ClassNotFoundException被上层捕获
}
```



（4）调用getSpringFactoriesInstances获取ApplicationContextInitializer.class类型的集合后，加入到initializers集合中

```java
private <T> Collection<? extends T> getSpringFactoriesInstances(Class<T> type) {
	return getSpringFactoriesInstances(type, new Class<?>[] {});
}
private <T> Collection<? extends T> getSpringFactoriesInstances(Class<T> type,
		Class<?>[] parameterTypes, Object... args) {
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	// 用set集合来避免名称重复
	Set<String> names = new LinkedHashSet<String>(
        	//主要用SpringFactoriesLoader.loadFactoryNames来加载名称集合
			SpringFactoriesLoader.loadFactoryNames(type, classLoader));
	List<T> instances = createSpringFactoriesInstances(type, parameterTypes,
			classLoader, args, names);
	AnnotationAwareOrderComparator.sort(instances);
	return instances;
}

public void setInitializers(
		Collection<? extends ApplicationContextInitializer<?>> initializers) {
	this.initializers = new ArrayList<ApplicationContextInitializer<?>>();
	this.initializers.addAll(initializers);
}
```

SpringFactoriesLoader.loadFactoryNames

```java
/**
 * Load the fully qualified class names of factory implementations of the
 * given type from {@value #FACTORIES_RESOURCE_LOCATION}, using the given
 * class loader.
 * @param factoryClass the interface or abstract class representing the factory
 * @param classLoader the ClassLoader to use for loading resources; can be
 * {@code null} to use the default
 * @see #loadFactories
 * @throws IllegalArgumentException if an error occurs while loading factory names
 */
public static List<String> loadFactoryNames(Class<?> factoryClass, ClassLoader classLoader) {
	String factoryClassName = factoryClass.getName();
	try {
		Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
					ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
		List<String> result = new ArrayList<String>();
//包括用户配置的META-INF下的spring.factories及org/springframework/boot/spring-boot/1.5.9.RELEASE/spring-boot-1.5.9.RELEASE.jar!/META-INF/spring.factories及
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
			String factoryClassNames = properties.getProperty(factoryClassName);
result.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(factoryClassNames)));
		}
		return result;
	}
	catch (IOException ex) {
		throw new IllegalArgumentException("Unable to load [" + factoryClass.getName() + "] factories from location [" + FACTORIES_RESOURCE_LOCATION + "]", ex);
	}
}

```

上述加载的spring.factories包含如下内容，一共会加载到如下6个Initializer

（配置中每行后方的"\\"标识换行，即本行内容还未结束）

```
# spring-boot-1.5.9.RELEASE.jar!/META-INF/spring.factories
org.springframework.context.ApplicationContextInitializer=\
org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer,\
org.springframework.boot.context.ContextIdApplicationContextInitializer,\
org.springframework.boot.context.config.DelegatingApplicationContextInitializer,\
org.springframework.boot.context.embedded.ServerPortInfoApplicationContextInitializer

# spring-boot-autoconfigure-1.5.9.RELEASE.jar!/META-INF/spring.factories
org.springframework.context.ApplicationContextInitializer=\
org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer,\
org.springframework.boot.autoconfigure.logging.AutoConfigurationReportLoggingInitializer
```

获取到需要加载的classname后，触发他们的构造方法创建实例

```java
@SuppressWarnings("unchecked")
private <T> List<T> createSpringFactoriesInstances(Class<T> type,
		Class<?>[] parameterTypes, ClassLoader classLoader, Object[] args,
		Set<String> names) {
	List<T> instances = new ArrayList<T>(names.size());
	for (String name : names) {
		try {
			Class<?> instanceClass = ClassUtils.forName(name, classLoader);
			Assert.isAssignable(type, instanceClass);
			Constructor<?> constructor = instanceClass
					.getDeclaredConstructor(parameterTypes);
			T instance = (T) BeanUtils.instantiateClass(constructor, args);
			instances.add(instance);
		}
		catch (Throwable ex) {
			throw new IllegalArgumentException(
					"Cannot instantiate " + type + " : " + name, ex);
		}
	}
	return instances;
}
```



```java
public ConfigurableApplicationContext run(String... args) {
		//时间监控
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
		//java.awt.headless是J2SE的一种模式用于在缺少显示屏、键盘或者鼠标时的系统配置，很多监控工具如jconsole 需要将该值设置为true，系统变量默认为true
		configureHeadlessProperty();
		//获取spring.factories中的监听器变量，args为指定的参数数组，默认为当前类SpringApplication
		//第一步：获取并启动监听器
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
		try {
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(
					args);
			//第二步：构造容器环境
			ConfigurableEnvironment environment = prepareEnvironment(listeners,
					applicationArguments);
			//设置需要忽略的bean
			configureIgnoreBeanInfo(environment);
			//打印banner
			Banner printedBanner = printBanner(environment);
			//第三步：创建容器
			context = createApplicationContext();
			//第四步：实例化SpringBootExceptionReporter.class，用来支持报告关于启动的错误
			exceptionReporters = getSpringFactoriesInstances(
					SpringBootExceptionReporter.class,
					new Class[] { ConfigurableApplicationContext.class }, context);
			//第五步：准备容器
			prepareContext(context, environment, listeners, applicationArguments,
					printedBanner);
			//第六步：刷新容器
			refreshContext(context);
			//第七步：刷新容器后的扩展接口
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass)
						.logStarted(getApplicationLog(), stopWatch);
			}
			listeners.started(context);
			callRunners(context, applicationArguments);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, listeners);
			throw new IllegalStateException(ex);
		}

		try {
			listeners.running(context);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, null);
			throw new IllegalStateException(ex);
		}
		return context;
	}

```



### 二、@SpringBootApplication