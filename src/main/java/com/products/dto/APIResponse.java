package com.products.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.products.exceptions.ErrorDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class APIResponse<T> {

    private Integer status;
    private List<ErrorDetails> errors;
    private T data;

}
