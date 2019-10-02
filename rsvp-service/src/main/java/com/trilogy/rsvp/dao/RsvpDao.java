package com.trilogy.rsvp.dao;

import com.trilogy.rsvp.model.Rsvp;

import java.util.List;

public interface RsvpDao {
    public Rsvp addRsvp(Rsvp rsvp);
    public Rsvp getRsvp(int id);
    public List<Rsvp> getAllRsvps();
    public void updateRsvp(Rsvp rsvp);
    public void deleteRsvp(int id);
}
