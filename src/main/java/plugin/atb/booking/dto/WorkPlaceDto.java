package plugin.atb.booking.dto;

public class WorkPlaceDto {
    private int idWorkPlace;
    private boolean type;
    private int numSeats;

    public WorkPlaceDto () {

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
}
