package com.sds.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author caoshuai
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    /**
     * win 系统
     */
    private static final String WIN = "win";

    /**
     * mac 系统
     */
    private static final String MAC = "mac";

    /** 文件大小限制 */
    private Long maxSize;

    /** 头像大小限制 */
    private Long avatarMaxSize;

    private ElPath mac;

    private ElPath linux;

    private ElPath windows;

    public ElPath getPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith(WIN)) {
            return windows;
        } else if(os.toLowerCase().startsWith(MAC)){
            return mac;
        }
        return linux;
    }

    @Data
    public static class ElPath{

        private String path;

        private String avatar;
    }
}
