package ru.practicum.exploreWithMe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.exploreWithMe.dto.EndpointHitDto;
import ru.practicum.exploreWithMe.dto.ViewStatsDto;
import ru.practicum.exploreWithMe.error.exceptions.BadRequestException;
import ru.practicum.exploreWithMe.mapper.Mapper;
import ru.practicum.exploreWithMe.model.EndpointHit;
import ru.practicum.exploreWithMe.repository.StatsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository repository;

    @Override
    public EndpointHitDto addHit(EndpointHitDto endpointHitDto) {
        EndpointHit savedHit = repository.save(Mapper.convertToEndpointHit(endpointHitDto));
        return Mapper.convertToEndpointHitDto(savedHit);
    }

    @Override
    public List<ViewStatsDto> getStats(LocalDateTime start, LocalDateTime end, List<String> uris, boolean unique) {
        if (start.isAfter(end)) {
            throw new BadRequestException("Дата начала не может быть позже даты окончания.");
        }
        if (unique) {
            if (Objects.isNull(uris) || uris.isEmpty()) {
                return Mapper.convertToViewStatsDtoList(repository.getUniqueIpStatisticsForPeriod(start, end));
            }
            return Mapper.convertToViewStatsDtoList(repository.getUniqueIpStatisticsForPeriodAndUris(start, end, uris));
        }
        if (Objects.isNull(uris) || uris.isEmpty()) {
            return Mapper.convertToViewStatsDtoList(repository.getStatisticsForPeriod(start, end));
        }
        return Mapper.convertToViewStatsDtoList(repository.getStatisticsForPeriodAndUris(start, end, uris));
    }


}
