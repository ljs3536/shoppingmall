package com.hertz.shoppingMall;

import com.hertz.shoppingMall.member.model.Member;
import com.hertz.shoppingMall.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.yml")
public class DataBaseConnectionTest {

    @Autowired
    private final MemberRepository memberRepository = Mockito.mock(MemberRepository.class);

//    @Test
//    @DisplayName("회원 저장 및 조회 테스트")
//    void saveAndFindMember(){
//        // Given (테스트 데이터 준비)
//        Member member = new Member();
//        member.setUsername("Test1");
//        member.setEmailAddress("test@example.com");
//
//        // When (저장)
//        Member saveMember = memberRepository.save(member);
//
//        // Then (검증)
//        Member foundMember = memberRepository.findById(saveMember.getId()).orElse(null);
//        assertThat(foundMember).isNotNull();
//        assertThat(foundMember.getUsername()).isEqualTo("Test1");
//        assertThat(foundMember.getEmailAddress()).isEqualTo("test@example.com");
//    }
}
