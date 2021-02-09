package itlu.itlu.service;


import itlu.itlu.dto.WorkerDto;
import itlu.itlu.model.Worker;
import itlu.itlu.repository.WorkerRepository;
import org.springframework.stereotype.Service;

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


    }

}
