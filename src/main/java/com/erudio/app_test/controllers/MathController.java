package com.erudio.app_test.controllers;

import com.erudio.app_test.services.MathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/math")
public class MathController {

    private static final Logger log = LoggerFactory.getLogger(MathController.class);

    @Autowired
    private MathService mathService;

    @GetMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        Double soma = mathService.calcularSoma(numberOne, numberTwo);
        log.info("Operação de soma realizada sobre os números {} e {}.", numberOne, numberTwo);
        return soma;
    }

    @GetMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        Double sub = mathService.calcularSubtracao(numberOne, numberTwo);
        log.info("Operação de subtração realizada sobre os números {} e {}.", numberOne, numberTwo);
        return sub;
    }

    @GetMapping("/div/{numberOne}/{numberTwo}")
    public Double div(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        Double div = mathService.calcularDivisao(numberOne, numberTwo);
        log.info("Operação de divisão realizada sobre os números {} e {}.", numberOne, numberTwo);
        return div;
    }

    @GetMapping("/mult/{numberOne}/{numberTwo}")
    public Double mult(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        Double mult = mathService.calcularMultiplicacao(numberOne, numberTwo);
        log.info("Operação de multiplicação realizada sobre os números {} e {}.", numberOne, numberTwo);
        return mult;
    }

    @GetMapping("/med/{numberOne}/{numberTwo}")
    public Double med(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        Double med = mathService.calcularMedia(numberOne, numberTwo);
        log.info("Operação de média aritmética realizada sobre os números {} e {}.", numberOne, numberTwo);
        return med;
    }

    @GetMapping("/raiz/{numberOne}/{numberTwo}")
    public Double raiz(
            @PathVariable(value = "numberOne") String numberOne,
            @PathVariable(value = "numberTwo") String numberTwo
    ){
        Double raiz = mathService.calcularRaiz(numberOne, numberTwo);
        log.info("Operação de radiciação realizada sobre os números {} e {}.", numberOne, numberTwo);
        return raiz;
    }

}
