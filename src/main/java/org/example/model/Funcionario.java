package org.example.model;

import org.example.util.DateUtils;
import org.example.util.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento,
                       BigDecimal salario, String funcao) {
        super();
        setNome(nome);
        setDataNascimento(dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        DateUtils dateUtils = new DateUtils("dd/MM/yyyy");
        NumberUtils numberUtils = new NumberUtils("###,##0.00");
        String funcionarioToString =
                getNome() + " | "
                        + dateUtils.formatDate(getDataNascimento())  + " | "
                        + numberUtils.formatNumber(getSalario())  + " | "
                        + getFuncao() + " | ";

        return funcionarioToString;
    }

    public int getAge(){
        return Period.between(getDataNascimento(), LocalDate.now()).getYears();
    }

    public String getTheNumberOfMinimumWages() {
        BigDecimal minimumWage = new BigDecimal("1212.00");
        NumberUtils numberUtils = new NumberUtils("###,##0.00");

        BigDecimal numberOfMinimumWages = salario.divide(minimumWage, 4, RoundingMode.HALF_UP);

        return numberUtils.formatNumber(numberOfMinimumWages);
    }
}
