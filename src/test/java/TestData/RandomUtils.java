package TestData;

import com.github.javafaker.Faker;

public class RandomUtils {
    Faker faker = new Faker();
    public String userName = faker.funnyName().name();
    public String password = faker.internet()
            .password(8,10, true, true)+1 +"Qa";
}
