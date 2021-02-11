package itlu.itlu.service;


import itlu.itlu.dto.WorkerDto;
import itlu.itlu.model.Worker;
import itlu.itlu.repository.WorkerRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkerService {

    private  final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public void saveWorker(WorkerDto workerDto){

        Worker newWorker = new Worker();
        newWorker.setName(workerDto.getName());
        newWorker.setSurname(workerDto.getSurname());
        newWorker.setEmail(workerDto.getEmail());
        newWorker.setCity(workerDto.getCity());
        newWorker.setPhone_number(workerDto.getPhone_number());
        newWorker.setDate_of_employment(workerDto.getDate_of_employment());

        workerRepository.save(newWorker);
    }

    public void deleteWorker(Long id){
        workerRepository.deleteById(id);
    }

    public List<Worker> findAllWorkersToTeam(Long teamId) {
        return workerRepository.findAllWorkersByTeamId(teamId);
    }

    @Transactional
    public void deleteWorkerFromTeam(Long id) {
         workerRepository.deleteWorkerFromTeam(id);
    }
}
