package banco;

import banco.cliente.ClientePessoaFisica;
import banco.cliente.ClientePessoaJuridica;
import banco.conta.BancoContas;
import banco.conta.ContaInvestimento;
import banco.conta.ContaPoupanca;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("******CONTA PESSOA FÍSICA******");
        ClientePessoaFisica cliente = new ClientePessoaFisica("1", "Raphael", 1,new BigDecimal(10000));
        cliente.getContaCorrente().deposito(BigDecimal.valueOf(500));
        cliente.getContaCorrente().saque(BigDecimal.valueOf(200));
        cliente.getContaCorrente().consultaSaldo();

        System.out.println("******CONTA PESSOA JURÍDICA******");
        ClientePessoaJuridica cliente1 = new ClientePessoaJuridica("2","Caixa",2,new BigDecimal(100000000));
        cliente1.getContaCorrente().deposito(BigDecimal.valueOf(5000));
        cliente1.getContaCorrente().saque(BigDecimal.valueOf(10000));
        cliente1.getContaCorrente().consultaSaldo();

        System.out.println(BancoContas.getListaContasCliente(cliente));
        System.out.println(BancoContas.getListaContasCliente(cliente1));

        cliente.getContaCorrente().transferencia(BigDecimal.valueOf(100),cliente1);
        cliente.getContaCorrente().transferencia(BigDecimal.valueOf(100),null);//conta inexistente
        cliente.getContaCorrente().consultaSaldo();
        cliente1.getContaCorrente().consultaSaldo();

        System.out.println("******CONTA POUPANÇA*******");

        ContaPoupanca poupanca = null;
        ContaPoupanca poupanca1 = null;

        poupanca = new ContaPoupanca(1,new BigDecimal(2000), cliente);
        poupanca.deposito(BigDecimal.valueOf(100));
        poupanca.consultaSaldo();
        poupanca.saque(BigDecimal.valueOf(500));
        poupanca.consultaSaldo();

        System.out.println(poupanca);
        //poupanca1 = new ContaPoupanca(1,new BigDecimal(2000), cliente1);não pode criar


        System.out.println("****CONTA INVESTIMENTO*******");

        ContaInvestimento investimento = ContaInvestimento.investir(BigDecimal.valueOf(100),cliente.getContaCorrente());

        investimento.deposito(BigDecimal.valueOf(100));
        investimento.consultaSaldo();
        investimento.saque(BigDecimal.valueOf(50));
        investimento.consultaSaldo();
        investimento.getRendimento();

        ContaInvestimento investimento1 = ContaInvestimento.investir(BigDecimal.valueOf(100),cliente1.getContaCorrente());
        investimento1.getRendimento();
    }
}
