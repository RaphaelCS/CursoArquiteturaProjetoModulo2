package banco.model;

import banco.enums.TipoPessoaEnum;

import java.math.BigDecimal;

public class ClientePessoaFisica extends Cliente{

    private final String cpf;
    public String getCpf() {
        return cpf;
    }
    public ClientePessoaFisica(String cpf, String nome, Integer numeroConta, BigDecimal saldo) {
        super(TipoPessoaEnum.FISICA, nome, numeroConta, saldo);
        this.cpf = cpf;
    }
}
