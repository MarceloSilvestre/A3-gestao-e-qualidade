import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Professor {
    protected String nome;

    public Professor(String nome) {
        this.nome = nome;
    }

    public abstract double calcularSalario();

    public void exibirSalario() {
        System.out.println("O professor " + nome + " recebe um salário de R$ " + calcularSalario() + " por mês");
    }
}

class ProfessorCLT extends Professor {
    private double salarioCLT;

    public ProfessorCLT(String nome, double salarioCLT) {
        super(nome);
        this.salarioCLT = salarioCLT;
    }

    @Override
    public double calcularSalario() {
        return salarioCLT;
    }
}

class ProfessorHorista extends Professor {
    private double horasTrabalhadas;
    private double valorHora;

    public ProfessorHorista(String nome, double horasTrabalhadas, double valorHora) {
        super(nome);
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
    }

    @Override
    public double calcularSalario() {
        return horasTrabalhadas * valorHora;
    }
}

class ProfessorPJ extends Professor {
    private double salarioPJ;

    public ProfessorPJ(String nome, double salarioPJ) {
        super(nome);
        this.salarioPJ = salarioPJ;
    }

    @Override
    public double calcularSalario() {
        return salarioPJ;
    }
}

public class Calculadora {
    public static void main(String[] args) {

        //esse programa serve para informar o valor que um professor ficticio ganha, de acordo com a sua modalidade de trabalho sejam elas horista,clt ou pj
        
        Scanner teclado = new Scanner(System.in);

        try {
            System.out.println("Digite seu nome: ");
            String nome = teclado.next();

            System.out.println("Digite o número equivalente ao tipo de professor você é:");
            System.out.println("1 - Horista");
            System.out.println("2 - CLT");
            System.out.println("3 - PJ");
            int TipoDeProf = teclado.nextInt();

            Professor professor = null;

            switch (TipoDeProf) {
                case 1:
                    System.out.println("Digite o número de horas trabalhadas:");
                    double horas = teclado.nextDouble();

                    System.out.println("Digite o valor recebido por hora de trabalho:");
                    double valorRecebidoHora = teclado.nextDouble();

                    professor = new ProfessorHorista(nome, horas, valorRecebidoHora);
                    break;

                case 2:
                    System.out.println("Digite seu salário mensal:");
                    double salario = teclado.nextDouble();

                    professor = new ProfessorCLT(nome, salario);
                    break;

                case 3:
                    System.out.println("Digite o valor do seu contrato com a instituição:");
                    double contrato = teclado.nextDouble();
                    
                    professor = new ProfessorPJ(nome, contrato);
                    break;

                default:
                    System.out.println("Erro: Tipo de professor inválido.");
                    break;
            }

            professor.exibirSalario();

        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar números corretamente.");
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
            teclado.close();
    }
}