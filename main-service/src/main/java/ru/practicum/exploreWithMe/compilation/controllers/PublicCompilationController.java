package ru.practicum.exploreWithMe.compilation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.exploreWithMe.compilation.service.CompilationService;

@RestController
@RequestMapping(path = "/compilations")
@RequiredArgsConstructor
public class PublicCompilationController {
    private final CompilationService service;

    @GetMapping
    public ResponseEntity<Object> getCompilations(@RequestParam(required = false) Boolean pinned,
                                                  @RequestParam(defaultValue = "0") int from,
                                                  @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(200).body(service.getCompilations(pinned, from, size));
    }

    @GetMapping("/{compId}")
    public ResponseEntity<Object> getCompilationById(@PathVariable long compId) {
        return ResponseEntity.status(200).body(service.getCompilationById(compId));
    }
}
