package banco.conta;

import banco.cliente.Cliente;
import banco.enums.TipoContaEnum;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{

    public ContaCorrente(Integer numero, BigDecimal saldo, Cliente cliente) {
        super(numero, saldo, TipoContaEnum.CORRENTE, cliente);
        BancoContas.gravarConta(this);
    }
}
