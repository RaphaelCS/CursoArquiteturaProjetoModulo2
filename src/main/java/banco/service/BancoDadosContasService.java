package banco.service;

import banco.model.Cliente;
import banco.enums.TipoContaEnum;
import banco.model.Conta;

import java.util.ArrayList;

public class BancoDadosContasService {

    public static ArrayList<Conta> listaContas = new ArrayList<>();

    public static void gravarConta(Conta conta){
        listaContas.add(conta);
    }

    public static Conta ObterConta(Integer numeroConta, TipoContaEnum tipoConta){
        for(int i = 0 ; i<listaContas.size();i++){
            Conta conta = listaContas.get(i);
            if(conta.getNumero().equals(numeroConta)){
                if(conta.getTipoConta().equals(tipoConta)){
                    return conta;
                }
            }
        }
        System.out.println("Conta inexistente ou não é conta corrente!");
        return null;
    }

    public static ArrayList<Conta> getListaContasCliente(Cliente cliente){
        ArrayList<Conta> contas = new ArrayList<>();
        for(int i = 0 ; i<listaContas.size();i++){
            Conta conta = listaContas.get(i);
            if(conta.getCliente().equals(cliente)){
                contas.add(conta);
            }
        }
        return contas;
    }

    public static ArrayList<Conta> getListaContas(){
       return listaContas;
    }
}
