package com.meet.ck.configs;

import com.meet.ck.database.entities.ContactData;
import com.meet.ck.database.entities.Image;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interests;
import com.meet.ck.database.repositories.IContactDataRepository;
import com.meet.ck.database.repositories.IImageRepository;
import com.meet.ck.database.repositories.IUserRepository;
import com.meet.ck.services.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    public static final String IMAGES_PATH = "C:\\Users\\beata\\Downloads\\";
    private final IContactDataRepository contactDataRepository;
    private final IUserRepository userRepository;
    private final IImageRepository imageRepository;


    public void run(ApplicationArguments args) throws IOException {
        if (userRepository.count() < 1) {
            //image1
            Image image1 = addImage(1L, "ania.jpg");
            //user1
            userRepository.save(User.builder()
                    .id(1L)
                    .username("ania")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.WOMAN)
                    .nickname("Ania")
                    .description("Szukam kogoś do wspólnych spacerów lub przejażdżek rowerowych. " +
                            "Mile widziane spotkania w dziewczęcym gronie na robienie paznokci czy planszówki :)")
                    .dateOfBirth(LocalDate.of(1995, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.WOMAN))
                    .preferredAgeToMeetFrom((short) 20)
                    .preferredAgeToMeetTo((short) 40)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("ania123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.ART))
                    .avatar(image1)
                    .build()
            );

            //image2
            Image image2 = addImage(2L, "adam.jpg");
            //user2
            userRepository.save(User.builder()
                    .id(2L)
                    .username("adam")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MAN)
                    .nickname("Adam")
                    .description("Chętnie wybiorę się na kawę lub przejażdżkę rowerową. Lubię spędzać aktywnie czas!")
                    .dateOfBirth(LocalDate.of(1987, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.WOMAN))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 50)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("adam123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.ART))
                    .avatar(image2)
                    .build()
            );
            //image3
            Image image3 = addImage(3L, "justyna.jpg");
            //user3
            userRepository.save(User.builder()
                    .id(3L)
                    .username("justyna")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.WOMAN)
                    .nickname("Justyna")
                    .description("Chętnie wybiorę się do kina lub kawiarni, żeby spędzić czas w miłym towarzystwie.")
                    .dateOfBirth(LocalDate.of(1980, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.WOMAN))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 60)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("justyna123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.ART))
                    .avatar(image3)
                    .build()
            );
            //image4
            //Image image4 = addImage(4L, "justyna.jpg");
            //user4
            userRepository.save(User.builder()
                    .id(4L)
                    .username("kasia")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.WOMAN)
                    .nickname("Kasia")
                    .description("Jestem fanką kreatywnego spędzania czasu i pozytywnego myślenia :D")
                    .dateOfBirth(LocalDate.of(1998, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.WOMAN))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 60)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("kasia123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.ART))
                    //.avatar(image4)
                    .build()
            );
        }
    }

    private Image addImage(Long imageId, String fileName) throws IOException {
        return imageRepository.save(Image.builder()
                .id(imageId)
                .name(fileName)
                .type("image/jpeg")
                .data(Files.readAllBytes(Paths.get(IMAGES_PATH + fileName)))
                .build()
        );
    }
}