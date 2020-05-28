package java100;

import lombok.Data;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestOOM {

    //自动完成的索引，Key是用户输入的部分用户名，Value是对应的用户数据
//    private ConcurrentHashMap<String, List<UserDTO>> autoCompleteIndex = new ConcurrentHashMap<>();
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostConstruct
//    public void wrong() {
//        //先保存10000个用户名随机的用户到数据库中
//        userRepository.saveAll(LongStream.rangeClosed(1, 10000).mapToObj(i -> new UserEntity(i, randomName())).collect(Collectors.toList()));
//
//        //从数据库加载所有用户
//        userRepository.findAll().forEach(userEntity -> {
//            int len = userEntity.getName().length();
//            //对于每一个用户，对其用户名的前N位进行索引，N可能是1~6六种长度类型
//            for (int i = 0; i < len; i++) {
//                String key = userEntity.getName().substring(0, i + 1);
//                autoCompleteIndex.computeIfAbsent(key, s -> new ArrayList<>())
//                        .add(new UserDTO(userEntity.getName()));
//            }
//        });
//        log.info("autoCompleteIndex size:{} count:{}", autoCompleteIndex.size(),
//                autoCompleteIndex.entrySet().stream().map(item -> item.getValue().size()).reduce(0, Integer::sum));
//    }


    @Data
    public class UserDTO {
        private String name;
        private String payload;

        public UserDTO(String name) {
            this.name = name;
            this.payload = IntStream.rangeClosed(1, 10_000)
                    .mapToObj(__ -> "a")
                    .collect(Collectors.joining(""));
        }
    }
}
