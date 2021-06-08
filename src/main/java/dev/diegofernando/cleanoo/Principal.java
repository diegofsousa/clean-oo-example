package dev.diegofernando.cleanoo;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Principal {
    public static void main(String[] args) {
        CurrencyUnit usd = Monetary.getCurrency(Locale.US);
        MonetaryAmount salary = Money.of(1_000_000, usd);
        Email email = Email.of("vitinho@flamengo.com.br");
        Year start = Year.now();
        Year end = start.plus(5, ChronoUnit.YEARS);

        Player vitinho = Player.builder().withName("Vitinho 11")
                .withEmail(email)
                .withSalary(salary)
                .withStart(start)
                .withEnd(end)
                .withPosition(Position.FORWARD)
                .build();

        Team flamengo = Team.of("Flamengo");
        flamengo.add(vitinho);

        System.out.println(flamengo.getPlayers());

    }
}
