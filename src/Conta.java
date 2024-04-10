public class Conta {
    private double saldo; 
    private double limite; 
    private int numeroConta;
    private Cliente cliente;
    private static int proximoNumeroConta = 100;

    public Conta (Cliente cliente, double limite, double saldo) {
        this.numeroConta = proximoNumeroConta++;
        this.saldo = saldo; 
        this.limite = limite;
        this.cliente = cliente;

    }
    public Conta (Cliente cliente, double limite) {
        this.numeroConta = proximoNumeroConta++;
        saldo = 0;
        this.limite = limite;
        this.cliente = cliente;
    
    }
    public double getSaldo () {
        return saldo;
    }
    public double getLimite () { 
        return limite;
    }
    public void saque (double valor) {
      saldo -= valor;
    
    }

    public void deposito (double valor) {
        saldo += valor;
    }
    public int getNumeroDaConta () {
        return numeroConta;
    }

    public void transfere (double valor, Conta conta) {
        conta.deposito(valor);
    }
    public Cliente getCliente () {
        return cliente;
    }
}
