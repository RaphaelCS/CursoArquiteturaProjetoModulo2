package banco;

import java.math.BigDecimal;

public class ContaCorrente extends Conta{

    public ContaCorrente(Integer numero, BigDecimal saldo) {
        super(numero, saldo, null);
        setTipoConta(TipoContaEnum.CORRENTE);
    }
}
