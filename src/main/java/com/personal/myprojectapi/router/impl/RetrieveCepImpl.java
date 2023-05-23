package com.personal.myprojectapi.router.impl;
import com.personal.myprojectapi.core.entity.Adress;
import com.personal.myprojectapi.core.entity.dto.CepResponse;
import com.personal.myprojectapi.core.repository.AdressRepository;
import com.personal.myprojectapi.router.RetrieveCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.util.List;


@RestController
@RequestMapping("/retrieveCep")
public class RetrieveCepImpl implements RetrieveCep {

    private final String viaCep = "https://viacep.com.br/ws/";
    @Autowired
    private AdressRepository adressRepository;


    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Adress>> getAdress()  {
        List<Adress> adress = adressRepository.findAll();
        if(adress.isEmpty())    {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(adress, HttpStatus.OK);
        }
    }

    @Override
    @PostMapping("/{cep}")
    public ResponseEntity<String> retrieveCep(@PathVariable String cep) throws MalformedURLException, Exception {

        RestTemplate restTemplate = new RestTemplate();
        String url = viaCep + cep + "/json/";


        CepResponse viaCepResponse = restTemplate.getForObject(url, CepResponse.class);

        if (viaCepResponse != null && viaCepResponse.getCep() != null) {
            // Mapear a resposta para a entidade Address
            Adress address = new Adress();
            address.setCep(viaCepResponse.getCep());
            address.setLogradouro(viaCepResponse.getLogradouro());
            address.setBairro(viaCepResponse.getBairro());
            address.setLocalidade(viaCepResponse.getLocalidade());
            address.setUf(viaCepResponse.getUf());


          adressRepository.save(address);

            return new ResponseEntity<>("Endereço salvo com sucesso.", HttpStatus.OK);
    } else {
            return new ResponseEntity<>("Cep nao encontrado", HttpStatus.NOT_FOUND);
        }
}
}
