package dev.diegofernando.cleanoo;

import javax.money.MonetaryAmount;
import java.time.Year;
import java.util.Objects;

public class Player {
    static final Year SOCCER_BORN = Year.of(1863);

    private String id;

    private String name;

    private Year start;

    private Year end;

    private Email email;

    private Position position;

    private MonetaryAmount salary;

    public Player(String name, Year start, Year end, Email email, Position position, MonetaryAmount salary) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.salary  = salary;
    }

    @Deprecated
    public Player() {
    }

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", email=" + email +
                ", position=" + position +
                ", salary=" + salary +
                '}';
    }

    public static class PlayerBuilder {
        private String id;

        private String name;

        private Year start;

        private Year end;

        private Email email;

        private Position position;

        private MonetaryAmount salary;

        private PlayerBuilder() {
        }

        public PlayerBuilder withName(String name) {
            this.name = Objects.requireNonNull(name, "name is required");
            return this;
        }

        public PlayerBuilder withStart(Year start) {
            Objects.requireNonNull(start, "start is required");
            if (Year.now().isBefore(start)) {
                throw new IllegalArgumentException("you cannot start in the future");
            }
            if (SOCCER_BORN.isAfter(start)) {
                throw new IllegalArgumentException("Soccer was not born on this time");
            }
            this.start = start;
            return this;
        }

        public PlayerBuilder withEnd(Year end) {
            Objects.requireNonNull(end, "end is required");


            if (start != null && start.isAfter(end)) {
                throw new IllegalArgumentException("the last year of a player must be equal or higher than the start.");
            }

            if (SOCCER_BORN.isAfter(end)) {
                throw new IllegalArgumentException("Soccer was not born on this time");
            }
            this.end = end;
            return this;
        }

        public PlayerBuilder withEmail(Email email) {
            this.email = Objects.requireNonNull(email, "email is required");
            return this;
        }

        public PlayerBuilder withPosition(Position position) {
            this.position = Objects.requireNonNull(position, "position is required");
            return this;
        }

        public PlayerBuilder withSalary(MonetaryAmount salary) {
            Objects.requireNonNull(salary, "salary is required");
            if (salary.isNegativeOrZero()) {
                throw new IllegalArgumentException("A player needs to earn money to play; otherwise, it is illega.");
            }
            this.salary = salary;
            return this;
        }

        public Player build() {
            Objects.requireNonNull(name, "name is required");
            Objects.requireNonNull(start, "start is required");
            Objects.requireNonNull(email, "email is required");
            Objects.requireNonNull(position, "position is required");
            Objects.requireNonNull(salary, "salary is required");

            return new Player(name, start, end, email, position, salary);
        }
    }
}
