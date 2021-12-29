package helpers;

import com.github.javafaker.Faker;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PUBLIC)
public class Generator {
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            middleName = faker.name().nameWithMiddle(),
            company = faker.company().name(),
            email = faker.internet().emailAddress(),
            longName = faker.lorem().characters(130);
}
