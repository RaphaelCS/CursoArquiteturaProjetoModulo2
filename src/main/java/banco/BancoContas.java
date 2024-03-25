package banco;

import java.util.ArrayList;

public class BancoContas {

    public static ArrayList<Conta> listaContas = new ArrayList<>();

    public static void gravarConta(Conta conta){
        listaContas.add(conta);
    }

    public static Conta getConta(Cliente cliente, TipoContaEnum tipoConta){
        for(int i = 0 ; i<listaContas.size();i++){
            Conta conta = listaContas.get(i);
            if(conta.getCliente().equals(cliente)){
                if(conta.getTipoConta().equals(tipoConta)){
                    return conta;
                }
            }
        }
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
