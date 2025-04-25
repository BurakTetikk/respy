package com.repsy.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Object> parseMetaJson(MultipartFile file) throws IOException {
        return OBJECT_MAPPER.readValue(file.getInputStream(), Map.class);
    }
}
