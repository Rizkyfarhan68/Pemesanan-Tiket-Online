public class Route {
        private String from;
        private String to;
        private int basePrice;
    
        public Route(String from, String to, int basePrice) {
            this.from = from;
            this.to = to;
            this.basePrice = basePrice;
        }
    
        public int getBasePrice() {
            return basePrice;
        }
    
        @Override
        public String toString() {
            return from + " - " + to;
        }

        public Object getDeparture() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getDeparture'");
        }

        public Object getArrival() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getArrival'");
        }
    
}
