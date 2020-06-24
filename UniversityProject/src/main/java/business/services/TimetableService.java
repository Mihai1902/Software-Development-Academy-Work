package business.services;

import model.dto.Student;
import model.dto.Timetable;
import model.service.GenericService;

import java.util.List;

public class TimetableService {
    GenericService<Timetable> timetableGeneric = new GenericService<>();

    public void addTimetable(Timetable timetable){
        timetableGeneric.add(timetable);
    }

    public void updateTimetable(Timetable timetable){
        timetableGeneric.update(timetable);
    }

    public void deleteTimetable(Timetable timetable){
        timetableGeneric.delete(timetable);
    }

    public Timetable findTimetable(Timetable timetable, int id){
        return timetableGeneric.findById(timetable, id);
    }

    public List<Timetable> getTimetables(Timetable timetable){
        return timetableGeneric.getAll(timetable);
    }
}