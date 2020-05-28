package java100.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class TestFileIO {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public void test() throws IOException {
        Files.write(Paths.get("demo.txt"),
                IntStream.rangeClosed(1, 10)
                        .mapToObj(i -> UUID.randomUUID().toString())
                        .collect(Collectors.toList()),
                UTF_8,
                CREATE,
                TRUNCATE_EXISTING);

        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000)
                .forEach(i -> {
                    try (Stream<String> fileStream = Files.lines(Paths.get("demo.txt"))) {
                        fileStream.forEach(line -> longAdder.increment());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        log.info("total : {}", longAdder.longValue());
    }

    public void test2(){
    }

    public static void main(String[] args) throws IOException {
        TestFileIO io = new TestFileIO();
        io.test();
    }
}
