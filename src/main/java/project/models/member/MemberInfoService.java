package project.models.member;

import lombok.RequiredArgsConstructor;
import project.entities.Member;
import project.repositories.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.models.member.MemberInfo;

@Service
@RequiredArgsConstructor
public class MemberInfoService implements UserDetailsService {

    private final MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        return MemberInfo.builder()
                .email(member.getEmail())
                .password(member.getPassword()) //hash되어 있는 password
                .member(member)
                .build();
    }
}