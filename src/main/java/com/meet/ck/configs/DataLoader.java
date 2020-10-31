package com.meet.ck.configs;

import com.meet.ck.database.entities.ContactData;
import com.meet.ck.database.entities.User;
import com.meet.ck.database.enums.Gender;
import com.meet.ck.database.enums.Interests;
import com.meet.ck.database.repositories.IContactDataRepository;
import com.meet.ck.database.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final IContactDataRepository contactDataRepository;
    private final IUserRepository userRepository;


    public void run(ApplicationArguments args) {
        //userRepository.deleteByContactDataEmail("ania123@gmail.com");
        if (userRepository.count() < 1) {
            userRepository.save(User.builder()
                    .id(1L)
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
                    .build()
            );
        }
    }
}