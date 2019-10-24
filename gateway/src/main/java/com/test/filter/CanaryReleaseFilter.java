package com.test.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.test.loadbalancer.CanaryHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 金丝雀发布拦截
 *
 * @author hedongzhou
 * @since 2019/10/24
 */
@Component
public class CanaryReleaseFilter extends ZuulFilter {

    private static final String CANARY_KEY = "canary";

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String canary = request.getHeader(CANARY_KEY);
        if (StringUtils.isNotBlank(canary)) {
            context.set(FilterConstants.LOAD_BALANCER_KEY, "canary");
            CanaryHolder.set(canary);
        }
        return null;
    }

}
