import dao.FilmDAO;
import dao.JdbcUtils;
import models.Film;

public class Main {
    public static void main(String[] args) {

        //Probando el filtrar pelicula por año
        new FilmDAO(JdbcUtils.getCon()).findByYearRange(1994,2015).forEach(System.out::println);

        //Probando el contar el total de peliculas
        int total = new FilmDAO(JdbcUtils.getCon()).totalNumber();
        System.out.println("Total number of films: "+total);

        //Probando el añadir pelicula
        var pelicula = new Film(7,"Holilla",2019,"Comedia");
        new FilmDAO(JdbcUtils.getCon()).save(pelicula);
    }
}
