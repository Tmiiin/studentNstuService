package ru.nstu.students.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.nstu.students.model.StudentEntity;
import ru.nstu.students.service.StudentService;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notes")
@Tag(name = "notesService", description = "Сервис для управления заметками студентов")
public class NotesController {

    private final StudentService stringEntityService;

    public NotesController(StudentService stringEntityService) {
        this.stringEntityService = stringEntityService;
    }

    @Operation(summary = "Получение списка заметок")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "успешная операция")})
    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<StudentEntity> getEntities() {
        return stringEntityService.findAll();
    }


    @Operation(summary = "Создание заметки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "успешная операция"),
            @ApiResponse(responseCode = "400", description = "неверный формат запроса"),
            @ApiResponse(responseCode = "500", description = "внутренняя ошибка сервера")
    })
    @PostMapping(produces = "application/json")
    @ResponseBody
    public StudentEntity createEntity(@Validated @NotBlank @RequestBody String string) {
        return stringEntityService.createStringEntity(string);
    }

    @Operation(summary = "Удаление заметки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "успешная операция"),
            @ApiResponse(responseCode = "400", description = "неверный формат запроса"),
            @ApiResponse(responseCode = "404", description = "строка не найдена", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "внутренняя ошибка сервера")
    })
    @DeleteMapping(value = "/{entityId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTemplate(@Validated @NotBlank @PathVariable UUID entityId) {
        stringEntityService.deleteStringEntity(entityId);
    }
}
