package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
//@Ignore
public class JdbcUserServiceTest extends AbstractUserServiceTest {

//    @Rule
//    public final EnvironmentVariables env = new EnvironmentVariables();
//
//    @BeforeClass
//    public static void checkProfile() {
//        Assume.assumeTrue(isJdbcBased());
//    }
//
//    private static boolean isJdbcBased() {
//        Logger log = Logger.getLogger("my.logger");
//        log.setLevel(Level.ALL);
//        ConsoleHandler handler = new ConsoleHandler();
//        handler.setFormatter(new SimpleFormatter());
//        log.addHandler(handler);
//        log.info(env.toString());
//
//        return Arrays.toString(new JdbcUserServiceTest().env.getActiveProfiles()).toLowerCase().contains("jdbc");
//    }

}