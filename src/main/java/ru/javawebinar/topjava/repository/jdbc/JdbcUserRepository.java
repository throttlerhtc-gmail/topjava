package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Repository
@Transactional
public class JdbcUserRepository extends AbstractJdbcValidator implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    private Set<ConstraintViolation<User>> violations;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        violations = validator.validate(user);
        if (!violations.isEmpty()) {
            System.out.println(violations);
            throw new ConstraintViolationException(violations);
        }

        List<Role> roles = new ArrayList<>();
        roles.addAll(user.getRoles());
        int[] butchUpdates;
        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
        } else {
            int updateClauses = namedParameterJdbcTemplate.update("UPDATE users SET name=:name, email=:email, password=:password, " +
                    "registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay " +
                    "WHERE id=:id", parameterSource);
            Set<Role> persistedRolesSet = EnumSet.copyOf(jdbcTemplate.queryForList("SELECT role FROM user_roles WHERE user_id=?", Role.class, user.getId()));
            if (persistedRolesSet.equals(user.getRoles()) && updateClauses == 0) {
                return null;
            }
            if (persistedRolesSet.equals(user.getRoles()) && updateClauses != 0) {
                return user;
            }
            jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", user.getId());
        }
        butchUpdates = jdbcTemplate.batchUpdate("INSERT INTO user_roles (user_id, role)  " +
                        "VALUES (?, ?)"
                , new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, user.getId());
                        ps.setString(2, roles.get(i).name());
                    }

                    @Override
                    public int getBatchSize() {
                        return roles.size();
                    }
                });

        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        if (users.isEmpty()) return null;
        else return DataAccessUtils.singleResult(List.of(acceptJdbcUsersExistingRoles(users).get(0)));
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        return DataAccessUtils.singleResult(List.of(acceptJdbcUsersExistingRoles(users).get(0)));
    }

    @Override
    public List<User> getAll() {
        List<User> usersList = jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);
        return acceptJdbcUsersExistingRoles(usersList);
//        for (User u : usersList) {
//            u.setRoles(EnumSet.noneOf(Role.class));
//            jdbcTemplate.query("SELECT users.id, users.name, users.email, users.password, users.registered" +
//                    ", users.enabled, users.calories_per_day, user_roles.role FROM users, user_roles WHERE users.id=user_roles.user_id AND id=?", new RowMapper<User>() {
//                @Override
//                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                    u.setId(Integer.parseInt(rs.getString("id")));
//                    u.setName(rs.getString("name"));
//                    u.setEmail(rs.getString("email"));
//                    u.setPassword(rs.getString("password"));
//                    u.setRegistered(Timestamp.valueOf(rs.getString("registered")));
//                    u.setEnabled("t".equals(rs.getString("enabled")));
//                    u.setCaloriesPerDay(Integer.parseInt(rs.getString("calories_per_day")));
//                    if (rs.getString("role").toLowerCase().contains("user")) {
//                        u.getRoles().add(Role.USER);
//                    }
//                    if (rs.getString("role").toLowerCase().contains("admin")) {
//                        u.getRoles().add(Role.ADMIN);
//                    }
//                    if (rs.getString("role").toLowerCase().contains("super")) {
//                        u.getRoles().add(Role.SUPER);
//                    }
//                    return u;
//                }
//            }, u.getId());
//        }
//        return jdbcTemplate.query("SELECT users.id, users.name, users.email, users.password, users.registered, users.enabled" +
//                ", users.calories_per_day, user_roles.role FROM users, user_roles WHERE id=user_id ORDER BY name, email", new RowMapper<User>() {
//            @Override
//            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//                User user = new User();
//                user.setRoles(EnumSet.of(Role.USER));
//                user.setId(Integer.parseInt(rs.getString("id")));
//                user.setName(rs.getString("name"));
//                user.setEmail(rs.getString("email"));
//                user.setPassword(rs.getString("password"));
//                user.setRegistered(Timestamp.valueOf(rs.getString("registered")));
//                user.setEnabled(Boolean.parseBoolean(rs.getString("enabled")));
//                user.setCaloriesPerDay(Integer.parseInt(rs.getString("calories_per_day")));
//                if (rs.getString("role").toLowerCase().contains("user")) {
//                    user.getRoles().add(Role.USER);
//                }
//                if (rs.getString("role").toLowerCase().contains("admin")) {
//                    user.getRoles().add(Role.ADMIN);
//                }
//                return user;
//            }
//        });
//        return usersList;
    }

    public List<User> acceptJdbcUsersExistingRoles(List<User> usersList) {
        List<Map<String, Object>> rolesList = jdbcTemplate.queryForList("SELECT * FROM user_roles");
        for (User u : usersList) {
            u.setRoles(EnumSet.noneOf(Role.class));
            for (Map<String, Object> pair : rolesList) {
                int id = 0;
                if (pair.get("user_id") instanceof Integer) {
                    id = (Integer) pair.get("user_id");
                }
                Role r = Role.valueOf((String) pair.get("role"));
                if (id == u.getId()) {
                    u.getRoles().add(r);
                }
            }
        }
        return usersList;
    }
}
