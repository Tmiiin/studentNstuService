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
import ru.nstu.students.model.serverResponses.ServerResponse;
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

    @Operation(summary = "Получение расписания")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "успешная операция"),
            @ApiResponse(responseCode = "502", description = "ошибка при обработке веб-страницы")})
    @GetMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<ServerResponse> getSchedule(@RequestParam String groupName) {
        try {
            if (groupName.length() < 3)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Неверное название группы");
            return ResponseEntity.ok(new ServerResponse(HttpStatus.OK, scheduleService.getSchedule(groupName)));
        } catch (IOException | StringNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }

    @Operation(summary = "Получение номера текущей недели")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "успешная операция"),
            @ApiResponse(responseCode = "502", description = "ошибка при обработке веб-страницы")})
    @GetMapping(produces = "application/json", value = "/getWeek")
    @ResponseBody
    public ResponseEntity<ServerResponse> getWeekNumber() {
        try {
            return ResponseEntity.ok(new ServerResponse(HttpStatus.OK, scheduleService.getNumberOfWeek()));
        } catch (IOException | StringNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }

    @Operation(summary = "Получение названий групп",
            description = "Возвращает список групп, для которых доступно получение расписания")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "успешная операция"),
            @ApiResponse(responseCode = "502", description = "ошибка при обработке веб-страницы")})
    @GetMapping(produces = "application/json",  value = "/getGroups")
    @ResponseBody
    public ResponseEntity<ServerResponse> getGroupList() {
        try {
            return ResponseEntity.ok(new ServerResponse(HttpStatus.OK,
                    "Not implemented yet"/*scheduleService.getGroupNames()*/));
        } catch (/*IOException*/Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, e.getMessage());
        }
    }

}
