/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CineWorldCinemas.logic;

import CineWorldCinemas.data.AuditoriumDAO;
import CineWorldCinemas.data.MovieDAO;
import CineWorldCinemas.data.ScreeningDAO;
import CineWorldCinemas.data.SeatDAO;
import CineWorldCinemas.data.SeatReservedDAO;
import CineWorldCinemas.data.TicketDAO;
import CineWorldCinemas.data.UserDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felig
 */
public class Service {

    private static Service uniqueInstance;

    public static Service instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Service();
        }
        return uniqueInstance;
    }

    private AuditoriumDAO auditoriumDAO;
    private MovieDAO movieDAO;
    private ScreeningDAO screeningDAO;
    private SeatDAO seatDAO;
    private SeatReservedDAO seatReservedDAO;
    private TicketDAO ticketDAO;
    private UserDAO userDAO;

    public Service() {

        this.auditoriumDAO = new AuditoriumDAO();
        this.movieDAO = new MovieDAO();
        this.screeningDAO = new ScreeningDAO();
        this.seatDAO = new SeatDAO();
        this.seatReservedDAO = new SeatReservedDAO();
        this.ticketDAO = new TicketDAO();
        this.userDAO = new UserDAO();
    }

    // <editor-fold defaultstate="collapsed" desc="Auditorium C-R-U-D methods. Click on the + sign on the left to edit the code.">
    public Auditorium findAuditoriumById(int id) {
        return auditoriumDAO.findById(id);
    }

    public List<Auditorium> findAllAuditoriums() {
        return auditoriumDAO.findAll();
    }

    public Auditorium saveAuditorium(Auditorium auditorium) {
        return auditoriumDAO.save(auditorium);
    }

    public Auditorium updateAuditorium(Auditorium auditorium) {
        return auditoriumDAO.update(auditorium);
    }

    public void deleteAuditoriumById(int id) throws Exception {
        auditoriumDAO.deleteById(id);
    }

    public void deleteAuditorium(Auditorium auditorium) throws Exception {
        auditoriumDAO.delete(auditorium);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Movie C-R-U-D methods. Click on the + sign on the left to edit the code.">
    public Movie findMovieById(int id) {
        return movieDAO.findById(id);
    }

    public List<Movie> findMoviesByName(String name) {
        List<Movie> movies = new ArrayList<>();
        this.findAllMovies().stream().filter(movie -> (movie.getTitle().toUpperCase().
                contains(name.toUpperCase()))).forEachOrdered(movie -> {
            movies.add(movie);
        });
        return movies;
    }

    public List<Movie> findAllMovies() {
        return movieDAO.findAll();
    }

    public Movie saveMovie(Movie movie) {
        return movieDAO.save(movie);
    }

    public Movie updateMovie(Movie movie) {
        return movieDAO.update(movie);
    }

    public void deleteMovieById(int id) throws Exception {
        movieDAO.deleteById(id);
    }

    public void deleteMovie(Movie movie) throws Exception {
        movieDAO.delete(movie);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Screening C-R-U-D methods. Click on the + sign on the left to edit the code.">
    public Screening findScreeningById(int id) {
        return screeningDAO.findById(id);
    }

    public List<Screening> findAllScreenings() {
        return screeningDAO.findAll();
    }

    public Screening saveScreening(Screening screening) {
        return screeningDAO.save(screening);
    }

    public Screening updateScreening(Screening screening) {
        return screeningDAO.update(screening);
    }

    public void deleteScreeningById(int id) throws Exception {
        screeningDAO.deleteById(id);
    }

    public void deleteScreening(Screening screening) throws Exception {
        screeningDAO.delete(screening);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Seat C-R-U-D methods. Click on the + sign on the left to edit the code.">
    public Seat findSeatById(int id) {
        return seatDAO.findById(id);
    }

    public List<Seat> findAllSeats() {
        return seatDAO.findAll();
    }

    public Seat saveSeat(Seat seat) {
        return seatDAO.save(seat);
    }

    public Seat updateUser(Seat seat) {
        return seatDAO.update(seat);
    }

    public void deleteSeatById(int id) throws Exception {
        seatDAO.deleteById(id);
    }

    public void deleteSeat(Seat seat) throws Exception {
        seatDAO.delete(seat);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="SeatReserved C-R-U-D methods. Click on the + sign on the left to edit the code.">
    public SeatReserved findSeatReservedById(int id) {
        return seatReservedDAO.findById(id);
    }

    public List<SeatReserved> findAllSeatsReserved() {
        return seatReservedDAO.findAll();
    }

    public SeatReserved saveSeatReserved(SeatReserved seatReserved) {
        return seatReservedDAO.save(seatReserved);
    }

    public SeatReserved updateSeatReserved(SeatReserved seatReserved) {
        return seatReservedDAO.update(seatReserved);
    }

    public void deleteSeatReservedById(int id) throws Exception {
        seatReservedDAO.deleteById(id);
    }

    public void deleteSeatReserved(SeatReserved seatReserved) throws Exception {
        seatReservedDAO.delete(seatReserved);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Ticket C-R-U-D methods. Click on the + sign on the left to edit the code.">
    public Ticket findTicketById(int id) {
        return ticketDAO.findById(id);
    }

    public List<Ticket> findAllTickets() {
        return ticketDAO.findAll();
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketDAO.save(ticket);
    }

    public Ticket updateTicket(Ticket ticket) {
        return ticketDAO.update(ticket);
    }

    public void deleteTicketById(int id) throws Exception {
        ticketDAO.deleteById(id);
    }

    public void deleteTicket(Ticket ticket) throws Exception {
        ticketDAO.delete(ticket);
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="User C-R-U-D methods. Click on the + sign on the left to edit the code.">
    public User findUserById(String id) {
        return userDAO.findById(id);
    }

    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    public User saveUser(User user) throws Exception {
        return userDAO.save(user);
    }

    public User updateUser(User user, String id) {
        return userDAO.update(user);
    }

    public void deleteUserById(String id) throws Exception {
        userDAO.deleteById(id);
    }

    public void deleteUser(User user) throws Exception {
        userDAO.delete(user);
    }
    // </editor-fold>
}
