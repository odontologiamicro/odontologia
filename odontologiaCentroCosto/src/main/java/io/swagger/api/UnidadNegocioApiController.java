package io.swagger.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiParam;
import io.swagger.model.UnidadDeNegocio;
import io.swagger.service.UnidadNegocioService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T03:36:42.350Z")

@Controller
public class UnidadNegocioApiController implements UnidadNegocioApi {

	@Autowired
    private UnidadNegocioService unidadNegocioService;
	
    private static final Logger log = LoggerFactory.getLogger(UnidadNegocioApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UnidadNegocioApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> adicionarUnidadNegocio(@ApiParam(value = "unidad de negocio a adicionar"  )  @Valid @RequestBody UnidadDeNegocio unidadDeNegocio) {
        unidadNegocioService.insertUnidadNegocio(unidadDeNegocio);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Object> buscarUnidadNegocioById(@ApiParam(value = "ingrese el id de la unidad de negocio a buscar",required=true) @PathVariable("idUnidadDeNegocioParam") String idUnidadDeNegocioParam) {
    		
    		UnidadDeNegocio unidad = unidadNegocioService.getUnidadNegocioById(idUnidadDeNegocioParam);
		
		Link selfLinkC = linkTo(methodOn(UnidadNegocioApiController.class).buscarUnidadNegocioById(unidad.getIdUnidadDeNegocio())).withSelfRel();
		unidad.add(selfLinkC);
		
		HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Object>(unidad, responseHeaders, HttpStatus.OK);
    }

}
