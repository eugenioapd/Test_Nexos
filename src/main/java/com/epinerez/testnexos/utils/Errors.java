package com.epinerez.testnexos.utils;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Errors {

    private Integer status;
    private String message;
    private String url;
    private List<String> fields;

}
