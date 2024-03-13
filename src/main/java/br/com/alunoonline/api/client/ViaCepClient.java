package br.com.alunoonline.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "viacep", url = "https://viacep.com.br/ws/")
public interface ViaCepClient {

    @GetMapping("{cep}/json")
    String consultacep(@PathVariable("cep")String cep);

    @GetMapping("{cep}/xml")
    String consultacep2(@PathVariable("cep") String cep);

}



