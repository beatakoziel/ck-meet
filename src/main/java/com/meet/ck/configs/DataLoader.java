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

    public static final String IMAGES_PATH = "C:\\Users\\beata\\IdeaProjects\\ck\\src\\main\\resources\\images\\";
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

            //image5
            Image image5 = addImage(5L, "tomek.jpg");
            //user5
            userRepository.save(User.builder()
                    .id(5L)
                    .username("tomek")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MAN)
                    .nickname("Tomek")
                    .description("Jestem fanem dobrej zabawy. Uwielbiam skateboard, więc chętnie wyskoczę pojeździć w towarzystwie.")
                    .dateOfBirth(LocalDate.of(1999, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.WOMAN))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 30)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("tomek123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.ART))
                    .avatar(image5)
                    .build()
            );

            //image6
            Image image6 = addImage(6L, "marek.jpg");
            //user6
            userRepository.save(User.builder()
                    .id(6L)
                    .username("marek")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MAN)
                    .nickname("Marek")
                    .description("Moją wielką pasją jest motoryzacja. Szukam kolegów do wspólnego majsterkowania przy samochodach.")
                    .dateOfBirth(LocalDate.of(1975, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MAN))
                    .preferredAgeToMeetFrom((short) 25)
                    .preferredAgeToMeetTo((short) 70)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("marek123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.AUTOMOTIVE))
                    .avatar(image6)
                    .build()
            );

            //image7
            Image image7 = addImage(7L, "ela.jpg");
            //user7
            userRepository.save(User.builder()
                    .id(7L)
                    .username("ela")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.WOMAN)
                    .nickname("Ela")
                    .description("Jestem architektem wnętrz oraz pasjonatką przejażdżek rowerowych. Chętnie poznam kogoś do spędzania wolnych chwil.")
                    .dateOfBirth(LocalDate.of(1982, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.WOMAN))
                    .preferredAgeToMeetFrom((short) 20)
                    .preferredAgeToMeetTo((short) 50)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("ela123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.CYCLING))
                    .avatar(image7)
                    .build()
            );

            //image8
            //Image image8 = addImage(8L, "marcin.jpg");
            //user8
            userRepository.save(User.builder()
                    .id(8L)
                    .username("marcin")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MAN)
                    .nickname("Marcin")
                    .description("Szukam osób do gry w Dungeons and Dragons - miłe towarzystwo gwarantowane")
                    .dateOfBirth(LocalDate.of(1995, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MAN))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 35)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("marcin123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.BOARD_GAMES))
                    //.avatar(image8)
                    .build()
            );

            //image9
            Image image9 = addImage(9L, "jacek.jpg");
            //user9
            userRepository.save(User.builder()
                    .id(9L)
                    .username("jacek")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MAN)
                    .nickname("Jacek")
                    .description("Student z zawodu i zamiłowania. Wyjdźmy na jakieś piwko!")
                    .dateOfBirth(LocalDate.of(1997, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MAN))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 35)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("jacek123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.PARTIES))
                    .avatar(image9)
                    .build()
            );

            //image10
            Image image10 = addImage(10L, "hanna.jpg");
            //user10
            userRepository.save(User.builder()
                            .id(10L)
                            .username("hanna")
                            .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                            .enabled(true)
                            .gender(Gender.WOMAN)
                            .nickname("Hanna")
                            .description("Z wielką chęcią wybiorę się na kawę i pogawędzę.")
                            .dateOfBirth(LocalDate.of(1950, 12, 21))
                            .preferredGenderToMeet(Collections.singletonList(Gender.WOMAN))
                            .preferredAgeToMeetFrom((short) 30)
                            .preferredAgeToMeetTo((short) 80)
                            .contactData(contactDataRepository.save(ContactData.builder()
                                    .email("hanna123@gmail.com")
                                    .build()
                            ))
                            .interests(Collections.singletonList(Interests.COOKING))
                            .avatar(image10)
                            .build()
            );

            //image11
            Image image11 = addImage(11L, "julka.jpg");
            //user11
            userRepository.save(User.builder()
                    .id(11L)
                    .username("julka")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.WOMAN)
                    .nickname("Julka")
                    .description("Mam maltańczyka o imieniu Kokos. Szukam miłośnika zwierząt do wspólnego wyprowadzania piesków.")
                    .dateOfBirth(LocalDate.of(2000, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.WOMAN))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 25)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("julka123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.DANCE))
                    .avatar(image11)
                    .build()
            );

            //image12
            Image image12 = addImage(12L, "wiktor.jpg");
            //user12
            userRepository.save(User.builder()
                    .id(12L)
                    .username("wiktor")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.WOMAN)
                    .nickname("Wiktor")
                    .description("Szukam kompana do gry w szachy. Człowiek doświadczony.")
                    .dateOfBirth(LocalDate.of(1945, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MAN))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 90)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("wiktor123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interests.BOARD_GAMES))
                    .avatar(image12)
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