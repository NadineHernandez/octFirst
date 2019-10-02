package com.trilogy.rsvp.dao;

import com.trilogy.rsvp.model.Rsvp;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class RsvpDaoTest {

    @Autowired
    RsvpDao dao;

    @Before
    public void setUp() throws Exception {
        List<Rsvp> rsvpDaos = dao.getAllRsvps();

        rsvpDaos.stream()
                .forEach(rsvp -> dao.deleteRsvp(rsvp.getId()));
    }

    @Test
    public void addRsvp() {
        Rsvp rsvp = new Rsvp("John Doe", 2);
        rsvp = dao.addRsvp(rsvp);

        assertEquals(rsvp, dao.getRsvp(rsvp.getId()));
    }

    @Test
    public void getRsvp() {
        Rsvp rsvp = new Rsvp("John Doe", 2);
        rsvp = dao.addRsvp(rsvp);

        assertEquals(rsvp, dao.getRsvp(rsvp.getId()));
    }

    @Test
    public void getAllRsvps() {
        Rsvp rsvp = new Rsvp("John Doe", 2);
        dao.addRsvp(rsvp);
        Rsvp rsvp1 = new Rsvp("Jane Doe", 2);
        dao.addRsvp(rsvp1);

        assertEquals(2, dao.getAllRsvps().size());
    }

    @Test
    public void updateRsvp() {
        Rsvp rsvp = new Rsvp("John Doe", 2);
        rsvp = dao.addRsvp(rsvp);
        rsvp.setGuestName("Jane Doe");
        rsvp.setTotalAttending(3);
        dao.updateRsvp(rsvp);

        assertEquals(rsvp, dao.getRsvp(rsvp.getId()));
    }

    @Test
    public void deleteRsvp() {
        Rsvp rsvp = new Rsvp("John Doe", 2);
        rsvp = dao.addRsvp(rsvp);
        dao.deleteRsvp(rsvp.getId());

        assertNull(dao.getRsvp(rsvp.getId()));
    }
}