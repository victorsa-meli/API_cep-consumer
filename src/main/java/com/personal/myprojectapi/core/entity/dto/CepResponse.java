package com.personal.myprojectapi.core.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CepResponse {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;


}
