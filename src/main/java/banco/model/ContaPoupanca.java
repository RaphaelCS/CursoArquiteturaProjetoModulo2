package banco.model;

import banco.enums.TipoContaEnum;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Integer numero, BigDecimal saldo, ClientePessoaFisica cliente)  {
        super(numero, saldo, TipoContaEnum.POUPANCA, cliente);
    }

    public ContaPoupanca(){ }
}
