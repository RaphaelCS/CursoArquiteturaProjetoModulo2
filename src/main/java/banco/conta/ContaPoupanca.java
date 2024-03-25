package banco.conta;

import banco.cliente.ClientePessoaFisica;
import banco.enums.TipoContaEnum;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta{
    public ContaPoupanca(Integer numero, BigDecimal saldo, ClientePessoaFisica cliente)  {
        super(numero, saldo, TipoContaEnum.POUPANCA, cliente);
    }
}
