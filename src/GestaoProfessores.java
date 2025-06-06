import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class Professor {
    protected String nome;

    public Professor(String nome) {
        this.nome = nome;
    }

    public abstract double calcularSalario();

    public void exibirSalario() {
        System.out.println("Professor(a) " + nome + " recebe um salário de R$ " + calcularSalario() + " por mês");
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

public class GestaoProfessores {
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Connection conexao = null;

        try {
            limparTela();
            System.out.println("Bem-vindo ao sistema de gerenciamento!"
            + "\nPor favor, insira a opção que deseja para avançar."
            + "\n"
            + "\n1 - Exibir todos os professores"
            + "\n2 - Procurar professor por número"
            + "\n3 - Inserir novo professor"
            + "\n4 - Calcular salário de um professor"
            + "\n5 - Atualizar professor"
            + "\n6 - Apagar professor"
            + "\n7 - Atualizar salário de um professor"
            + "\n8 - Apagar salário de um professor"
            + "\n9 - Sair do programa"
            + "\nOpção: ");
            int opcao = teclado.nextInt();
            teclado.nextLine();

            limparTela();
            switch (opcao) {
                case 1:
                    exibirTodosProfessores();
                    break;
                case 2:
                    buscarProfessorPorNumero(teclado);
                    break;
                case 3:
                    inserirNovoProfessor(teclado);
                    break;
                case 4:
                    calcularSalarioProfessor(teclado);
                    break;
                case 5:
                    atualizarProfessor(teclado);
                    break;
                case 6:
                    apagarProfessor(teclado);
                    break;
                case 7:
                    atualizarSalario(teclado);
                    break;
                case 8:
                    apagarSalario(teclado);
                    break;
                case 9:
                    System.out.println("Até logo!");
                    teclado.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
            if (teclado != null) {
                teclado.close();
            }
        }
    }

    // Métodos auxiliares para cada operação

    private static void exibirTodosProfessores() {
        try (Connection conexao = Conexao.obterConexao();
             ResultSet rsProfessores = conexao.createStatement().executeQuery(
                "select prof_numid, prof_nome, tp_descricao, prof_dtatualizacao, sal_dtatualizacao, " +
                "case when tp_id = 1 then sal_horacalc when tp_id = 2 then sal_valormensal when tp_id = 3 then sal_valorcontrato else 0 end as salario " +
                "from professores left join tiposprofessores on tp_id = prof_tpid left join salarios on sal_profid = prof_id order by prof_numid asc")) {
            while (rsProfessores.next()) {
                System.out.println("Número/ID: " + rsProfessores.getInt("prof_numid") +
                        " | Professor: " + rsProfessores.getString("prof_nome") +
                        " | Tipo: " + rsProfessores.getString("tp_descricao") +
                        " | Salário: " + rsProfessores.getDouble("salario"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    private static void buscarProfessorPorNumero(Scanner teclado) {
        System.out.println("Digite o número/ID do professor que deseja exibir:");
        int numId = teclado.nextInt();
        teclado.nextLine();
        limparTela();
        try (Connection conexao = Conexao.obterConexao();
             java.sql.PreparedStatement stmt = conexao.prepareStatement(
                "select prof_numid, prof_nome, tp_descricao, prof_dtatualizacao, sal_dtatualizacao, " +
                "case when tp_id = 1 then sal_horacalc when tp_id = 2 then sal_valormensal when tp_id = 3 then sal_valorcontrato else 0 end as salario " +
                "from professores left join tiposprofessores on tp_id = prof_tpid left join salarios on sal_profid = prof_id where prof_numid = ?")) {
            stmt.setInt(1, numId);
            ResultSet rsProfessor = stmt.executeQuery();
            if (rsProfessor.next()) {
                System.out.println("Número/ID: " + rsProfessor.getInt("prof_numid") +
                        "\nProfessor: " + rsProfessor.getString("prof_nome") +
                        "\nTipo: " + rsProfessor.getString("tp_descricao") +
                        "\nSalário: " + rsProfessor.getDouble("salario") +
                        "\nÚlt. Atualização Professor: " + rsProfessor.getString("prof_dtatualizacao") +
                        "\nÚlt. Atualização Salário: " + rsProfessor.getString("sal_dtatualizacao"));
            } else {
                System.out.println("Professor não encontrado para o número/ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    private static void inserirNovoProfessor(Scanner teclado) {
        try {
            System.out.println("Digite o nome do professor:");
            String nome = teclado.nextLine();
            limparTela();

            System.out.println("Selecione o tipo de professor:");
            System.out.println("1 - Horista");
            System.out.println("2 - CLT");
            System.out.println("3 - PJ\n");
            System.out.print("Opção: ");
            int tipo = teclado.nextInt();
            limparTela();

            try (Connection conexao = Conexao.obterConexao();
                 java.sql.PreparedStatement stmt = conexao.prepareStatement("INSERT INTO professores (prof_id, prof_nome, prof_tpid, prof_dtinsercao, prof_dtatualizacao) VALUES (UUID(), ?, ?, NOW(), NOW())")) {
                stmt.setString(1, nome);
                stmt.setInt(2, tipo);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Professor inserido com sucesso!");
                } else {
                    System.out.println("Falha ao inserir professor.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir professor: " + e.getMessage());
        }
    }

    private static void calcularSalarioProfessor(Scanner teclado) {
        try (Connection conexao = Conexao.obterConexao()) {
            System.out.println("Digite o ID do professor que deseja calcular o salário:");
            int idProf = teclado.nextInt();
            teclado.nextLine();
            limparTela();

            // Busca o nome e o tipo do professor
            String sql = "SELECT p.prof_id, p.prof_nome, t.tp_id FROM professores p JOIN tiposprofessores t ON p.prof_tpid = t.tp_id WHERE p.prof_numid = ?";
            try (java.sql.PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, idProf);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String profId = rs.getString("prof_id");
                    String nome = rs.getString("prof_nome");
                    int TipoDeProf = rs.getInt("tp_id");

                    // Verifica se já existe salário cadastrado para o professor
                    String verificaSalarioSql = "select * from salarios join professores on prof_id = sal_profid where sal_profid = ?";
                    try (java.sql.PreparedStatement verificaStmt = conexao.prepareStatement(verificaSalarioSql)) {
                        verificaStmt.setString(1, profId);
                        ResultSet rsSalario = verificaStmt.executeQuery();
                        if (rsSalario.next()) {
                            Professor professor = null;
                            switch (TipoDeProf) {
                                case 1:
                                    professor = new ProfessorHorista(nome, rsSalario.getDouble("sal_hora"), rsSalario.getDouble("sal_valorhora"));
                                    break;
                                case 2:
                                    professor = new ProfessorCLT(nome, rsSalario.getDouble("sal_valormensal"));
                                    break;
                                case 3:
                                    professor = new ProfessorPJ(nome, rsSalario.getDouble("sal_valorcontrato"));
                                    break;
                            }
                            System.out.println("Professor(a) já possui um salário cadastrado:");
                            if (professor != null) {
                                System.out.println(nome + " recebe um salário de R$ " + professor.calcularSalario() + " por mês");
                            }
                            return;
                        }
                    }
                
                    Professor professor = null;

                    // Variáveis para inserção
                    Double sal_hora = null, sal_valorhora = null, sal_horacalc = null, sal_valormensal = null, sal_valorcontrato = null;

                    switch (TipoDeProf) {
                        case 1:
                            System.out.println("Digite o número de horas trabalhadas:");
                            double horas = teclado.nextDouble();
                            System.out.println("Digite o valor recebido por hora de trabalho:");
                            double valorRecebidoHora = teclado.nextDouble();
                            professor = new ProfessorHorista(nome, horas, valorRecebidoHora);
                            sal_hora = horas;
                            sal_valorhora = valorRecebidoHora; // <-- Aqui armazena o valor recebido por hora
                            sal_horacalc = professor.calcularSalario();
                            limparTela();
                            break;
                        case 2:
                            System.out.println("Digite seu salário mensal:");
                            double salario = teclado.nextDouble();
                            professor = new ProfessorCLT(nome, salario);
                            sal_valormensal = salario;
                            limparTela();
                            break;
                        case 3:
                            System.out.println("Digite o valor do seu contrato com a instituição:");
                            double contrato = teclado.nextDouble();
                            professor = new ProfessorPJ(nome, contrato);
                            sal_valorcontrato = contrato;
                            limparTela();
                            break;
                        default:
                            System.out.println("Tipo de professor inválido.");
                            break;
                    }

                    if (professor != null) {
                        professor.exibirSalario();

                        // Monta o SQL de inserção
                        String insertSql = "insert into salarios (sal_profid, sal_hora, sal_valorhora, sal_horacalc, sal_valormensal, sal_valorcontrato, sal_dtinsercao, sal_dtatualizacao) values (?, ?, ?, ?, ?, ?, NOW(), NOW())";
                        try (java.sql.PreparedStatement insertStmt = conexao.prepareStatement(insertSql)) {
                            insertStmt.setString(1, profId);

                            // sal_hora
                            if (TipoDeProf == 1) insertStmt.setDouble(2, sal_hora);
                            else insertStmt.setNull(2, java.sql.Types.DOUBLE);

                            // sal_valorhora
                            if (TipoDeProf == 1) insertStmt.setDouble(3, sal_valorhora); // <-- Aqui insere o valor recebido por hora
                            else insertStmt.setNull(3, java.sql.Types.DOUBLE);

                            // sal_horacalc
                            if (TipoDeProf == 1) insertStmt.setDouble(4, sal_horacalc);
                            else insertStmt.setNull(4, java.sql.Types.DOUBLE);

                            // sal_valormensal
                            if (TipoDeProf == 2) insertStmt.setDouble(5, sal_valormensal);
                            else insertStmt.setNull(5, java.sql.Types.DOUBLE);

                            // sal_valorcontrato
                            if (TipoDeProf == 3) insertStmt.setDouble(6, sal_valorcontrato);
                            else insertStmt.setNull(6, java.sql.Types.DOUBLE);

                            insertStmt.executeUpdate();
                            // System.out.println("Salário inserido com sucesso na tabela de salários!");
                        }
                    }
                } else {
                    System.out.println("Professor(a) não encontrado para o ID informado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    private static void atualizarProfessor(Scanner teclado) {
        System.out.println("Digite o número/ID do professor que deseja atualizar:");
        int numIdAtualizar = teclado.nextInt();
        teclado.nextLine();
        limparTela();

        try (Connection conexao = Conexao.obterConexao();
             java.sql.PreparedStatement stmtBusca = conexao.prepareStatement("SELECT p.prof_id, p.prof_nome, t.tp_id, t.tp_descricao FROM professores p JOIN tiposprofessores t ON p.prof_tpid = t.tp_id WHERE p.prof_numid = ?")) {
            stmtBusca.setInt(1, numIdAtualizar);
            ResultSet rs = stmtBusca.executeQuery();
            if (rs.next()) {
                String profId = rs.getString("prof_id");
                String nomeAtual = rs.getString("prof_nome");
                int tipoAtual = rs.getInt("tp_id");
                String tipoDescAtual = rs.getString("tp_descricao");

                System.out.println("Nome atual: " + nomeAtual);
                System.out.print("Digite o novo nome (ou pressione Enter para manter o atual): ");
                String novoNome = teclado.nextLine();
                if (novoNome.trim().isEmpty()) {
                    novoNome = nomeAtual;
                }
                limparTela();

                System.out.println("Tipo atual: " + tipoDescAtual + " (" + tipoAtual + ")");
                System.out.println("Selecione o novo tipo:");
                if (tipoAtual != 1) System.out.println("1 - HORISTA");
                if (tipoAtual != 2) System.out.println("2 - CLT");
                if (tipoAtual != 3) System.out.println("3 - PJ");
                System.out.print("Opção: ");
                String tipoStr = teclado.nextLine().trim();
                limparTela();
                int novoTipo = tipoAtual;
                if (!tipoStr.isEmpty()) {
                    try {
                        int tipoDigitado = Integer.parseInt(tipoStr);
                        if (tipoDigitado == tipoAtual) {
                            limparTela();
                            System.out.println("Tipo não será atualizado pois já é o tipo atual.");
                        } else if (tipoDigitado >= 1 && tipoDigitado <= 3) {
                            novoTipo = tipoDigitado;
                        } else {
                            limparTela();
                            System.out.println("Tipo inválido. Mantendo o tipo atual.");
                        }
                    } catch (NumberFormatException ex) {
                        limparTela();
                        System.out.println("Entrada inválida. Mantendo o tipo atual.");
                    }
                }

                try (java.sql.PreparedStatement stmtUpdate = conexao.prepareStatement("UPDATE professores SET prof_nome = ?, prof_tpid = ?, prof_dtatualizacao = NOW() WHERE prof_id = ?")) {
                    stmtUpdate.setString(1, novoNome);
                    stmtUpdate.setInt(2, novoTipo);
                    stmtUpdate.setString(3, profId);
                    int linhasAfetadas = stmtUpdate.executeUpdate();
                    if (linhasAfetadas > 0) {
                        System.out.println("Professor atualizado com sucesso!");
                    } else {
                        System.out.println("Falha ao atualizar professor.");
                    }
                }
            } else {
                limparTela();
                System.out.println("Professor não encontrado para o número/ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    private static void apagarProfessor(Scanner teclado) {
        System.out.println("Digite o número/ID do professor que deseja apagar:");
        int numIdDelete = teclado.nextInt();
        teclado.nextLine();
        limparTela();

        try (Connection conexao = Conexao.obterConexao();
             java.sql.PreparedStatement stmtBusca = conexao.prepareStatement("SELECT prof_id, prof_nome FROM professores WHERE prof_numid = ?")) {
            stmtBusca.setInt(1, numIdDelete);
            ResultSet rs = stmtBusca.executeQuery();
            if (rs.next()) {
                String profId = rs.getString("prof_id");
                String nome = rs.getString("prof_nome");

                // Deleta salários do professor, se existirem
                try (java.sql.PreparedStatement stmtDelSal = conexao.prepareStatement("DELETE FROM salarios WHERE sal_profid = ?")) {
                    stmtDelSal.setString(1, profId);
                    stmtDelSal.executeUpdate();
                }

                // Deleta o professor
                try (java.sql.PreparedStatement stmtDelProf = conexao.prepareStatement("DELETE FROM professores WHERE prof_id = ?")) {
                    stmtDelProf.setString(1, profId);
                    int linhasAfetadas = stmtDelProf.executeUpdate();
                    if (linhasAfetadas > 0) {
                        System.out.println("Professor(a) '" + nome + "' foi apagado(a) com sucesso!");
                    } else {
                        System.out.println("Falha ao apagar professor.");
                    }
                }
            } else {
                System.out.println("Professor não encontrado para o número/ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    private static void atualizarSalario(Scanner teclado) {
        System.out.println("Digite o número/ID do professor que deseja atualizar o salário:");
        int numIdSalario = teclado.nextInt();
        teclado.nextLine(); 
        limparTela();

        try (Connection conexao = Conexao.obterConexao();
             java.sql.PreparedStatement stmtBusca = conexao.prepareStatement("SELECT p.prof_id, p.prof_nome, t.tp_id, t.tp_descricao FROM professores p JOIN tiposprofessores t ON p.prof_tpid = t.tp_id WHERE p.prof_numid = ?")) {
            stmtBusca.setInt(1, numIdSalario);
            ResultSet rs = stmtBusca.executeQuery();
            if (rs.next()) {
                String profId = rs.getString("prof_id");
                String nome = rs.getString("prof_nome");
                int tipo = rs.getInt("tp_id");
                String tipoDesc = rs.getString("tp_descricao");

                // Busca salário atual
                try (java.sql.PreparedStatement stmtSal = conexao.prepareStatement("SELECT * FROM salarios WHERE sal_profid = ?")) {
                    stmtSal.setString(1, profId);
                    ResultSet rsSal = stmtSal.executeQuery();
                    if (rsSal.next()) {
                        System.out.println("Salário atual de " + nome + " (" + tipoDesc + "):");
                        switch (tipo) {
                            case 1:
                                System.out.println("Horas trabalhadas: " + rsSal.getDouble("sal_hora"));
                                System.out.println("Valor por hora: R$ " + rsSal.getDouble("sal_valorhora"));
                                System.out.println("Salário calculado: R$ " + rsSal.getDouble("sal_horacalc"));
                                System.out.println("\nDigite o novo número de horas trabalhadas:");
                                double novasHoras = teclado.nextDouble();
                                System.out.println("Digite o novo valor recebido por hora:");
                                double novoValorHora = teclado.nextDouble();
                                double novoSalarioHorista = novasHoras * novoValorHora;
                                try (java.sql.PreparedStatement stmtUpdate = conexao.prepareStatement("UPDATE salarios SET sal_hora = ?, sal_valorhora = ?, sal_horacalc = ?, sal_dtatualizacao = NOW() WHERE sal_profid = ?")) {
                                    stmtUpdate.setDouble(1, novasHoras);
                                    stmtUpdate.setDouble(2, novoValorHora);
                                    stmtUpdate.setDouble(3, novoSalarioHorista);
                                    stmtUpdate.setString(4, profId);
                                    stmtUpdate.executeUpdate();
                                }
                                break;
                            case 2:
                                System.out.println("Salário mensal atual: R$ " + rsSal.getDouble("sal_valormensal"));
                                System.out.println("\nDigite o novo valor mensal:");
                                double novoMensal = teclado.nextDouble();
                                try (java.sql.PreparedStatement stmtUpdate = conexao.prepareStatement("UPDATE salarios SET sal_valormensal = ?, sal_dtatualizacao = NOW() WHERE sal_profid = ?")) {
                                    stmtUpdate.setDouble(1, novoMensal);
                                    stmtUpdate.setString(2, profId);
                                    stmtUpdate.executeUpdate();
                                }
                                break;
                            case 3:
                                System.out.println("Valor do contrato atual: R$ " + rsSal.getDouble("sal_valorcontrato"));
                                System.out.println("\nDigite o novo valor do contrato:");
                                double novoContrato = teclado.nextDouble();
                                try (java.sql.PreparedStatement stmtUpdate = conexao.prepareStatement("UPDATE salarios SET sal_valorcontrato = ?, sal_dtatualizacao = NOW() WHERE sal_profid = ?")) {
                                    stmtUpdate.setDouble(1, novoContrato);
                                    stmtUpdate.setString(2, profId);
                                    stmtUpdate.executeUpdate();
                                }
                                break;
                        }
                        limparTela();
                        System.out.println("Salário atualizado com sucesso!");
                    } else {
                        System.out.println("Professor não possui salário cadastrado.");
                    }
                }
            } else {
                System.out.println("Professor não encontrado para o número/ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }

    private static void apagarSalario(Scanner teclado) {
        System.out.println("Digite o número/ID do professor que deseja remover o salário:");
        int numIdRemoveSalario = teclado.nextInt();
        teclado.nextLine();
        limparTela();

        try (Connection conexao = Conexao.obterConexao();
             java.sql.PreparedStatement stmtBusca = conexao.prepareStatement("SELECT prof_id, prof_nome FROM professores WHERE prof_numid = ?")) {
            stmtBusca.setInt(1, numIdRemoveSalario);
            ResultSet rs = stmtBusca.executeQuery();
            if (rs.next()) {
                String profId = rs.getString("prof_id");
                String nome = rs.getString("prof_nome");

                // Deleta salário do professor
                try (java.sql.PreparedStatement stmtDelSal = conexao.prepareStatement("DELETE FROM salarios WHERE sal_profid = ?")) {
                    stmtDelSal.setString(1, profId);
                    int linhasAfetadas = stmtDelSal.executeUpdate();
                    if (linhasAfetadas > 0) {
                        System.out.println("Salário do professor(a) '" + nome + "' removido com sucesso!");
                    } else {
                        System.out.println("Esse professor não possui salário cadastrado.");
                    }
                }
            } else {
                System.out.println("Professor não encontrado para o número/ID informado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco: " + e.getMessage());
        }
    }
}