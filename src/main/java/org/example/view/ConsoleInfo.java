package org.example.view;

import org.example.controller.InputHandler;
import org.example.model.Funcionario;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class ConsoleInfo {
    InputHandler inputHandler;

    public ConsoleInfo() {

    }

    public void startMainRoutineLoop() {
        Scanner input = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        input.useLocale(Locale.of("pt", "BR"));
        inputHandler = new InputHandler(this);

        int userInput = 1;

        do {
            try {
                printMainMenu();
                userInput = Integer.parseInt(input.nextLine());

                inputHandler.handleWithUserOption(userInput, input);

            } catch (Exception ex) {
                printErr("Caractere inválido, " +
                        "por favor digite um caractere válido!");

            }

        } while (userInput != 0);
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("--------- Menu de navegação do teste prático da Projedata ---------");
        System.out.println("1 - Exibir a lista de funcionários (3.3)");
        System.out.println("2 - Excluir um funcionário pelo nome");
        System.out.println("3 - Excluir o João (3.2)");
        System.out.println("4 - Aumentar o salário dos funcionários");
        System.out.println("5 - Agrupar funcionários por função (3.5 e 3.6)");
        System.out.println("6 - Exibir os funcionários nascidos no mês 10 e 12 (3.8)");
        System.out.println("7 - Exibir o funcionário mais velho (3.9)");
        System.out.println("8 - Exibir a lista de funcionários em ordem alfabética (3.10)");
        System.out.println("9 - Exibir o a soma de todos os salários dos funcionários (3.11)");
        System.out.println("10 - Exibir quantos salários mínimos ganha cada funcionário (3.12)");
        System.out.println("0 - Finalizar a execução");
    }

    public void printErr(String erro) {
        System.err.println("\n" + erro);
    }

    public void showEmployees(List<Funcionario> funcionarios) {

        System.out.println(" Nome | Data de Nascimento | Salário (R$) | Função |\n");

        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.toString());
        }
    }

    public String showRemoveMenuAndGetEmployeeName(Scanner input) {
        System.out.println("Digite o nome dos funcionários que serão removidos: (ou digite zero para voltar)");
        String EmployeeName = input.nextLine();
        return EmployeeName;
    }


    public void showRemovedEmployees(List<Funcionario> employees) {
        System.out.println();
        if (!employees.isEmpty()) {
            for (Funcionario e : employees) {
                System.out.println(employees.size() + " Funcionário(s) removido(s):");
                System.out.println(e.toString());
            }
        } else {
            System.out.println("Nenhum funcionário foi removido, " +
                    "porque não há um funcionário com o nome passado!");
        }

    }

    public void showIncreaseSalaryMenu(Scanner input) {
        System.out.println("Digite uma porcentagem de aumento de " +
                "salário para todos os funcionários:");
        try {
            BigDecimal increasePercentage = new BigDecimal(
                    input.nextLine().replace(",", ".")
            );

            if (inputHandler != null) {
                inputHandler.increaseSalary(increasePercentage);
                System.out.println();
                System.out.println("O salário de todos os funcionários aumentou em "
                        + increasePercentage +
                        "%");
            } else {
                printErr("Houve um erro inesperado, que impossibilitou o aumento do salário dos funcionários");
            }
        } catch (Exception ex) {
            System.out.println("Um número inválido foi digitado!");
        }
    }

    public void showEmployeesGroupedByRoles(Map<String, List<Funcionario>> employeesGroupedByRoles) {

        for (String key : employeesGroupedByRoles.keySet()) {
            System.out.println(key + "(s)" + ":");
            System.out.println();
            List<Funcionario> funcionarios = employeesGroupedByRoles.get(key);

            for (Funcionario f : funcionarios) {
                System.out.println(f.toString());
            }
            System.out.println();
        }
    }

    public void showEmployeesGroupedByMonths(Map<Integer, List<Funcionario>> employeesGroupedByMonth) {

        for (Integer month : employeesGroupedByMonth.keySet()) {
            System.out.println();
            System.out.println("Funcionários nascidos no " + month + "º mês do ano:");
            System.out.println();

            List<Funcionario> employees = employeesGroupedByMonth.get(month);
            if (!employees.isEmpty()) {
                for (Funcionario employee : employees) {
                    System.out.println(employee.toString());
                }
            } else {
                System.out.println("Nenhum funcionário nasceu nesse mês");
            }

        }
    }

    public void showOldestEmployee(Funcionario employee) {
        System.out.println("Funcionário com maior idade:");
        System.out.println(employee.getNome() + " | " + employee.getAge() + " Ano(s)");
    }

    public void showSumOfAllSalaries(String sumOfAllSalaries) {
        System.out.println();
        System.out.println("O Valor total de todos os salários é:");
        System.out.println(sumOfAllSalaries);
        System.out.println();
    }

    public void showTheNumbersOfMinimumWages(Map<String, String> numbersOfMinimumWages) {

        System.out.println();

        for (String employee : numbersOfMinimumWages.keySet()) {
            System.out.println();
            System.out.println(employee);
            System.out.println("Recebe "+numbersOfMinimumWages.get(employee)+" salário(s)");
            System.out.println();
        }
    }
}
