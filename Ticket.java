public class Ticket {
        private String type;
        private int price;
    
        public Ticket(String type, int price) {
            this.type = type;
            this.price = price;
        }
    
        public int getPrice() {
            return price;
        }
    
        @Override
        public String toString() {
            return type + " Ticket: " + price;
        }
    }

