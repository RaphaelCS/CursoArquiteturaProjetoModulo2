package banco;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{

    public ContaCorrente(Integer numero, BigDecimal saldo, Cliente cliente) {
        super(numero, saldo, TipoContaEnum.CORRENTE, cliente);
        BancoContas.gravarConta(this);
    }
}
