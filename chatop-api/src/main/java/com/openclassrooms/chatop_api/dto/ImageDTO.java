package com.openclassrooms.chatop_api.dto;

import org.springframework.core.io.Resource;

public record ImageDTO(String contentType, Resource resource) {
}
