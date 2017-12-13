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
import io.swagger.model.CentroCosto;
import io.swagger.model.UnidadDeNegocio;
import io.swagger.service.CentroCostoService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T03:36:42.350Z")

@Controller
public class CentroCostoApiController implements CentroCostoApi {
	
	@Autowired
    private CentroCostoService centroCostoService;

    private static final Logger log = LoggerFactory.getLogger(CentroCostoApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CentroCostoApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> adicionarCentroCosto(@ApiParam(value = "centro de costos a adicionar"  )  @Valid @RequestBody CentroCosto centroCosto) {
        centroCostoService.insertCentroCosto(centroCosto);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Object> buscarCentroCostoById(@ApiParam(value = "ingrese el id del centro de costos a buscar",required=true) @PathVariable("idCentroCostoParam") String idCentroCostoParam) {
    		
    		CentroCosto centro = centroCostoService.getCentroCostoById(idCentroCostoParam);
    		
    		UnidadDeNegocio unidad = centro.getUnidadDeNegocio();
    		
    		Link selfLink = linkTo(methodOn(CentroCostoApiController.class).buscarCentroCostoById(centro.getIdCentroCosto())).withSelfRel();
    		centro.add(selfLink);

        Link selfLinkC = linkTo(methodOn(UnidadNegocioApiController.class).buscarUnidadNegocioById(unidad.getIdUnidadDeNegocio())).withSelfRel();
        unidad.add(selfLinkC);
    		
    		HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<Object>(centro, responseHeaders, HttpStatus.OK);
    }

}
