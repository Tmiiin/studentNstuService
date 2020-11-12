package ru.nstu.students.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.nstu.students.service.ScheduleServiceImpl;

import java.io.IOException;

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
    public ResponseEntity<String> getSchedule(@RequestParam String groupName) {
        try {
            scheduleService.getSchedule(groupName);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.FOUND, "(( грусна");
        }
        return ResponseEntity.ok("jjiji");
    }


}
