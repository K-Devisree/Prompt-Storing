package com.build.prompt_storing.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.build.prompt_storing.model.Prompt;
import com.build.prompt_storing.util.DBUtil;

public class PromptDaoJdbc implements PromptDao {

    @Override
    public int create(Prompt p) {
        String sql = "INSERT INTO prompts(title, content, category) VALUES (?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getTitle());
            ps.setString(2, p.getContent());
            ps.setString(3, p.getCategory());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return -1;

        } catch (SQLException e) {
            throw new RuntimeException("Create failed", e);
        }
    }

    @Override
    public Optional<Prompt> findById(int id) {
        String sql = "SELECT id, title, content, category, created_at FROM prompts WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
            }
            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Find by id failed", e);
        }
    }

    @Override
    public List<Prompt> findAll() {
        String sql = "SELECT id, title, content, category, created_at FROM prompts ORDER BY id DESC";
        List<Prompt> list = new ArrayList<>();

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) list.add(mapRow(rs));
            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Find all failed", e);
        }
    }

    @Override
    public boolean update(int id, Prompt p) {
        String sql = "UPDATE prompts SET title=?, content=?, category=? WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getTitle());
            ps.setString(2, p.getContent());
            ps.setString(3, p.getCategory());
            ps.setInt(4, id);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM prompts WHERE id=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }

    //This is the "map" method (renamed to avoid confusion)
    private Prompt mapRow(ResultSet rs) throws SQLException {
        return new Prompt(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("category"),
                rs.getTimestamp("created_at")
        );
    }
}