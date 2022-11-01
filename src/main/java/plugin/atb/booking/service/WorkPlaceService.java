package plugin.atb.booking.service;

import plugin.atb.booking.dto.WorkPlaceDto;
import plugin.atb.booking.model.WorkPlace;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkPlaceService {

    public static List<WorkPlace> workPlace = new ArrayList<>();

    public void createWorkPlace(WorkPlaceDto workPlaceDto) {
        int idWorkPlace = workPlaceDto.getIdWorkPlace();
        boolean type = workPlaceDto.isType();
        int numSeats = workPlaceDto.getNumSeats();
        try {
            boolean checkLogin = workPlace.stream()
                    .anyMatch(e -> e.getIdWorkPlace() == idWorkPlace);
            if (checkLogin) {
                throw new Exception("Рабочее место с id " + idWorkPlace + " уже существует");
            }
            WorkPlace place = new WorkPlace(idWorkPlace, type, numSeats);
            workPlace.add(place);
            System.out.println("Рабочее место с id " + idWorkPlace + " было создано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public WorkPlace readWorkPlace(int idWorkPlace) {
        try {
            Optional<WorkPlace> place = workPlace.stream()
                    .filter(e -> e.getIdWorkPlace() == idWorkPlace)
                    .findFirst();
            if (place.isPresent()) {
                System.out.println("Рабочее место с номером " + idWorkPlace + " было получено");
                return place.get();
            } else {
                throw new Exception("Рабочее место с номером " + idWorkPlace + " не найдено");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public void updateWorkPlace(WorkPlaceDto workPlaceDto) {
        int idWorkPlace = workPlaceDto.getIdWorkPlace();
        boolean type = workPlaceDto.isType();
        int numSeats = workPlaceDto.getNumSeats();
        try {
            Optional<WorkPlace> place = workPlace.stream()
                    .filter(e -> e.getIdWorkPlace() == idWorkPlace)
                    .findFirst();
            if (place.isEmpty()) {
                throw new Exception("Рабочее место с номером " + idWorkPlace + " не найдено для изменения");
            }
            //int indexPlace = workPlace.indexOf(place.get());
            place.get().setType(type);
            place.get().setNumSeats(numSeats);
            //workPlace.set(indexPlace, place.get());
            System.out.println("Рабочее место с номером " + idWorkPlace + " было отреактировано");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteWorkPlace(int idWorkPlace) {
        try {
            if (workPlace.removeIf(e -> e.getIdWorkPlace() == idWorkPlace)) {
                System.out.println("Рабочее место с номером " + idWorkPlace + " было удалено");
            } else {
                throw new Exception("Рабочее место с номером " + idWorkPlace + " не найдено для удаления");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
