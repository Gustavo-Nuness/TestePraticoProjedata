package org.example.controller;

import org.example.dao.FuncionarioDAO;
import org.example.model.Funcionario;
import org.example.view.ConsoleInfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputHandler {

    private final int IMPRIMIR_LISTA = 1;
    private final int EXCLUIR_FUNCIONARIOS_PELO_NOME = 2;
    private final int EXCLUIR_JOAO = 3;
    private final int AUMENTAR_SALARIO = 4;
    private final int AGRUPAR_FUNCIONARIOS_POR_FUNCAO = 5;
    private final int PEGAR_FUNCIONARIOS_DO_MES_10_E_12 = 6;
    private final int PEGAR_O_FUNCIONARIO_MAIS_VELHO = 7;
    private final int EXIBIR_FUNCIONARIOS_EM_ORDEM_AFALBETICA = 8;
    private final int EXIBIR_A_SOMA_DE_TODOS_OS_SALARIOS = 9;
    private final int EXIBIR_A_QUANTIDADE_DE_SALARIOS_MINIMOS = 10;


    private ConsoleInfo consoleInfo;
    FuncionarioDAO funcionarioRepository;


    public void handleWithUserOption(int userOption, Scanner in) {
        switch (userOption) {
            case IMPRIMIR_LISTA:
                printList();
                break;
            case EXCLUIR_FUNCIONARIOS_PELO_NOME:
                removeEmployeesByName(consoleInfo.showRemoveMenuAndGetEmployeeName(in));
                break;
            case EXCLUIR_JOAO:
                removeEmployeesByName("João");
                break;
            case AUMENTAR_SALARIO:
                consoleInfo.showIncreaseSalaryMenu(in);
                break;
            case AGRUPAR_FUNCIONARIOS_POR_FUNCAO:
                consoleInfo.showEmployeesGroupedByRoles(groupEmployeesByRole());
                break;
            case PEGAR_FUNCIONARIOS_DO_MES_10_E_12:
                int monthRequired10 = 10;
                int monthRequired12 = 12;

                Map<Integer, List<Funcionario>> employeesGroupedByMonth = new HashMap<>();

                List<Funcionario> employeesGroupByOctober = getEmployeesByMonth(monthRequired10);
                List<Funcionario> employeesGroupByDecember = getEmployeesByMonth(monthRequired12);

                employeesGroupedByMonth.put(monthRequired10, getEmployeesByMonth(monthRequired10));
                employeesGroupedByMonth.put(monthRequired12, getEmployeesByMonth(monthRequired12));

                consoleInfo.showEmployeesGroupedByMonths(employeesGroupedByMonth);
                break;
            case PEGAR_O_FUNCIONARIO_MAIS_VELHO:
                Funcionario employee = getOldestEmployee();
                consoleInfo.showOldestEmployee(employee);
                break;

            case EXIBIR_FUNCIONARIOS_EM_ORDEM_AFALBETICA:
                consoleInfo.showEmployees(getEmployeesInAlphabeticalOrder());
                break;

            case EXIBIR_A_SOMA_DE_TODOS_OS_SALARIOS:
                consoleInfo.showSumOfAllSalaries(getSumOfAllSalaries());
                break;
            case EXIBIR_A_QUANTIDADE_DE_SALARIOS_MINIMOS:
                consoleInfo.showTheNumbersOfMinimumWages(getTheNumbersOfMinimumWages());
                break;
        }
    }

    public InputHandler(ConsoleInfo consoleInfo) {
        this.consoleInfo = consoleInfo;
        funcionarioRepository = FuncionarioDAO.getInstance();

    }

    public void printList() {

        consoleInfo.showEmployees(funcionarioRepository.getFuncionarios());
    }

    public void removeEmployeesByName(String employeeName) {
        List<Funcionario> removedEmployees = funcionarioRepository.deleteEmployeeByName(employeeName);
        consoleInfo.showRemovedEmployees(removedEmployees);

    }

    public void increaseSalary(BigDecimal increasePercentage) {

        funcionarioRepository.increaseSalary(increasePercentage);
    }

    public Map<String, List<Funcionario>> groupEmployeesByRole() {
        return funcionarioRepository.groupEmployeesByRole();
    }

    public List<Funcionario> getEmployeesByMonth(int month) {
        return funcionarioRepository.getEmployeesByMonth(month);
    }

    public Funcionario getOldestEmployee() {
        return funcionarioRepository.getOldestEmployee();
    }

    public String getSumOfAllSalaries() {
        return funcionarioRepository.getSumOfAllSalaries();
    }
    public Map<String, String> getTheNumbersOfMinimumWages(){
        return funcionarioRepository.getNumbersOfMinimumWages();
    }
    public List<Funcionario> getEmployeesInAlphabeticalOrder() {
        return funcionarioRepository.getEmployeesInAlphabeticalOrder();
    }
}
