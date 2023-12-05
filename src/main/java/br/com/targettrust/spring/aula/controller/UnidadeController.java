package br.com.targettrust.spring.aula.controller;


import br.com.targettrust.spring.aula.controller.request.UnidadeRequest;
import br.com.targettrust.spring.aula.controller.response.UnidadeResponse;
import br.com.targettrust.spring.aula.service.unidade.UnidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/unidades")
@RequiredArgsConstructor
public class UnidadeController {

    private final UnidadeService unidadeService;

    @PostMapping
    public UnidadeResponse create(@RequestBody @Valid UnidadeRequest request) {
        return UnidadeResponse.of(unidadeService.create(request.toModel()));
    }

    @GetMapping
    public Page<UnidadeResponse> findAll(Pageable pageable) {
        return UnidadeResponse.of(unidadeService.findAll(pageable));
    }

}
