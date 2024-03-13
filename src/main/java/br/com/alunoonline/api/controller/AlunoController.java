package br.com.alunoonline.api.controller;

import br.com.alunoonline.api.client.ViaCepClient;
import br.com.alunoonline.api.model.Aluno;
import br.com.alunoonline.api.model.dto.AlunoDTO;
import br.com.alunoonline.api.service.AlunoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
@Log4j2
public class AlunoController {

    @Autowired
    AlunoService service;

    @Autowired
    ViaCepClient viaCepClient;
    @GetMapping("/cep/{cep}")
    public ResponseEntity<String> consultarCep(String cep){
        log.info("Consultando Cep do aluno: " + cep);
        return ResponseEntity.ok(viaCepClient.consultacep(cep));
    }
    @GetMapping("/cep-xml/{cep}")
    public ResponseEntity<String> consultarCep2(String cep) {
        log.info("Consultando Cep do aluno: " + cep);
        return ResponseEntity.ok(viaCepClient.consultacep2(cep));
    }

    @GetMapping("/nome/email/{id}")
    public ResponseEntity<AlunoDTO> consultarEmailAlunoByID(@PathVariable Long id){
        return ResponseEntity.ok(service.consultaAlunoEmail(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Aluno aluno) {
        service.create(aluno);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Aluno> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Aluno buscarPorEmail( @PathVariable String email) {
        return service.buscarPorEmail(email);

    }

    @GetMapping("/nome/{nome}/email/{email}") // posso fazer assim: ("nome-email/{nome}/{email})
    @ResponseStatus(HttpStatus.OK)
    public Aluno buscarPorNomeEmail (@PathVariable String nome, @PathVariable String email) {
        return service.buscarPorNomeEmail(nome, email);
    }

    @GetMapping("/email/jpql/{email}")
    @ResponseStatus(HttpStatus.OK)
    public  Aluno buscarPorEmailJpql (@PathVariable String email) {
        return service.buscarPorEmailJpql(email);
    }

    @GetMapping("/all/order/asc")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> listarTodosAlunosOrdenadosPorNome () {
        return service.listarTodosAlunosOrdenadosPorNome();
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Aluno> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody Aluno aluno) {
        service.update(id, aluno);
    }

}
