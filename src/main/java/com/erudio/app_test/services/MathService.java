package com.erudio.app_test.services;

import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Double calcularSoma(String numberOne, String numberTwo){
        Double n1 = convertToDouble(numberOne);
        Double n2 =convertToDouble(numberTwo);
        return n1 + n2;
    }

    public Double calcularSubtracao(String numberOne, String numberTwo){
        Double n1 = convertToDouble(numberOne);
        Double n2 =convertToDouble(numberTwo);
        return n1 - n2;
    }

    public Double calcularMultiplicacao(String numberOne, String numberTwo){
        Double n1 = convertToDouble(numberOne);
        Double n2 =convertToDouble(numberTwo);
        return n1 * n2;
    }

    public Double calcularDivisao(String numberOne, String numberTwo){
        Double n1 = convertToDouble(numberOne);
        Double n2 =convertToDouble(numberTwo);
        return n1 / n2;
    }

    public Double calcularMedia(String numberOne, String numberTwo){
        Double n1 = convertToDouble(numberOne);
        Double n2 =convertToDouble(numberTwo);
        return n1 + n2 / 2;
    }

    public Double calcularRaiz(String numberOne, String numberTwo){
        Double n1 = convertToDouble(numberOne);
        Double n2 =convertToDouble(numberTwo);
        return Math.sqrt(n1 + n2);
    }

    private Double convertToDouble(String number){
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Erro ao converter '" + number + "' para double.");
        }
    }

}
