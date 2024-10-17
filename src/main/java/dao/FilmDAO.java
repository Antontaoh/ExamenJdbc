package dao;

import models.Film;

import java.sql.*;
import java.util.List;

public class FilmDAO implements DAO<Film>{
    Connection con;

    public FilmDAO(Connection con) {
        this.con = con;
    }

    @Override
    public List<Film> findAll() {
        return List.of();
    }

    @Override
    public Film findById(Integer id) {
        Film output = null;
        try(PreparedStatement ps = con.prepareStatement("select * from pelicula where id=?")) {
            ps.setInt(1,id);
            var result = ps.executeQuery();
            if (result.next()) output = new Film(result.getInt(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    @Override
    public void save(Film film) {
        try(PreparedStatement ps=con.prepareStatement("insert into pelicula(id, titulo, año, genero) values (?,?,?,?)")) {
        ps.setInt(1,film.getId());
        ps.setString(2,film.getTitle());
        ps.setInt(3,film.getYear());
        ps.setString(4,film.getGenre());
        int result = ps.executeUpdate();

            if(result>0){
                ResultSet keys = ps.getGeneratedKeys();
                keys.next();
                Integer film_id = keys.getInt(1);
                film.setId(film_id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Film film) {

    }

    @Override
    public void delete(Film film) {

    }

    @Override
    public List<Film> findByYearRange(Integer yearA, Integer yearB) {
        try (PreparedStatement ps = con.prepareStatement("select * from pelicula where ? between ?")){
            Film output=null;
            List filmList = null;
            ps.setInt(1,yearA);
            ps.setInt(2,yearB+1);
            var result = ps.executeQuery();
            while (result.next()) {
            output = new Film(result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getString(4));
            filmList.add(output);
            }
            return filmList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer totalNumber() {
        int result=0;
        try(Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("select * from pelicula");
            while (rs.next()){
                result++;
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
