package com.example.demo.order.infra.domain;

import com.example.demo.order.command.domain.CancelPolicy;
import com.example.demo.order.command.domain.Canceller;
import com.example.demo.order.command.domain.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SecurityCancelPolicy implements CancelPolicy {

    @Override
    public boolean hasCancellationPermission(Order order, Canceller canceller) {
        return isCancellerOrderer(order, canceller) || isCurrentUserAdminRole();
    }

    private boolean isCancellerOrderer(Order order, Canceller canceller) {
        return order.getOrderer().getMemberId().getId().equals(canceller.getMemberId());
    }

    private boolean isCurrentUserAdminRole() {
        SecurityContext context = SecurityContextHolder.getContext();
        if(context == null) return false;

        Authentication authentication = context.getAuthentication();
        if(authentication == null) return false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if(authorities == null) return false;

        return authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

}
