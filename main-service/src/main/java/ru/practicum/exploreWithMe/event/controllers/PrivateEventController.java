package ru.practicum.exploreWithMe.event.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.exploreWithMe.event.dto.EventRequestStatusUpdateRequest;
import ru.practicum.exploreWithMe.event.dto.NewEventDto;
import ru.practicum.exploreWithMe.event.dto.UpdateEventUserRequest;
import ru.practicum.exploreWithMe.event.service.EventService;

@RestController
@RequestMapping(path = "/users/{userId}/events")
@RequiredArgsConstructor
public class PrivateEventController {
    private final EventService service;

    @GetMapping
    public ResponseEntity<Object> getUserEvents(@PathVariable long userId,
                                                @RequestParam(defaultValue = "0") int from,
                                                @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(200).body(service.getUserEventsPrivate(userId, from, size));
    }

    @PostMapping
    public ResponseEntity<Object> addNewEvent(@PathVariable long userId,
                                              @Valid @RequestBody NewEventDto newEventDto) {
        return ResponseEntity.status(201).body(service.addNewEventPrivate(userId, newEventDto));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Object> getEventById(@PathVariable long userId,
                                               @PathVariable long eventId) {
        return ResponseEntity.status(200).body(service.getEventByIdPrivate(userId, eventId));
    }

    @PatchMapping("/{eventId}")
    public ResponseEntity<Object> updateEvent(@PathVariable long userId,
                                              @PathVariable long eventId,
                                              @Valid @RequestBody UpdateEventUserRequest updateEventUserRequest) {
        return ResponseEntity.status(200).body(service.updateEventPrivate(userId, eventId, updateEventUserRequest));
    }

    @GetMapping("/{eventId}/requests")
    public ResponseEntity<Object> getEventRequests(@PathVariable long userId,
                                                   @PathVariable long eventId) {
        return ResponseEntity.status(200).body(service.getEventRequestsPrivate(userId, eventId));
    }

    @PatchMapping("/{eventId}/requests")
    public ResponseEntity<Object> updateRequestsStatus(@PathVariable long userId,
                                                       @PathVariable long eventId,
                                                       @Valid @RequestBody EventRequestStatusUpdateRequest request) {
        return ResponseEntity.status(200).body(service.updateRequestsStatusPrivate(userId, eventId, request));
    }
}
