package org.platform.modules.user.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSentinelExceptionHandler {

    private static Logger LOG = LoggerFactory.getLogger(UserSentinelExceptionHandler.class);

    public static Object useBlockHandler(String account, String password, BlockException be) {
        LOG.error(String.format("BlockHandler：%s %s %s", account, password, be.getMessage()), be);
        return String.format("BlockHandler：%s %s %s", account, password, be.getMessage());
    }

    public static Object useFallbackHandler(String account, String password, Throwable e) {
        LOG.error(String.format("FallbackHandler：%s %s %s", account, password, e.getMessage()), e);
        return String.format("FallbackHandler：%s %s %s", account, password, e.getMessage());
    }

    public static Object useDefaultFallbackHandler(Throwable e) {
        LOG.error(String.format("DefaultFallbackHandler：%s", e.getMessage()), e);
        return String.format("DefaultFallbackHandler %s", e.getMessage());
    }

}