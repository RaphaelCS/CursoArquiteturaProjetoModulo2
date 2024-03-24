package banco;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("CONTA PESSOA FÍSICA");
        Cliente cliente = new Cliente("1", TipoPessoaEnum.FISICA,"Raphael", 1,new BigDecimal(10000));
        BancoContas.listaContas.add(cliente.getContaCorrente());
        cliente.getContaCorrente().setCliente(cliente);
        cliente.getContaCorrente().deposito(BigDecimal.valueOf(500));
        cliente.getContaCorrente().saque(BigDecimal.valueOf(200));
        cliente.getContaCorrente().consultaSaldo();


        System.out.println("CONTA PESSOA JURÍDICA");
        Cliente cliente1 = new Cliente("2", TipoPessoaEnum.JURIDICA,"Caixa",2,new BigDecimal(100000000));
        BancoContas.listaContas.add(cliente1.getContaCorrente());
        cliente1.getContaCorrente().setCliente(cliente1);
        cliente1.getContaCorrente().deposito(BigDecimal.valueOf(5000));
        cliente1.getContaCorrente().saque(BigDecimal.valueOf(10000));
        cliente1.getContaCorrente().consultaSaldo();

        cliente.getContaCorrente().transferencia(BigDecimal.valueOf(100),cliente1.getContaCorrente());
        cliente.getContaCorrente().consultaSaldo();
        cliente1.getContaCorrente().consultaSaldo();

        System.out.println("CONTA POUPANÇA");
        ContaPoupanca poupanca = null;
        ContaPoupanca poupanca1 = null;
        try {
            poupanca = new ContaPoupanca(1,new BigDecimal(2000), cliente);
            BancoContas.listaContas.add(poupanca);
            poupanca1 = new ContaPoupanca(1,new BigDecimal(2000), cliente1);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(poupanca);
        System.out.println(poupanca1);

        poupanca.deposito(BigDecimal.valueOf(100));
        poupanca.consultaSaldo();
        poupanca.saque(BigDecimal.valueOf(500));
        poupanca.consultaSaldo();

        System.out.println("CONTA PESSOA INVESTIMENTO");

        ContaInvestimento investimento = cliente.getContaCorrente().investir(new BigDecimal(100), cliente.getContaCorrente());
        BancoContas.listaContas.add(investimento);
        investimento.deposito(BigDecimal.valueOf(100));
        investimento.consultaSaldo();
        investimento.saque(BigDecimal.valueOf(50));
        investimento.consultaSaldo();
        investimento.getRendimento();

        ContaInvestimento investimento1 = new ContaInvestimento(3,new BigDecimal(100), cliente1);
        BancoContas.listaContas.add(investimento1);
        investimento1.getRendimento();
    }
}
