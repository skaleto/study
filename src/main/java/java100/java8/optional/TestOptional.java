package java100.java8.optional;

import lombok.Data;

import java.util.Optional;

/**
 * java8中的Optional主要用来解决代码中犯人的NullPointerException
 */
public class TestOptional {

    public void testOpt() {
        User u = new User();
        //empty方法返回一个空的Optional对象
        Optional<User> o1 = Optional.empty();
        //of方法返回传入对象的Optional包装，如果传入对象为空，则抛出NPE
        Optional<User> o2 = Optional.of(u);
        //ofNull方法返回传入对象的Optional包装，如果传入对象为空，则返回空的Optional对象
        Optional<User> o3 = Optional.ofNullable(u);

        //orElse方法判断optional对象是否为空，如果为空则返回方法参数中的对象【不管是否为空参数中的new一定执行】
        User newU1 = o3.orElse(new User());
        //orElseGet方法判断optional对象是否为空，如果为空则返回方法参数中的对象【只有当参数为空时lambda才会被执行】
        //这里的User::new即代表() -> new User()
        User newU2 = o3.orElseGet(User::new);
        //orElseThrow判断optional对象是否为空，如果为空则抛出异常
        User newU3 = o3.orElseThrow(IllegalAccessError::new);

        //map方法将执行mapper lambda表达式的逻辑，并返回一个表达式返回值的optional包装
        o3.map(User::getName);
        //flatmap和map方法类似，不同的是flatmap接收的lambda表达式需返回包装好的Optional对象
        o3.flatMap(User::getNameOption);

        //filter接收predicate表达式，如果测试为false，则返回空的optional
        o3.filter(user -> "111".equals(user.getName()));

        //链式调用
        o3
                .map(User::getProvince)
                .map(Province::getCode)
                .filter(c -> c == 1)
                .get();

    }

    @Data
    public class User {
        private String id;
        private String name;
        private Province province;

        private Optional<String> getNameOption() {
            return Optional.ofNullable(name);
        }
    }

    @Data
    public class Province {
        private int code;
        private String name;
    }



}
