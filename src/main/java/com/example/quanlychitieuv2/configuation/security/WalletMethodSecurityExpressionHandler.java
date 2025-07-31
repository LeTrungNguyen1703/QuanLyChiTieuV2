package com.example.quanlychitieuv2.configuation.security;

import com.example.quanlychitieuv2.service.WalletPermissionService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

/**
 * Handler biểu thức bảo mật tùy chỉnh để sử dụng WalletSecurityExpressionRoot
 * cho việc đánh giá các biểu thức bảo mật trong phương thức
 */
public class WalletMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
    private final WalletPermissionService walletPermissionService;

    public WalletMethodSecurityExpressionHandler(WalletPermissionService walletPermissionService) {
        this.walletPermissionService = walletPermissionService;
        // Cần thiết lập việc sử dụng SpringEL parameter name discovery
        setParameterNameDiscoverer(new org.springframework.security.core.parameters.DefaultSecurityParameterNameDiscoverer());
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        WalletSecurityExpressionRoot root = new WalletSecurityExpressionRoot(authentication, walletPermissionService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}
