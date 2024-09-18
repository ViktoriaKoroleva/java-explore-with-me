package ru.practicum.exploreWithMe.event.service;

import jakarta.servlet.http.HttpServletRequest;
import ru.practicum.exploreWithMe.event.dto.*;
import ru.practicum.exploreWithMe.request.dto.RequestDto;

import java.util.List;

public interface EventService {
    List<EventFullDto> searchEventAdmin(List<Long> users,
                                        List<String> states,
                                        List<Long> categories,
                                        String rangeStart,
                                        String rangeEnd,
                                        int from,
                                        int size);

    EventFullDto updateEventAdmin(long eventId, UpdateEventAdminRequest updateEventAdminRequest);

    List<EventShortDto> getUserEventsPrivate(long userId, int from, int size);

    EventFullDto addNewEventPrivate(long userId, NewEventDto newEventDto);

    EventFullDto getEventByIdPrivate(long userId, long eventId);

    EventFullDto updateEventPrivate(long userId, long eventId, UpdateEventUserRequest updateEventUserRequest);

    List<RequestDto> getEventRequestsPrivate(long userId, long eventId);

    EventRequestStatusUpdateResult updateRequestsStatusPrivate(long userId, long eventId, EventRequestStatusUpdateRequest request);

    List<EventShortDto> searchEventsPublic(String text,
                                           List<Long> categories,
                                           Boolean paid,
                                           String rangeStart,
                                           String rangeEnd,
                                           boolean onlyAvailable,
                                           String sort,
                                           int from,
                                           int size,
                                           HttpServletRequest request);

    EventFullDto getEventByIdPublic(long id, HttpServletRequest request);
}
