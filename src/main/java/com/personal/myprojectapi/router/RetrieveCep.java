package com.personal.myprojectapi.router;

import com.personal.myprojectapi.core.entity.Adress;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;
import java.util.List;

public interface RetrieveCep {


    ResponseEntity <List<Adress>> getAdress() throws Exception;

    @GetMapping("/retrieveCep/{cep}")
    ResponseEntity<String> retrieveCep(@PathVariable String cep) throws MalformedURLException, Exception;
}
