package com.first951.securitycompanyserver.schema.organization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.first951.securitycompanyserver.schema.post.PostDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrganizationDto {

    @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("address")
    @NotBlank
    private String address;

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty(value = "posts", access = JsonProperty.Access.READ_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PostDto> postDtos;

}
