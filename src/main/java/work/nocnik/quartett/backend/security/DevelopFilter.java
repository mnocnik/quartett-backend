package work.nocnik.quartett.backend.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Profile("profile_develop")
@Component
@RequiredArgsConstructor
public class DevelopFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse, final FilterChain filterChain) throws ServletException, IOException {
    final GrantedAuthority granted = new SimpleGrantedAuthority("ROLE_DEV");
    final Authentication auth = new UsernamePasswordAuthenticationToken("developer", null, List.of(granted));
    SecurityContextHolder.getContext().setAuthentication(auth);

//    servletResponse.setHeader("Access-Control-Allow-Origin", "*");
//    servletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
//    servletResponse.setHeader("Access-Control-Allow-Headers", "*");
//    servletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//    servletResponse.setHeader("Access-Control-Max-Age", "180");

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
