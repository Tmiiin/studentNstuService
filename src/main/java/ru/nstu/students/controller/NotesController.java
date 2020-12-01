package ru.nstu.students.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.nstu.students.exception.StudentsServiceException;
import ru.nstu.students.model.NoteEntity;
import ru.nstu.students.model.StudentEntity;
import ru.nstu.students.model.serverResponses.ServerResponse;
import ru.nstu.students.service.NoteService;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notes")
@Tag(name = "notesService", description = "Сервис для управления заметками студентов")
public class NotesController {

    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Operation(summary = "Получение списка заметок")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "успешная операция")})
    @GetMapping(produces = "application/json")
    @ResponseBody
    public List<NoteEntity> getEntities() {
        return noteService.findAll();
    }


    @Operation(summary = "Создание заметки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "успешная операция"),
            @ApiResponse(responseCode = "400", description = "неверный формат запроса"),
            @ApiResponse(responseCode = "500", description = "внутренняя ошибка сервера")
    })
    @PostMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<ServerResponse> createEntity(@Validated @NotBlank @RequestBody String string) {
        try {
            return ResponseEntity.ok(new ServerResponse(HttpStatus.OK,
                    noteService.createNote(string)));
        }
        catch (StudentsServiceException e){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getLocalizedMessage());
        }
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
        noteService.deleteNote(entityId);
    }
}
