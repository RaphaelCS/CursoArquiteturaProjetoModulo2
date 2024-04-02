package banco.model;

import banco.model.ClientePessoaFisica;
import banco.enums.TipoContaEnum;
import banco.model.Conta;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(Integer numero, BigDecimal saldo, ClientePessoaFisica cliente)  {
        super(numero, saldo, TipoContaEnum.POUPANCA, cliente);
    }

    public ContaPoupanca(){ }
}
