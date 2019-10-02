package com.trilogy.rsvp.dto;

import com.trilogy.rsvp.dao.RsvpDao;
import com.trilogy.rsvp.model.Rsvp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RsvpDaoJdbcTemplateImpl implements RsvpDao {

    public static final String INSERT_RSVP =
            "INSERT INTO rsvp (guest_name, total_attending) values (?, ?)";

    public static final String SELECT_RSVP =
            "SELECT * FROM rsp WHERE rsvp_id = ?";

    public static final String SELECT_ALL_RSVPS =
            "SELECT * FROM rsvp";

    public static final String UPDATE_RSVP =
            "UPDATE rsvp SET guest_name = ?, total_attending = ? where rsvp_id = ?";

    public static final String DELETE_RSVP =
            "DELETE FROM rsvp WHERE rsvp_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public RsvpDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Rsvp addRsvp(Rsvp rsvp) {
        jdbcTemplate.update(INSERT_RSVP, rsvp.getGuestName(), rsvp.getTotalAttending());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        rsvp.setId(id);
        return rsvp;
    }

    @Override
    public Rsvp getRsvp(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_RSVP, this::mapToRowRsvp, id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Rsvp> getAllRsvps() {
        return jdbcTemplate.query(SELECT_ALL_RSVPS, this::mapToRowRsvp);
    }

    @Override
    public void updateRsvp(Rsvp rsvp) {
        jdbcTemplate.update(UPDATE_RSVP, rsvp.getGuestName(), rsvp.getTotalAttending(), rsvp.getId());
    }

    @Override
    public void deleteRsvp(int id) {
        jdbcTemplate.update(DELETE_RSVP, id);
    }

    private Rsvp mapToRowRsvp(ResultSet rs, int rowNum) throws SQLException {
        Rsvp rsvp = new Rsvp();
        rsvp.setId(rs.getInt("rsvp_id"));
        rsvp.setGuestName(rs.getString("guest_name"));
        rsvp.setTotalAttending(rs.getInt("total_attending"));
        return rsvp;
    }
}
