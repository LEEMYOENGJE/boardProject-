package project.configs;

import project.models.member.MemberInfo;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Spring Data JPA에서 자동으로 생성 및 변경된 엔티티의 감사 정보(Audit)를 설정하는 클래스입니다.
 * AuditorAware 인터페이스를 구현하여 현재 사용자의 이메일을 추적합니다.
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * 현재 사용자의 이메일을 추적하여 Optional로 반환합니다.
     * Spring Security의 SecurityContextHolder를 사용하여 현재 인증된 사용자를 가져옵니다.
     * 만약 사용자가 인증되어 있고 Principal이 MemberInfo 클래스의 인스턴스인 경우,
     * 해당 MemberInfo 객체에서 이메일을 추출하여 반환합니다.
     *
     * @return 현재 사용자의 이메일을 감사 정보로 사용하는 Optional
     */
    @Override
    public Optional<String> getCurrentAuditor() {

        String email = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 현재 사용자가 인증되어 있고 Principal이 MemberInfo 클래스의 인스턴스인 경우
        if (auth != null && auth.getPrincipal() instanceof MemberInfo) {
            MemberInfo memberInfo = (MemberInfo) auth.getPrincipal();
            email = memberInfo.getEmail();
        }

        // 추적된 이메일을 Optional로 반환
        return Optional.ofNullable(email);
    }
}
