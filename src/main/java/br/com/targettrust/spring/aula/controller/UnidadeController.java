package br.com.targettrust.spring.aula.controller;


import br.com.targettrust.spring.aula.controller.request.UnidadeRequest;
import br.com.targettrust.spring.aula.controller.response.UnidadeResponse;
import br.com.targettrust.spring.aula.service.unidade.UnidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
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
@Tag(name = "Unidade", description = "Mantem dados de unidade")
public class UnidadeController {

    private final UnidadeService unidadeService;

    @Operation(summary = "Cria uma unidade")
    @PostMapping
    public UnidadeResponse create(@RequestBody @Valid UnidadeRequest request) {
        return UnidadeResponse.of(unidadeService.create(request.toModel()));
    }

    @Operation(summary = "Lista todas as unidades paginadas", description = "Description")
    @GetMapping
    public Page<UnidadeResponse> findAll(@ParameterObject Pageable pageable) {
        return UnidadeResponse.of(unidadeService.findAll(pageable));
    }

}
