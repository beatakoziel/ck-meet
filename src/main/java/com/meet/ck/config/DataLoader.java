package com.meet.ck.config;

import com.meet.ck.database.entity.*;
import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interest;
import com.meet.ck.database.enums.MeetingCategory;
import com.meet.ck.database.enums.RegistrationStatus;
import com.meet.ck.database.repository.*;
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

    public static final String PEOPLE_IMAGES_PATH = "/home/beatakoziel/Projects/ck-meet/src/main/resources/images/people/";
    //"C:\\Users\\beata\\IdeaProjects\\ck-meet\\src\\main\\resources\\images\\people\\";
    public static final String MEETINGS_IMAGES_PATH = "/home/beatakoziel/Projects/ck-meet/src/main/resources/images/meetings/";
    private final IContactDataRepository contactDataRepository;
    private final IUserRepository userRepository;
    private final IMeetingRepository meetingRepository;
    private final ICommentRepository commentRepository;
    private final IImageRepository imageRepository;

    public void run(ApplicationArguments args) throws IOException {
        initializeUserRepository();
        initializeMeetingRepository();
    }

    private void initializeMeetingRepository() throws IOException {
        if (meetingRepository.count() < 1) {
            Image image15 = addMeetingImage(15L, "coffee.jpg");
            meetingRepository.save(Meeting.builder()
                    .id(1L)
                    .name("Wyjście na kawę")
                    .description("Usłyszałam ostatnio o kawiarnii Bosko w Kielcach. Chętnie wyjdę tam na kawę i jakieś ciacho.")
                    .maxNumOfParticipants(2)
                    .host(userRepository.findById(1L).get())
                    .category(MeetingCategory.BOARD_GAMES)
                    .build()
            );
            Image image16 = addMeetingImage(16L, "football.jpg");
            meetingRepository.save(Meeting.builder()
                    .id(2L)
                    .name("Gra w piłkę nożną")
                    .description("Kto chce pokopać w piłkę na boisku Staszica niech dołącza sobie do spotkania.")
                    .date(LocalDate.now())
                    .maxNumOfParticipants(12)
                    .host(userRepository.findById(2L).get())
                    .category(MeetingCategory.ACTIVITY)
                    .build()
            );
        }
    }

    private void initializeUserRepository() throws IOException {
        if (userRepository.count() < 1) {
            //image1
            Image image1 = addUserImage(1L, "ania.jpg");
            //user1
            userRepository.save(User.builder()
                    .id(1L)
                    .username("ania")
                    .password("$2a$10$mGeLen.dK53WfuEvahVGPOzl7ijRUPxmH5/0.ZfqVVAEy7uFC5ERK")
                    .enabled(true)
                    .gender(Gender.FEMALE)
                    .nickname("Ania")
                    .description("Szukam kogoś do wspólnych spacerów lub przejażdżek rowerowych. " +
                            "Mile widziane spotkania w dziewczęcym gronie na robienie paznokci czy planszówki :)")
                    .dateOfBirth(LocalDate.of(1995, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.FEMALE))
                    .preferredAgeToMeetFrom((short) 20)
                    .preferredAgeToMeetTo((short) 40)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("ania123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.ART))
                    .avatar(image1)
                    .build()
            );

            //image2
            Image image2 = addUserImage(2L, "adam.jpg");
            //user2
            userRepository.save(User.builder()
                    .id(2L)
                    .username("adam")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MALE)
                    .nickname("Adam")
                    .description("Chętnie wybiorę się na kawę lub przejażdżkę rowerową. Lubię spędzać aktywnie czas!")
                    .dateOfBirth(LocalDate.of(1987, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.FEMALE))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 50)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("adam123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.ART))
                    .avatar(image2)
                    .build()
            );
            //image3
            Image image3 = addUserImage(3L, "julka.jpg");
            //user3
            userRepository.save(User.builder()
                    .id(3L)
                    .username("julka")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.FEMALE)
                    .nickname("Julka")
                    .description("Mam maltańczyka o imieniu Kokos. Szukam miłośnika zwierząt do wspólnego wyprowadzania piesków.")
                    .dateOfBirth(LocalDate.of(2000, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.FEMALE))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 25)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("julka123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.DANCE))
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
                    .gender(Gender.FEMALE)
                    .nickname("Kasia")
                    .description("Jestem fanką kreatywnego spędzania czasu i pozytywnego myślenia :D")
                    .dateOfBirth(LocalDate.of(1998, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.FEMALE))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 60)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("kasia123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.ART))
                    //.avatar(image4)
                    .build()
            );

            //image5
            Image image5 = addUserImage(5L, "tomek.jpg");
            //user5
            userRepository.save(User.builder()
                    .id(5L)
                    .username("tomek")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MALE)
                    .nickname("Tomek")
                    .description("Jestem fanem dobrej zabawy. Uwielbiam skateboard, więc chętnie wyskoczę pojeździć w towarzystwie.")
                    .dateOfBirth(LocalDate.of(1999, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.FEMALE))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 30)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("tomek123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.ART))
                    .avatar(image5)
                    .build()
            );

            //image6
            Image image6 = addUserImage(6L, "marek.jpg");
            //user6
            userRepository.save(User.builder()
                    .id(6L)
                    .username("marek")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MALE)
                    .nickname("Marek")
                    .description("Moją wielką pasją jest motoryzacja. Szukam kolegów do wspólnego majsterkowania przy samochodach.")
                    .dateOfBirth(LocalDate.of(1975, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MALE))
                    .preferredAgeToMeetFrom((short) 25)
                    .preferredAgeToMeetTo((short) 70)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("marek123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.AUTOMOTIVE))
                    .avatar(image6)
                    .build()
            );

            //image7
            Image image7 = addUserImage(7L, "ela.jpg");
            //user7
            userRepository.save(User.builder()
                    .id(7L)
                    .username("ela")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.FEMALE)
                    .nickname("Ela")
                    .description("Jestem architektem wnętrz oraz pasjonatką przejażdżek rowerowych. Chętnie poznam kogoś do spędzania wolnych chwil.")
                    .dateOfBirth(LocalDate.of(1982, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.FEMALE))
                    .preferredAgeToMeetFrom((short) 20)
                    .preferredAgeToMeetTo((short) 50)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("ela123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.CYCLING))
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
                    .gender(Gender.MALE)
                    .nickname("Marcin")
                    .description("Szukam osób do gry w Dungeons and Dragons - miłe towarzystwo gwarantowane")
                    .dateOfBirth(LocalDate.of(1995, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MALE))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 35)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("marcin123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.BOARD_GAMES))
                    //.avatar(image8)
                    .build()
            );

            //image9
            Image image9 = addUserImage(9L, "jacek.jpg");
            //user9
            userRepository.save(User.builder()
                    .id(9L)
                    .username("jacek")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MALE)
                    .nickname("Jacek")
                    .description("Student z zawodu i zamiłowania. Wyjdźmy na jakieś piwko!")
                    .dateOfBirth(LocalDate.of(1997, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MALE))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 35)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("jacek123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.PARTIES))
                    .avatar(image9)
                    .build()
            );

            //image10
            Image image10 = addUserImage(10L, "hanna.jpg");
            //user10
            userRepository.save(User.builder()
                    .id(10L)
                    .username("hanna")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.FEMALE)
                    .nickname("Hanna")
                    .description("Z wielką chęcią wybiorę się na kawę i pogawędzę.")
                    .dateOfBirth(LocalDate.of(1950, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.FEMALE))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 80)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("hanna123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.COOKING))
                    .avatar(image10)
                    .build()
            );

            //image11
            Image image11 = addUserImage(11L, "justyna.jpg");
            //user11
            userRepository.save(User.builder()
                    .id(11L)
                    .username("justyna")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.FEMALE)
                    .nickname("Justyna")
                    .description("Chętnie wybiorę się do kina lub kawiarni, żeby spędzić czas w miłym towarzystwie.")
                    .dateOfBirth(LocalDate.of(1980, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.FEMALE))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 60)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("justyna123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.ART))
                    .avatar(image11)
                    .build()
            );

            //image12
            Image image12 = addUserImage(12L, "wiktor.jpg");
            //user12
            userRepository.save(User.builder()
                    .id(12L)
                    .username("wiktor")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.MALE)
                    .nickname("Wiktor")
                    .description("Szukam kompana do gry w szachy. Człowiek doświadczony.")
                    .dateOfBirth(LocalDate.of(1945, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MALE))
                    .preferredAgeToMeetFrom((short) 30)
                    .preferredAgeToMeetTo((short) 90)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("wiktor123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.BOARD_GAMES))
                    .avatar(image12)
                    .build()
            );

            //image13
            //Image image13 = addImage(13L, "ola.jpg");
            //user13
            userRepository.save(User.builder()
                    .id(13L)
                    .username("ola")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.FEMALE)
                    .nickname("Ola")
                    .description("Chętnie wybiorę się na wspinaczki górskie czy dłuuuuugie i ciężkie przejażdżki rowerowe.")
                    .dateOfBirth(LocalDate.of(1996, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MALE))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 30)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("ola123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.HIKING))
                    //.avatar(image13)
                    .build()
            );

            //image14
            Image image14 = addUserImage(14L, "agnieszka.jpg");
            //user14
            userRepository.save(User.builder()
                    .id(14L)
                    .username("agnieszka")
                    .password("$2a$10$VnLSeqWqthL83OVxbOurEOqH0aOBh.0Q8JkPXQYaabcwCrmmYGtsG")
                    .enabled(true)
                    .gender(Gender.FEMALE)
                    .nickname("Agnieszka")
                    .description("Chcę wraz z przyjaciółką założyć koło śpiewu. Chętnych zapraszamy serdecznie. Pośpiewamy sobie")
                    .dateOfBirth(LocalDate.of(1984, 12, 21))
                    .preferredGenderToMeet(Collections.singletonList(Gender.MALE))
                    .preferredAgeToMeetFrom((short) 18)
                    .preferredAgeToMeetTo((short) 50)
                    .status(RegistrationStatus.COMPLETED)
                    .contactData(contactDataRepository.save(ContactData.builder()
                            .email("agnieszka123@gmail.com")
                            .build()
                    ))
                    .interests(Collections.singletonList(Interest.HIKING))
                    .avatar(image14)
                    .build()
            );
        }
    }

    private Image addUserImage(Long imageId, String fileName) throws IOException {
        return imageRepository.save(Image.builder()
                .id(imageId)
                .name(fileName)
                .type("image/jpeg")
                .data(Files.readAllBytes(Paths.get(PEOPLE_IMAGES_PATH + fileName)))
                .build()
        );
    }

    private Image addMeetingImage(Long imageId, String fileName) throws IOException {
        return imageRepository.save(Image.builder()
                .id(imageId)
                .name(fileName)
                .type("image/jpeg")
                .data(Files.readAllBytes(Paths.get(MEETINGS_IMAGES_PATH + fileName)))
                .build()
        );
    }
}