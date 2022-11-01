package plugin.atb.booking.model;

public class WorkPlace {
    private int idWorkPlace;
    private boolean type;
    private int numSeats;

    public WorkPlace () {

    }

    public WorkPlace(int idWorkPlace, boolean type, int numSeats) {
        this.idWorkPlace = idWorkPlace;
        this.type = type;
        this.numSeats = numSeats;
    }

    public int getIdWorkPlace() {
        return idWorkPlace;
    }

    public void setIdWorkPlace(int idWorkPlace) {
        this.idWorkPlace = idWorkPlace;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    @Override
    public String toString() {
        return "WorkPlace{" +
                "idWorkPlace=" + idWorkPlace +
                ", type=" + type +
                ", numSeats=" + numSeats +
                '}';
    }
}
