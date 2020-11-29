package ru.nstu.students.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.nstu.students.exception.StringNotFoundException;
import ru.nstu.students.model.scheduleModels.ScheduleOnDay;
import ru.nstu.students.service.ScheduleServiceImpl;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/schedule")
@Tag(name = "scheduleService", description = "Получение расписания студента")
public class ScheduleController {

    private final ScheduleServiceImpl scheduleService;

    public ScheduleController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Operation(summary = "Получение расписания по названию группы")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "успешная операция")})
    @GetMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<ArrayList<ScheduleOnDay>> getSchedule(@RequestParam String groupName) {
        try {
            return ResponseEntity.ok((ArrayList<ScheduleOnDay>) scheduleService.getSchedule(groupName));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, e.getMessage());
        } catch (StringNotFoundException i) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, i.getMessage());
        }
    }

    @Operation(summary = "Получение номера текущей недели")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "успешная операция")})
    @GetMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> getWeekNumber() {
        try {
            return ResponseEntity.ok(scheduleService.getNumberOfWeek());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "(( грусна");
        }
    }

}
