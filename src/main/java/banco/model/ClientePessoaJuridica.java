package banco.model;

import banco.enums.TipoPessoaEnum;

import java.math.BigDecimal;

public class ClientePessoaJuridica extends Cliente{

    private String cpnj;
    public String getCpnj() {
        return cpnj;
    }
    public ClientePessoaJuridica(String cnpj, String nome, Integer numeroConta, BigDecimal saldo) {
        super(TipoPessoaEnum.JURIDICA, nome, numeroConta, saldo);
        this.cpnj=cnpj;
    }
}
