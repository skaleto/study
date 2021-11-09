SpringApplication 启动类

我们在创建一个springboot项目的时候，一般会自动生成一个启动类
启动类会带一个main方法，同时启动类会带有注解@SpringBootApplication

1、main方法中的SpringApplication.run(MyApplication.class,args);

从SpringApplication的javadoc中我们先了解到，启动顺序如下：

(1)Create an appropriate {@link ApplicationContext} instance (depending on your classpath)
(2)Register a {@link CommandLinePropertySource} to expose command line arguments as Spring properties
(3)Refresh the application context, loading all com.skaleto.things.singleton beans
(4)Trigger any {@link CommandLineRunner} beans

在大多数情况下，可以通过
SpringApplication.run(MyApplication.class, args);
来直接启动

也可以通过如下的方式：
SpringApplication app = new SpringApplication(MyApplication.class);
// ... customize app settings here
app.run(args);
上面方式可以对app对一些定制化的配置，我们看SpringApplication提供了什么方法：

addListeners
addInitializers

setEnvironment

```java
	/**
	 * Run the Spring application, creating and refreshing a new
	 * {@link ApplicationContext}.
	 * @param args the application arguments (usually passed from a Java main method)
	 * @return a running {@link ApplicationContext}
	 */
	public ConfigurableApplicationContext run(String... args) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		FailureAnalyzers analyzers = null;
		configureHeadlessProperty();
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
		try {
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
			ConfigurableEnvironment environment = prepareEnvironment(listeners,
					applicationArguments);
			Banner printedBanner = printBanner(environment);
			context = createApplicationContext();
			analyzers = new FailureAnalyzers(context);
			prepareContext(context, environment, listeners, applicationArguments,
					printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			listeners.finished(context, null);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass)
						.logStarted(getApplicationLog(), stopWatch);
			}
			return context;
		}
		catch (Throwable ex) {
			handleRunFailure(context, listeners, analyzers, ex);
			throw new IllegalStateException(ex);
		}
	}
```

