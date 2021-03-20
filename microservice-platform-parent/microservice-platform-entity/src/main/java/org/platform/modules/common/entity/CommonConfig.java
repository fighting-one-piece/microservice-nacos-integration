package org.platform.modules.common.entity;

import org.platform.modules.abstr.entity.PKAutoEntity;

public class CommonConfig extends PKAutoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public interface Attribute {
        public static final String ID = "id";
        public static final String MODULE = "module";
        public static final String IDENTITY = "identity";
        public static final String KEY = "key";
        public static final String VALUE = "value";
    }

    /** 配置ID */
    private Long id = null;

    /** 配置模块 */
    private String module = null;

    /** 配置标识 */
    private String identity = null;

    /** 配置key */
    private String key = null;

    /** 配置value */
    private String value = null;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
