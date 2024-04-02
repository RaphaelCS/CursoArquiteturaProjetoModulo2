package banco.model;

import banco.enums.TipoContaEnum;
import banco.service.BancoDadosContasService;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {

    public ContaCorrente(Integer numero, BigDecimal saldo, Cliente cliente) {
        super(numero, saldo, TipoContaEnum.CORRENTE, cliente);
        BancoDadosContasService.gravarConta(this);
    }

    public ContaCorrente(){}
}
