public class TicketBooking {
    private Route route;
    private Ticket ticket;

    public TicketBooking(Route route, Ticket ticket) {
        this.route = route;
        this.ticket = ticket;
    }

    public int calculateTotal() {
        int total = route.getBasePrice() + ticket.getPrice();
        if (ticket.toString().contains("Eksekutif") || ticket.toString().contains("Luxury")) {
            total -= total * 0.15; // Diskon 15%
        }
        return total;
    }
}

