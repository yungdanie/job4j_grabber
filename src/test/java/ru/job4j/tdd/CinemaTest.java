package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CinemaTest {

    @Test @Ignore
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, Calendar.NOVEMBER, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test @Ignore
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test @Ignore
    public void whenAddSession() {
        Cinema cinema = new Cinema3D();
        Session exp = new Session3D();
        cinema.add(exp);
        assertEquals(List.of(exp), is(cinema.find(session -> true)));
    }

    @Test (expected = IllegalArgumentException.class) @Ignore
    public void whenWrongDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2034, Calendar.NOVEMBER, -1, 12, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        throw new IllegalArgumentException();
    }

    @Test (expected = IllegalArgumentException.class) @Ignore
    public void whenWrongSeat() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.NOVEMBER, 1, 1, 01);
        Ticket ticket = cinema.buy(account, -2, -5, date);
        throw new IllegalArgumentException();
    }

    @Test (expected = IllegalArgumentException.class) @Ignore
    public void whenSeatAlreadyBought() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.NOVEMBER, 1, 11, 01);
        Ticket ticket1 = cinema.buy(account, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, 1, 1, date);
        throw new IllegalArgumentException();
    }
}