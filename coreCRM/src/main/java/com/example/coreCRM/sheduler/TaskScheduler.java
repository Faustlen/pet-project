package com.example.coreCRM.sheduler;

import com.example.coreCRM.entity.BuildingEntity;
import com.example.coreCRM.service.BuildingService;
import com.example.coreCRM.service.TaskService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskScheduler {

    private final TaskService taskService;
    private final BuildingService buildingService;

    @Scheduled(cron = "0 0 8 * * ?")
    @SchedulerLock(name = "createContactTasks", lockAtLeastFor = "5m", lockAtMostFor = "10m")
    public void createTasksForOwners() {
        List<BuildingEntity> buildings = buildingService.findBuildingsWithoutRecentContact(30);
        for (BuildingEntity building : buildings) {
            taskService.createAutoContactTask(building);
        }
    }
}