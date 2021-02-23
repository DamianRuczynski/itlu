package itlu.itlu.service;

import itlu.itlu.dto.CreateMeetsDto;
import itlu.itlu.dto.MeetsDto;
import itlu.itlu.model.Meets;
import itlu.itlu.repository.MeetsDtoRepository;
import itlu.itlu.repository.MeetsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MeetsService {

    private final MeetsRepository meetsRepository;
    private final MeetsDtoRepository meetsDtoRepository;

    public MeetsService(MeetsRepository meetsRepository, MeetsDtoRepository meetsDtoRepository) {
        this.meetsRepository = meetsRepository;
        this.meetsDtoRepository = meetsDtoRepository;
    }

    public List<MeetsDto> getAllMeets() {
        return meetsDtoRepository.getAllMeetsWithName();
    }

    public void deleteMeets(Long id) {
        meetsRepository.deleteById(id);
    }

    public void saveMeets(CreateMeetsDto createMeetsDto) {

        Meets newMeets = new Meets();
        newMeets.setDate_of_meet(LocalDate.parse(createMeetsDto.getDate_of_meet()));
        newMeets.setTime_of_meet(createMeetsDto.getTime_of_meet());
        newMeets.setMeet_purpose(createMeetsDto.getMeet_purpose());
        newMeets.setId_team(createMeetsDto.getId_team());
        newMeets.setId_customer(createMeetsDto.getId_customer());
        newMeets.setLocation(createMeetsDto.getLocation());
        newMeets.setCity(createMeetsDto.getCity());
        newMeets.setMeets_status(createMeetsDto.getMeets_status());

        meetsRepository.save(newMeets);
    }

    public boolean checkStatus(Long id) {
        return meetsRepository.findById(id).orElse(new Meets()).getMeets_status() >= 1;
    }

}
