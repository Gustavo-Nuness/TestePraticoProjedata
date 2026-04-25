package org.example.dao;

import org.example.model.Funcionario;
import org.example.util.DateUtils;
import org.example.util.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

public class FuncionarioDAO {

    private static FuncionarioDAO instance;
    private List<Funcionario> funcionarios;
    private DateUtils dateUtils;

    private FuncionarioDAO() {
        dateUtils = new DateUtils("dd-MM-yyyy");
        this.funcionarios = inicializarLista();
    }

    /*
    Uma implementação bem simples do padrão de projeto Singlenton,
    para garantir que apenas uma instância da lista de funcionário exista,
    durante a execução do código.
    */
    public static FuncionarioDAO getInstance() {
        if (instance == null) {
            instance = new FuncionarioDAO();
        }
        return instance;
    }

    /* 3.1 */
    private List<Funcionario> inicializarLista() {

        List<Funcionario> funcionarios = new ArrayList<>(
                List.of(
                        new Funcionario(
                                "Maria", LocalDate.parse("18-10-2000", dateUtils.getFormatter()),
                                new BigDecimal("2009.44"), "Operador"
                        ),

                        new Funcionario(
                                "João", LocalDate.parse("12-05-1990", dateUtils.getFormatter()),
                                new BigDecimal("2284.38"), "Operador"
                        ),

                        new Funcionario(
                                "Caio", LocalDate.parse("02-05-1961", dateUtils.getFormatter()),
                                new BigDecimal("9836.14"), "Coordenador"
                        ),

                        new Funcionario(
                                "Miguel", LocalDate.parse("14-10-1988", dateUtils.getFormatter()),
                                new BigDecimal("19119.88"), "Diretor"
                        ),

                        new Funcionario(
                                "Alice", LocalDate.parse("05-01-1995", dateUtils.getFormatter()),
                                new BigDecimal("2234.68"), "Recepcionista"
                        ),

                        new Funcionario(
                                "Heitor", LocalDate.parse("19-11-1999", dateUtils.getFormatter()),
                                new BigDecimal("1582.72"), "Operador"
                        ),

                        new Funcionario(
                                "Arthur", LocalDate.parse("31-03-1993", dateUtils.getFormatter()),
                                new BigDecimal("4071.84"), "Contador"
                        ),

                        new Funcionario(
                                "Laura", LocalDate.parse("08-07-1994", dateUtils.getFormatter()),
                                new BigDecimal("3017.45"), "Gerente"
                        ),

                        new Funcionario(
                                "Heloísa", LocalDate.parse("24-05-2003", dateUtils.getFormatter()),
                                new BigDecimal("1606.85"), "Eletricista"
                        ),

                        new Funcionario(
                                "Helena", LocalDate.parse("02-09-1996", dateUtils.getFormatter()),
                                new BigDecimal("2799.93"), "Gerente")


                )
        );

        return funcionarios;
    }

    public Funcionario adicionarFuncionario(Funcionario funcionario) {

        funcionarios.add(funcionario);

        return funcionario;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public List<Funcionario> deleteEmployeeByName(String name) {
        List<Funcionario> removedEmployees = new ArrayList<>();

        for (int i = 0; i <= funcionarios.size() - 1; i++) {
            if (funcionarios.get(i).getNome().equalsIgnoreCase(name)) {
                removedEmployees.add(funcionarios.get(i));
                funcionarios.remove(funcionarios.get(i));
            }
        }
        return removedEmployees;
    }

    public void increaseSalary(BigDecimal increasePercentage) {
        /*
            factor é o número que será multiplicado pelo salário atual do funcionário para aumentá-lo;
            Por exemplo: factor(1.20) * salarioAtual;

         */
        BigDecimal factor = increasePercentage
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(1));

        for (Funcionario f : funcionarios) {
            f.setSalario(f.getSalario().multiply(factor));
        }
    }

    public Map<String, List<Funcionario>> groupEmployeesByRole() {

        Map<String, List<Funcionario>> employeesGroupByRoles = new HashMap<>();

        for (Funcionario f : funcionarios) {
            String role = f.getFuncao();

            if (!employeesGroupByRoles.containsKey(role)) {

                List<Funcionario> employees = new ArrayList<>();
                employees.add(f);
                employeesGroupByRoles.put(role, employees);

            } else {
                employeesGroupByRoles.get(role).add(f);
            }
        }
        return employeesGroupByRoles;
    }

    public List<Funcionario> getEmployeesByMonth(int month) {

        List<Funcionario> employeesBornInTheSameMonth = new ArrayList<>();

        for (Funcionario f : funcionarios) {
            if (f.getDataNascimento().getMonthValue() == month) {
                employeesBornInTheSameMonth.add(f);
            }
        }

        return employeesBornInTheSameMonth;
    }

    public Funcionario getOldestEmployee() {
        Funcionario oldestEmployee = null;

        for (int i = 0; i < funcionarios.size() - 1; i++) {
            if (oldestEmployee == null) {
                Funcionario currentEmployee = funcionarios.get(i);
                Funcionario nextEmployee = funcionarios.get(i + 1);
                if (currentEmployee
                        .getDataNascimento()
                        .isBefore(nextEmployee.getDataNascimento())
                ) {
                    oldestEmployee = currentEmployee;
                } else {
                    oldestEmployee = nextEmployee;
                }
            } else {
                Funcionario nextEmployee = funcionarios.get(i + 1);

                if (!oldestEmployee
                        .getDataNascimento()
                        .isBefore(nextEmployee.getDataNascimento())
                ) {
                    oldestEmployee = nextEmployee;

                }
            }
        }
        return oldestEmployee;
    }

    public String getSumOfAllSalaries() {
        BigDecimal totalSalaries = new BigDecimal("0.00");

        for (Funcionario employee : funcionarios) {
            totalSalaries = totalSalaries.add(employee.getSalario());
        }
        NumberUtils numberUtils = new NumberUtils("###,##0.00");

        return numberUtils.formatNumber(totalSalaries);
    }

    public Map<String, String> getNumbersOfMinimumWages() {
        HashMap<String, String> mapOfNumbersOfMinimumWages = new HashMap<>();

        for (Funcionario employee : funcionarios) {
            mapOfNumbersOfMinimumWages.put(employee.toString(), employee.getTheNumberOfMinimumWages());
        }
        return mapOfNumbersOfMinimumWages;
    }

    public List<Funcionario> getEmployeesInAlphabeticalOrder() {

        List<Funcionario> auxList = new ArrayList<>(funcionarios);

        auxList.sort(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER));

        return auxList;
    }
}
