package banco;

import banco.enums.StatusEnum;
import banco.model.Cliente;
import banco.model.ClientePessoaFisica;
import banco.model.ClientePessoaJuridica;
import banco.service.BancoDadosContasService;
import banco.model.ContaInvestimento;
import banco.model.ContaPoupanca;
import banco.model.Conta;
import banco.service.ContaCorrenteServiceImpl;
import banco.service.ContaInvestimentoServiceImpl;
import banco.service.ContaService;

import java.math.BigDecimal;

public class Main {



    public static void main(String[] args) {
        System.out.println("******CONTA PESSOA FÍSICA******");
        Cliente cliente = new ClientePessoaFisica("1", "Raphael", 1,new BigDecimal(10000));

        ContaService contaService = new ContaCorrenteServiceImpl();
        contaService.deposito(cliente.getContaCorrente(),BigDecimal.valueOf(500));
        contaService.saque(cliente.getContaCorrente(), BigDecimal.valueOf(200));
        contaService.consultaSaldo(cliente.getContaCorrente());

        System.out.println("******CONTA PESSOA JURÍDICA******");
        Cliente cliente1 = new ClientePessoaJuridica("2","Caixa",2,new BigDecimal(100000000));
        contaService.deposito(cliente.getContaCorrente(),BigDecimal.valueOf(5000));
        contaService.saque(cliente.getContaCorrente(),BigDecimal.valueOf(10000));
        contaService.consultaSaldo(cliente.getContaCorrente());

        System.out.println(BancoDadosContasService.getListaContasCliente(cliente));
        System.out.println(BancoDadosContasService.getListaContasCliente(cliente1));

        contaService.transferencia(cliente.getContaCorrente(), cliente1.getContaCorrente(), BigDecimal.valueOf(100));
        contaService.consultaSaldo(cliente.getContaCorrente());
        contaService.consultaSaldo(cliente1.getContaCorrente());

        System.out.println("******CONTA POUPANÇA*******");


        Conta poupanca = new ContaPoupanca(1,new BigDecimal(2000), (ClientePessoaFisica) cliente);
        contaService.deposito(poupanca,BigDecimal.valueOf(100));
        contaService.consultaSaldo(poupanca);
        contaService.saque(poupanca,BigDecimal.valueOf(500));
        contaService.consultaSaldo(poupanca);

        //Conta poupanca1 = new ContaPoupanca(1,new BigDecimal(2000), cliente1);não pode criar

        Cliente cliente2 = new ClientePessoaFisica("3","Rebeca",3,BigDecimal.valueOf(100));
        cliente2.setStatus(StatusEnum.INATIVO);
        Conta poupanca1 = new ContaPoupanca(3,new BigDecimal(2000), (ClientePessoaFisica) cliente2);


        System.out.println("****CONTA INVESTIMENTO*******");

        ContaInvestimentoServiceImpl contaInvestimentoService = new ContaInvestimentoServiceImpl();

        ContaInvestimento contaInvestimento = contaInvestimentoService.investir(BigDecimal.valueOf(100),cliente.getContaCorrente());

        contaInvestimentoService.deposito(contaInvestimento,BigDecimal.valueOf(100));
        contaInvestimentoService.consultaSaldo(contaInvestimento);
        contaInvestimentoService.saque(contaInvestimento,BigDecimal.valueOf(50));
        contaInvestimentoService.consultaSaldo(contaInvestimento);
        contaInvestimentoService.getRendimento(contaInvestimento);

        ContaInvestimento investimento1 = contaInvestimentoService.investir(BigDecimal.valueOf(100),cliente1.getContaCorrente());
        contaInvestimentoService.getRendimento(investimento1);


    }
}
